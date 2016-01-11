package gplx.dbs.diffs; import gplx.*; import gplx.dbs.*;
class Gfdb_diff_tbl_mgr {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int Len() {return hash.Count();}
	public Gfdb_diff_tbl Get_at(int idx) {return (Gfdb_diff_tbl)hash.Get_at(idx);}
	public Gfdb_diff_tbl Get_by(String key) {return (Gfdb_diff_tbl)hash.Get_by(key);}
}
class Gfdb_diff_tbl_mgr__sqlite {
	public void Fill(Gfdb_diff_tbl_mgr tbl_mgr, Db_conn conn) {
		// String schema_str = ""; // conn.Get_schema();
	}
	public void Fill(Gfdb_diff_tbl_mgr tbl_mgr, String schema_str) {
		/*
		Db_conn conn = null;
		conn.Meta_get_tbls(Gfdb_diff_tbl_mgr tbl_mgr, "");
		*/
	}
}	
