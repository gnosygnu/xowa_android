package gplx.xowa.xtns.wdatas.hwtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.core.*;
class Wdata_visitor__lbl_gatherer implements Wdata_claim_visitor {
	private Wdata_lbl_mgr lbl_mgr;
	public Wdata_visitor__lbl_gatherer(Wdata_lbl_mgr lbl_mgr) {this.lbl_mgr = lbl_mgr;}
	public void Visit_entity(Wdata_claim_itm_entity itm) {
		if (itm.Entity_tid_is_qid())
			lbl_mgr.Queue_if_missing__qid(itm.Entity_id());
		else
			lbl_mgr.Queue_if_missing__pid(itm.Entity_id());
	}
	public void Visit_time(Wdata_claim_itm_time itm) {
		byte[] ttl = Wdata_lbl_itm.Extract_ttl(itm.Calendar());
		itm.Calendar_ttl_(ttl);
		lbl_mgr.Queue_if_missing__ttl(ttl);
	}
	public void Visit_globecoordinate(Wdata_claim_itm_globecoordinate itm) {
		byte[] ttl = Wdata_lbl_itm.Extract_ttl(itm.Glb());
		itm.Glb_ttl_(ttl);
		lbl_mgr.Queue_if_missing__ttl(ttl);
	}
	public void Visit_str(Wdata_claim_itm_str itm) {}
	public void Visit_monolingualtext(Wdata_claim_itm_monolingualtext itm) {}
	public void Visit_quantity(Wdata_claim_itm_quantity itm) {}
	public void Visit_system(Wdata_claim_itm_system itm) {}
}
