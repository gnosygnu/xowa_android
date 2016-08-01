package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Hash_adp__primitive {
	private final    Hash_adp hash = Hash_adp_.New();
	public byte Get_by_str_or_max(String key) {
		Byte_obj_val rv = (Byte_obj_val)hash.Get_by(key);
		return rv == null ? Byte_.Max_value_127 : rv.Val();
	}
	public Hash_adp__primitive Add_byte(String key, byte val) {
		hash.Add(key, Byte_obj_val.new_(val));
		return this;
	}
}
