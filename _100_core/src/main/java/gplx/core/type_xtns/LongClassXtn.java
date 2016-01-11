package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class LongClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "long";
	@Override public Class<?> UnderClass()					{return long.class;}
	public Object DefaultValue()								{return 0;}
	public boolean Eq(Object lhs, Object rhs) {try {return Long_.cast(lhs) == Long_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{return raw == null ? (Object)null : Long_.parse(raw);}
	@Override public Object XtoDb(Object obj)					{return Long_.cast(obj);}						// necessary for enums
	public static final LongClassXtn Instance = new LongClassXtn(); LongClassXtn() {} // added to ClassXtnPool by default
}