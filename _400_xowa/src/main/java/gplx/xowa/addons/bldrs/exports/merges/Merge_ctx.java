package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*;
public class Merge_ctx {
	public void Init(Xow_wiki wiki, Db_conn pack_conn) {
		this.wiki = wiki;
		this.pack_conn = pack_conn;
	}
	public Xow_wiki Wiki() {return wiki;} private Xow_wiki wiki;
	public Db_conn Pack_conn() {return pack_conn;} private Db_conn pack_conn;
	public int Idx_cur() {return idx_cur;} private int idx_cur; public void Idx_cur_add_() {++idx_cur;}
	public int Idx_end = 70;
	public int Idx_gap = 10;
	public int Idx_nxt = 10;
	public boolean Heap__copy_to_wiki()		{return idx_cur == Idx_nxt || idx_cur == Idx_end;}
	public void Heap__increment_nxt()		{Idx_nxt += Idx_gap;}
	public boolean Heap__last_idx()			{return idx_cur == Idx_end;}
}
