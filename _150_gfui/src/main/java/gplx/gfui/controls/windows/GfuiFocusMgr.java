package gplx.gfui.controls.windows; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.controls.elems.*;
public class GfuiFocusMgr implements Gfo_evt_mgr_owner {
	public static final    String FocusChanged_evt = "focusChanged_evt";
	public Gfo_evt_mgr Evt_mgr() {if (evt_mgr == null) evt_mgr = new Gfo_evt_mgr(this); return evt_mgr;} Gfo_evt_mgr evt_mgr;
	public GfuiElem FocusedElem() {return focusedElem;} GfuiElem focusedElem;
	public GfuiElem FocusedElemPrev() {return focusedElemPrev;} GfuiElem focusedElemPrev;
	public void FocusedElem_set(GfuiElem focused) {
		focusedElemPrev = focusedElem;
		this.focusedElem = focused;
		Gfo_evt_mgr_.Pub_val(this, FocusChanged_evt, focused);
	}
	public static final    GfuiFocusMgr Instance = new GfuiFocusMgr(); GfuiFocusMgr() {}
}
