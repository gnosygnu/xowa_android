package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class ByteClassXtn extends ClassXtn_base implements ClassXtn {
	public static final String Key_const = "byte";
	public String Key() {return Key_const;}
	@Override public Class<?> UnderClass()					{return byte.class;}
	public Object DefaultValue()								{return 0;}
	public boolean Eq(Object lhs, Object rhs) {try {return Byte_.cast(lhs) == Byte_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{return raw == null ? (Object)null : Byte_.parse(raw);}
	@Override public Object XtoDb(Object obj)					{return Byte_.cast(obj);}
	public static final ByteClassXtn Instance =  new ByteClassXtn(); ByteClassXtn() {} // added to ClassXtnPool by default
}