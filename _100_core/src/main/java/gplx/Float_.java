package gplx;
public class Float_ {
	public static final String Cls_val_name = "float";
	public static final Class<?> Cls_ref_type = Float.class;//#<>typeof(float)~Float.class
	public static final float NaN = Float.NaN;;					//#<>float.NaN~Float.NaN;
	public static boolean IsNaN(float v) {return Float.isNaN(v);}		//#<>float.IsNaN~Float.isNaN
	public static float cast(Object obj)	{try {return (Float)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, float.class, obj);}}//#<>(float)obj~(Float)obj
	public static float parse(String raw)	{try {return Float.parseFloat(raw);} catch(Exception exc) {throw Err_.new_parse_exc(exc, float.class, raw);}} //#<>float.Parse~Float.parseFloat
	public static int Compare(float lhs, float rhs) {
		if		( lhs == rhs)	return CompareAble_.Same;
		else if ( lhs <  rhs)	return CompareAble_.Less;
		else	/*lhs >  rhs*/	return CompareAble_.More;
	}
	public static String To_str(float v) {
		//#{To_str
		int v_int = (int)v;
		return v - v_int == 0 ? Int_.To_str(v_int) : Float.toString(v);
		//#}
	}
	public static float Div(int val, int divisor) {return (float)val / (float)divisor;}
	public static float Div(long val, long divisor) {return (float)val / (float)divisor;}
	public static int RoundUp(float val) {
		int rv = (int)val;
		return (rv == val) ? rv : rv + 1;
	}
}
