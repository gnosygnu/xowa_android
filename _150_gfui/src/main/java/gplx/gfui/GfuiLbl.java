package gplx.gfui; import gplx.*;
public class GfuiLbl extends GfuiElemBase { // standard label does not support tooltips
	@Override public void Click() {
		int focusOrder = this.OwnerElem().SubElems().IndexOfA(this);
		GfuiElem focusNext = this.OwnerElem().SubElems().Get_at(focusOrder + 1);	// FIXME: incorporate into new FocusOrder
		focusNext.Focus();
	}
	@Override public boolean PaintCbk(PaintArgs args) {
		super.PaintCbk(args);
		this.TextMgr().DrawData(args.Graphics());
		return true;
	}		
	@Override public void ctor_GfuiBox_base(KeyValHash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		this.CustomDraw_set(true);
	}
	@Override public void ctor_kit_GfuiElemBase(Gfui_kit kit, String key, GxwElem underElem, KeyValHash ctorArgs) {
		super.ctor_kit_GfuiElemBase(kit, key, underElem, ctorArgs);
		this.CustomDraw_set(true);
	}
	@Override public GxwElem UnderElem_make(KeyValHash ctorArgs) {return GxwElemFactory_.Instance.lbl_();}
}
