package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*;
public class Xowd_wbase_qid_tbl implements Rls_able {
	private final String tbl_name; private final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	private final String fld_src_wiki, fld_src_ns, fld_src_ttl, fld_trg_ttl;
	private final Db_conn conn; private Db_stmt stmt_select, stmt_insert;
	private boolean src_ttl_has_spaces;
	public Xowd_wbase_qid_tbl(Db_conn conn, boolean schema_is_1, boolean src_ttl_has_spaces) {
		this.conn = conn; this.src_ttl_has_spaces = src_ttl_has_spaces;
		String fld_prefix = "";
		if (schema_is_1)	{tbl_name = "wdata_qids"; fld_prefix = "wq_";}
		else				{tbl_name = "wbase_qid";}
		fld_src_wiki		= flds.Add_str(fld_prefix + "src_wiki", 255);
		fld_src_ns			= flds.Add_int(fld_prefix + "src_ns");
		fld_src_ttl			= flds.Add_str(fld_prefix + "src_ttl", 512);
		fld_trg_ttl			= flds.Add_str(fld_prefix + "trg_ttl", 512);
		conn.Rls_reg(this);
	}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx() {conn.Meta_idx_create(Xoa_app_.Usr_dlg(), Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "src", fld_src_wiki, fld_src_ns, fld_src_ttl));}
	public void Insert_bgn() {conn.Txn_bgn("schema__wbase_qid__insert"); stmt_insert = conn.Stmt_insert(tbl_name, flds);}
	public void Insert_end() {conn.Txn_end(); stmt_insert = Db_stmt_.Rls(stmt_insert);}
	public void Insert_cmd_by_batch(byte[] src_wiki, int src_ns, byte[] src_ttl, byte[] trg_ttl) {
		stmt_insert.Clear()
			.Val_bry_as_str(fld_src_wiki, src_wiki).Val_int(fld_src_ns, src_ns).Val_bry_as_str(fld_src_ttl, src_ttl).Val_bry_as_str(fld_trg_ttl,trg_ttl)
			.Exec_insert();
	}
	public byte[] Select_qid(byte[] src_wiki, byte[] src_ns, byte[] src_ttl) {
		if (stmt_select == null) stmt_select = conn.Stmt_select(tbl_name, flds, fld_src_wiki, fld_src_ns, fld_src_ttl);
		if (src_ttl_has_spaces) src_ttl = Xoa_ttl.Replace_unders(src_ttl);	// NOTE: v2.4.2.1-v2.4.3.2 stores ttl in spaces ("A B"), while xowa will use under form ("A_B"); DATE:2015-04-21
		Db_rdr rdr = stmt_select.Clear()
				.Crt_bry_as_str(fld_src_wiki, src_wiki).Crt_int(fld_src_ns, Bry_.To_int_or_neg1(src_ns)).Crt_bry_as_str(fld_src_ttl, src_ttl)
				.Exec_select__rls_manual();
		try {
			return rdr.Move_next() ? rdr.Read_bry_by_str(fld_trg_ttl) : null;
		} finally {rdr.Rls();}
	}
	public void Rls() {
		stmt_insert = Db_stmt_.Rls(stmt_insert);
		stmt_select = Db_stmt_.Rls(stmt_select);
	}
}
