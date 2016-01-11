package gplx.core.lists; import gplx.*; import gplx.core.*;
public class Hash_adp_list extends Hash_adp_base {
	@gplx.New public List_adp Get_by(Object key) {return List_adp_.as_(Fetch_base(key));}
	public List_adp Get_by_or_new(Object key) {
		List_adp rv = Get_by(key);
		if (rv == null) {
			rv = List_adp_.new_();
			Add_base(key, rv);
		}
		return rv;
	}
	public void AddInList(Object key, Object val) {
		List_adp list = Get_by_or_new(key);
		list.Add(val);
	}
	public void DelInList(Object key, Object val) {
		List_adp list = Get_by(key);
		if (list == null) return;
		list.Del(val);
		if (list.Count() == 0) Del(key);
	}
	public static Hash_adp_list new_() {return new Hash_adp_list();} Hash_adp_list() {}
}
