package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Int_pool {
	private final    List_adp available_list = List_adp_.New(); private int available_len;
	// private final    Bry_bfr dbg_bfr = Bry_bfr_.New();
	private int uid_max = -1;
	public void Clear() {
		synchronized (available_list) {
			available_list.Clear();
			available_len = 0;
			uid_max = -1;
		}
	}
	public int Get_next() {
		synchronized (available_list) {
			if (available_len == 0) {
				// dbg_bfr.Add_str("+:u:").Add_int_variable(uid_max + 1).Add_byte_nl();
				return ++uid_max;
			}
			else {
				Int_obj_val val = (Int_obj_val)List_adp_.Pop_last(available_list);
				--available_len;
				// dbg_bfr.Add_str("+:a:").Add_int_variable(val.Val()).Add_byte_nl();
				return val.Val();
			}
		}
	}
	public void Del(int v) {
		if (v > uid_max) throw Err_.new_("core", "value is greater than range", "value", v, "max", uid_max);
		synchronized (available_list) {
			if (available_len == 0 && v == uid_max) {
				--this.uid_max;
				// dbg_bfr.Add_str("-:m:").Add_int_variable(v).Add_byte_nl();
				return;
			}
			if (available_len == uid_max) {
				available_list.Add(Int_obj_val.new_(v));
				available_list.Sort();
				for (int i = 0; i < available_len; ++i) {
					Int_obj_val itm = (Int_obj_val)available_list.Get_at(i);
					if (i != itm.Val())
						throw Err_.new_("core", "available_list out of order", "contents", available_list.To_str());
				}
				// dbg_bfr.Add_str("-:c:").Add_int_variable(v).Add_byte_nl();
				this.Clear();
			}
			else {
				// dbg_bfr.Add_str("-:a:").Add_int_variable(v).Add_byte_nl();
				available_list.Add(Int_obj_val.new_(v));
				++available_len;
			}
		}
	}
}
