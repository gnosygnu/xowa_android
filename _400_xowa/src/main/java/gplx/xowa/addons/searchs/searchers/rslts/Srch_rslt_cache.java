package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_rslt_cache {
	private final    Hash_adp_bry hash = Hash_adp_bry.cs();
	public void Clear() {hash.Clear();}
	public Srch_rslt_list Get_or_new(byte[] key) {
		Srch_rslt_list rv = (Srch_rslt_list)hash.Get_by(key);
		if (rv == null) {
			rv = new Srch_rslt_list();
			hash.Add(key, rv);
		}
		rv.Rslts_are_first = true;
		rv.Rslts_are_enough = false;
		return rv;
	}
}
