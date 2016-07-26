package gplx.xowa.addons.bldrs.mass_parses.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
public class Xomp_db_core {
	private final    Object thread_lock = new Object();
	private final    Io_url root_dir;
	Xomp_db_core(Io_url root_dir) {
		this.root_dir = root_dir;
		Io_url mgr_url = root_dir.GenSubFil("xomp.sqlite3");
		this.mgr_db = new Xomp_mgr_db(mgr_url);
	}
	public Db_conn Conn() {return mgr_db.Conn();}
	public Xomp_mgr_db Mgr_db() {return mgr_db;} private Xomp_mgr_db mgr_db;
	public Xomp_wkr_db Wkr_db(boolean delete, int idx) {
		Io_url wkr_url = root_dir.GenSubFil_nest("xomp_" + Int_.To_str_fmt(idx, "000"), "xomp_wkr.sqlite3");
		if (delete) Io_mgr.Instance.DeleteFil(wkr_url);
		return new Xomp_wkr_db(idx, wkr_url);
	}
	public int Wkr_count() {
		Io_url[] wkr_dirs = Io_mgr.Instance.QueryDir_args(root_dir).DirOnly_().ExecAsUrlAry();
		return wkr_dirs.length;
	}
	public void Update_wkr_id(int idx, Db_conn wkr_conn) {
		synchronized (thread_lock) {
			Db_attach_mgr attach_mgr = new Db_attach_mgr(mgr_db.Conn(), new Db_attach_itm("wkr_db", wkr_conn));
			attach_mgr.Exec_sql_w_msg("updating page_regy: wkr_id=" + idx, String_.Concat_lines_nl_skip_last	// ANSI.Y
			( "UPDATE  xomp_page"
			, "SET     xomp_wkr_id = " + Int_.To_str(idx)
			, ",       html_len = (SELECT length(body) FROM <wkr_db>html h WHERE h.page_id = xomp_page.page_id)"
			, "WHERE   page_id IN (SELECT page_id FROM <wkr_db>html h)"
			));
		}
	}

	public static Xomp_db_core New__make(Xowe_wiki wiki) {
		Io_url root_dir = wiki.Fsys_mgr().Root_dir().GenSubDir_nest("tmp", "xomp");
		Io_mgr.Instance.DeleteDirDeep(root_dir);
		return new Xomp_db_core(root_dir);
	}
	public static Xomp_db_core New__load(Xowe_wiki wiki) {
		Io_url root_dir = wiki.Fsys_mgr().Root_dir().GenSubDir_nest("tmp", "xomp");
		return new Xomp_db_core(root_dir);
	}
}
