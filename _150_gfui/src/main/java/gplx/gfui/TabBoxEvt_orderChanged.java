package gplx.gfui; import gplx.*;
public class TabBoxEvt_orderChanged {
	public int CurIdx() {return curIdx;} public TabBoxEvt_orderChanged CurIdx_(int v) {curIdx = v; return this;} int curIdx;
	public int NewIdx() {return newIdx;} public TabBoxEvt_orderChanged NewIdx_(int v) {newIdx = v; return this;} int newIdx;

	public static final String OrderChanged_evt = "OrderChanged_evt";
	public static void Publish(TabBox tabBox, int curIdx, int newIdx) {
		GfoEvMgr_.PubVals(tabBox, OrderChanged_evt, Keyval_.new_("curIdx", curIdx), Keyval_.new_("newIdx", newIdx));
	}
	public static TabBoxEvt_orderChanged Handle(GfsCtx ctx, GfoMsg m) {
		TabBoxEvt_orderChanged rv = new TabBoxEvt_orderChanged();
		rv.curIdx = m.ReadInt("curIdx");
		rv.newIdx = m.ReadInt("newIdx");
		return rv;
	}
}
