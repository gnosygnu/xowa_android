package gplx.core.criterias; import gplx.*; import gplx.core.*;
public class Criteria_between implements Criteria {
	public Criteria_between(boolean negate, Comparable lhs, Comparable rhs) {this.negate = negate; this.lhs = lhs; this.rhs = rhs;}
	public byte			Tid()		{return Criteria_.Tid_between;}
	public boolean			Negated()	{return negate;} private final boolean negate;
	public void			Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public void			Val_as_obj_(Object v) {
		Object[] ary = (Object[])v;
		lhs = (Comparable)ary[0];
		rhs = (Comparable)ary[1];
	}
	public Comparable	Lhs()		{return lhs;} private Comparable lhs;
	public Comparable	Rhs()		{return rhs;} private Comparable rhs;
	public String To_str() {return String_.Concat_any("BETWEEN ", lhs, " AND ", rhs);}
	public boolean Matches(Object compObj) {
		Comparable comp = CompareAble_.as_(compObj);
		int lhsResult = CompareAble_.CompareComparables(lhs, comp);
		int rhsResult = CompareAble_.CompareComparables(rhs, comp);
		boolean rv = (lhsResult * rhsResult) != 1;
		return negate ? !rv : rv;
	}
	public static Criteria_between as_(Object obj) {return obj instanceof Criteria_between ? (Criteria_between)obj : null;}
}
