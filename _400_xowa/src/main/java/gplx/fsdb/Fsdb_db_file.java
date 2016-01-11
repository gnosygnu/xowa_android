package gplx.fsdb; import gplx.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*;
public class Fsdb_db_file {
	public Fsdb_db_file(Io_url url, Db_conn conn) {
		this.url = url; this.conn = conn;
		this.tbl__core_cfg = new Db_cfg_tbl(conn, "xowa_cfg");
	}
	public Io_url				Url()			{return url;}				private final Io_url url;
	public Db_conn				Conn()			{return conn;}				private final Db_conn conn;		
	public Db_cfg_tbl			Tbl__cfg()		{return tbl__core_cfg;}		private final Db_cfg_tbl	tbl__core_cfg;
}
