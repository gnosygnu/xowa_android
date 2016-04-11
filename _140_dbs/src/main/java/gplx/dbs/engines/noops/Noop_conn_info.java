package gplx.dbs.engines.noops; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Noop_conn_info extends Db_conn_info__base {
	public Noop_conn_info(String raw, String db_api, String database) {super(raw, db_api, database);}
	@Override public String Key() {return Tid_const;} public static final    String Tid_const = "null_db";
	@Override public Db_conn_info New_self(String raw, Keyval_hash hash) {return this;}
	public static final    Noop_conn_info Instance = new Noop_conn_info("gplx_key=null_db", "", "");
}
