package gplx.dbs.engines.mysql; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Mysql_conn_info extends Db_conn_info__base {
	public Mysql_conn_info(String raw, String db_api, String database, String server, String uid, String pwd) {super(raw, db_api, database);
		this.server = server;
		this.uid = uid;
		this.pwd = pwd;
	}
	@Override public String Key() {return Tid_const;} public static final String Tid_const = "mysql";
	public String Server() {return server;} private final String server;
	public String Uid() {return uid;} private final String uid;
	public String Pwd() {return pwd;} private final String pwd;
	public static Db_conn_info new_(String server, String database, String uid, String pwd) {
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key", Tid_const
		, "server", server
		, "database", database
		, "uid", uid
		, "pwd", pwd
		, "charset", "utf8"
		));
	}
	@Override public Db_conn_info New_self(String raw, Keyval_hash hash) {
		return new Mysql_conn_info
			( raw, Bld_api(hash, Keyval_.new_("charset", "utf8")), hash.Get_val_as_str_or_fail("database")
			, hash.Get_val_as_str_or_fail("server"), hash.Get_val_as_str_or_fail("uid"), hash.Get_val_as_str_or_fail("pwd"));
	}
        public static final Mysql_conn_info Instance = new Mysql_conn_info("", "", "", "", "", "");
}
