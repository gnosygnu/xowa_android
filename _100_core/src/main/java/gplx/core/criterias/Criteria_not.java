package gplx.core.criterias; import gplx.*; import gplx.core.*;
public class Criteria_not implements Criteria {
	public Criteria_not(Criteria v) {this.criteria = v;}
	public byte				Tid() {return Criteria_.Tid_not;}
	public boolean				Matches(Object obj) {return !criteria.Matches(obj);}
	public void				Val_from_args(Hash_adp args) {criteria.Val_from_args(args);}
	public void				Val_as_obj_(Object v) {criteria.Val_as_obj_(v);}
	public String			To_str() {return String_.Concat_any(" NOT ", criteria.To_str());}
	public Criteria			Crt() {return criteria;} private final Criteria criteria;
}
