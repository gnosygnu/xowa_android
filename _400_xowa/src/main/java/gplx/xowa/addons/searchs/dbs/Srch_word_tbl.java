package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*; import gplx.dbs.*; import gplx.dbs.cfgs.*; import gplx.dbs.qrys.*; import gplx.xowa.wikis.data.*;
public class Srch_word_tbl implements Rls_able {
	public final String tbl_name;
	public final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	public final String fld_id, fld_text, fld_page_count, fld_page_score_max;
	public final Db_conn conn; private Db_stmt stmt_insert, stmt_select_by, stmt_select_in, stmt_select_like_1st, stmt_select_like;
	public Srch_word_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn;
		String fld_prefix = "", fld_text_name = "word_text";
		if (schema_is_1)		{tbl_name = "search_title_word"; fld_prefix = "stw_"; fld_text_name = "stw_word";}
		else					{tbl_name = "search_word";}
		this.fld_id				= flds.Add_int_pkey(fld_prefix + "word_id");
		this.fld_text			= flds.Add_str(fld_text_name, 255);
		this.fld_page_count		= Dbmeta_fld_itm.Make_or_null(conn, flds, tbl_name, Dbmeta_fld_tid.Tid__int, 0, "word_page_count");
		this.fld_page_score_max	= Dbmeta_fld_itm.Make_or_null(conn, flds, tbl_name, Dbmeta_fld_tid.Tid__int, 0, "word_page_score_max");
		conn.Rls_reg(this);
	}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx() {
		try {conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_unique_by_tbl(tbl_name, "main", fld_text, fld_id, fld_page_count));} 
		catch (Exception e) {Xoa_app_.Usr_dlg().Warn_many("", "", "bldr.search_word.unique_search_failed: err=~{0}", Err_.Message_gplx_full(e));}
	}
	public void Insert_bgn() {conn.Txn_bgn("schema__search_word__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end() {conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert_cmd_by_batch(int id, byte[] word, int page_count, int score_max) {stmt_insert.Clear().Val_int(fld_id, id).Val_bry_as_str(fld_text, word).Val_int(fld_page_count, page_count).Val_int(fld_page_score_max, score_max).Exec_insert();}
	public Srch_word_row Select_by_or_null(byte[] word) {
		if (stmt_select_by == null) stmt_select_by = conn.Stmt_select(tbl_name, flds, fld_text);
		Db_rdr rdr = stmt_select_by.Clear().Crt_bry_as_str(fld_text, word).Exec_select__rls_manual();
		try		{return rdr.Move_next() ? New_row(rdr) : Srch_word_row.Null;}
		finally {rdr.Rls();}
	}
	public Db_rdr Select_like_as_rdr(byte[] word, int offset) {
		Db_qry__select_cmd qry = Db_qry_.select_().Cols_all_()
			.From_(tbl_name)
			.Where_(Db_crt_.New_and(Db_crt_.New_mte(fld_text, ""), Db_crt_.New_lt(fld_text, "")))
			.Order_(fld_page_score_max, Bool_.N)
			.Offset_(offset);
		return conn.Stmt_new(qry).Crt_bry_as_str(fld_text, word).Crt_bry_as_str(fld_text, gplx.core.intls.Utf8_.Increment_char_at_last_pos(word)).Exec_select__rls_manual();
	}
	public Srch_word_row New_row(Db_rdr rdr) {
		int page_count		= fld_page_count	== Dbmeta_fld_itm.Key_null ? 0 : rdr.Read_int(fld_page_count);
		int page_score_max	= fld_page_score_max == Dbmeta_fld_itm.Key_null ? page_count : rdr.Read_int(fld_page_score_max);
		return new Srch_word_row(rdr.Read_int(fld_id), rdr.Read_bry_by_str(fld_text), page_count, page_score_max);
	}
	public void Rls() {
		stmt_insert				= Db_stmt_.Rls(stmt_insert);
		stmt_select_by			= Db_stmt_.Rls(stmt_select_by);
		stmt_select_in			= Db_stmt_.Rls(stmt_select_in);
		stmt_select_like_1st	= Db_stmt_.Rls(stmt_select_like_1st);
		stmt_select_like		= Db_stmt_.Rls(stmt_select_like);
	}

	// old search system
	public Srch_word_row[] Select_like(Cancelable cxl, byte[] word) {
		List_adp list = List_adp_.new_();
		byte[] search_bgn = word;
		byte[] search_end = gplx.core.intls.Utf8_.Increment_char_at_last_pos(search_bgn);
		String sql = String_.Format
		( "SELECT * FROM {0} INDEXED BY {0}__main WHERE {1} BETWEEN '{2}' AND '{3}' ORDER BY {4} DESC;"
		, tbl_name, fld_text, String_.new_u8(search_bgn), String_.new_u8(search_end), fld_page_count
		);
		Db_qry qry = Db_qry_sql.rdr_(sql);
		Db_rdr rdr = conn.Stmt_new(qry).Exec_select__rls_manual();
		try	{
			int row_count = 0;
			while (rdr.Move_next()) {
				if (cxl.Canceled()) {cxl.Cancel_ackd_(); break;}
				Srch_word_row word_row = New_row(rdr);
				if (++row_count % 10 == 0)
					Xoa_app_.Usr_dlg().Prog_many("", "", "search; reading pages for word: word=~{0} pages=~{1}", word_row.Text, word_row.Page_count);
				list.Add(word_row);
			}
		}
		finally {rdr.Rls();}
		return (Srch_word_row[])list.To_ary_and_clear(Srch_word_row.class);
	}
	public void Select_by_word(Cancelable cancelable, Srch_link_tbl search_page_tbl, List_adp rv, byte[] search, int results_max) {
		gplx.core.criterias.Criteria crt = null; 
		if (Bry_.Has(search, Byte_ascii.Star)) {
			search = Bry_.Replace(search, Byte_ascii.Star, Byte_ascii.Percent);
			crt = Db_crt_.New_like	(fld_text, String_.new_u8(search));
		}
		else
			crt = Db_crt_.New_eq	(fld_text, String_.new_u8(search));
		Db_qry__select_cmd qry = Db_qry_.select_().Cols_(fld_id).From_(tbl_name).Where_(crt);

		List_adp words = List_adp_.new_();
		Db_rdr rdr = conn.Stmt_new(qry).Crt_bry_as_str(fld_text, search).Exec_select__rls_auto();
		try {
			while (rdr.Move_next())
				words.Add(Int_obj_val.new_(rdr.Read_int(fld_id)));
		}
		finally {rdr.Rls();}

		search_page_tbl.Select_in(cancelable, rv, words);
	}
}
