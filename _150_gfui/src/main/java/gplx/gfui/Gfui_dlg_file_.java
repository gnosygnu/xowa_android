package gplx.gfui; import gplx.*;
public class Gfui_dlg_file_ {
	public static final Gfui_dlg_file Noop = new Gfui_dlg_file_noop();
}
class Gfui_dlg_file_noop implements Gfui_dlg_file {
	public Gfui_dlg_file Init_msg_(String v) {return this;}
	public Gfui_dlg_file Init_file_(String v) {return this;}
	public Gfui_dlg_file Init_dir_(Io_url v) {return this;}
	public Gfui_dlg_file Init_exts_(String... v) {return this;}
	public String Ask() {return "";}
}
