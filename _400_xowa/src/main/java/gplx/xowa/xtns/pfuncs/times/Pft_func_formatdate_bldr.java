package gplx.xowa.xtns.pfuncs.times; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*;
public class Pft_func_formatdate_bldr {
	public int Idx_cur() {return idx_cur;} private int idx_cur;
	public Pft_func_formatdate_bldr Idx_nxt_(int v) {idx_nxt = v; return this;} private int idx_nxt;
	public Pft_fmt_itm[] Fmt_itms() {return fmt_itms;} private Pft_fmt_itm[] fmt_itms;
	public void Format(Bry_bfr bfr, Xowe_wiki wiki, Xol_lang_itm lang, DateAdp date, Pft_fmt_itm fmt_itm) {
		synchronized (this) {	// LOCK:static-obj;Pft_func_formatdate.Date_bldr(); DATE:2016-07-07
			fmt_itm.Fmt(bfr, wiki, lang, date, this);
		}
	}
	public void Format(Bry_bfr bfr, Xowe_wiki wiki, Xol_lang_itm lang, DateAdp date, Pft_fmt_itm[] fmt_itms) {
		synchronized (this) {	// LOCK:static-obj;Pft_func_formatdate.Date_bldr(); DATE:2016-07-07
			this.fmt_itms = fmt_itms;
			int len = fmt_itms.length;
			idx_cur = 0; idx_nxt = -1;
			Pft_fmt_itm chained_fmt = null;
			while (idx_cur < len) {
				Pft_fmt_itm fmt_itm = fmt_itms[idx_cur];
				if (fmt_itm.TypeId() == Pft_fmt_itm_.Tid_hebrew_numeral)
					chained_fmt = fmt_itm;
				else {
					if (chained_fmt != null) {
						Bry_bfr tmp_bfr = Xoa_app_.Utl__bfr_mkr().Get_b128();
						synchronized (tmp_bfr) {
							fmt_itm.Fmt(tmp_bfr, wiki, lang, date, this);
							chained_fmt.Fmt(tmp_bfr, wiki, lang, date, this);
							bfr.Add(tmp_bfr.To_bry_and_rls());
							chained_fmt = null;
						}
					}
					else
						fmt_itm.Fmt(bfr, wiki, lang, date, this);
				}
				if (idx_nxt == -1)
					++idx_cur;
				else {
					idx_cur = idx_nxt;
					idx_nxt = -1;
				}
			}
			if (chained_fmt != null) {
				int year_int = bfr.To_int_and_clear(-1);
				if (year_int != -1) {	// handle no format; EX:{{#time:xh}} DATE:2014-07-20
					date = DateAdp_.seg_(new int[]  {year_int, date.Month(), date.Day(), date.Hour(), date.Minute(), date.Second(), date.Frac()});
					chained_fmt.Fmt(bfr, wiki, lang, date, this);
				}
			}
		}
	}
}
