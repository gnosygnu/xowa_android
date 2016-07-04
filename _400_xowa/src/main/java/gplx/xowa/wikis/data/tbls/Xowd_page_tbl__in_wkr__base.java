package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.core.criterias.*;
import gplx.dbs.*; import gplx.dbs.utls.*;
abstract class Xowd_page_tbl__in_wkr__base extends Db_in_wkr__base {
	protected Xowd_page_tbl tbl; private String tbl_name, fld_in_name;
	public String Tbl_name() {return tbl_name;}
	public void Ctor(Xowd_page_tbl tbl, String tbl_name, String fld_in_name) {this.tbl = tbl; this.tbl_name = tbl_name; this.fld_in_name = fld_in_name;}
	public String In_fld_name() {return  fld_in_name;}
	protected abstract Criteria In_filter(Object[] part_ary);
	public abstract Xowd_page_itm Read_data_to_page(Xowd_page_itm rdr_page);
	public boolean Fill_idx_fields_only() {return fill_idx_fields_only;} public void Fill_idx_fields_only_(boolean v) {fill_idx_fields_only = v;} private boolean fill_idx_fields_only;
	@Override protected Db_qry Make_qry(int bgn, int end) {
		Object[] part_ary = In_ary(end - bgn);
		return Db_qry_.select_cols_
		(	this.Tbl_name()
		, 	In_filter(part_ary)
		, 	fill_idx_fields_only ? tbl.Flds_select_idx() : tbl.Flds_select_all()
		)
		;
	}
	@Override protected void Read_data(Cancelable cancelable, Db_rdr rdr) {
		Xowd_page_itm temp = new Xowd_page_itm();
		while (rdr.Move_next()) {
			if (cancelable.Canceled()) return;
			if (fill_idx_fields_only)
				tbl.Read_page__idx(temp, rdr);
			else
				tbl.Read_page__all(temp, rdr);
			Xowd_page_itm page = Read_data_to_page(temp);
			if (page == null) continue; // page not found
			temp.Exists_(true);
			page.Copy(temp);
		}
	}
}
