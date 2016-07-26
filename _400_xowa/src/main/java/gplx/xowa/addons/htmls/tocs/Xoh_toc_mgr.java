package gplx.xowa.addons.htmls.tocs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.htmls.*;
import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.core.wkrs.tocs.*;
public class Xoh_toc_mgr {
	private final    Ordered_hash itms = Ordered_hash_.New_bry();
	private final    Xoh_toc_wkr__lvl lvl_wkr = new Xoh_toc_wkr__lvl();
	private final    Xoh_toc_wkr__txt txt_wkr = new Xoh_toc_wkr__txt();
	private final    Xoh_toc_htmlr htmlr = new Xoh_toc_htmlr();
	public boolean Exists() {return exists && Enabled;} private boolean exists;
	public void Exists_y_() {exists = true;}
	public int Toc_bgn() {return toc_bgn;} private int toc_bgn;
	public void Toc_bgn_(int v) {this.toc_bgn = v;}
	public void Clear() {
		this.exists = false;
		itms.Clear();
		lvl_wkr.Clear();
		txt_wkr.Clear();
		htmlr.Clear();
		toc_bgn = -1;
	}
	public void Init(byte[] toc_title, byte[] page_name) {
		this.Clear();
		htmlr.Init(toc_title);
		txt_wkr.Init(page_name);
	}
	public Xoh_toc_itm Add(int hdr_num, byte[] hdr_txt) {
		Xoh_toc_itm itm = new Xoh_toc_itm();
		lvl_wkr.Calc_level(itm, hdr_num);
		txt_wkr.Calc_anch_text(itm, hdr_txt);
		itms.Add(itm.Anch(), itm);
		return itm;
	}
	public void To_html(Bry_bfr rv, Xoh_wtr_ctx hctx, boolean toc_mode_is_pgbnr) {htmlr.To_html(rv, hctx, itms, toc_mode_is_pgbnr);}
	public byte[] Test__to_html() {
		Bry_bfr bfr = Bry_bfr_.New();
		htmlr.Test__to_html(bfr, itms);
		return bfr.To_bry_and_clear();
	}
	public static boolean Enabled = true;	// TEST
}