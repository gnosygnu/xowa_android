package gplx;
import gplx.core.stores.*;
public class Yn {//_20101127
	public static final String Y = "y", N = "n";
	public static boolean parse_by_char_or(String v, boolean or) {
		if		(String_.Eq(v, Y))	return true;
		else if	(String_.Eq(v, N))	return false;
		else						return or;
	}
	public static boolean parse_or_n_(String v) {return parse_or(v, false);}
	public static int parse_as_int(String v) {
		if		(v == null)				return Bool_.__int;
		else if (String_.Eq(v, "y"))	return Bool_.Y_int;
		else if (String_.Eq(v, "n"))	return Bool_.N_int;
		else							return Bool_.__int;
	}
	public static boolean parse_or(String v, boolean or) {
		int v_int = parse_as_int(v);
		switch (v_int) {
			case Bool_.N_int: return false;
			case Bool_.Y_int: return true;
			case Bool_.__int: return or;
			default: throw Err_.new_unhandled(v_int);
		}
	}
	public static boolean parse(String v) {
		int v_int = parse_as_int(v);
		if (v_int == Bool_.__int) Err_.new_unhandled(v);
		return v_int == Bool_.Y_int;
	}
	public static String To_str(boolean v) {return v ? "y" : "n";}
	public static String To_nullable_str(byte v) {
		switch (v) {
			case Bool_.Y_byte:		return "y";
			case Bool_.N_byte:		return "n";
			case Bool_.__byte:		return "?";
			default:				throw Err_.new_unhandled(v);
		}
	}
	public static byte To_nullable_byte(String v) {
		if (v != null && String_.Len(v) == 1) {
			char c = String_.CharAt(v, 0);
			switch (c) {
				case 'y':			return Bool_.Y_byte;
				case 'n':			return Bool_.N_byte;
				case '?':			return Bool_.__byte;
			}
		}
		throw Err_.new_unhandled(v);
	}
	public static boolean store_bool_or(SrlMgr mgr, String key, boolean or) {
		String v = mgr.SrlStrOr(key, "");
		return mgr.Type_rdr() ? parse_or(v, or) : or;
	}
	public static boolean coerce_(Object o) {String s = String_.as_(o); return s != null ? parse_or(s, false) : Bool_.cast(o);}
	public static boolean readOrFalse_(DataRdr rdr, String key) {return read_(rdr, key, false);}
	public static boolean readOrTrue_(DataRdr rdr, String key) {return read_(rdr, key, true);}
	static boolean read_(DataRdr rdr, String key, boolean or) {
		String v = rdr.ReadStrOr(key, null);
		return parse_or(v, or);
	}
}