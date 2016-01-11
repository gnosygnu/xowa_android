package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.lists.*; /*ComparerAble*/
public interface GfoNdeList {//_20110416
	int Count();
	GfoNde FetchAt_asGfoNde(int index);
	void Add(GfoNde rcd);
	void Del(GfoNde rcd);
	void Clear();
	void Sort_by(ComparerAble comparer);
}
