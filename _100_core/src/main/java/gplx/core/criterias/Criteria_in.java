package gplx.core.criterias; import gplx.*; import gplx.core.*;
public class Criteria_in implements Criteria {
	public Criteria_in(boolean neg, Object[] ary) {this.neg = neg; Ary_(ary);}
	public byte			Tid() {return Criteria_.Tid_in;}
	public boolean			Neg() {return neg;} private final boolean neg;
	public Object[]		Ary() {return ary;} private Object[] ary;
	public int			Ary_len() {return ary_len;} private int ary_len;
	public Class<?>	Itm_type() {return itm_type;} private Class<?> itm_type;
	private void		Ary_(Object[] v) {
		this.ary = v;
		this.ary_len = ary.length;
		this.itm_type = ary_len == 0 ? Object.class : Type_adp_.ClassOf_obj(ary[0]);
	}
	public void			Val_as_obj_(Object v) {Ary_((Object[])v);}
	public void			Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public boolean Matches(Object comp) {
		if (ary_len == 0) return false;	// empty array never matches
		if (!Type_adp_.Eq_typeSafe(comp, itm_type)) throw Err_.new_type_mismatch(itm_type, comp);
		boolean rv = false;
		for (int i = 0; i < ary_len; ++i) {
			Object val = ary[i];
			if (Object_.Eq(val, comp)) {
				rv = true;
				break;
			}
		}
		return neg ? !rv : rv;
	}

	public String To_str() {return String_.Concat_any("IN ", String_.Concat_any(ary));}
}
