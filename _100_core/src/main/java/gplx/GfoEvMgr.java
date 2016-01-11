package gplx;
import gplx.core.lists.*;
public class GfoEvMgr {
	@gplx.Internal protected void AddSub(GfoEvMgrOwner pub, String pubEvt, GfoEvObj sub, String subPrc) {
		GfoEvLnk lnk = new GfoEvLnk(pub, pubEvt, sub, subPrc);
		if (subsRegy == null) subsRegy = Ordered_hash_.New();
		AddInList(subsRegy, pubEvt, lnk);
		sub.EvMgr().AddPub(pubEvt, lnk);
	}
	@gplx.Internal protected void Lnk(GfoEvMgrOwner pub) {
		if (pub.EvMgr().lnks == null) pub.EvMgr().lnks = List_adp_.new_();
		pub.EvMgr().lnks.Add(this);
	}	List_adp lnks;
	void AddInList(Ordered_hash regy, String key, GfoEvLnk lnk) {
		GfoEvLnkList list = (GfoEvLnkList)regy.Get_by(key);
		if (list == null) {
			list = new GfoEvLnkList(key);
			regy.Add(key, list);
		}
		list.Add(lnk);
	}
	@gplx.Internal protected void AddPub(String pubEvt, GfoEvLnk lnk) {
		if (pubsRegy == null) pubsRegy = Ordered_hash_.New();
		AddInList(pubsRegy, pubEvt, lnk);
	}
	@gplx.Internal protected void Pub(GfsCtx ctx, String evt, GfoMsg m) {
		ctx.MsgSrc_(sender);
		GfoEvLnkList subs = subsRegy == null ? null : (GfoEvLnkList)subsRegy.Get_by(evt);
		if (subs != null) {
			for (int i = 0; i < subs.Count(); i++) {
				GfoEvLnk lnk = (GfoEvLnk)subs.Get_at(i);
				lnk.Sub().Invk(ctx, 0, lnk.SubPrc(), m);	// NOTE: itm.Key() needed for Subscribe_diff()
			}
		}
		if (lnks != null) {
			for (int i = 0; i < lnks.Count(); i++) {
				GfoEvMgr lnk = (GfoEvMgr)lnks.Get_at(i);
				lnk.Pub(ctx, evt, m);
			}
		}
	}
	@gplx.Internal protected void RlsSub(GfoEvMgrOwner eobj) {
		RlsRegyObj(pubsRegy, eobj, true);
		RlsRegyObj(subsRegy, eobj, false);
	}
	@gplx.Internal protected void RlsPub(GfoEvMgrOwner eobj) {
		RlsRegyObj(pubsRegy, eobj, true);
		RlsRegyObj(subsRegy, eobj, false);
	}
	@gplx.Internal protected void RlsRegyObj(Ordered_hash regy, GfoEvMgrOwner eobj, boolean pub) {
		if (regy == null) return;
		List_adp delList = List_adp_.new_();
		for (int i = 0; i < regy.Count(); i++) {
			GfoEvLnkList pubsList = (GfoEvLnkList)regy.Get_at(i);
			delList.Clear();
			for (int j = 0; j < pubsList.Count(); j++) {
				GfoEvLnk lnk = (GfoEvLnk)pubsList.Get_at(j);
				if (lnk.End(!pub) == eobj) delList.Add(lnk);
			}
			for (int j = 0; j < delList.Count(); j++) {
				GfoEvLnk del = (GfoEvLnk)delList.Get_at(j);
				del.End(pub).EvMgr().RlsLnk(!pub, pubsList.Key(), del.End(!pub));
				pubsList.Del(del);
			}
		}
	}
	@gplx.Internal protected void RlsLnk(boolean pubEnd, String key, GfoEvMgrOwner endObj) {
		Ordered_hash regy = pubEnd ? pubsRegy : subsRegy;
		GfoEvLnkList list = (GfoEvLnkList)regy.Get_by(key);
		List_adp delList = List_adp_.new_();
		for (int i = 0; i < list.Count(); i++) {
			GfoEvLnk lnk = (GfoEvLnk)list.Get_at(i);
			if (lnk.End(pubEnd) == endObj) delList.Add(lnk);
		}
		for (int i = 0; i < delList.Count(); i++) {
			GfoEvLnk lnk = (GfoEvLnk)delList.Get_at(i);
			list.Del(lnk);
		}
		delList.Clear();
	}

	Object sender; Ordered_hash subsRegy, pubsRegy;
	public static GfoEvMgr new_(Object sender) {
		GfoEvMgr rv = new GfoEvMgr();
		rv.sender = sender;
		return rv;
	}	GfoEvMgr() {}
}
class GfoEvLnkList {
	public String Key() {return key;} private String key;
	public int Count() {return list.Count();}
	public void Add(GfoEvLnk lnk) {list.Add(lnk);}
	public void Del(GfoEvLnk lnk) {list.Del(lnk);}
	public GfoEvLnk Get_at(int i) {return (GfoEvLnk)list.Get_at(i);}
	public GfoEvLnkList(String key) {this.key = key;}
	List_adp list = List_adp_.new_();
}
class GfoEvLnk {
	public GfoEvMgrOwner Pub() {return pub;} GfoEvMgrOwner pub;
	public String PubEvt() {return pubEvt;} private String pubEvt;
	public GfoEvObj Sub() {return sub;} GfoEvObj sub;
	public String SubPrc() {return subPrc;} private String subPrc;
	public GfoEvMgrOwner End(boolean pubEnd) {return pubEnd ? pub : sub;}
	public GfoEvLnk(GfoEvMgrOwner pub, String pubEvt, GfoEvObj sub, String subPrc) {this.pub = pub; this.pubEvt = pubEvt; this.sub = sub; this.subPrc = subPrc;}
}
class GfoEvItm {
	public String Key() {return key;} private String key;
	public GfoInvkAble InvkAble() {return invkAble;} GfoInvkAble invkAble;
	public static GfoEvItm new_(GfoInvkAble invkAble, String key) {
		GfoEvItm rv = new GfoEvItm();
		rv.invkAble = invkAble; rv.key = key;
		return rv;
	}
}
