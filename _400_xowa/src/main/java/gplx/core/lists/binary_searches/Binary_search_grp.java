package gplx.core.lists.binary_searches; import gplx.*; import gplx.core.*; import gplx.core.lists.*;
interface Binary_search_grp {
	int Len();
	Object Get_at(int i);
}
class Binary_search_grp__ary implements Binary_search_grp {
	private final    Object[] ary;
	public Binary_search_grp__ary(Object[] ary) {this.ary = ary;}
	public int Len() {return ary.length;}
	public Object Get_at(int i) {return ary[i];}
}
class Binary_search_grp__list implements Binary_search_grp {
	private final    List_adp list;
	public Binary_search_grp__list(List_adp list) {this.list = list;}
	public int Len() {return list.Len();}
	public Object Get_at(int i) {return list.Get_at(i);}
}
