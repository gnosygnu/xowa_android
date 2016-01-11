package gplx.dbs.utls; import gplx.*; import gplx.dbs.*;
import gplx.core.stores.*;
import gplx.dbs.qrys.*;
public class PoolIds {
	public int FetchNext(Db_conn conn, String url) {
		Db_qry__select_cmd cmd = Db_qry_.select_().From_(Tbl_Name).Where_(Db_crt_.eq_(Fld_id_path, url));
		int rv = 0;//boolean isNew = true;
		DataRdr rdr = DataRdr_.Null;
		try {
			rdr = cmd.Exec_qry_as_rdr(conn);
			if (rdr.MoveNextPeer()) {
				rv = rdr.ReadInt(Fld_id_next_id);
			}
		}
		finally {rdr.Rls();}
		return rv;
	}
	public int FetchNextAndCommit(String dbInfo, String url) {
		Db_conn conn = Db_conn_pool.Instance.Get_or_new(dbInfo);
		int rv = PoolIds.Instance.FetchNext(conn, url);
		PoolIds.Instance.Commit(conn, url, rv + 1);
		return rv;
	}
	public void Commit(Db_conn conn, String url, int val) {
		int rv = conn.Exec_qry(Db_qry_.update_(Tbl_Name, Db_crt_.eq_(Fld_id_path, url)).Arg_(Fld_id_path, url).Arg_(Fld_id_next_id, val));
		if (rv == 0) {
			rv = conn.Exec_qry(Db_qry_.insert_(Tbl_Name).Arg_(Fld_id_path, url).Arg_(Fld_id_next_id, val));
		}
		if (rv != 1) throw Err_.new_wo_type("failed to update nextId", "url", url, "nextId", val);
	}
	public static final String Tbl_Name					= "pool_ids";
	@gplx.Internal protected static final String Fld_id_path				= "id_path";
	@gplx.Internal protected static final String Fld_id_next_id			= "id_next_id";
	public static final PoolIds Instance = new PoolIds(); PoolIds() {}
}
