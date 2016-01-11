package gplx.core.ios; import gplx.*; import gplx.core.*;
public class Io_sort_fil_basic implements Io_sort_cmd { // 123|bgn|end|1
	public Io_sort_fil_basic(Gfo_usr_dlg usr_dlg, Io_url_gen url_gen, int flush_len) {this.usr_dlg = usr_dlg; this.url_gen = url_gen; this.flush_len = flush_len;} Io_url_gen url_gen; Bry_bfr bfr = Bry_bfr.new_(); int flush_len; Gfo_usr_dlg usr_dlg;
	public void Sort_bgn() {}
	public void Sort_do(Io_line_rdr rdr) {
		int bgn = rdr.Itm_pos_bgn(), end = rdr.Itm_pos_end();
		if (bfr.Len() + (end - bgn) > flush_len) Flush();
		bfr.Add_mid(rdr.Bfr(), bgn, end);
	}
	public void Sort_end() {
		Flush();
		bfr.Rls();
	}
	private void Flush() {
		Io_url url = url_gen.Nxt_url();
		usr_dlg.Prog_one(GRP_KEY, "make", "making: ~{0}", url.NameAndExt());
		Io_mgr.Instance.SaveFilBry(url, bfr.Bfr(), bfr.Len());
		bfr.Clear();
	}
	static final String GRP_KEY = "xowa.bldr.io_sort";
}
