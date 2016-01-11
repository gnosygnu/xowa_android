package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.scribunto.libs.*;
public class Scrib_proc {
	private Scrib_lib lib;
	public Scrib_proc(Scrib_lib lib, String proc_name, int proc_id) {
		this.lib = lib;
		this.proc_name = proc_name;
		this.proc_key = Scrib_core.Key_mw_interface + "-" + proc_name;
		this.proc_id = proc_id;
	}
	public String Proc_name() {return proc_name;} private String proc_name;
	public int Proc_id() {return proc_id;} private int proc_id;
	public String Proc_key() {return proc_key;} private String proc_key;
	public boolean Proc_exec(Scrib_proc_args args, Scrib_proc_rslt rslt) {return lib.Procs_exec(proc_id, args, rslt);}
}
