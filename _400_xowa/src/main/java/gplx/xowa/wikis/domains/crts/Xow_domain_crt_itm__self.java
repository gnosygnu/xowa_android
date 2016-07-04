package gplx.xowa.wikis.domains.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
class Xow_domain_crt_itm__self implements Xow_domain_crt_itm {
	public boolean Matches(Xow_domain_itm cur, Xow_domain_itm comp) {return Bry_.Eq(cur.Domain_bry(), comp.Domain_bry());}
        public static final    Xow_domain_crt_itm__self Instance = new Xow_domain_crt_itm__self(); Xow_domain_crt_itm__self() {}
}
