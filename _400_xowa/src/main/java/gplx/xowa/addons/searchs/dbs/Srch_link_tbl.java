package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.dbs.utls.*; import gplx.xowa.wikis.data.tbls.*;
public class Srch_link_tbl {
	public final String tbl_name;
	public final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	public final String fld_word_id, fld_page_id, fld_page_score_tier;
	public final boolean schema_is_1;
	public final Db_conn conn; private final Srch_link_tbl__in_wkr in_wkr = new Srch_link_tbl__in_wkr();
	public Srch_link_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn; this.schema_is_1 = schema_is_1;
		String fld_prefix = "";
		if (schema_is_1)	{tbl_name = "search_title_page"; fld_prefix = "stp_";}
		else				{tbl_name = "search_link";}
		fld_word_id			= flds.Add_int(fld_prefix + "word_id");
		fld_page_id			= flds.Add_int(fld_prefix + "page_id");
		fld_page_score_tier	= Dbmeta_fld_itm.Make_or_null(conn, flds, tbl_name, Dbmeta_fld_tid.Tid__int, 0, "page_score_tier");
		in_wkr.Ctor(tbl_name, flds, fld_page_id, fld_word_id);
	}
	public void Create_tbl()		{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx() {
		try {conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_word_id, fld_page_id));}
		catch (Exception e) {
			Xoa_app_.Usr_dlg().Warn_many("", "", "bldr.search_page.unique_search_failed: err=~{0}", Err_.Message_gplx_full(e));
			conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "main", fld_word_id, fld_page_id));
		}
		conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "word_id", fld_word_id));	// NOTE: looks redundant but greatly improves search performance
	}
	public void Select_in(Cancelable cancelable, List_adp rv, List_adp words) {
		in_wkr.Init(words, rv);
		in_wkr.Select_in(cancelable, conn, 0, words.Count());
	}
	public Db_stmt Select_all__stmt(Db_attach_mgr attach_mgr, Xowd_page_tbl page_tbl) {
		Db_qry__select_cmd qry = Db_qry_.select_()
			.Cols_w_tbl_("sl", fld_word_id, fld_page_id)
			.Cols_w_tbl_("p", page_tbl.Fld_page_ns(), page_tbl.Fld_page_score())
			.From_("link_db", tbl_name, "sl")
			// .	Join_("page_db", page_tbl.Tbl_name(), "p", Db_qry_.New_join__same("l", page_tbl.Fld_page_id()))
			.	Join_(page_tbl.Tbl_name(), "p", Db_qry_.New_join__same("sl", page_tbl.Fld_page_id()))
			.Where_(Db_crt_.New_mt(fld_word_id, -1))
			.Order_(fld_word_id).Order_("p", page_tbl.Fld_page_ns()).Order_("p", page_tbl.Fld_page_score(), Bool_.N).Order_("p", page_tbl.Fld_page_id());
		return attach_mgr.Make_stmt_and_attach(qry, qry.From());
	}
	public int Select_all__exec(Cancelable cxl, List_adp rv, Db_stmt stmt, Xowd_page_tbl page_tbl, int word_count_max, int word_id) {
		rv.Clear();
		Db_rdr rdr = stmt.Clear().Crt_int(fld_word_id, word_id).Exec_select__rls_manual();
		try {
			int cur_word_count = -1, prv_word_id = -1, cur_word_id = -1;
			while (rdr.Move_next()) {
				if (prv_word_id != cur_word_id) {
					prv_word_id  = cur_word_id;
					cur_word_count++;
					if (cur_word_count == word_count_max) break;	// max words read; exit
				}
				cur_word_id = rdr.Read_int(fld_word_id);
				Srch_link_row row = new Srch_link_row(cur_word_id, rdr.Read_int(fld_page_id), rdr.Read_int(page_tbl.Fld_page_ns()), rdr.Read_int(page_tbl.Fld_page_score()));
				rv.Add(row);
			}
			return cur_word_id;
		}	finally {rdr.Rls();}
	}
	public Db_stmt Update_tier__stmt() {return conn.Stmt_update(tbl_name, String_.Ary(fld_word_id, fld_page_id), fld_page_score_tier);}
	public static final Srch_link_tbl[] Ary_empty = new Srch_link_tbl[0];
	public static final String Fld_page_score_tier_str = "page_score_tier", Fld_page_score = "page_score";
}
