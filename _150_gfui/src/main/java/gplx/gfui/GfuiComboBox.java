package gplx.gfui; import gplx.*;
public class GfuiComboBox extends GfuiElemBase {
	@Override public GxwElem UnderElem_make(Keyval_hash ctorArgs) {return GxwElemFactory_.Instance.comboBox_();} GxwComboBox comboBox;
	public Object SelectedItm() {return comboBox.SelectedItm();} public void SelectedItm_set(Object v) {comboBox.SelectedItm_set(v);}
	@Override public void ctor_GfuiBox_base(Keyval_hash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		this.comboBox = (GxwComboBox)this.UnderElem();
	}
	public void DataSource_set(Object... ary) {
		comboBox.DataSource_set(ary);
	}
	public static GfuiComboBox new_() {
		GfuiComboBox rv = new GfuiComboBox();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_true_());
		return rv;
	}
}
