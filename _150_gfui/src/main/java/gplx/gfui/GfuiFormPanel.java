package gplx.gfui; import gplx.*;
public class GfuiFormPanel extends GfuiElemBase {
	@Override public void ctor_GfuiBox_base(KeyValHash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		this.Width_(60);	// default to 60; do not force callers to always set width
		GfuiWin ownerForm = (GfuiWin)ctorArgs.FetchValOr(GfuiElem_.InitKey_ownerWin, null);

		GfoFactory_gfui.Btn_MoveBox(this, ownerForm);
		GfoFactory_gfui.Btn_MinWin2(this);
		GfoFactory_gfui.Btn_QuitWin3(this);

		this.Lyt_activate();
		this.Lyt().Bands_add(GftBand.new_().Cells_num_(3));
	}
	public static GfuiFormPanel new_(GfuiWin form) {
		GfuiFormPanel rv = new GfuiFormPanel();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_false_().Add(GfuiElem_.InitKey_ownerWin, form));
		rv.Owner_(form, "formPanel");
		return rv;
	}
}