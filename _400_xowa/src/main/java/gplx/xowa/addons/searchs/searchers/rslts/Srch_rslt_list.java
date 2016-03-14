package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_rslt_list {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	private final Hash_adp__int ids_hash = new Hash_adp__int();
	public int				Len()						{return hash.Len();}
	public boolean				Has(byte[] key)				{return hash.Has(key);}
	public Srch_rslt_row	Get_by(byte[] key)			{return (Srch_rslt_row)hash.Get_by(key);}
	public Srch_rslt_row	Get_at(int i)				{return (Srch_rslt_row)hash.Get_at(i);}
	public Srch_rslt_row	Ids__get(int id)			{return (Srch_rslt_row)ids_hash.Get_by(id);}
	public void				Ids__add(int id, Srch_rslt_row r)		{ids_hash.Add(id, r);}
	public void				Clear()						{hash.Clear(); ids_hash.Clear();}
	public void				Add(Srch_rslt_row row)		{hash.Add(row.Key, row); ids_hash.Add(row.Page_id, row);}
	public void				Sort()						{hash.Sort_by(Srch_rslt_row_sorter.Score_dsc);}
	public boolean				Done()						{return done;} public void Done_y_() {done = true;} private boolean done;			// true if all words searched
	public void				Copy_to(Srch_rslt_list trg_hash, int bgn, int end) {
		int hash_len = hash.Count();
		for (int i = bgn; i < end; ++i) {
			if (i >= hash_len) break;
			trg_hash.Add(this.Get_at(i));
		}
	}
}
class Srch_rslt_row_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Srch_rslt_row lhs = (Srch_rslt_row)lhsObj;
		Srch_rslt_row rhs = (Srch_rslt_row)rhsObj;
		return -Int_.Compare(lhs.Page_score, rhs.Page_score);
	}
	public static final Srch_rslt_row_sorter Score_dsc = new Srch_rslt_row_sorter();
}
