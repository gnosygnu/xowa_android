package gplx.xowa.htmls.core.wkrs.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*;
import gplx.xowa.htmls.core.wkrs.lnkis.anchs.*;
public class Xoh_lnki_dict_ {
	public static void Ns_encode(Xoh_hzip_bfr bfr, int ns_id) {bfr.Add_hzip_int(1, ns_id + 2);}
	public static void Ns_encode(Bry_bfr bfr, int ns_id) {
		gplx.core.encoders.Gfo_hzip_int_.Encode(1, bfr, ns_id + 2);
	}
	public static int Ns_decode(Bry_rdr rdr) {
		return rdr.Read_hzip_int(1) - 2;
	}
}
