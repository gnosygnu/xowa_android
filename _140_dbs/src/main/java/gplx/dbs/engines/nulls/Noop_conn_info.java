package gplx.dbs.engines.nulls; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Noop_conn_info extends Db_conn_info__base {
	@Override public String Tid() {return Tid_const;} public static final String Tid_const = "null_db";
	@Override public Db_conn_info New_self(String raw, GfoMsg m) {return this;}
	public static final Noop_conn_info Instance = new Noop_conn_info(); Noop_conn_info() {this.Ctor("", "", "gplx_key=null_db", "");}
}
