package gplx.xowa.bldrs.wtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.ios.*;
import gplx.xowa.wikis.nss.*;
public class Xob_tmp_wtr {
	Xob_tmp_wtr(Xow_ns ns_itm, Io_url_gen url_gen, int fil_max) {
		this.ns_itm = ns_itm;
		this.url_gen = url_gen;
		this.fil_max = fil_max; 
		bfr = Bry_bfr.reset_(fil_max);
	}	int fil_max;
	public Bry_bfr Bfr() {return bfr;} Bry_bfr bfr;
	public Io_url_gen Url_gen() {return url_gen;} Io_url_gen url_gen;
	public void Clear() {bfr.ClearAndReset();}
	public boolean FlushNeeded(int writeLen) {return bfr.Len() + writeLen > fil_max;} //int bfr_len;
	public Xow_ns Ns_itm() {return ns_itm;} private Xow_ns ns_itm;
	public void Flush(Gfo_usr_dlg usr_dlg) {
		if (bfr.Len() == 0) return;		// nothing to flush
		Io_url url = url_gen.Nxt_url();
		Io_mgr.Instance.AppendFilBfr(url, bfr);
	}
	public void Rls() {bfr.Rls();}
	public static Xob_tmp_wtr new_(Xow_ns ns_itm, Io_url_gen url_gen, int fil_max)	{return new Xob_tmp_wtr(ns_itm, url_gen, fil_max);}
	public static Xob_tmp_wtr new_wo_ns_(Io_url_gen url_gen, int fil_max)			{return new Xob_tmp_wtr(null, url_gen, fil_max);}
}
