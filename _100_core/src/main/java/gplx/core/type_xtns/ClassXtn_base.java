package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public abstract class ClassXtn_base {
	public abstract Class<?> UnderClass();
	public abstract Object ParseOrNull(String raw);
	@gplx.Virtual public Object XtoDb(Object obj)						{return obj;}
	@gplx.Virtual public String XtoUi(Object obj, String fmt)			{return Object_.Xto_str_strict_or_null_mark(obj);}
	@gplx.Virtual public boolean MatchesClass(Object obj) {if (obj == null) throw Err_.new_null();
		return Type_adp_.Eq_typeSafe(obj, UnderClass());
	}
	@gplx.Virtual public int compareTo(Object lhs, Object rhs) {return CompareAble_.Compare_obj(lhs, rhs);}
}
