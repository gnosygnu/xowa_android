package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Scrib_proc_rslt {
	private Keyval[] ary;
	public Keyval[] Ary() {return ary;}
	public String Fail_msg() {return fail_msg;} private String fail_msg;
	public boolean Init_fail(String v)		{fail_msg = v; return false;}
	public boolean Init_null()				{Init_obj(null); return true;}	// return array(null);
	public boolean Init_str_empty()		{Init_obj(""); return true;}
	public boolean Init_ary_empty()		{ary = Keyval_.Ary_empty; return true;}
	public boolean Init_obj(Object val) {
		ary = new Keyval[] {Keyval_.int_(Scrib_core.Base_1, val)};
		return true;
	}
	public boolean Init_many_objs(Object... vals) {
		int len = vals.length;
		ary = new Keyval[len];
		for (int i = 0; i < len; i++)
			ary[i] = Keyval_.int_(i + Scrib_core.Base_1, vals[i]);
		return true;
	}
	public boolean Init_many_kvs(Keyval... kvs) {
		ary = kvs;
		return true;
	}
	public boolean Init_many_list(List_adp list) {
		int len = list.Count();
		ary = new Keyval[len];
		for (int i = 0; i < len; i++)
			ary[i] = Keyval_.int_(i + Scrib_core.Base_1, list.Get_at(i));
		list.Clear();
		return true;
	}
	public boolean Init_many_empty() {
		ary = Keyval_.Ary_empty;
		return true;
	}
	public boolean Init_bry_ary(byte[][] v) {	// NOTE:fallbacks should return "table {fallback_1, fallback_2}"; PAGE:wd:Main_Page DATE:2015-04-21
		int len = v.length;
		Keyval[] itms = new Keyval[len];
		for (int i = 0; i < len; i++)
			itms[i] = Keyval_.int_(i + Scrib_core.Base_1, String_.new_u8(v[i]));
		this.ary = new Keyval[] {Keyval_.int_(Scrib_core.Base_1, itms)};
		return true;
	}
}
