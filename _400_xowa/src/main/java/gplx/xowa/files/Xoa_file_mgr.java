package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
public class Xoa_file_mgr {
	private final List_adp list = List_adp_.new_();
	public void Clear() {list.Clear();}
	public boolean Check_cache(Xof_fsdb_itm itm) {
		return false;
	}
	public void Queue_for_viewing(Xof_fsdb_itm itm) {
		list.Add(itm);
	}
}
