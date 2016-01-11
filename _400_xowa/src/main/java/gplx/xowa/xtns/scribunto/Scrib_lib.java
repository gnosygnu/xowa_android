package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public interface Scrib_lib {
	Scrib_proc_mgr	Procs();
	Scrib_lib		Init();
	Scrib_lua_mod	Register(Scrib_core core, Io_url script_dir);
	boolean			Procs_exec(int key, Scrib_proc_args args, Scrib_proc_rslt rslt);
}
