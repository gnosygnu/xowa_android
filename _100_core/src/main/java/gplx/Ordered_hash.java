package gplx;
import gplx.core.lists.*; /*EnumerAble,ComparerAble*/
public interface Ordered_hash extends Hash_adp, List_adp__getable {//_20110320
	void Add_at(int i, Object o);
	int Idx_of(Object item);
	void Sort();
	void Sort_by(ComparerAble comparer);
	void Resize_bounds(int i);
	Object To_ary(Class<?> t);
	Object To_ary_and_clear(Class<?> t);
	void Move_to(int src, int trg);
	void Lock();
}
