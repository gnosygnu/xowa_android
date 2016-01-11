package gplx.xowa.parsers.hdrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.core.hzips.*;
public class Xop_hdr_tkn extends Xop_tkn_itm_base {
	public Xop_hdr_tkn(int bgn, int end, int hdr_level) {this.Tkn_ini_pos(false, bgn, end); this.hdr_level = hdr_level;}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_hdr;}
	public int Hdr_level() {return hdr_level;} public Xop_hdr_tkn Hdr_level_(int v) {hdr_level = v; return this;} private int hdr_level = -1;
	public int Hdr_bgn_manual() {return hdr_bgn_manual;} public Xop_hdr_tkn Hdr_bgn_manual_(int v) {hdr_bgn_manual = v; return this;} private int hdr_bgn_manual;
	public int Hdr_end_manual() {return hdr_end_manual;} public Xop_hdr_tkn Hdr_end_manual_(int v) {hdr_end_manual = v; return this;} private int hdr_end_manual;
	public boolean Hdr_html_first() {return hdr_html_first;} public Xop_hdr_tkn Hdr_html_first_y_() {hdr_html_first = true; return this;} private boolean hdr_html_first;
	public int Hdr_html_dupe_idx() {return hdr_html_dupe_idx;} private int hdr_html_dupe_idx;
	public byte[] Hdr_html_id() {return hdr_html_id;} public Xop_hdr_tkn Hdr_html_id_(byte[] v) {hdr_html_id = v; return this;} private byte[] hdr_html_id = Bry_.Empty;
	public byte[] Hdr_toc_text() {return hdr_toc_text;} public Xop_hdr_tkn Hdr_toc_text_(byte[] v) {hdr_toc_text = v; return this;} private byte[] hdr_toc_text;
	public int Hdr_html_dupe_idx_next() {
		hdr_html_dupe_idx = hdr_html_dupe_idx == 0 ? 2 : hdr_html_dupe_idx + 1;
		return hdr_html_dupe_idx;
	} 
}
