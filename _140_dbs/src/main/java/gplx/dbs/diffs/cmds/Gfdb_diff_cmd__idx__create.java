package gplx.dbs.diffs.cmds; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
import gplx.core.srls.*; import gplx.dbs.metas.*;
class Gfdb_diff_cmd__idx__create implements Gfo_srl_itm {
	public Gfdb_diff_cmd__idx__create(Dbmeta_idx_itm cur) {this.cur = cur;}
	public Dbmeta_idx_itm cur;
	private Gfdb_diff_cmd__idx__fld[] flds = new Gfdb_diff_cmd__idx__fld[0];
	public Gfo_srl_itm Make_new(Gfo_srl_ctx ctx) {return new Gfdb_diff_cmd__idx__create();} Gfdb_diff_cmd__idx__create() {} 
	private int idx_uid;
	// *_sdif_ddl_idx : txn_uid,idx_uid,idx_tbl,idx_name,idx_unique
	public void Save(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_mgr_wtr wtr) {
		wtr.Itm_bgn("idx");
		wtr.Set_int		("idx_uid"		, idx_uid);
		wtr.Set_str		("idx_tbl"		, cur.Tbl());
		wtr.Set_str		("idx_name"		, cur.Name());
		wtr.Set_bool	("idx_unique"	, cur.Unique());
            Tfds.Write(flds.length);
		wtr.Itm_end();
	}
	public void Load(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_mgr_rdr rdr) {
		rdr.Itm_bgn("idx");
		this.idx_uid	= rdr.Get_int		("idx_uid");
		String tbl		= rdr.Get_str		("idx_tbl");
		String name		= rdr.Get_str		("idx_name");
		boolean unique		= rdr.Get_bool		("idx_unique");
		this.flds = (Gfdb_diff_cmd__idx__fld[])rdr.Get_subs(ctx, this, Gfdb_diff_cmd__idx__fld.Instance, ctx.Rdr_subs("idx_fld").Add_int("idx_uid", idx_uid));
		cur = new Dbmeta_idx_itm(unique, tbl, name, Dbmeta_idx_fld.Ary_empty);
		rdr.Itm_end();
	}
	public void Exec(Db_conn conn) {
		conn.Ddl_create_idx(cur);
	}
}
class Gfdb_diff_cmd__idx__fld implements Gfo_srl_itm {
	public Gfdb_diff_cmd__idx__fld(Dbmeta_idx_fld cur) {this.cur = cur;}
	private Dbmeta_idx_fld cur;
	public Gfo_srl_itm Make_new(Gfo_srl_ctx ctx) {return new Gfdb_diff_cmd__idx__fld();} Gfdb_diff_cmd__idx__fld() {} 
	// *_sdif_ddl_idx_fld : idx_uid,fld_order,fld_name,fld_asc
	public void Save(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_mgr_wtr wtr) {
		wtr.Itm_bgn("idx_fld");
		wtr.Set_int		("fld_order"	, cur.Order);
		wtr.Set_str		("fld_name"		, cur.Name);
		wtr.Set_int		("fld_asc"		, cur.Sort_tid);
		wtr.Itm_end();
	}
	public void Load(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_mgr_rdr rdr) {
		rdr.Itm_bgn("idx_fld");
		int order		= rdr.Get_int		("fld_order");
		String name		= rdr.Get_str		("fld_name");
		int sort_tid	= rdr.Get_int		("fld_sort");
		cur = new Dbmeta_idx_fld(order, name, sort_tid);
		rdr.Itm_end();
	}

        public static final Gfdb_diff_cmd__idx__fld Instance = new Gfdb_diff_cmd__idx__fld();
}
