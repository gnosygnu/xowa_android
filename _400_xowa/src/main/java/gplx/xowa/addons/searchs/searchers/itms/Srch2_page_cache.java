package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_page_cache {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public void Clear() {hash.Clear();}
	public Srch2_rslt_row Get_or_null(byte[] key) {return (Srch2_rslt_row)hash.Get_by(key);}
	public void Add(Srch2_rslt_row row) {hash.Add(row.Key, row);}
}
