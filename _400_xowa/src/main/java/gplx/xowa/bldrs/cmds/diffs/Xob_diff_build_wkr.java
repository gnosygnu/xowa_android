package gplx.xowa.bldrs.cmds.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
import gplx.dbs.*; import gplx.dbs.metas.*; import gplx.dbs.diffs.*; import gplx.dbs.diffs.builds.*;
class Xob_diff_build_wkr {		
	private final Gfdb_diff_bldr dif_bldr = new Gfdb_diff_bldr();
	private Db_conn prv_conn, cur_conn, dif_conn;
	public Xob_diff_build_wkr(Xob_bldr bldr, Xowe_wiki wiki, String prv_url, String cur_url, String dif_url, int commit_interval) {			
		Bry_fmt url_fmt = Bry_fmt.New("").Args_(New_url_args(wiki));
		Bry_bfr tmp_bfr = Bry_bfr.new_();
		prv_conn = New_conn(Bool_.N, wiki, url_fmt, prv_url, tmp_bfr);
		cur_conn = New_conn(Bool_.N, wiki, url_fmt, cur_url, tmp_bfr);
		dif_conn = New_conn(Bool_.Y, wiki, url_fmt, dif_url, tmp_bfr);
            Tfds.Dbg(prv_conn, cur_conn, dif_conn);
	}
	public void Exec() {
		Gfdb_diff_db dif_db = new Gfdb_diff_db(dif_conn);
		Gfdb_diff_wkr__db dif_wkr = new Gfdb_diff_wkr__db();
		dif_wkr.Init_conn(dif_db, 1000);
		dif_bldr.Init(dif_wkr);
		Dbmeta_tbl_mgr prv_tbl_mgr = prv_conn.Meta_tbl_load_all();
		Dbmeta_tbl_mgr cur_tbl_mgr = prv_conn.Meta_tbl_load_all();
		int cur_tbl_len = cur_tbl_mgr.Len();
		for (int i = 0; i < cur_tbl_len; ++i) {
			Dbmeta_tbl_itm cur_tbl = cur_tbl_mgr.Get_at(i);
			Dbmeta_tbl_itm prv_tbl = prv_tbl_mgr.Get_by(cur_tbl.Name()); if (prv_tbl == null) continue;
			Gfdb_diff_tbl dif_tbl = Gfdb_diff_tbl.New(cur_tbl);
			dif_bldr.Compare(dif_tbl, prv_conn, cur_conn);
		}
		int prv_tbl_len = prv_tbl_mgr.Len();
		for (int i = 0; i < prv_tbl_len; ++i) {
			Dbmeta_tbl_itm prv_tbl = prv_tbl_mgr.Get_at(i);
			Dbmeta_tbl_itm cur_tbl = cur_tbl_mgr.Get_by(prv_tbl.Name());
			if (cur_tbl == null) {
				// delete all
			}
		}
	}
	public static Db_conn New_conn(boolean autocreate, Xow_wiki wiki, Bry_fmt fmtr, String url_fmt, Bry_bfr tmp_bfr) {
		fmtr.Fmt_(url_fmt).Bld_bfr_many(tmp_bfr);
		return Db_conn_bldr.Instance.Get_or_autocreate(autocreate, Io_url_.new_any_(tmp_bfr.To_str_and_clear()));
	}
	private static Bfr_fmt_arg[] New_url_args(Xow_wiki wiki) {
		Bfr_fmt_arg[] rv = new Bfr_fmt_arg[]
		{ new Bfr_fmt_arg(Bry_.new_a7(".dump_dir"), new Bfr_arg__dump_dir(wiki))
		, new Bfr_fmt_arg(Bry_.new_a7(".dump_core"), new Bfr_arg__dump_core(wiki))
		, new Bfr_fmt_arg(Bry_.new_a7(".dump_domain"), new Bfr_arg__dump_domain(wiki))
		};
		return rv;
	}
	//prv_url='~{.dump_dir}-prev/~{.dump_core}';
	//cur_url='~{.dump_dir}/~{.dump_core}';
	//dif_url='~{.dump_dir}/~{.dump_domain}-diff.xowa';
}
