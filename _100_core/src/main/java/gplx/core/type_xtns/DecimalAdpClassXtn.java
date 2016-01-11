package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class DecimalAdpClassXtn extends ClassXtn_base implements ClassXtn {
	public String Key() {return Key_const;}						public static final String Key_const = "decimal";	// current dsv files reference "decimal"
	@Override public Class<?> UnderClass()					{return Decimal_adp.class;}
	public Object DefaultValue()								{return 0;}
	public boolean Eq(Object lhs, Object rhs) {try {return Decimal_adp_.cast(lhs).Eq(Decimal_adp_.cast(rhs));} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{return Decimal_adp_.parse(raw);}
	@Override public String XtoUi(Object obj, String fmt)		{return Decimal_adp_.cast(obj).To_str();}
	public static final DecimalAdpClassXtn Instance =  new DecimalAdpClassXtn(); DecimalAdpClassXtn() {} // added to ClassXtnPool by default
}
