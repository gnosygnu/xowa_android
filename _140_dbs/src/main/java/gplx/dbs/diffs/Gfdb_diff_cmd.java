package gplx.dbs.diffs; import gplx.*; import gplx.dbs.*;
import gplx.dbs.metas.*; import gplx.dbs.diffs.builds.*;
class Gfdb_diff_cmd {
	private final Gfdb_diff_wkr__db diff_bldr_wkr = new Gfdb_diff_wkr__db();
	private final Gfdb_diff_bldr diff_bldr = new Gfdb_diff_bldr();
	public Gfdb_diff_cmd() {
		diff_bldr.Init(diff_bldr_wkr);
	}
	public Gfdb_diff_job New_job(Gfdb_diff_db db, String guid, String name, String made_by, String desc) {
		return new Gfdb_diff_job(db);
	}
	public void Bld(Gfdb_diff_job job, Gfdb_diff_tbl_mgr lhs_mgr, Gfdb_diff_tbl_mgr rhs_mgr) {
		diff_bldr_wkr.Init_conn(job.Db(), 1000);
		int rhs_len = rhs_mgr.Len();
		for (int i = 0; i < rhs_len; ++i) {
			Gfdb_diff_tbl rhs_tbl = rhs_mgr.Get_at(i);
			Gfdb_diff_tbl lhs_tbl = lhs_mgr.Get_by(rhs_tbl.Name);
			if (lhs_tbl == null) {
				// Add_cmd_tbl_create();
				// Add_cmd_tbl_insert_all());
			}
			else {
				// Compare_flds();
				// Compare_idxs();
				// diff_bldr.Compare(lhs_tbl, rhs_tbl);
			}
		}
		int lhs_len = lhs_mgr.Len();
		for (int i = 0; i < lhs_len; ++i) {
			Gfdb_diff_tbl lhs_tbl = lhs_mgr.Get_at(i);
			if (lhs_tbl == null) {
				// Add_cmd_dat_delete_all());
				// Add_cmd_tbl_delete();
			}
		}			
	}
}
class Gfdb_diff_job {
	public Gfdb_diff_job(Gfdb_diff_db db) {this.db = db;}
	public Gfdb_diff_db Db() {return db;} private Gfdb_diff_db db;
}
