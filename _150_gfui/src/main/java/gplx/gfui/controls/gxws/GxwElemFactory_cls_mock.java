package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public class GxwElemFactory_cls_mock extends GxwElemFactory_base {
	@Override public GxwElem control_() {return GxwElem_mock_base.new_();}
	@Override public GxwWin win_app_() {return MockForm.Instance;}
	@Override public GxwWin win_tool_(Keyval_hash ctorArgs)	{return MockForm.Instance;}
	@Override public GxwWin win_toaster_(Keyval_hash ctorArgs)	{return MockForm.Instance;}
	@Override public GxwElem lbl_() {return GxwElem_mock_base.new_();}
	@Override public GxwTextFld text_fld_() {return new MockTextBox();}
	@Override public GxwTextFld text_memo_() {return new MockTextBoxMulti();}
	@Override public GxwTextHtml text_html_() {return new MockTextBoxMulti();}
	@Override public GxwCheckListBox checkListBox_(Keyval_hash ctorArgs) {throw Err_.new_unimplemented();}
	@Override public GxwComboBox comboBox_() {return new MockComboBox();}
	@Override public GxwListBox listBox_() {return new MockListBox();}
}
