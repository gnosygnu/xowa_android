package gplx.dbs; import gplx.*;
import gplx.dbs.engines.sqlite.*;
public interface Db_conn_bldr_wkr {
	void Clear_for_tests();
	boolean Exists(Io_url url);
	Db_conn Get(Io_url url);
	Db_conn New(Io_url url);
}
class Db_conn_bldr_wkr__sqlite implements Db_conn_bldr_wkr {
	public void Clear_for_tests() {}
	public boolean Exists(Io_url url) {return Io_mgr.Instance.ExistsFil(url);}
	public Db_conn Get(Io_url url) {
		if (!Io_mgr.Instance.ExistsFil(url)) return null;
		Db_conn_info db_url = Db_conn_info_.sqlite_(url);
		return Db_conn_pool.Instance.Get_or_new(db_url);
	}
	public Db_conn New(Io_url url) {
		Io_mgr.Instance.CreateDirIfAbsent(url.OwnerDir());	// must assert that dir exists
		Db_conn_info db_url = Sqlite_conn_info.make_(url);
		Db_conn conn = Db_conn_pool.Instance.Get_or_new(db_url);
		Sqlite_engine_.Pragma_page_size(conn, 4096);
		// conn.Conn_term();	// close conn after PRAGMA adjusted
		return conn;
	}
        public static final Db_conn_bldr_wkr__sqlite Instance = new Db_conn_bldr_wkr__sqlite(); Db_conn_bldr_wkr__sqlite() {}
}
class Db_conn_bldr_wkr__mem implements Db_conn_bldr_wkr {
	private final Hash_adp hash = Hash_adp_.new_();
	public void Clear_for_tests() {hash.Clear(); Db_conn_pool.Instance.Clear();}
	public boolean Exists(Io_url url) {
		String io_url_str = url.Xto_api();
		return hash.Has(io_url_str);
	}
	public Db_conn Get(Io_url url) {
		String io_url_str = url.Xto_api();
		if (!hash.Has(io_url_str)) return null;
		return Db_conn_pool.Instance.Get_or_new__mem(url.Xto_api());
	}
	public Db_conn New(Io_url url) {
		String io_url_str = url.Xto_api();
		hash.Add(io_url_str, io_url_str);
		return Db_conn_pool.Instance.Get_or_new__mem(url.Xto_api());
	}
        public static final Db_conn_bldr_wkr__mem Instance = new Db_conn_bldr_wkr__mem(); Db_conn_bldr_wkr__mem() {}
}