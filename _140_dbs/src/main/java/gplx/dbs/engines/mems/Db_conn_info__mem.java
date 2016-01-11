package gplx.dbs.engines.mems; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Db_conn_info__mem extends Db_conn_info__base {
	@Override public String Tid() {return Tid_const;} public static final String Tid_const = "mem";
	@Override public Db_conn_info New_self(String raw, GfoMsg m) {
		Db_conn_info__mem rv = new Db_conn_info__mem();
		rv.Ctor("", m.ReadStr("database"), raw, raw);
		return rv;
	}
	public static Db_conn_info new_(String database) {
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key", Tid_const
		, "database", database
		));
	}
        public static final Db_conn_info__mem Instance = new Db_conn_info__mem(); Db_conn_info__mem() {}
}
