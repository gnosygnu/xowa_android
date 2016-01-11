package gplx.core.criterias; import gplx.*; import gplx.core.*;
public class Criteria_comp implements Criteria {
	private final int comp_mode;
	@gplx.Internal protected Criteria_comp(int comp_mode, Comparable val) {this.comp_mode = comp_mode; this.val = val;}
	public byte				Tid() {return Criteria_.Tid_comp;}
	public Comparable		Val() {return val;} private Comparable val;
	public void				Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public void				Val_as_obj_(Object v) {val = (Comparable)v;}
	public boolean Matches(Object compObj) {
		Comparable comp = CompareAble_.as_(compObj);
		return CompareAble_.Is(comp_mode, comp, val);
	}
	public String To_str() {return String_.Concat_any(XtoSymbol(), " ", val);}
	public String XtoSymbol() {
		String comp_sym = comp_mode < CompareAble_.Same ? "<" : ">";
		String eq_sym = comp_mode % 2 == CompareAble_.Same ? "=" : "";
		return comp_sym + eq_sym;
	}
	public static Criteria_comp as_(Object obj) {return obj instanceof Criteria_comp ? (Criteria_comp)obj : null;}
}
