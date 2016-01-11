package gplx;
public class Long_ {//_20101128
	public static final String Cls_val_name = "long";
	public static final Class<?> Cls_ref_type = Long.class; //#<>typeof(long)~Long.class
	public static final int Log10Ary_len = 21;
	public static long[] Log10Ary = new long[] 
  			{ 1, 10, 100, 1000, 10000
		, 100000, 1000000, 10000000, 100000000, 1000000000
  			, Long_.Pow(10, 10), Long_.Pow(10, 11), Long_.Pow(10, 12), Long_.Pow(10, 13), Long_.Pow(10, 14)
  			, Long_.Pow(10, 15), Long_.Pow(10, 16), Long_.Pow(10, 17), Long_.Pow(10, 18), Long_.Pow(10, 19)
  			, Long_.Max_value
  			};
	public static long parse(String raw)			{try {return Long.parseLong(raw);} catch(Exception e) {throw Err_.new_parse_exc(e, long.class, raw);}} //#<>long.Parse~Long.parseLong
	public static long cast(Object obj) {try {return (Long)obj;} catch(Exception e) {throw Err_.new_type_mismatch_w_exc(e, long.class, obj);}}//#<>(long)obj~(Long)obj
	public static long coerce_(Object v) {
		try {String s = String_.as_(v); return s == null ? Long_.cast(v) : Long_.parse(s);}
		catch (Exception e) {throw Err_.new_cast(e, long.class, v);}
	}
	public static String To_str(long v) {return Long.toString(v);}	//#<>v.toString()~Long.toString(v)
	public static String To_str_PadBgn(long v, int reqdPlaces) {return String_.Pad(To_str(v), reqdPlaces, "0", true);}	// ex: 1, 3 returns 001
	public static long parse_or(String raw, long or) {
		if (raw == null) return or;
		try {
			int rawLen = String_.Len(raw);
			if (raw == null || rawLen == 0) return or;
			long rv = 0, factor = 1; int tmp = 0;
			for (int i = rawLen; i > 0; i--) {
				tmp = Char_.To_int_or(String_.CharAt(raw, i - 1), Int_.Min_value);
				if (tmp == Int_.Min_value) return or;
				rv += (tmp * factor);
				factor *= 10;
			}
			return rv;
		} catch (Exception e) {Err_.Noop(e); return or;}
	}
	public static int Compare(long lhs, long rhs) {
		if		(lhs == rhs) 	return CompareAble_.Same;
		else if (lhs < rhs)		return CompareAble_.Less;
		else 					return CompareAble_.More;
	}
 		public static int FindIdx(long[] ary, long find_val) {
		int ary_len = ary.length;
		int adj = 1;
		int prv_pos = 0;
		int prv_len = ary_len;
		int cur_len = 0;
		int cur_idx = 0;
		long cur_val = 0;
		while (true) {
			cur_len = prv_len / 2;
			if (prv_len % 2 == 1) ++cur_len;
			cur_idx = prv_pos + (cur_len * adj);
			if		(cur_idx < 0)			cur_idx = 0;
			else if (cur_idx >= ary_len)	cur_idx = ary_len - 1;
			cur_val = ary[cur_idx];
			if		(find_val <	 cur_val) adj = -1;
			else if (find_val >	 cur_val) adj =	1;
			else if (find_val == cur_val) return cur_idx;
			if (cur_len == 1) {
				if (adj == -1 && cur_idx > 0)
					return --cur_idx;
				return cur_idx;
			}
			prv_len = cur_len;
			prv_pos = cur_idx;
		}
	}		
	public static int DigitCount(long v) {
		int adj = Int_.Base1;
		if (v < 0) {
			if (v == Long_.Min_value) return 19;	// NOTE: Long_.Min_value * -1 = Long_.Min_value
			v *= -1;
			++adj;
		}
		return FindIdx(Log10Ary, v) + adj;
	}
	public static long Pow(int val, int exp) {
		long rv = val;
		for (int i = 1; i < exp; i++)
			rv *= val;
		return rv;
	}
	public static long Int_merge(int hi, int lo)	{return (long)hi << 32 | (lo & 0xFFFFFFFFL);}
	public static int  Int_split_lo(long v)			{return (int)(v);}
	public static int  Int_split_hi(long v)			{return (int)(v >> 32);}
	public static final long
		Min_value	= Long.MIN_VALUE		//#<>long.MinValue~Long.MIN_VALUE
	,	Max_value	= Long.MAX_VALUE		//#<>long.MaxValue~Long.MAX_VALUE
	;
}
/* alternate for Int_merge; does not work in java
		public static long MergeInts(int lo, int hi)	{return (uint)(hi << 32) | (lo & 0xffffffff);}
		public static int  SplitLo(long v)				{return (int)(((ulong)v & 0x00000000ffffffff));}
		public static int  SplitHi(long v)				{return (int)(((ulong)v & 0xffffffff00000000)) >> 32;}
*/
