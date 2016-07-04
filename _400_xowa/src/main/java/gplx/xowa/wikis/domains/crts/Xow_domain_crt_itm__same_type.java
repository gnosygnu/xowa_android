package gplx.xowa.wikis.domains.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
class Xow_domain_crt_itm__same_type implements Xow_domain_crt_itm {
	public boolean Matches(Xow_domain_itm cur, Xow_domain_itm comp) {return cur.Domain_type_id() == comp.Domain_type_id();}
        public static final    Xow_domain_crt_itm__same_type Instance = new Xow_domain_crt_itm__same_type(); Xow_domain_crt_itm__same_type() {}
}
