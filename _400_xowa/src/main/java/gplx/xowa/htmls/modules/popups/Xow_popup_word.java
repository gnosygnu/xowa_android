package gplx.xowa.htmls.modules.popups; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.modules.*;
import gplx.xowa.parsers.*;
public class Xow_popup_word {
	public Xow_popup_word(int tid, int bfr_bgn, int idx, int bgn, int end, Xop_tkn_itm tkn) {this.tid = tid; this.bfr_bgn = bfr_bgn; this.idx = idx; this.bgn = bgn; this.end = end; this.tkn = tkn;}
	public int Tid() {return tid;} private int tid;
	public int Bfr_bgn() {return bfr_bgn;} private int bfr_bgn;
	public int Bfr_end() {return bfr_bgn + this.Len();}
	public int Idx() {return idx;} private int idx;
	public int Bgn() {return bgn;} private int bgn;
	public int End() {return end;} private int end;
	public int Len() {return end - bgn;}
	public Xop_tkn_itm Tkn() {return tkn;} private Xop_tkn_itm tkn;
}
