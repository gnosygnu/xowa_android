package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class IntClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "int";
	@Override public Class<?> UnderClass()					{return Integer.class;}	//#<>typeof(int)~Integer.class
	public Object DefaultValue()								{return 0;}
	@Override public Object ParseOrNull(String raw)				{return raw == null ? (Object)null : Int_.parse(raw);}
	public boolean Eq(Object lhs, Object rhs) {try {return Int_.cast(lhs) == Int_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object XtoDb(Object obj)					{return Int_.cast(obj);}	// necessary for enums

	public static final IntClassXtn Instance = new IntClassXtn(); IntClassXtn() {} // added to ClassXtnPool by default
}
