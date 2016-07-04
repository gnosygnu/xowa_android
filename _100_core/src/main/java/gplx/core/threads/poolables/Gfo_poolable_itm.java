package gplx.core.threads.poolables; import gplx.*; import gplx.core.*; import gplx.core.threads.*;
public interface Gfo_poolable_itm {
	Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args);
	void				Pool__rls();
}
