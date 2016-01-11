package gplx.gfui; import gplx.*;
public interface Gfui_dlg_file {
	Gfui_dlg_file Init_msg_(String v);
	Gfui_dlg_file Init_file_(String v);
	Gfui_dlg_file Init_dir_(Io_url v);
	Gfui_dlg_file Init_exts_(String... v);
	String Ask();
}
