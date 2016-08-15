package gplx.xowa.files.origs; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.primitives.*;
import gplx.dbs.*; import gplx.dbs.utls.*; 
import gplx.xowa.files.fsdb.*; import gplx.xowa.files.repos.*;
public class Xof_orig_tbl implements Rls_able {
	public final    String tbl_name; public final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	public final    String fld_repo, fld_ttl, fld_status, fld_ext, fld_w, fld_h, fld_redirect;
	public final    Db_conn conn; private final    Xof_orig_tbl__in_wkr select_in_wkr = new Xof_orig_tbl__in_wkr();
	public Db_conn Conn() {return conn;}
	public Xof_orig_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn;
		String fld_status_name = "orig_status";
		if (schema_is_1)		{tbl_name = "wiki_orig"; fld_status_name = "status";}
		else					{tbl_name = "orig_reg";}
		fld_ttl					= flds.Add_str("orig_ttl", 1024);
		fld_repo				= flds.Add_byte("orig_repo");
		fld_status				= flds.Add_byte(fld_status_name);
		fld_ext					= flds.Add_int("orig_ext");
		fld_w					= flds.Add_int("orig_w");
		fld_h					= flds.Add_int("orig_h");
		fld_redirect			= flds.Add_str("orig_redirect", 1024);
		select_in_wkr.Ctor(this, tbl_name, flds, fld_ttl);
		conn.Rls_reg(this);
	}
	public void Rls() {}
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds, Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "main", fld_ttl)));}
	public void Select_by_list(Ordered_hash rv, List_adp itms) {select_in_wkr.Init(rv, itms).Select_in(Cancelable_.Never, conn, 0, itms.Count());}
	public Xof_orig_itm Select_itm(byte[] ttl) {
		Xof_orig_itm rv = Xof_orig_itm.Null;
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_ttl).Clear().Crt_bry_as_str(fld_ttl, ttl).Exec_select__rls_auto();
		try {
			if (rdr.Move_next())
				rv = Load_by_rdr(rdr);
		}
		finally {rdr.Rls();}
		return rv;
	}
	public boolean Exists__repo_ttl(byte repo, byte[] ttl) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_repo, fld_ttl).Crt_byte(fld_repo, repo).Crt_bry_as_str(fld_ttl, ttl).Exec_select__rls_auto();
		try {return rdr.Move_next();}
		finally {rdr.Rls();}
	}
	public void Insert(byte repo, byte[] ttl, int ext, int w, int h, byte[] redirect) {
		Db_stmt stmt = conn.Stmt_insert(tbl_name, flds);
		this.Insert(stmt, repo, ttl, ext, w, h, redirect);
	}
	public void Insert(Db_stmt stmt, byte repo, byte[] ttl, int ext, int w, int h, byte[] redirect) {
		stmt.Clear()
			.Val_bry_as_str(fld_ttl, ttl).Val_byte(fld_repo, repo).Val_byte(fld_status, Status_found)
			.Val_int(fld_ext, ext).Val_int(fld_w, w).Val_int(fld_h, h).Val_bry_as_str(fld_redirect, redirect)
		.Exec_insert();
	}
	public void Update(byte repo, byte[] ttl, int ext, int w, int h, byte[] redirect) {
		Db_stmt stmt = conn.Stmt_update_exclude(tbl_name, flds, String_.Ary(fld_repo, fld_ttl));
		stmt.Clear()
			.Val_byte(fld_status, Status_found)
			.Val_int(fld_ext, ext).Val_int(fld_w, w).Val_int(fld_h, h).Val_bry_as_str(fld_redirect, redirect)
			.Crt_byte(fld_repo, repo).Crt_bry_as_str(fld_ttl, ttl)
		.Exec_update();
	}
	public Xof_orig_itm Load_by_rdr(Db_rdr rdr) {
		byte repo = rdr.Read_byte(fld_repo);
		Xof_orig_itm rv = new Xof_orig_itm
		( repo
		, rdr.Read_bry_by_str(fld_ttl)
		, rdr.Read_int(fld_ext)
		, rdr.Read_int(fld_w)
		, rdr.Read_int(fld_h)
		, rdr.Read_bry_by_str(fld_redirect)
		);
		return rv.W() == Xof_img_size.Null ? Xof_orig_itm.Null : rv;
	}
	private static final byte Status_found = 1;
}
class Xof_orig_tbl__in_wkr extends Db_in_wkr__base {
	private Xof_orig_tbl tbl; private String tbl_name; private Dbmeta_fld_list flds; private String fld_ttl;
	private List_adp itms; private Ordered_hash rv;		
	public void Ctor(Xof_orig_tbl tbl, String tbl_name, Dbmeta_fld_list flds, String fld_ttl) {
		this.tbl = tbl; this.tbl_name = tbl_name; this.flds = flds; this.fld_ttl = fld_ttl;
	}
	public Xof_orig_tbl__in_wkr Init(Ordered_hash rv, List_adp itms) {this.itms = itms; this.rv = rv; return this;}
	@Override protected Db_qry Make_qry(int bgn, int end) {
		Object[] part_ary = In_ary(end - bgn);			
		return Db_qry_.select_cols_(tbl_name, Db_crt_.New_in(fld_ttl, part_ary), flds.To_str_ary());
	}
	@Override protected void Fill_stmt(Db_stmt stmt, int bgn, int end) {
		for (int i = bgn; i < end; i++) {
			Xof_fsdb_itm fsdb_itm = (Xof_fsdb_itm)itms.Get_at(i);
			stmt.Crt_bry_as_str(fld_ttl, fsdb_itm.Lnki_ttl());
		}
	}
	@Override protected void Read_data(Cancelable cancelable, Db_rdr rdr) {
		while (rdr.Move_next()) {
			if (cancelable.Canceled()) return;
			Xof_orig_itm itm = tbl.Load_by_rdr(rdr);
			if (itm == Xof_orig_itm.Null) continue;
			byte[] itm_ttl = itm.Ttl();
			rv.Add_if_dupe_use_1st(itm_ttl, itm);	// guard against dupes; fails on en.w:Paris; DATE:2015-03-08
		}
	}
}
