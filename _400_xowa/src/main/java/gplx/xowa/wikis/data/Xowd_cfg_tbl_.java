package gplx.xowa.wikis.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*;
public class Xowd_cfg_tbl_ {
	public static final String Tbl_name = "xowa_cfg";
	public static Db_cfg_tbl New(gplx.dbs.Db_conn conn) {
		return new Db_cfg_tbl(conn, Tbl_name);
	}
	public static Db_cfg_tbl Get_or_null(gplx.dbs.Db_conn conn) {
		return conn.Meta_tbl_exists(Tbl_name) ? new Db_cfg_tbl(conn, Tbl_name) : null;
	}
}
