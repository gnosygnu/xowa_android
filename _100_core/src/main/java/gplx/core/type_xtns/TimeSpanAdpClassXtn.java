package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class TimeSpanAdpClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "timeSpan";
	@Override public Class<?> UnderClass()					{return TimeSpanAdp.class;}
	public Object DefaultValue()								{return TimeSpanAdp_.Zero;}
	@Override public Object ParseOrNull(String raw)				{return TimeSpanAdp_.parse(raw);}
	@Override public Object XtoDb(Object obj)					{return TimeSpanAdp_.cast(obj).TotalSecs();}
	@Override public String XtoUi(Object obj, String fmt)		{return TimeSpanAdp_.cast(obj).To_str(fmt);}
	public boolean Eq(Object lhs, Object rhs) {try {return TimeSpanAdp_.cast(lhs).Eq(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	public static final TimeSpanAdpClassXtn Instance =  new TimeSpanAdpClassXtn(); TimeSpanAdpClassXtn() {} // added to ClassXtnPool by default
}