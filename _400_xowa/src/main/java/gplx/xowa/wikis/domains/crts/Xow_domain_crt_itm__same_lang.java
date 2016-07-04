package gplx.xowa.wikis.domains.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
class Xow_domain_crt_itm__same_lang implements Xow_domain_crt_itm {
	public boolean Matches(Xow_domain_itm cur, Xow_domain_itm comp) {return Bry_.Eq(cur.Lang_orig_key(), comp.Lang_orig_key());}
        public static final    Xow_domain_crt_itm__same_lang Instance = new Xow_domain_crt_itm__same_lang(); Xow_domain_crt_itm__same_lang() {}
}
