package gplx.core.btries; import gplx.*; import gplx.core.*;
import gplx.core.threads.poolables.*;
public class Btrie_rv implements Gfo_poolable_itm {
	public Object Obj() {return obj;} private Object obj;
	public int Pos() {return pos;} private int pos;
	public Btrie_rv Init(int pos, Object obj) {
		this.obj = obj;
		this.pos = pos;
		return this;
	}

	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Btrie_rv rv = new Btrie_rv(); rv.pool_mgr = mgr; rv.pool_idx = idx; return rv;}
}
