package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
public interface Gfui_dlg_msg {
	Gfui_dlg_msg Init_msg_(String v);
	Gfui_dlg_msg Init_ico_(int v);
	Gfui_dlg_msg Init_btns_(int... ary);
	int Ask();
	boolean Ask(int expd);
}
