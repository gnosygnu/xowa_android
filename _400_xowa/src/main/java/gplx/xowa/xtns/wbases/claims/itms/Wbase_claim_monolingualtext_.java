package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_monolingualtext_ {
	public static final byte
	  Tid__text									= 0
	, Tid__language								= 1
	;
	private static final int Ary__len = 2;
	private static final    Wbase_claim_enum[] Ary = new Wbase_claim_enum[Ary__len];
	private static final    Hash_adp_bry hash_by_bry = Hash_adp_bry.cs();
	public static final    Wbase_claim_enum
	  Itm__text						= New(Tid__text			, "text")
	, Itm__language					= New(Tid__language		, "language")
	;
	private static Wbase_claim_enum New(byte tid, String key) {
		Wbase_claim_enum rv = new Wbase_claim_enum(tid, key);
		hash_by_bry.Add(rv.Key_bry(), rv);
		Ary[tid] = rv;
		return rv;
	}

	public static String To_str_or_invalid(byte tid) {return Ary[tid].Key_str();}
	public static byte[] To_bry_or_fail(byte tid) {return Ary[tid].Key_bry();}
	public static byte To_tid_or_invalid(byte[] page_url, byte[] key) {return Wbase_claim_enum_.To_tid_or_invalid(hash_by_bry, page_url, key);}
}
