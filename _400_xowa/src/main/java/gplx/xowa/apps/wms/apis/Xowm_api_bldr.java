package gplx.xowa.apps.wms.apis; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.wms.*;
public class Xowm_api_bldr {
	public static void Bld_bgn(Bry_bfr bfr, byte[] wiki) {
		bfr.Add_str_a7("https://");
		bfr.Add(wiki);
		bfr.Add_str_a7("/w/api.php?");
	}
}
