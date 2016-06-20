package gplx;
import gplx.core.consoles.*; import gplx.core.lists.rings.*;
public class Gfo_usr_dlg__gui_ {
	public static final    Gfo_usr_dlg__gui Noop		= new Gfo_usr_dlg__gui_noop();
	public static final    Gfo_usr_dlg__gui Console		= new Gfo_usr_dlg__gui_console();
	public static final    Gfo_usr_dlg__gui Test		= new Gfo_usr_dlg__gui_test();
	public static final    Gfo_usr_dlg__gui Mem			= new Gfo_usr_dlg__gui_mem_string();
	public static String Mem_file() {return ((Gfo_usr_dlg__gui_mem_string)Mem).file;}
}
class Gfo_usr_dlg__gui_noop implements Gfo_usr_dlg__gui {
	public void Clear() {}
	public Ring__string Prog_msgs() {return ring;} Ring__string ring = new Ring__string().Max_(0);
	public void Write_prog(String text) {}
	public void Write_note(String text) {}
	public void Write_warn(String text) {}
	public void Write_stop(String text) {}
}
class Gfo_usr_dlg__gui_console implements Gfo_usr_dlg__gui {
	private final    Console_adp__sys console = Console_adp__sys.Instance;
	public void Clear() {}
	public Ring__string Prog_msgs() {return ring;} private final    Ring__string ring = new Ring__string().Max_(0);
	public void Write_prog(String text) {console.Write_tmp(text);}
	public void Write_note(String text) {console.Write_str_w_nl(text);}
	public void Write_warn(String text) {console.Write_str_w_nl(text);}
	public void Write_stop(String text) {console.Write_str_w_nl(text);}
}
class Gfo_usr_dlg__gui_mem_string implements Gfo_usr_dlg__gui {
	public String file = "";
	public void Clear() {file = "";}
	public Ring__string Prog_msgs() {return ring;} private final    Ring__string ring = new Ring__string().Max_(0);
	public void Write_prog(String text) {file += text + "\n";}
	public void Write_note(String text) {file += text + "\n";}
	public void Write_warn(String text) {file += text + "\n";}
	public void Write_stop(String text) {file += text + "\n";}
}
