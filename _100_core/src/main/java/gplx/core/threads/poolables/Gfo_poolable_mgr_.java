package gplx.core.threads.poolables; import gplx.*; import gplx.core.*; import gplx.core.threads.*;
public class Gfo_poolable_mgr_ {
	public static Gfo_poolable_mgr New(int len, int max, Gfo_poolable_itm prototype) {return new Gfo_poolable_mgr(prototype, Object_.Ary_empty, len, max);}
	public static Gfo_poolable_mgr New(int len, int max, Gfo_poolable_itm prototype, Object[] make_args) {return new Gfo_poolable_mgr(prototype, make_args, len, max);}
}
