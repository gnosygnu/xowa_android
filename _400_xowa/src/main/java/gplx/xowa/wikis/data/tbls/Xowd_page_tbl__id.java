package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.core.criterias.*;
import gplx.dbs.*; import gplx.dbs.utls.*;
class Xowd_page_tbl__id extends Xowd_page_tbl__in_wkr__base {
	private List_adp list;		// list is original list of ids which may have dupes; needed to fill statement (which takes range of bgn - end); DATE:2013-12-08
	private Ordered_hash hash;	// hash is unique list of ids; needed for fetch from rdr (which indexes by id)
	public void Init(List_adp list, Ordered_hash hash) {this.list = list; this.hash = hash; this.Fill_idx_fields_only_(true);}
	@Override protected boolean		Show_progress() {return true;}
	@Override protected Criteria In_filter(Object[] part_ary) {
		return Db_crt_.New_in(this.In_fld_name(), part_ary);
	}
	@Override protected void Fill_stmt(Db_stmt stmt, int bgn, int end) {
		for (int i = bgn; i < end; i++) {
			Xowd_page_itm page = (Xowd_page_itm)list.Get_at(i);
			stmt.Val_int(page.Id());		
		}
	}
	@Override public Xowd_page_itm Read_data_to_page(Xowd_page_itm rdr_page) {return (Xowd_page_itm)hash.Get_by(rdr_page.Id_val());}
}