package gplx.dbs; import gplx.*;
public class Db_idx_itm {
	public String Xto_sql() {return sql;} private String sql;
	public static Db_idx_itm sql_(String sql) {
		Db_idx_itm rv = new Db_idx_itm();
		rv.sql = sql;
		return rv;
	}
	public static Db_idx_itm[] ary_sql_(String... ary) {
		int len = ary.length;
		Db_idx_itm[] rv = new Db_idx_itm[len];
		for (int i = 0; i < len; i++) {
			rv[i] = sql_(ary[i]);
		}
		return rv;
	}
}
