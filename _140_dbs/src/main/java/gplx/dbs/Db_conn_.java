package gplx.dbs; import gplx.*;
public class Db_conn_ {
	public static final Db_conn Noop = Db_conn_pool.Instance.Get_or_new(Db_conn_info_.Null);
}
