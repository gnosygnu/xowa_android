package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class FloatClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "float";
	@Override public Class<?> UnderClass()					{return float.class;}
	public Object DefaultValue()								{return 0;}
	public boolean Eq(Object lhs, Object rhs) {try {return Float_.cast(lhs) == Float_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{return Float_.parse(raw);}
	public static final FloatClassXtn Instance =  new FloatClassXtn(); FloatClassXtn() {} // added to ClassXtnPool by default
}