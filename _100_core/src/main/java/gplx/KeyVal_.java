package gplx;
import gplx.core.strings.*;
public class KeyVal_ {//_20101217
	public static final KeyVal[] Ary_empty = new KeyVal[0];
	public static KeyVal[] Ary(KeyVal... ary) {return ary;}
	public static KeyVal[] Ary_cast_(Object o) {
		try {return (KeyVal[])o;}
		catch (Exception e) {throw Err_.new_cast(e, KeyVal.class, o);}
	}
	public static KeyVal[] Ary_insert(KeyVal[] orig, boolean insert_at_end, KeyVal... vals) {
		int orig_len = orig.length, vals_len = vals.length;
		int rv_len = orig_len + vals_len;
		KeyVal[] rv = new KeyVal[rv_len];
		int vals_bgn = 0		, vals_end = vals_len;
		int orig_bgn = vals_len	, orig_end = rv_len;
		if (insert_at_end) {
			orig_bgn = 0		; orig_end = orig_len;
			vals_bgn = orig_len	; vals_end = rv_len;
		}
		for (int i = orig_bgn; i < orig_end; i++)
			rv[i] = orig[i - orig_bgn];
		for (int i = vals_bgn; i < vals_end; i++)
			rv[i] = vals[i - vals_bgn];
		return rv;
	}
	public static String Ary_to_str(KeyVal... ary) {
		String_bldr sb = String_bldr_.new_();
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			KeyVal itm = ary[i];
			sb.Add(itm.Key()).Add("=");
			Object itm_val = itm.Val();
			if (Type_adp_.Eq_typeSafe(itm_val, KeyVal[].class))
				sb.Add(Ary_to_str((KeyVal[])itm_val));
			else
				sb.Add(Object_.Xto_str_strict_or_null_mark(itm_val));
			sb.Add_char_nl();
		}
		return sb.To_str();
	}
	public static Object Ary_get_by_key_or_null(KeyVal[] ary, String key) {
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			KeyVal kv = ary[i];
			if (String_.Eq(kv.Key(), key)) return kv.Val();
		}
		return null;
	}
	public static String Ary_to_str_nested(KeyVal... ary) {
		Bry_bfr bfr = Bry_bfr.new_();
		Ary_to_str_nested(bfr, 0, ary);
		return bfr.To_str_and_clear();
	}
	private static void Ary_to_str_nested(Bry_bfr bfr, int indent, KeyVal[] ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			KeyVal itm = ary[i];
			if (indent > 0)
				bfr.Add_byte_repeat(Byte_ascii.Space, indent * 2);					// add indent	: "  "
			bfr.Add_str_u8(Object_.Xto_str_strict_or_empty(itm.Key())).Add_byte_eq();// add key + eq : "key="
			Object val = itm.Val();
			if (val == null)
				bfr.Add_str_a7(String_.Null_mark);
			else {
				Class<?> val_type = Type_adp_.ClassOf_obj(val);
				if		(Type_adp_.Eq(val_type, KeyVal[].class)) {				// val is KeyVal[]; recurse
					bfr.Add_byte_nl();												// add nl		: "\n"
					Ary_to_str_nested(bfr, indent + 1, (KeyVal[])val);
					continue;														// don't add \n below
				}
				else if (Type_adp_.Eq(val_type, Bool_.Cls_ref_type)) {					// val is boolean
					boolean val_as_bool = Bool_.cast(val);
					bfr.Add(val_as_bool ? Bool_.True_bry : Bool_.False_bry);		// add "true" or "false"; don't call toString
				}
				else
					bfr.Add_str_u8(Object_.Xto_str_strict_or_null_mark(val));						// call toString()
			}
			bfr.Add_byte_nl();
		}
	}
	public static KeyVal as_(Object obj) {return obj instanceof KeyVal ? (KeyVal)obj : null;}
	public static KeyVal new_(String key)				{return new KeyVal(Type_adp_.Tid__str, key, key);}
	public static KeyVal new_(String key, Object val)	{return new KeyVal(Type_adp_.Tid__str, key, val);}
	public static KeyVal int_(int key, Object val)		{return new KeyVal(Type_adp_.Tid__int, key, val);}
	public static KeyVal obj_(Object key, Object val)	{return new KeyVal(Type_adp_.Tid__obj, key, val);}
}
