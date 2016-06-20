package gplx.gfui.controls.tabs; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public class TabBoxMgr implements Gfo_evt_mgr_owner {
	public Gfo_evt_mgr Evt_mgr() {if (evt_mgr == null) evt_mgr = new Gfo_evt_mgr(this); return evt_mgr;} Gfo_evt_mgr evt_mgr;
	public int Count() {return itms.Count();}
	public TabPnlItm Get_by(String k) {return (TabPnlItm)itms.Get_by(k);}
	public TabPnlItm Get_at(int i) {return (TabPnlItm)itms.Get_at(i);}
	public TabPnlItm CurTab() {return curTab;} TabPnlItm curTab;
	public TabPnlItm Add(String key, String name) {
		TabPnlItm itm = TabPnlItm.new_(this, key).Name_(name).Idx_(itms.Count());
		itms.Add(itm.Key(), itm);
		return itm;
	}
	public void Del_at(int i) {
		boolean isCur = i == curTab.Idx(), isLast = i == itms.Count() - 1;
		TabPnlItm itm = this.Get_at(i);
		itms.Del(itm.Key());
		this.Reorder(i);
		if (isCur) {
			curTab = null; // must null out curTab since it has been deleted from itms; TODO_OLD: should raise Deleted event to delete btn,pnl,view; wait for rewrite of view
			if (isLast) i--; // last was dropped; select prev; otherwise re-select idx (effectively, whatever tab drops into slot)
			if (i >=0)
				this.Select(i);
		}
	}
	public void Select(int i) {Select((TabPnlItm)itms.Get_at(i));}
	@gplx.Internal protected void Move_to(int src, int trg) {itms.Move_to(src, trg);}
	@gplx.Internal protected void Reorder(int bgn) {
		for (int i = bgn; i < itms.Count(); i++) {
			TabPnlItm itm = (TabPnlItm)itms.Get_at(i);
			itm.Idx_(i);
		}
	}
	@gplx.Internal protected void Select(TabPnlItm newTab) {
		TabPnlItm oldTab = curTab;
		curTab = newTab;
		TabBoxEvt_tabSelect.Send(this, oldTab, newTab);
	}		
	Ordered_hash itms = Ordered_hash_.New();
	@gplx.Internal protected static TabBoxMgr new_() {return new TabBoxMgr();} TabBoxMgr() {}
}
