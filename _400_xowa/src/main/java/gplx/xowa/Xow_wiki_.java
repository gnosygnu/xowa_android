package gplx.xowa; import gplx.*;
public class Xow_wiki_ {
	public static void Rls_mem(Xowe_wiki wiki, boolean clear_ctx) {
		wiki.Appe().Free_mem(clear_ctx);
		wiki.Cache_mgr().Free_mem_all();
		gplx.xowa.xtns.scribunto.Scrib_core.Core_invalidate();
	}
}
