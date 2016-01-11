package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Tdb_conn_info extends Db_conn_info__base {//_20110430
	public Io_url Url() {return url;} Io_url url;
	@Override public String Tid() {return Tid_const;} public static final String Tid_const = "tdb";
        public static Db_conn_info new_(Io_url url) {
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key", Tid_const
		, "url", url.Raw()
		));
	}	Tdb_conn_info() {}
	@Override public Db_conn_info New_self(String raw, GfoMsg m) {
		Tdb_conn_info rv = new Tdb_conn_info();
		String urlStr = m.ReadStr("url");
		Io_url url = Io_url_.new_any_(urlStr);
		rv.Ctor(urlStr, url.NameOnly(), raw, BldApi(m));
		rv.url = url;
		return rv;
	}
        public static final Tdb_conn_info Instance = new Tdb_conn_info();
}
