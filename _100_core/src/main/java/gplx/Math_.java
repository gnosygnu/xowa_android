package gplx;
public class Math_ {
	public static double Pow(double val, double exponent) {return java.lang.Math.pow(val, exponent);}//#<>System.Math.Pow~java.lang.Math.pow
	public static double Pi = java.lang.Math.PI; //#<>System.Math.PI~java.lang.Math.PI
	public static double E = java.lang.Math.E; //#<>System.Math.E~java.lang.Math.E
	public static double Ceil(double v) {return java.lang.Math.ceil(v);}//#<>System.Math.Ceiling~java.lang.Math.ceil
	public static double Floor(double v) {return java.lang.Math.floor(v);}//#<>System.Math.Floor~java.lang.Math.floor
	public static double Round(double v, int places) {
		//#{Round
		return java.math.BigDecimal.valueOf(v).setScale(places, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		//#}
	}
	public static int Trunc(double v) {return (int)v;}
	public static double Exp(double v) {return java.lang.Math.exp(v);}//#<>System.Math.Exp~java.lang.Math.exp
	public static double Log(double v) {return java.lang.Math.log(v);}//#<>System.Math.Log~java.lang.Math.log
	public static double Sin(double v) {return java.lang.Math.sin(v);}//#<>System.Math.Sin~java.lang.Math.sin
	public static double Cos(double v) {return java.lang.Math.cos(v);}//#<>System.Math.Cos~java.lang.Math.cos
	public static double Tan(double v) {return java.lang.Math.tan(v);}//#<>System.Math.Tan~java.lang.Math.tan
	public static double Asin(double v) {return java.lang.Math.asin(v);}//#<>System.Math.Asin~java.lang.Math.asin
	public static double Acos(double v) {return java.lang.Math.acos(v);}//#<>System.Math.Acos~java.lang.Math.acos
	public static double Atan(double v) {return java.lang.Math.atan(v);}//#<>System.Math.Atan~java.lang.Math.atan
	public static double Sqrt(double v) {return java.lang.Math.sqrt(v);}//#<>System.Math.Sqrt~java.lang.Math.sqrt
	public static int	Abs(int val)	{return val > 0 ? val : val * -1;}
	public static long	Abs(long val)	{return val > 0 ? val : val * -1;}
	public static float	Abs(float val)	{return val > 0 ? val : val * -1;}
	public static double Abs_double(double val)	{return val > 0 ? val : val * -1;}
	public static int	Log10(int val) {
		if (val <= 0) return Int_.Min_value;
		int rv = -1, baseVal = 10;
		while (val != 0) {
			val = (val / baseVal);
			rv++;
		}
		return rv;
	}
	public static int Div_safe_as_int(int val, int divisor) {return divisor == 0 ? 0 : val / divisor;}
	public static long Div_safe_as_long(long val, long divisor) {return divisor == 0 ? 0 : val / divisor;}
	public static double Div_safe_as_double(double val, double divisor) {return divisor == 0 ? 0 : val / divisor;}
	public static int Min(int val0, int val1) {return val0 < val1 ? val0 : val1;}
	public static int Max(int val0, int val1) {return val0 > val1 ? val0 : val1;}
	public static int[] Base2Ary(int v, int max) {
		int[] idxs = new int[32];
		int cur = v, mult = max, idx = 0;
		while (mult > 0) {
			int tmp = cur / mult;
			if (tmp >= 1) {
				idxs[idx++] = mult;
				cur -= mult;
			}
			mult /= 2;
		}
		int[] rv = new int[idx];
		for (int i = 0; i < idx; i++)
			rv[i] = idxs[idx - i - 1];
		return rv;
	}
}