package gplx.core.criterias; import gplx.*; import gplx.core.*;
public class Criteria_in implements Criteria {
	public Criteria_in(boolean negated, Object[] ary) {this.negated = negated; Val_as_obj_ary_(ary);}
	public byte			Tid() {return Criteria_.Tid_in;}
	public boolean			Negated() {return negated;} private final boolean negated;
	public Object[]		Val_as_obj_ary() {return ary;} private Object[] ary; private Class<?> ary_type; private int ary_len;
	private void		Val_as_obj_ary_(Object[] v) {
		this.ary = v;
		ary_len = Array_.Len(ary);
		ary_type = ary_len == 0 ? Object.class : Type_adp_.ClassOf_obj(ary[0]);
	}
	public void			Val_as_obj_(Object v) {Val_as_obj_ary_((Object[])v);}
	public void			Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public boolean Matches(Object comp) {
		if (ary_len == 0) return false;	// empty array never matches
		if (!Type_adp_.Eq_typeSafe(comp, ary_type)) throw Err_.new_type_mismatch(ary_type, comp);
		boolean rv = false;
		for (int i = 0; i < ary_len; i++) {
			Object val = ary[i];
			if (Object_.Eq(val, comp)) {
				rv = true;
				break;
			}
		}
		return negated ? !rv : rv;
	}
	public String To_str() {return String_.Concat_any("IN ", String_.Concat_any(ary));}
	public static Criteria_in as_(Object obj) {return obj instanceof Criteria_in ? (Criteria_in)obj : null;}
}
