package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*; import gplx.xowa.addons.bldrs.exports.utls.*;
public class Merge2_heap_db implements Merge2_trg_itm {
	public Merge2_heap_db(Split_tbl tbl, Dbmeta_fld_list flds, int idx, Io_url url, Db_conn conn) {
		this.tbl = tbl; this.flds = flds; this.idx = idx;
		this.url = url; this.conn = conn; 
	}
	public Split_tbl		Tbl()	{return tbl;}	private final    Split_tbl tbl;
	public Dbmeta_fld_list	Flds()	{return flds;}	private final    Dbmeta_fld_list flds;
	public int				Idx()	{return idx;}	private final    int idx;
	public Io_url			Url()	{return url;}	private final    Io_url url;
	public Db_conn			Conn()	{return conn;}	private final    Db_conn conn;
}
