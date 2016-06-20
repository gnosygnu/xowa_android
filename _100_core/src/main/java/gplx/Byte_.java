package gplx;
public class Byte_ {
	public static final String Cls_val_name = "byte";
	public static final    Class<?> Cls_ref_type = Byte.class; //#<>typeof(byte)~Byte.class
	public static final byte
	  Zero			= 0
	, Min_value		= Byte.MIN_VALUE		//#<>byte.MinValue~Byte.MIN_VALUE
	, Max_value_127	= 127
	, Val_128		= -128				//#<>= 128~= -128
	, Val_255		= -1				//#<>= 255~= -1
	; 
	public static byte		cast(Object o)		{try {return (Byte)o;} catch (Exception e) {throw Err_.new_type_mismatch_w_exc(e, byte.class, o);}}//#<>(byte)o~(Byte)o
	public static byte		parse(String raw)	{return Byte.parseByte(raw);}	//#<>byte.Parse~Byte.parseByte
	public static byte		parse_or(String raw, byte or) {
		if (raw == null) return or;
		try {return parse(raw);}
		catch (Exception e) {Err_.Noop(e); return or;}
	}
	public static byte		By_int(int v)	{return v > 127 ? (byte)(v - 256) : (byte)v;} // PERF?: (byte)(v & 0xff)
	public static int		To_int(byte v)	{return v < 0 ? (int)v + 256 : v;}
	public static String	To_str(byte v)	{return new Byte(v).toString();} //#<>v.toString~new Byte(v).toString
	public static byte[]	To_bry(byte v)	{return new byte[] {v};}
	public static boolean In(byte v, byte... ary) {
		for (byte itm : ary)
			if (v == itm) return true;
		return false;
	}
	public static int Compare(byte lhs, byte rhs) {
		if		(lhs == rhs) 	return CompareAble_.Same;
		else if (lhs < rhs)		return CompareAble_.Less;
		else 					return CompareAble_.More;
	}
	public static byte[] Ary(byte... ary) {return ary;}
	public static byte[] Ary_by_ints(int... ary) {
		int ary_len = ary.length;
		byte[] rv = new byte[ary_len];
		for (int i = 0; i < ary_len; i++)
			rv[i] = By_int(ary[i]);
		return rv;
	}
}
