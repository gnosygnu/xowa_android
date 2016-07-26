package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Scrib_lib_mgr {
	private List_adp list = List_adp_.New();
	public int Len() {return list.Count();}
	public void Add(Scrib_lib v) {list.Add(v); v.Init();}
	public Scrib_lib Get_at(int i) {return (Scrib_lib)list.Get_at(i);}
	public void Init_for_core(Scrib_core core, Io_url script_dir) {
		int len = list.Count();
		for (int i = 0; i < len; i++) {
			Scrib_lib lib = Get_at(i).Clone_lib(core);
			lib.Register(core, script_dir);
		}
	}
}
