package gplx.dbs; import gplx.*;
public class Db_conn_bldr {
	private Db_conn_bldr_wkr wkr;
	public void Reg_default_sqlite()	{wkr = Db_conn_bldr_wkr__sqlite.Instance; wkr.Clear_for_tests();}
	public void Reg_default_mem()		{wkr = Db_conn_bldr_wkr__mem.Instance; wkr.Clear_for_tests();}
	public boolean Exists(Io_url url) {return wkr.Exists(url);}
	public Db_conn Get(Io_url url) {return wkr.Get(url);}
	public Db_conn New(Io_url url) {return wkr.New(url);}
	public Db_conn_bldr_data Get_or_new(Io_url url) {
		boolean exists = wkr.Exists(url);
		Db_conn conn = exists ? Get(url) : New(url);
		return new Db_conn_bldr_data(conn, exists);
	}
	public Db_conn Get_or_noop(Io_url url) {
		Db_conn rv = wkr.Get(url);
		return rv == null ? Db_conn_.Noop : rv;
	}
	public Db_conn Get_or_autocreate(boolean autocreate, Io_url url) {
		boolean exists = wkr.Exists(url);
		if (exists) return Get(url);
		if (autocreate) return New(url);
		else throw Err_.new_("dbs", "db does not exist", "url", url.Raw());
	}
        public static final Db_conn_bldr Instance = new Db_conn_bldr(); Db_conn_bldr() {}
}
