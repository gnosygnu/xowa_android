package gplx.gfui; import gplx.*;
public class TabBoxEvt_tabSelect {
	public static String Key = "TabBoxEvt_tabSelect";
	public static final String SelectedChanged_evt = "SelectedChanged_evt";
	public static void Send(TabBoxMgr tabBoxMgr, TabPnlItm oldTab, TabPnlItm newTab) {
		GfoEvMgr_.PubVal(tabBoxMgr, Key, new TabPnlItm[] {oldTab, newTab});
	}
	@gplx.Internal protected static void Select(TabBox tabBox, GfsCtx ctx, GfoMsg m) {
		TabPnlItm[] ary = (TabPnlItm[])m.CastObj("v");
		Select(tabBox, ary[0], ary[1]);
	}
	@gplx.Internal protected static void Select(TabBox tabBox, TabPnlItm curTabItm, TabPnlItm newTabItm) {
		TabPnlAreaMgr.Select(tabBox, curTabItm, newTabItm);
		TabBtnAreaMgr.Select(tabBox, curTabItm, newTabItm);
		GfoEvMgr_.PubVal(tabBox, SelectedChanged_evt, newTabItm.Idx());
	}		
	public static int Handle(GfsCtx ctx, GfoMsg m) {
		return m.ReadInt("v");
	}
}
