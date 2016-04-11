package gplx.xowa.addons.apps.searchs.searchers.slabs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*; import gplx.xowa.addons.apps.searchs.searchers.*;
import gplx.core.brys.*;
public class Srch_slab_itm_parser {
	private final    List_adp itm_list = List_adp_.new_();
	private final    Bry_rdr rdr = new Bry_rdr();
	public Srch_slab_itm[] Parse(byte[] raw) {	// EX: en.wikipedia.org|41|60;en.wiktionary.org|21|40;
		rdr.Init_by_src(raw);
		while (!rdr.Pos_is_eos()) {
			byte[] wiki = rdr.Read_bry_to(Byte_ascii.Pipe);				
			int bgn = rdr.Read_int_to(Byte_ascii.Pipe);
			int end = rdr.Read_int_to(Byte_ascii.Semic);
			Srch_slab_itm itm = new Srch_slab_itm(wiki, bgn, end);
			itm_list.Add(itm);
		}
		return (Srch_slab_itm[])itm_list.To_ary_and_clear(Srch_slab_itm.class);
	}
}
