package gplx;
public class Keyval_list {
	public int				Count() {return list.Count();} private final    List_adp list = List_adp_.New();
	public void				Clear() {list.Clear();}
	public Keyval			Get_at(int i) {return (Keyval)list.Get_at(i);}
	public Keyval_list		Add(String key, Object val) {list.Add(Keyval_.new_(key, val)); return this;}
	public Keyval[]			To_ary() {return (Keyval[])list.To_ary(Keyval.class);}
	public String To_str() {
		Bry_bfr bfr = Bry_bfr_.New();
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Keyval kv = (Keyval)list.Get_at(i);
			if (i == 0) bfr.Add_byte_space();
			bfr.Add_str_u8(kv.Key()).Add_byte_eq().Add_str_u8(kv.Val_to_str_or_empty());
		}
		return bfr.To_str_and_clear();
	}
	public static Keyval_list New_with_one(String key, Object val) {return new Keyval_list().Add(key, val);}
}
