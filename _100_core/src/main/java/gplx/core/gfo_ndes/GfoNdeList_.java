package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.lists.*; /*ComparerAble*/
public class GfoNdeList_ {//_20110416
	public static final    GfoNdeList Null = new GfoNdeList_null();
	public static GfoNdeList new_() {return new GfoNdeList_base();}
}
class GfoNdeList_base implements GfoNdeList {//_20110416
	public int Count() {return list.Count();}
	public GfoNde FetchAt_asGfoNde(int i) {return (GfoNde)list.Get_at(i);}
	public void Add(GfoNde rcd) {list.Add(rcd);}
	public void Del(GfoNde rcd) {list.Del(rcd);}
	public void Clear() {list.Clear();}
	public void Sort_by(ComparerAble comparer) {list.Sort_by(comparer);}
	List_adp list = List_adp_.New();
}
class GfoNdeList_null implements GfoNdeList {//_20110416
	public int Count() {return 0;}
	public GfoNde FetchAt_asGfoNde(int index) {return null;}
	public void Add(GfoNde rcd) {}
	public void Del(GfoNde rcd) {}
	public void Clear() {}
	public void Sort_by(ComparerAble comparer) {}
}
