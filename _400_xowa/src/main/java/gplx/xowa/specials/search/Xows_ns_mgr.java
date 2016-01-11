package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.core.primitives.*;
import gplx.xowa.wikis.nss.*;
public class Xows_ns_mgr {
	private final Ordered_hash ns_hash = Ordered_hash_.New(); private final Int_obj_ref tmp_ns_id = Int_obj_ref.neg1_(); private final Bry_bfr tmp_bfr = Bry_bfr.reset_(32);
	private boolean ns_all, ns_main;
	public void Clear() {
		ns_hash.Clear();
		ns_all = ns_main = false;
	}
	public boolean Has(int ns_id) {
		return ns_all								// all flag set
			|| ns_main && ns_id == Xow_ns_.Tid__main	// main flag set
			|| ns_hash.Has(tmp_ns_id.Val_(ns_id))	// check against ns_hash
			;
	}
	public void Add_by_id(int ns_id) {
		if (ns_hash.Has(tmp_ns_id.Val_(ns_id)))
			ns_hash.Del(tmp_ns_id);
		ns_hash.Add_as_key_and_val(Int_obj_ref.new_(ns_id));
	}
	public void Add_by_name(byte[] ns_name) {
		int id = Xow_ns_canonical_.To_id(ns_name);
		if (id != Xow_ns_.Tid__null)
			Add_by_id(id);
	}
	public void Add_all() {
		ns_all = true;
	}
	public void Add_by_parse(byte[] key, byte[] val) {
		int ns_enabled = Bry_.To_int_or_neg1(val);
		if (ns_enabled == 1) {										// make sure set to 1; EX: ignore &ns0=0
			int key_len = key.length;
			if (key_len == 3 && key[2] == Byte_ascii.Star)		// translate ns* as ns_all
				ns_all = true;
			else {
				int ns_id = Bry_.To_int_or(key, 2, key_len, Int_.Min_value);
				if (ns_id != Int_.Min_value) {						// ignore invalid ints; EX: &nsabc=1;
					Add_by_id(ns_id);
					ns_main = ns_all = false;
				}
			}
		}
	}
	public void Add_main_if_empty() {
		if (ns_hash.Count() == 0)
			ns_main = true;
	}
	public byte[] To_hash_key() {
		if		(ns_all) 
			return Hash_key_all;
		else if (ns_main)
			return Hash_key_main;
		else {
			int ns_hash_len = ns_hash.Count();
			for (int i = 0; i < ns_hash_len; i++) {
				if (i != 0) tmp_bfr.Add_byte_semic();
				Int_obj_ref ns_id_ref = (Int_obj_ref)ns_hash.Get_at(i);
				tmp_bfr.Add_int_variable(ns_id_ref.Val());
			}
			return tmp_bfr.To_bry_and_clear();
		}
	}
	private static final byte[] Hash_key_all = new byte[] {Byte_ascii.Star}, Hash_key_main = new byte[] {Byte_ascii.Num_0};
}
