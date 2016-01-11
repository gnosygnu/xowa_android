package gplx.dbs.engines.mems; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Mem_row implements GfoInvkAble {
	private final Ordered_hash hash = Ordered_hash_.New();
	public Object	Get_by(String key) {return hash.Get_by(key);}
	public Object	Get_at(int i) {return hash.Get_at(i);}
	public void		Set_by(String key, Object val)		{hash.Add_if_dupe_use_nth(key, val);}
	public void		Add(String key, Object val)			{hash.Add(key, val);}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		Object rv = Get_by(k);
		if (rv == null) return GfoInvkAble_.Rv_unhandled;
		return rv;
	}
}
