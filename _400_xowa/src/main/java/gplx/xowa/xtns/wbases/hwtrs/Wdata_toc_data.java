package gplx.xowa.xtns.wbases.hwtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.encoders.*;
class Wdata_toc_data {
	private final    Wdata_fmtr__toc_div fmtr_toc;
	private final    Gfo_url_encoder href_encoder;
	private final    Bry_fmtr text_fmtr = Bry_fmtr.new_("~{orig} <sup><small>(~{len})</small></sup>", "orig", "len");
	private final    Bry_bfr tmp_bfr = Bry_bfr_.New_w_size(8);
	public Wdata_toc_data(Wdata_fmtr__toc_div fmtr_toc, Gfo_url_encoder href_encoder) {this.fmtr_toc = fmtr_toc; this.href_encoder = href_encoder;}
	public Wdata_toc_data Make(int itms_len) {
		this.text = itms_len_enable ? text_fmtr.Bld_bry_many(tmp_bfr, orig, itms_len) : orig;
		this.href = href_encoder.Encode(orig);
		fmtr_toc.Add(this);
		return this;
	}
	public Wdata_toc_data Itms_len_enable_n_() {itms_len_enable = false; return this;} private boolean itms_len_enable = true;
	public byte[] Orig() {return orig;} public void Orig_(byte[] v) {orig = v;} private byte[] orig;
	public byte[] Href() {return href;} private byte[] href;
	public byte[] Text() {return text;} private byte[] text;
}
