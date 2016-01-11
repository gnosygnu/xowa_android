package gplx.dbs; import gplx.*;
import gplx.core.criterias.*;
public class Db_crt_ {
	public static final Criteria Wildcard = Criteria_.All;
	public static Criteria_fld eq_(String key, Object val)				{return Criteria_fld.new_(key, Criteria_.eq_(val));}
	public static Criteria_fld eqn_(String key, Object val)				{return Criteria_fld.new_(key, Criteria_.eqn_(val));}
	public static Criteria_fld lt_(String key, Comparable val)			{return Criteria_fld.new_(key, Criteria_.lt_(val));}
	public static Criteria_fld lte_(String key, Comparable val)		{return Criteria_fld.new_(key, Criteria_.lte_(val));}
	public static Criteria_fld mt_(String key, Comparable val)			{return Criteria_fld.new_(key, Criteria_.mt_(val));}
	public static Criteria_fld mte_(String key, Comparable val)		{return Criteria_fld.new_(key, Criteria_.mte_(val));}
	public static Criteria_fld between_(String key, Comparable lhs, Comparable rhs) {return Criteria_fld.new_(key, Criteria_.between_(lhs, rhs));}
	public static Criteria_fld in_(String key, Object... vals)	{return Criteria_fld.new_(key, Criteria_.in_(vals));}
	public static Criteria_fld like_(String key, String pattern)		{return Criteria_fld.new_(key, Criteria_.like_(pattern));}
	public static Criteria_fld liken_(String key, String pattern)		{return Criteria_fld.new_(key, Criteria_.liken_(pattern));}
	public static Criteria_fld eq_(String key)							{return Criteria_fld.new_(key, Criteria_.eq_(null));}
	public static Criteria eq_many_(String... ary) {
		Criteria rv = null;
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			Criteria crt = Db_crt_.eq_(ary[i], null);
			rv = (i == 0)? crt : Criteria_.And(rv, crt);
		}
		return rv;
	}
	public static Criteria eq_many_wo_null(String... ary) {
		Criteria rv = null;
		int len = ary.length;
		int crt_idx = 0;
		for (int i = 0; i < len; i++) {
			String itm = ary[i]; if (itm == Dbmeta_fld_itm.Key_null) continue;
			Criteria crt = Db_crt_.eq_(itm, null);
			rv = (crt_idx == 0) ? crt : Criteria_.And(rv, crt);
			++crt_idx;
		}
		return rv;
	}
	public static Criteria eq_many_(KeyVal... array) {
		Criteria rv = null;
		for (int i = 0; i < array.length; i++) {
			KeyVal pair = array[i];
			Criteria crt = Db_crt_.eq_(pair.Key(), pair.Val());
			rv = (i == 0)? crt : Criteria_.And(rv, crt);
		}
		return rv;
	}
	public static Criteria_fld wrap_(String key, Criteria crt) {return Criteria_fld.new_(key, crt);}
}
