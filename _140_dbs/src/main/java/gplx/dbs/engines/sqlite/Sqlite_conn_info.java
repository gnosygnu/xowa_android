package gplx.dbs.engines.sqlite; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Sqlite_conn_info extends Db_conn_info__base {
	@Override public String Tid() {return Tid_const;} public static final String Tid_const = "sqlite";
	public Io_url Url() {return url;} private Io_url url;
	@Override public Db_conn_info New_self(String raw, GfoMsg m) {
		Sqlite_conn_info rv = new Sqlite_conn_info();
		String url = m.ReadStr("data source");
		rv.url = Io_url_.new_any_(url);
		rv.Ctor("", url, raw, BldApi(m, KeyVal_.new_("version", "3")));
		rv.database = rv.url.NameOnly();
		return rv;
	}
	public static Db_conn_info load_(Io_url url) {
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key"		, Tid_const
		, "data source"		, url.Xto_api()
		, "version"			, "3"
		));
	}
	public static Db_conn_info make_(Io_url url) {
		Io_mgr.Instance.CreateDirIfAbsent(url.OwnerDir());
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key"		, Tid_const
		, "data source"		, url.Xto_api()
		, "version"			, "3"
			//#<>, "New", "True"~
		));
	}
	public static final Sqlite_conn_info Instance = new Sqlite_conn_info(); Sqlite_conn_info() {}
}