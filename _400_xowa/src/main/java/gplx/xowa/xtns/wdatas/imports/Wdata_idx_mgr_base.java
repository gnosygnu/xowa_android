package gplx.xowa.xtns.wdatas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*; import gplx.core.ios.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.parsers.*;
import gplx.xowa.xtns.wdatas.imports.*;
abstract class Wdata_idx_mgr_base {
	public void Ctor(Xob_itm_dump_base wkr, Xob_bldr bldr, Xowe_wiki wiki, int dump_fil_len) {
		this.wkr = wkr; this.wiki = wiki; this.bldr = bldr; this.dump_fil_len = dump_fil_len;
	} 	Xob_itm_dump_base wkr; protected Xowe_wiki wiki; Xob_bldr bldr; Xol_csv_parser csv_parser = Xol_csv_parser.Instance; protected Ordered_hash hash = Ordered_hash_.New(); protected int dump_fil_len;
	public void Flush() {
		int len = hash.Count();
		for (int i = 0; i < len; i++) {
			Wdata_idx_wtr wtr = (Wdata_idx_wtr)hash.Get_at(i);
			wtr.Flush();
		}
	}
	public void Make() {
		int len = hash.Count();
		for (int i = 0; i < len; i++) {
			Wdata_idx_wtr wtr = (Wdata_idx_wtr)hash.Get_at(i);
			wtr.Make(bldr.Usr_dlg(), wkr.Make_fil_len());
		}		
		if (wkr.Delete_temp()) Io_mgr.Instance.DeleteDirDeep(wkr.Temp_dir());		
	}
}
