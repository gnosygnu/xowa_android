package gplx;
public interface Hash_adp extends gplx.core.lists.EnumerAble {//_20110428
	int		Count();
	boolean	Has(Object key);
	Object	Get_by(Object key);
	Object	Get_by_or_fail(Object key);
	void	Add(Object key, Object val);
	void	Add_as_key_and_val(Object val);
	boolean	Add_if_dupe_use_1st(Object key, Object val);
	void	Add_if_dupe_use_nth(Object key, Object val);
	void	Del(Object key);
	void	Clear();
}
