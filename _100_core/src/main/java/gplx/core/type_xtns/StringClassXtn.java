package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class StringClassXtn extends ClassXtn_base implements ClassXtn {
	public static final String Key_const = "str"+"ing";
	public String Key() {return Key_const;}
	@Override public Class<?> UnderClass()					{return String.class;}
	public Object DefaultValue()								{return "";}
	@Override public Object ParseOrNull(String raw)				{return raw;}
	@Override public String XtoUi(Object obj, String fmt)		{return String_.as_(obj);}
	public boolean Eq(Object lhs, Object rhs) {try {return String_.Eq(String_.cast(lhs), String_.cast(rhs));} catch (Exception e) {Err_.Noop(e); return false;}}
	public static final StringClassXtn Instance =  new StringClassXtn(); StringClassXtn() {} // added to ClassXtnPool by default
}