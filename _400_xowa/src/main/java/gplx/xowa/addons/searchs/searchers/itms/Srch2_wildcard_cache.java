package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_wildcard_cache {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public void Clear() {hash.Clear();}
	public Srch2_wildcard_row Get_or_make(byte[] key) {
		Srch2_wildcard_row rv = (Srch2_wildcard_row)hash.Get_by(key);
		if (rv == null) {
			rv = new Srch2_wildcard_row(key);
			this.Add(rv);
		}
		return rv;
	}
	public void Add(Srch2_wildcard_row row) {hash.Add(row.Text, row);}
}
