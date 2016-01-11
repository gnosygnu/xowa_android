package gplx;
import gplx.core.consoles.*; import gplx.core.strings.*;
public class Gfo_usr_dlg__gui_ {
	public static final Gfo_usr_dlg__gui Noop		= new Gfo_usr_dlg__gui_noop();
	public static final Gfo_usr_dlg__gui Console		= new Gfo_usr_dlg__gui_console();
	public static final Gfo_usr_dlg__gui Test		= new Gfo_usr_dlg__gui_test();
}
class Gfo_usr_dlg__gui_noop implements Gfo_usr_dlg__gui {
	public void Clear() {}
	public String_ring Prog_msgs() {return ring;} String_ring ring = new String_ring().Max_(0);
	public void Write_prog(String text) {}
	public void Write_note(String text) {}
	public void Write_warn(String text) {}
	public void Write_stop(String text) {}
}
class Gfo_usr_dlg__gui_console implements Gfo_usr_dlg__gui {
	private final Console_adp__sys console = Console_adp__sys.Instance;
	public void Clear() {}
	public String_ring Prog_msgs() {return ring;} private final String_ring ring = new String_ring().Max_(0);
	public void Write_prog(String text) {console.Write_tmp(text);}
	public void Write_note(String text) {console.Write_str_w_nl(text);}
	public void Write_warn(String text) {console.Write_str_w_nl(text);}
	public void Write_stop(String text) {console.Write_str_w_nl(text);}
}
