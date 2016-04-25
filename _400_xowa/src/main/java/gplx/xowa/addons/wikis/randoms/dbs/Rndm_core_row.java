package gplx.xowa.addons.wikis.randoms.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.randoms.*;
public class Rndm_core_row {
	public void Load(int uid, String where_sql, int total, int interval) {this.uid = uid; this.where_sql = where_sql; this.total = total; this.interval = interval;}
	public int uid;
	public String where_sql;
	public int total;
	public int interval;
}
