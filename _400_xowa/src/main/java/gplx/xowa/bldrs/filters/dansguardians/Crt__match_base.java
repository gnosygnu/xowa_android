package gplx.xowa.bldrs.filters.dansguardians; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.filters.*;
import gplx.core.criterias.*;
class Crt__match_exact implements Criteria {
	public Crt__match_exact(boolean negated, byte[][] ary) {this.negated = negated; Val_as_bry_ary_(ary);}
	public byte Tid() {return Tid_match_exact;}
	public String To_str_name() {return "MATCH_EXACT";}
	public boolean Matches(Object comp_obj) {
		if (ary_len == 0) return false;	// empty array never matches
		byte[] comp = (byte[])comp_obj;
		boolean rv = false;
		for (int i = 0; i < ary_len; i++) {
			byte[] val = ary[i];
			if (Bry_.Eq(val, comp)) {
				rv = true;
				break;
			}
		}
		return negated ? !rv : rv;
	}
	public boolean				Negated() {return negated;} private boolean negated;
	public byte[][]		Val_as_bry_ary() {return ary;} protected byte[][] ary; protected int ary_len;
	protected void		Val_as_bry_ary_(byte[][] v) {
		this.ary = v;
		ary_len = v.length;
	}
	public void			Val_as_obj_(Object v) {Val_as_bry_ary_((byte[][])v);}
	public void			Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public String To_str() {return String_.Concat_any(this.To_str_name(), " ", String_.Ary(ary));}
	public byte Tid_match_exact = 12;
}
