package gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*;
public class Xoctg_catpage_itm_sorter__sort_key implements gplx.core.lists.ComparerAble, gplx.core.lists.binary_searches.Binary_comparer {
	public int compare(Object lhsObj, Object rhsObj) {
		Xoctg_catpage_itm lhs = (Xoctg_catpage_itm)lhsObj;
		Xoctg_catpage_itm rhs = (Xoctg_catpage_itm)rhsObj;
		return Bry_.Compare(lhs.Sort_key(), rhs.Sort_key());
	}
	public int Compare_val_to_obj(Object val, Object obj) {
		Xoctg_catpage_itm itm = (Xoctg_catpage_itm)obj;
		return Bry_.Compare((byte[])val, itm.Sort_key());
	}
	public static final    Xoctg_catpage_itm_sorter__sort_key Sorter = new Xoctg_catpage_itm_sorter__sort_key(); 
}
