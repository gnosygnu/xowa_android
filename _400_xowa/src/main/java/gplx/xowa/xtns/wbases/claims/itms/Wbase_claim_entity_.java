package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_entity_ {
	public static final byte
	  Tid__entity_type							= 0
	, Tid__numeric_id							= 1
	;
	private static final    Hash_adp_bry hash_by_bry = Hash_adp_bry.cs();
	public static final    Wbase_claim_enum
	  Itm__entity_type				= New(Tid__entity_type		, "entity-type")
	, Itm__numeric_id				= New(Tid__numeric_id		, "numeric-id")
	;
	private static Wbase_claim_enum New(byte tid, String key) {
		Wbase_claim_enum rv = new Wbase_claim_enum(tid, key);
		hash_by_bry.Add(rv.Key_bry(), rv);
		return rv;
	}
	public static byte To_tid_or_invalid(byte[] page_url, byte[] key) {return Wbase_claim_enum_.To_tid_or_invalid(hash_by_bry, page_url, key);}
}
