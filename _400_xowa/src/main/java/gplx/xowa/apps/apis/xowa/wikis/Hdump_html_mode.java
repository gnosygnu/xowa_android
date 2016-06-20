package gplx.xowa.apps.apis.xowa.wikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
public class Hdump_html_mode {
	public Hdump_html_mode(int tid, String key, String gui) {
		this.tid = (byte)tid; this.key = key; this.gui = gui;
	}
	public byte			Tid() {return tid;} private final    byte tid;
	public String		Key() {return key;} private final    String key;
	public String		Gui() {return gui;} private final    String gui;
	public Keyval		Opt() {return Keyval_.new_(key, gui);}
	public boolean			Tid_is_hdump_save() {return tid == Hdump_save.tid;}

	public static final    Hdump_html_mode
	  Shown			= new Hdump_html_mode(0, "shown"		, "Shown")
	, Hdump_save	= new Hdump_html_mode(1, "hdump_save"	, "Saved for HTML DB")
	, Hdump_load	= new Hdump_html_mode(2, "hdump_load"	, "Loaded by HTML DB")
	;
	public static Hdump_html_mode Parse(String v) {
		if		(String_.Eq(v, Shown.key))			return Shown;
		else if	(String_.Eq(v, Hdump_save.key))		return Hdump_save;
		else if	(String_.Eq(v, Hdump_load.key))		return Hdump_load;
		else										throw Err_.new_unhandled(v);
	}
	public static Keyval[] Opt_list() {return new Keyval[] {Shown.Opt(), Hdump_save.Opt()};}
}
