package gplx.core.threads; import gplx.*; import gplx.core.*;
class Gfo_async_cmd_mkr {
//		private Gfo_async_cmd_itm[] free = Gfo_async_cmd_itm.Ary_empty, used = Gfo_async_cmd_itm.Ary_empty;
//		private int free_bgn = 0, free_end = 0, ary_len = 0;
//		public void Resize(int v) {
//			free = (Gfo_async_cmd_itm[])Array_.Resize(free, v);
//			used = (Gfo_async_cmd_itm[])Array_.Resize(used, v);
//			ary_len = v;
//		}
	public Gfo_async_cmd_itm Get(GfoInvkAble invk, String invk_key, Object... args) {
		Gfo_async_cmd_itm rv = new Gfo_async_cmd_itm();
		rv.Init(invk, invk_key, args);
		return rv;
	}
	public void Rls(Gfo_async_cmd_itm cmd) {
	}
}
