package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
public class Gfui_dlg_msg_ {
	public static final    Gfui_dlg_msg Noop = new Gfui_dlg_msg_noop();
	public static final    int Ico_error = 0, Ico_information = 1, Ico_question = 2, Ico_warning = 3, Ico_working = 4;
	public static final int Btn_ok = 0, Btn_cancel = 1, Btn_yes = 2, Btn_no = 3, Retry = 4, Btn_abort = 5, Btn_ignore = 6;
}
class Gfui_dlg_msg_noop implements Gfui_dlg_msg {
	public Gfui_dlg_msg Init_msg_(String v) {return this;}
	public Gfui_dlg_msg Init_ico_(int v) {return this;}
	public Gfui_dlg_msg Init_btns_(int... ary) {return this;}
	public boolean Ask(int expd) {return false;}
	public int Ask() {return Int_.Min_value;}
}
