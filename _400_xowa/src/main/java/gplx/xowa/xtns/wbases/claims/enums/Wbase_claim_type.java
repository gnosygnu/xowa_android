package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_type extends Wbase_enum_itm {	//#*inherit
	public Wbase_claim_type(byte tid, String key_str, String key_for_scrib) {super(tid, key_str);
		this.key_for_scrib = key_for_scrib;
	}
	public String Key_for_scrib()	{return key_for_scrib;} private final    String key_for_scrib;
}
