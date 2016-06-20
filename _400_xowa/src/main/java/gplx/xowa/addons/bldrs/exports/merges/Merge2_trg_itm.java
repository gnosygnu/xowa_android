package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*;
public interface Merge2_trg_itm {
	int			Idx();
	Db_conn		Conn();
}
class Merge2_trg_itm__wiki implements Merge2_trg_itm {
	public Merge2_trg_itm__wiki(int idx, Db_conn conn) {this.idx = idx; this.conn = conn;}
	public int			Idx()	{return idx;}	private final    int idx;
	public Db_conn		Conn()	{return conn;}	private final    Db_conn conn;
}
