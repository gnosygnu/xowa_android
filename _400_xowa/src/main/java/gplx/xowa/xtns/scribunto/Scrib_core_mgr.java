package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*;
public class Scrib_core_mgr {
	private static final    List_adp cores = List_adp_.New();
	public Scrib_core Core() {return core;} private Scrib_core core;
	public Scrib_core Core_make(Xop_ctx ctx) {
		core = new Scrib_core(ctx.App(), ctx);
		core_invalidate_when_page_changes = false;
		synchronized (cores) {
			cores.Add(core);
		}
		return core;
	}
	public void Core_term() {
		if (core != null) core.Term();
		synchronized (cores) {
			cores.Del(core);
		}
		core = null;
	}
	public void Core_invalidate_when_page_changes() {core_invalidate_when_page_changes = true;}	private boolean core_invalidate_when_page_changes;
	public void Core_page_changed(Xoae_page page) {
		if (	core != null						// core explicitly invalidated
			||	core_invalidate_when_page_changes	// core marked invalidated b/c of error in {{#invoke}} but won't be regen'd until page changes; invalidate now; PAGE:th.d:all; DATE:2014-10-03
			) {
			if (	core != null										// null check
				&&	Bry_.Eq(page.Wiki().Domain_bry(), core.Cur_wiki())	// current page is in same wiki as last page
				&&	!core_invalidate_when_page_changes					// if core_invalidate_when_page_changes, then must call Core_term
				)
				core.When_page_changed(page);
			else														// current page is in different wiki
				Core_term();											// invalidate scrib engine; note that lua will cache chunks by Module name and two modules in two different wikis can have the same name, but different data: EX:Module:Citation/CS1/Configuration and enwiki / zhwiki; DATE:2014-03-21
			core_invalidate_when_page_changes = false;
		}
	}

	public static void Term_all() {
		synchronized (cores) {
			int cores_len = cores.Len();
			for (int i = 0; i < cores_len; ++i) {
				Scrib_core core = (Scrib_core)cores.Get_at(i);
				core.Term();
			}
			cores.Clear();
		}
	}
}
