package gplx.gfui.controls.tabs; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public class TabBoxEvt_tabSelect {
	public static String Key = "TabBoxEvt_tabSelect";
	public static final    String SelectedChanged_evt = "SelectedChanged_evt";
	public static void Send(TabBoxMgr tabBoxMgr, TabPnlItm oldTab, TabPnlItm newTab) {
		Gfo_evt_mgr_.Pub_val(tabBoxMgr, Key, new TabPnlItm[] {oldTab, newTab});
	}
	@gplx.Internal protected static void Select(TabBox tabBox, GfsCtx ctx, GfoMsg m) {
		TabPnlItm[] ary = (TabPnlItm[])m.CastObj("v");
		Select(tabBox, ary[0], ary[1]);
	}
	@gplx.Internal protected static void Select(TabBox tabBox, TabPnlItm curTabItm, TabPnlItm newTabItm) {
		TabPnlAreaMgr.Select(tabBox, curTabItm, newTabItm);
		TabBtnAreaMgr.Select(tabBox, curTabItm, newTabItm);
		Gfo_evt_mgr_.Pub_val(tabBox, SelectedChanged_evt, newTabItm.Idx());
	}		
	public static int Handle(GfsCtx ctx, GfoMsg m) {
		return m.ReadInt("v");
	}
}
