package gplx;
import gplx.langs.gfs.*;
public class Bool_ implements Gfo_invk {
	public static final String Cls_val_name = "boolean";
	public static final    Class<?> Cls_ref_type = Boolean.class; //#<>typeof(boolean)~Boolean.class
	public static final boolean		N		= false	, Y			= true;
	public static final byte		N_byte	= 0		, Y_byte	= 1		, __byte = 127;
	public static final int		N_int	= 0		, Y_int		= 1		, __int  =  -1;
	public static final    byte[] N_bry = new byte[] {Byte_ascii.Ltr_n}, Y_bry = new byte[] {Byte_ascii.Ltr_y};
	public static final String		True_str = "true", False_str = "false";
	public static final    byte[] True_bry = Bry_.new_a7(True_str), False_bry = Bry_.new_a7(False_str);
	public static boolean		cast(Object obj)				{try {return (Boolean)obj;} catch (Exception e) {throw Err_.new_type_mismatch_w_exc(e, boolean.class, obj);}}//#<>(boolean)obj~(Boolean)obj
	public static boolean		cast_or(Object obj, boolean v)		{try {return (Boolean)obj;} catch (Exception e) {Err_.Noop(e); return v;}}//#<>(boolean)obj~(Boolean)obj
	public static boolean		parse(String raw) {
		if		(	String_.Eq(raw, True_str)
				||	String_.Eq(raw, "True")	// needed for Store_Wtr(){boolVal.toString();}
				)
			return true;
		else if (	String_.Eq(raw, "false")
				||	String_.Eq(raw, False_str)
			)
			return false;
		throw Err_.new_parse_type(boolean.class, raw);
	}
	public static int Compare(boolean lhs, boolean rhs) {
		if		( lhs ==  rhs)	return CompareAble_.Same;
		else if (!lhs &&  rhs)	return CompareAble_.Less;
		else	/*lhs && !rhs*/ return CompareAble_.More;
	}
	public static boolean		By_int(int v)			{return v == Y_int;}
	public static int		To_int(boolean v)			{return v ? Y_int		: N_int;}
	public static byte		To_byte(boolean v)			{return v ? Y_byte		: N_byte;}
	public static String	To_str_lower(boolean v)	{return v ? True_str	: False_str;}

	public static final    Bool_ Gfs = new Bool_();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_to_str))	{
			boolean v = m.ReadBool(GfsCore_.Arg_primitive);
			String fmt = m.ReadStrOr("fmt", null);
			if		(fmt == null)				return v ? "true" : "false";
			else if (String_.Eq(fmt, "yn"))		return v ? "y" : "n";
			else if (String_.Eq(fmt, "yes_no"))	return v ? "yes" : "no";
			else								return v ? "true" : "false";
		}
		else return Gfo_invk_.Rv_unhandled;
	}	public static final    String Invk_to_str = "to_str";
}
