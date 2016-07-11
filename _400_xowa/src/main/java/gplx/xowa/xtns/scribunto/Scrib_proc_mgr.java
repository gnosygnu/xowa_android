package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Scrib_proc_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New();
	public void Clear() {hash.Clear();}
	public Scrib_proc Get_by_key(String key) {synchronized (hash) {return (Scrib_proc)hash.Get_by(key);}}
	public Scrib_proc Get_at(int i) {synchronized (hash) {return (Scrib_proc)hash.Get_at(i);}}
	public void Set(String key, Scrib_proc proc) {
//			synchronized (hash) {	// LOCK:DELETE; DATE:2016-07-06
			hash.Add_if_dupe_use_nth(key, proc);		// WORKAROUND: Add_if_dupe_use_nth b/c some libraries reuse proc name; EX: getGlobalSiteId is used by mw.wikibase.lua and mw.wikibase.entity.lua
//			}
	}
	public Scrib_proc Set(Scrib_lib lib, String proc_name, int proc_id) {
		Scrib_proc proc = new Scrib_proc(lib, proc_name, proc_id);
		Set(proc.Proc_key(), proc);
		return proc;
	}
	public int Len() {synchronized (hash) {return hash.Count();}}
	public void Init_by_lib(Scrib_lib lib, String[] proc_names) {
		synchronized (hash) {
			hash.Clear();
			int len = proc_names.length;
			for (int i = 0; i < len; i++) {
				String proc_name = proc_names[i];
				Scrib_proc proc = new Scrib_proc(lib, proc_name, i);	// ASSUME: Proc_id consts matches order in which Init is called
				hash.Add(proc_name, proc);
			}
		}
	}
}
