package gplx.dbs.diffs.builds; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
public interface Gfdb_diff_wkr {
	void Init_rdrs(Gdif_bldr_ctx ctx, Gfdb_diff_tbl tbl, Db_rdr old_rdr, Db_rdr new_rdr);
	void Term_tbls();
	void Handle_same();
	void Handle_old_missing();
	void Handle_new_missing();
}
