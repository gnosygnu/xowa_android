package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.xowa.files.fsdb.*;
class Xof_wkr_mgr implements Gfo_invk {
	private Xow_file_mgr file_mgr;
	public Xof_wkr_mgr(Xow_file_mgr file_mgr) {this.file_mgr = file_mgr;}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))		return Get_or_new(m.ReadStr("v"));
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_get = "get";
	private Xof_fsdb_mgr Get_or_new(String key) {
		Xof_fsdb_mgr rv = null;
		if (String_.Eq(key, "fs.dir"))
			rv = new gplx.xowa.files.fsdb.fs_roots.Fs_root_fsdb_mgr(file_mgr.Wiki());
		else
			throw Err_.new_unhandled(key);
		file_mgr.Fsdb_mgr_(rv);
		return rv;
	}
}
