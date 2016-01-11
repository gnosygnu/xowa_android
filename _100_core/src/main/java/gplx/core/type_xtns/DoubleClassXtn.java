package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class DoubleClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "double";
	@Override public Class<?> UnderClass()					{return double.class;}
	public Object DefaultValue()								{return 0;}
	public boolean Eq(Object lhs, Object rhs) {try {return Double_.cast(lhs) == Double_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{return Double_.parse(raw);}
	public static final DoubleClassXtn Instance =  new DoubleClassXtn(); DoubleClassXtn() {} // added to ClassXtnPool by default
}