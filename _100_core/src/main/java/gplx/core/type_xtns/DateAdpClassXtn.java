package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class DateAdpClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "datetime";
	public boolean Eq(Object lhs, Object rhs) {try {return DateAdp_.cast(lhs).Eq(DateAdp_.cast(rhs));} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Class<?> UnderClass()					{return DateAdp.class;}
	public Object DefaultValue()								{return DateAdp_.MinValue;}
	@Override public Object ParseOrNull(String raw)				{return DateAdp_.parse_gplx(raw);}
	@Override public Object XtoDb(Object obj)					{return DateAdp_.cast(obj).XtoStr_gplx_long();}
	@Override public String XtoUi(Object obj, String fmt)		{return DateAdp_.cast(obj).XtoStr_fmt(fmt);}
	public static final DateAdpClassXtn Instance =  new DateAdpClassXtn(); DateAdpClassXtn() {} // added to ClassXtnPool by default
}
