package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_itm_ {
	public static final byte Tid_null = 0, Tid_bool_false = 1, Tid_bool_true = 2, Tid_int = 3, Tid_quote = 4, Tid_ary = 5, Tid_kv = 6, Tid_var = 7;
	public static int Parse_int_or(Php_itm itm, int or) {
		int rv = -1;
		switch (itm.Itm_tid()) {
			case Php_itm_.Tid_int:
				rv = ((Php_itm_int)itm).Val_obj_int();
				return rv;
			case Php_itm_.Tid_quote:
				byte[] bry = ((Php_itm_quote)itm).Val_obj_bry();
				rv = Bry_.To_int_or(bry, -1);
				return (rv == -1) ? or : rv;
			default:
				return or;
		}
	}
	public static byte[] Parse_bry(Php_itm itm) {
		switch (itm.Itm_tid()) {
			case Php_itm_.Tid_kv:
			case Php_itm_.Tid_ary:
				throw Err_.new_unhandled(itm.Itm_tid());
			default:
				return itm.Val_obj_bry();
		}
	}
}
