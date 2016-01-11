package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
class TabBoxEvt_nameChange {
	public static String Key = "TabBoxEvt_nameChange";
	public static void Send(TabBoxMgr mgr, TabPnlItm itm) {
		GfoEvMgr_.PubVal(mgr, Key, itm);
	}
	public static void Rcvd(TabBox tabBox, GfsCtx ctx, GfoMsg m) {
		TabPnlItm itm = (TabPnlItm)m.CastObj("v");
		GfuiBtn btn = GfuiBtn_.as_(tabBox.BtnBox().SubElems().Get_by(itm.Key()));
		if (btn != null)	// HACK: check needed b/c Gfds will raise UpdateCaption event before Creating tab
			btn.Text_(itm.Name()).TipText_(itm.Name());
	}
}
class TabBoxEvt_tabSelectByBtn {
	public static String Key = "TabBoxEvt_tabSelectByBtn";
	public static void Rcvd(Object sender, TabBox tabBox) {
		GfuiBtn btn = (GfuiBtn)sender;
		String key = btn.Key_of_GfuiElem();
		TabBoxMgr mgr = tabBox.Mgr();
		mgr.Select(mgr.Get_by(key));
	}
}
class TabBnd_selectTab implements InjectAble, GfoInvkAble {
	public void Inject(Object obj) {
		tabBox = TabBox_.cast(obj);
		IptBnd_.cmd_to_(IptCfg_.Null, tabBox, this, SelectNext_cmd, IptKey_.add_(IptKey_.Ctrl, IptKey_.Tab), IptKey_.add_(IptKey_.Ctrl, IptKey_.PageDown));
		IptBnd_.cmd_to_(IptCfg_.Null, tabBox, this, SelectPrev_cmd, IptKey_.add_(IptKey_.Ctrl, IptKey_.Tab, IptKey_.Shift), IptKey_.add_(IptKey_.Ctrl, IptKey_.PageUp));
	}
	void Select(GfoMsg msg, int delta) {
		TabPnlItm curTab = tabBox.Mgr().CurTab();
		int newIdx = TabBox_.Cycle(delta > 0, curTab.Idx(), tabBox.Mgr().Count());
		tabBox.Tabs_Select(newIdx);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, SelectNext_cmd))			Select(m,  1);
		else if	(ctx.Match(k, SelectPrev_cmd))			Select(m, -1);
		else return GfoInvkAble_.Rv_unhandled;
		return this;
	}	static final String SelectNext_cmd = "SelectNext", SelectPrev_cmd = "SelectPrev";
	TabBox tabBox;
	public static TabBnd_selectTab new_() {return new TabBnd_selectTab();} TabBnd_selectTab() {}
}
class TabBnd_reorderTab implements InjectAble, GfoInvkAble {
	public void Inject(Object owner) {
		GfuiBtn btn = GfuiBtn_.cast(owner);
		IptBnd_.cmd_to_(IptCfg_.Null, btn, this, MovePrev_cmd, IptKey_.add_(IptKey_.Ctrl, IptKey_.Left));
		IptBnd_.cmd_to_(IptCfg_.Null, btn, this, MoveNext_cmd, IptKey_.add_(IptKey_.Ctrl, IptKey_.Right));
	}
	@gplx.Internal protected void MoveTab(GfuiBtn curBtn, int delta) {
		TabPnlItm curItm = tabBox.Mgr().Get_by(curBtn.Key_of_GfuiElem());
		int curIdx = curItm.Idx();
		int newIdx = TabBox_.Cycle(delta > 0, curIdx, tabBox.Mgr().Count());

		tabBox.Mgr().Move_to(curIdx, newIdx);
		tabBox.Mgr().Reorder(0); // reorder all; exchanging curIdx for newIdx does not work when going from last to first (17 -> 0, but 0 -> 1)
		tabBox.PnlBox().SubElems().Move_to(curIdx, newIdx);
		TabBtnAreaMgr.Move_to(tabBox, curIdx, newIdx);
		TabBoxEvt_orderChanged.Publish(tabBox, curIdx, newIdx);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, MoveNext_cmd))			MoveTab(GfuiBtn_.cast(ctx.MsgSrc()),  1);
		else if	(ctx.Match(k, MovePrev_cmd))			MoveTab(GfuiBtn_.cast(ctx.MsgSrc()), -1);
		else return GfoInvkAble_.Rv_unhandled;
		return this;
	}	public static final String MoveNext_cmd = "MoveNext", MovePrev_cmd = "MovePrev";
	public static TabBnd_reorderTab new_(TabBox tabBox) {
		TabBnd_reorderTab rv = new TabBnd_reorderTab();
		rv.tabBox = tabBox;
		return rv;
	}	TabBnd_reorderTab() {}
	TabBox tabBox;
}
