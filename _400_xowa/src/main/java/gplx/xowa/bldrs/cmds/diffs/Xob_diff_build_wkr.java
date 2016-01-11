package gplx.xowa.bldrs.cmds.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
import gplx.dbs.*; import gplx.dbs.diffs.*; import gplx.dbs.diffs.builds.*;
class Xob_diff_build_wkr {		
	private final Gfdb_diff_bldr diff_bldr = new Gfdb_diff_bldr();
	private Db_conn prev_conn, curr_conn, diff_conn;
	public Xob_diff_build_wkr(Xob_bldr bldr, Xowe_wiki wiki, String prev_url, String curr_url, String diff_url, int commit_interval) {			
		Bry_fmt url_fmt = Bry_fmt.New("").Args_(New_url_args(wiki));
		Bry_bfr tmp_bfr = Bry_bfr.new_();
		prev_conn = New_conn(Bool_.N, wiki, url_fmt, prev_url, tmp_bfr);
		curr_conn = New_conn(Bool_.N, wiki, url_fmt, curr_url, tmp_bfr);
		diff_conn = New_conn(Bool_.Y, wiki, url_fmt, diff_url, tmp_bfr);
		// get Gfdb_diff_tbl
            Tfds.Dbg(prev_conn, curr_conn, diff_conn);
	}
	public void Exec() {
		diff_bldr.Init(null);			// diff_db_wkr
		diff_bldr.Compare(null, prev_conn, curr_conn);	// lhs_tbl, rhs_tbl
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
	//prev_url='~{.dump_dir}-prev/~{.dump_core}';
	//curr_url='~{.dump_dir}/~{.dump_core}';
	//diff_url='~{.dump_dir}/~{.dump_domain}-diff.xowa';
}
