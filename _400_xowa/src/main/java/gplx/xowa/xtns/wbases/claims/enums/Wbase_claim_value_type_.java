package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_value_type_ {
	public static final byte	// SERIALIZED.MW
	  Tid__novalue								=  0
	, Tid__value								=  1
	, Tid__somevalue							=  2
	;
	private static final int Ary__len = 3;
	private static final    Wbase_claim_enum[] Ary = new Wbase_claim_enum[Ary__len];
	private static final    Hash_adp_bry hash_by_bry = Hash_adp_bry.cs();
	public static final    Wbase_claim_enum
	  Itm__novalue					= New(Tid__novalue			, "novalue")
	, Itm__value					= New(Tid__value			, "value")
	, Itm__somevalue				= New(Tid__somevalue		, "somevalue")
	;
	private static Wbase_claim_enum New(byte tid, String key) {
		Wbase_claim_enum rv = new Wbase_claim_enum(tid, key);
		hash_by_bry.Add(rv.Key_bry(), rv);
		Ary[tid] = rv;
		return rv;
	}

	public static String To_str_or_fail(byte tid) {return Ary[tid].Key_str();}
	public static byte[] To_bry_or_fail(byte tid) {return Ary[tid].Key_bry();}
	public static byte To_tid_or_fail(byte[] bry) {return ((Wbase_claim_enum)hash_by_bry.Get_by_or_fail(bry)).Tid();}
}