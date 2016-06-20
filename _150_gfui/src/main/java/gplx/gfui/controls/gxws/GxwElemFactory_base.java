package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public abstract class GxwElemFactory_base {
	public abstract GxwElem control_();
	public abstract GxwWin win_app_();
	public abstract GxwWin win_tool_(Keyval_hash ctorArgs);
	public abstract GxwWin win_toaster_(Keyval_hash ctorArgs);
	public abstract GxwElem lbl_();
	public abstract GxwTextFld text_fld_();
	public abstract GxwTextFld text_memo_();
	public abstract GxwTextHtml text_html_();
	public abstract GxwCheckListBox checkListBox_(Keyval_hash ctorArgs);
	public abstract GxwComboBox comboBox_();
	public abstract GxwListBox listBox_();
	//#{temp1
//	@gplx.Internal protected GxwElem spacer_() {return MockControl.new_();}
	//#}
}
