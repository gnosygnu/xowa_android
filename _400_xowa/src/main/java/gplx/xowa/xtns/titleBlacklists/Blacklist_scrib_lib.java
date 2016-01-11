package gplx.xowa.xtns.titleBlacklists; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.scribunto.*; import gplx.xowa.xtns.scribunto.libs.*;
public class Blacklist_scrib_lib implements Scrib_lib {
	public Scrib_lua_mod Mod() {return mod;} private Scrib_lua_mod mod;
	public Scrib_lib Init() {procs.Init_by_lib(this, Proc_names); return this;}
	public Scrib_lua_mod Register(Scrib_core core, Io_url script_dir) {
		Init();			
		mod = core.RegisterInterface(this, core.App().Fsys_mgr().Bin_xtns_dir().GenSubFil_nest("TitleBlacklist", "mw.ext.TitleBlacklist.lua"));
		return mod;
	}
	public Scrib_proc_mgr Procs() {return procs;} private Scrib_proc_mgr procs = new Scrib_proc_mgr();
	public boolean Procs_exec(int key, Scrib_proc_args args, Scrib_proc_rslt rslt) {
		switch (key) {
			case Proc_test:										return Test(args, rslt);
			default: throw Err_.new_unhandled(key);
		}
	}
	private static final int Proc_test = 0;
	public static final String Invk_test = "test";
	private static final String[] Proc_names = String_.Ary(Invk_test);
	public boolean Test(Scrib_proc_args args, Scrib_proc_rslt rslt) {
		return rslt.Init_null();	// assume all titles are blacklisted; note that this info is not available;
	}
}
