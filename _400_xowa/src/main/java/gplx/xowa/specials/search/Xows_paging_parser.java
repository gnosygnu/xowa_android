package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.core.brys.*;
class Xows_paging_parser {
	private final List_adp itm_list = List_adp_.new_();
	private final Bry_rdr_old rdr = new Bry_rdr_old();
	public Xows_paging_itm[] Parse(byte[] raw) {	// EX: en.wikipedia.org|41|60;en.wiktionary.org|21|40;
		rdr.Init(raw);
		while (!rdr.Pos_is_eos()) {
			byte[] wiki = rdr.Read_bry_to_pipe();
			int bgn = rdr.Read_int_to_pipe();
			int end = rdr.Read_int_to_semic();
			Xows_paging_itm itm = new Xows_paging_itm(wiki, bgn, end);
			itm_list.Add(itm);
		}
		return (Xows_paging_itm[])itm_list.To_ary_and_clear(Xows_paging_itm.class);
	}
}
class Xows_paging_itm {
	public Xows_paging_itm(byte[] wiki, int bgn, int end) {this.wiki = wiki; this.bgn = bgn; this.end = end;}
	public byte[] Wiki() {return wiki;} private final byte[] wiki;
	public int Bgn() {return bgn;} private final int bgn;
	public int End() {return end;} private final int end;
}
