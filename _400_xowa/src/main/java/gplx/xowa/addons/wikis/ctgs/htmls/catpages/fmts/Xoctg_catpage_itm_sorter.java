package gplx.xowa.addons.wikis.ctgs.htmls.catpages.fmts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*;
import gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms.*;
class Xoctg_catpage_itm_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Xoctg_catpage_itm lhs = (Xoctg_catpage_itm)lhsObj;
		Xoctg_catpage_itm rhs = (Xoctg_catpage_itm)rhsObj;
		return Bry_.Compare(lhs.Sort_key(), rhs.Sort_key());
	}
	public static final    Xoctg_catpage_itm_sorter Sorter = new Xoctg_catpage_itm_sorter(); 
}
