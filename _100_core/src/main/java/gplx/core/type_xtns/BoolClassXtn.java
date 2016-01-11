package gplx.core.type_xtns; import gplx.*; import gplx.core.*;
public class BoolClassXtn extends ClassXtn_base implements ClassXtn {
	public static final String Key_const = "bo" + "ol";
	public String Key() {return Key_const;}
	@Override public Class<?> UnderClass()					{return boolean.class;}
	public Object DefaultValue()								{return false;}
	public boolean Eq(Object lhs, Object rhs) {try {return Bool_.cast(lhs) == Bool_.cast(rhs);} catch (Exception e) {Err_.Noop(e); return false;}}
	@Override public Object ParseOrNull(String raw)				{
		if	(	String_.Eq(raw, "true")
			||	String_.Eq(raw, "True")		// needed for Store_Wtr() {boolVal.toString();}
			||	String_.Eq(raw, "1")		// needed for db; gplx field for boolean is int; need simple way to convert from dbInt to langBool
			)		
			return true;
		else if 
			(	String_.Eq(raw, "false")
			||	String_.Eq(raw, "False")
			||	String_.Eq(raw, "0")
			) 
			return false;
		throw Err_.new_parse_type(boolean.class, raw);
	}
	@Override public Object XtoDb(Object obj)					{return obj;}
	public static final BoolClassXtn Instance =  new BoolClassXtn(); BoolClassXtn() {} // added to ClassXtnPool by default
}