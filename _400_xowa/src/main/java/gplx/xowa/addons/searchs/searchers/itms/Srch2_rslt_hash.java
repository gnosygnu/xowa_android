package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.addons.searchs.searchers.wkrs.*;
public class Srch2_rslt_hash {
	private final Ordered_hash hash = Ordered_hash_.New_bry(); private int hash_len;
	public Srch2_rslt_hash(int request_count) {
		this.Request_count = request_count;
	}
	public final int Request_count;
	public int Stage_id;
	public int Len() {return hash_len;}
	public boolean Has(byte[] key) {return hash.Has(key);}
	public Srch2_rslt_row Get_at(int i) {return (Srch2_rslt_row)hash.Get_at(i);}
	public void Add(Srch2_rslt_row row) {
		hash.Add(row.Key, row); ++hash_len;
//			return hash_len >= Request_count;
	}
	public void Merge(Srch2_rslt_hash lhs, Srch2_rslt_hash rhs) {
		Add(lhs);
		Add(rhs);
	}
	public void Sort() {hash.Sort_by(Srch2_rslt_row_sorter__score.Instance);}
	private void Add(Srch2_rslt_hash rslts) {
		int len = rslts.hash_len;
		for (int i = 0; i < len; ++i) {
			Srch2_rslt_row rslt = rslts.Get_at(i);
			hash.Add(rslt.Key, rslt); ++hash_len;
		}
	}
}
class Srch2_rslt_row_sorter__score implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch2_rslt_row lhs = (Srch2_rslt_row)lhsObj;
		Srch2_rslt_row rhs = (Srch2_rslt_row)rhsObj;
		return -Int_.Compare(lhs.Page_score, rhs.Page_score);
	}
        public static final Srch2_rslt_row_sorter__score Instance = new Srch2_rslt_row_sorter__score(); Srch2_rslt_row_sorter__score() {}
}
