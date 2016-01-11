package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class IoUrlClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "ioPath";
	@Override public Class<?> UnderClass()					{return Io_url.class;}
	public Object DefaultValue()								{return Io_url_.Empty;}
	@Override public Object ParseOrNull(String raw)				{return Io_url_.new_any_(raw);}
	@Override public Object XtoDb(Object obj)					{return Io_url_.cast(obj).Raw();}
	@Override public String XtoUi(Object obj, String fmt)		{return Io_url_.cast(obj).Raw();}
	@Override public boolean MatchesClass(Object obj)				{return Io_url_.as_(obj) != null;}
	public boolean Eq(Object lhs, Object rhs) {try {return Io_url_.cast(lhs).Eq(Io_url_.cast(rhs));} catch (Exception e) {Err_.Noop(e); return false;}}
	public static final IoUrlClassXtn Instance =  new IoUrlClassXtn(); IoUrlClassXtn() {} // added to ClassXtnPool by default
}
