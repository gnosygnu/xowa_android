package gplx.gfui; import gplx.*;
public abstract class GxwElemFactory_base {
	@gplx.Internal protected abstract GxwElem control_();
	@gplx.Internal protected abstract GxwWin win_app_();
	@gplx.Internal protected abstract GxwWin win_tool_(KeyValHash ctorArgs);
	@gplx.Internal protected abstract GxwWin win_toaster_(KeyValHash ctorArgs);
	@gplx.Internal protected abstract GxwElem lbl_();
	@gplx.Internal protected abstract GxwTextFld text_fld_();
	@gplx.Internal protected abstract GxwTextFld text_memo_();
	@gplx.Internal protected abstract GxwTextHtml text_html_();
	@gplx.Internal protected abstract GxwCheckListBox checkListBox_(KeyValHash ctorArgs);
	@gplx.Internal protected abstract GxwComboBox comboBox_();
	@gplx.Internal protected abstract GxwListBox listBox_();
	//#{temp1
//	@gplx.Internal protected GxwElem spacer_() {return MockControl.new_();}
	//#}
}
class GxwElemFactory_ {
	public static GxwElemFactory_base Instance = new GxwElemFactory_cls_mock();
	public static void winForms_() {Instance = new GxwElemFactory_cls_lang();}
	//#{swt
	public static void swt_(org.eclipse.swt.widgets.Display display) {Instance = new GxwElemFactory_swt(display);}
	//#}
}
class GxwElemFactory_cls_lang extends GxwElemFactory_base {
	@gplx.Internal @Override protected GxwElem control_() {return new GxwElem_lang();}
	@gplx.Internal @Override protected GxwWin win_tool_(KeyValHash ctorArgs)	{
		//#{win_tool_
		GfuiWin ownerForm = (GfuiWin)ctorArgs.FetchValOr(GfuiElem_.InitKey_ownerWin, null);
		GxwWin ownerElem = ownerForm == null ? null : (GxwWin)ownerForm.UnderElem(); 
		return GxwWin_jdialog.new_(ownerElem);
//		return GxwWin_lang.new_();
		//#}
	}
	@gplx.Internal @Override protected GxwWin win_toaster_(KeyValHash ctorArgs)	{
		//#{win_toaster_
		GfsCtx ctx = GfsCtx.new_(); ctx.Match("", "");
		GfuiWin ownerForm = (GfuiWin)ctorArgs.FetchValOr(GfuiElem_.InitKey_ownerWin, null);
		GxwWin ownerElem = ownerForm == null ? null : (GxwWin)ownerForm.UnderElem(); 
		return GxwWin_jwindow.new_(ownerElem);
//		return GxwWin_lang.new_();
		//#}
	}
	@gplx.Internal @Override protected GxwWin win_app_()			{return GxwWin_lang.new_();}
	@gplx.Internal @Override protected GxwElem lbl_()			{return new GxwElem_lang();}
	@gplx.Internal @Override protected GxwTextFld text_fld_()	{return GxwTextBox_lang_.fld_();}
	@gplx.Internal @Override protected GxwTextFld text_memo_()	{return GxwTextBox_lang_.memo_();}
	@gplx.Internal @Override protected GxwTextHtml text_html_()	{return new GxwTextHtml_lang().ctor();}
	@gplx.Internal @Override protected GxwCheckListBox checkListBox_(KeyValHash ctorArgs) {return new GxwCheckListBox_lang();}
	@gplx.Internal @Override protected GxwComboBox comboBox_()	{return GxwComboBox_lang.new_();}
	@gplx.Internal @Override protected GxwListBox listBox_()		{return GxwListBox_lang.new_();}
}
class GxwElemFactory_cls_mock extends GxwElemFactory_base {
	@gplx.Internal @Override protected GxwElem control_() {return GxwElem_mock_base.new_();}
	@gplx.Internal @Override protected GxwWin win_app_() {return MockForm.Instance;}
	@gplx.Internal @Override protected GxwWin win_tool_(KeyValHash ctorArgs)	{return MockForm.Instance;}
	@gplx.Internal @Override protected GxwWin win_toaster_(KeyValHash ctorArgs)	{return MockForm.Instance;}
	@gplx.Internal @Override protected GxwElem lbl_() {return GxwElem_mock_base.new_();}
	@gplx.Internal @Override protected GxwTextFld text_fld_() {return new MockTextBox();}
	@gplx.Internal @Override protected GxwTextFld text_memo_() {return new MockTextBoxMulti();}
	@gplx.Internal @Override protected GxwTextHtml text_html_() {return new MockTextBoxMulti();}
	@gplx.Internal @Override protected GxwCheckListBox checkListBox_(KeyValHash ctorArgs) {throw Err_.new_unimplemented();}
	@gplx.Internal @Override protected GxwComboBox comboBox_() {return new MockComboBox();}
	@gplx.Internal @Override protected GxwListBox listBox_() {return new MockListBox();}
}
