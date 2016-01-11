package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public interface ClassXtn {
	String Key();
	Class<?> UnderClass();
	Object DefaultValue();
	Object ParseOrNull(String raw);
	Object XtoDb(Object obj);
	String XtoUi(Object obj, String fmt);
	boolean MatchesClass(Object obj);
	boolean Eq(Object lhs, Object rhs);
	int compareTo(Object lhs, Object rhs);
}
