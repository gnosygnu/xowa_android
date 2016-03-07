package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*;
import gplx.dbs.*;
import gplx.xowa.wikis.data.tbls.*;
class Srch_link_tbl__in_wkr extends gplx.dbs.utls.Db_in_wkr__base {
	private String tbl_name; private Dbmeta_fld_list flds; private String fld_page_id, fld_word_id;
	private List_adp words, pages;		
	public void Ctor(String tbl_name, Dbmeta_fld_list flds, String fld_page_id, String fld_word_id) {
		this.tbl_name = tbl_name; this.flds = flds; this.fld_page_id = fld_page_id; this.fld_word_id = fld_word_id;
	}
	public void Init(List_adp words, List_adp pages) {this.words = words; this.pages = pages;}
	@Override protected Db_qry Make_qry(int bgn, int end) {
		Object[] part_ary = In_ary(end - bgn);			
		return Db_qry_.select_cols_(tbl_name, Db_crt_.New_in(fld_word_id, part_ary), flds.To_str_ary());
	}
	@Override protected void Fill_stmt(Db_stmt stmt, int bgn, int end) {
		for (int i = bgn; i < end; i++) {
			Int_obj_val word_id = (Int_obj_val)words.Get_at(i);
			stmt.Crt_int(fld_word_id, word_id.Val());		
		}
	}
	@Override protected void Read_data(Cancelable cxl, Db_rdr rdr) {
		while (rdr.Move_next()) {
			if (cxl.Canceled()) {cxl.Cancel_ackd_(); return;}
			int page_id = rdr.Read_int(fld_page_id);
			Xowd_page_itm page = new Xowd_page_itm().Id_(page_id);
			pages.Add(page);
		}
	}
}
