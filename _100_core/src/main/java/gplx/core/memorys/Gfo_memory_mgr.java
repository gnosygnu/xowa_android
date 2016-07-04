package gplx.core.memorys; import gplx.*; import gplx.core.*;
public class Gfo_memory_mgr {
	private final    List_adp list = List_adp_.New();
	public void Reg_safe(Gfo_memory_itm itm) {synchronized (list) {Reg_fast(itm);}}
	public void Reg_fast(Gfo_memory_itm itm) {list.Add(itm);}
	public void Rls_safe() {synchronized (list) {Rls_fast();}}
	public void Rls_fast() {
		int len = list.Len();
		for (int i = 0; i < len; ++i) {
			Gfo_memory_itm itm = (Gfo_memory_itm)list.Get_at(i);
			itm.Rls_mem();
		}
		list.Clear();
	}
	public static final    Gfo_memory_mgr Instance = new Gfo_memory_mgr(); Gfo_memory_mgr() {}
}
