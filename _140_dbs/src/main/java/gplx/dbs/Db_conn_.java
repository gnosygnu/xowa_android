package gplx.dbs; import gplx.*;
import gplx.core.stores.*;
import gplx.dbs.qrys.*;
public class Db_conn_ {
	public static final Db_conn Noop = Db_conn_pool.Instance.Get_or_new(Db_conn_info_.Null);
	public static int Select_fld0_as_int_or(Db_conn p, String sql, int or) {
		DataRdr rdr = DataRdr_.Null;
		try {
			rdr = p.Exec_qry_as_rdr(Db_qry_sql.rdr_(sql));
			int rv = or;
			if (rdr.MoveNextPeer()) {
				Object rv_obj = rdr.ReadAt(0);
				if (rv_obj != null)		// Max(fil_id) will be NULL if tbl is empty
					rv = Int_.cast_or(rv_obj, or);
			}
			return rv;
		}
		finally {
			rdr.Rls();
		}
	}
}
