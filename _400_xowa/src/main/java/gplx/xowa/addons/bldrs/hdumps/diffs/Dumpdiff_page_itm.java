package gplx.xowa.addons.bldrs.hdumps.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.hdumps.*;
class Dumpdiff_page_itm {
	public Dumpdiff_page_itm(int page_id, int ns_id, byte[] ttl_bry, int cur_db_id, int prv_db_id) {
		this.page_id = page_id;
		this.ns_id = ns_id;
		this.ttl_bry = ttl_bry;
		this.cur_db_id = cur_db_id;
		this.prv_db_id = prv_db_id;
	}
	public int Page_id() {return page_id;} private final    int page_id;
	public int Ns_id() {return ns_id;} private final    int ns_id;
	public byte[] Ttl_bry() {return ttl_bry;} private final    byte[] ttl_bry;
	public int Cur_db_id() {return cur_db_id;} private final    int cur_db_id;
	public int Prv_db_id() {return prv_db_id;} private final    int prv_db_id;
}
