package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class ObjectClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "Object";
	@Override public Class<?> UnderClass()					{return Object.class;}
	public Object DefaultValue()								{return null;}
	@Override public Object ParseOrNull(String raw)				{throw Err_.new_unimplemented();}
	@Override public Object XtoDb(Object obj)					{throw Err_.new_unimplemented();}
	public boolean Eq(Object lhs, Object rhs) {return lhs == rhs;}
	public static final ObjectClassXtn Instance =  new ObjectClassXtn(); ObjectClassXtn() {} // added to ClassXtnPool by default
}
