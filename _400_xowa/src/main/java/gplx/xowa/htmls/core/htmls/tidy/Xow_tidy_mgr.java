package gplx.xowa.htmls.core.htmls.tidy; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.langs.htmls.*;
public class Xow_tidy_mgr implements Gfo_evt_itm {
	private Xoae_app app; private Xoa_tidy_mgr tidy_mgr;
	private boolean enabled = true; private Xoh_tidy_wkr wkr = Xoh_tidy_wkr_.Wkr_null; // TEST: set default wkr to null
	public Xow_tidy_mgr() {this.evt_mgr = new Gfo_evt_mgr(this);}
	public Gfo_evt_mgr Evt_mgr() {return evt_mgr;} private final    Gfo_evt_mgr evt_mgr;
	private void Wkr_(byte tid) {
		switch (tid) {
			case Xoh_tidy_wkr_.Tid_tidy:	wkr = tidy_mgr.Wkr_tidy(); break;
			case Xoh_tidy_wkr_.Tid_jtidy:	wkr = new Xoh_tidy_wkr_jtidy(); break;
			case Xoh_tidy_wkr_.Tid_null:	wkr = Xoh_tidy_wkr_.Wkr_null; break;
			default:						throw Err_.new_unhandled_default(tid);
		}
		wkr.Init_by_app(app);
	}
	public void Init_by_app(Xoae_app app) {
		this.app = app;
		this.tidy_mgr = app.Html_mgr().Tidy_mgr();
		Gfo_evt_mgr_.Sub_same_many(tidy_mgr, this, Xoa_tidy_mgr.Evt__enabled_changed, Xoa_tidy_mgr.Evt__engine_changed);
		this.enabled = tidy_mgr.Enabled();
		this.Wkr_(tidy_mgr.Wkr_tid());
	}		
	public void Run_tidy_html(Xoae_page page, Bry_bfr bfr, boolean indent) {
		if (!enabled) return;
		if (bfr.Len_eq_0()) return;	// document is empty; do not exec b/c tidy will never generate files for 0 len files, and previous file will remain; DATE:2014-06-04
		Tidy_wrap(bfr);
		wkr.Indent_(indent);
		wkr.Exec_tidy(page, bfr);
		Tidy_unwrap(bfr);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Xoa_tidy_mgr.Evt__enabled_changed))	this.enabled = m.ReadBool("v");
		else if	(ctx.Match(k, Xoa_tidy_mgr.Evt__engine_changed))	this.Wkr_(m.ReadByte("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	public static void Tidy_wrap(Bry_bfr bfr) {
		bfr.Insert_at(0, Wrap_bgn);
		bfr.Add(Wrap_end);
	}
	public static boolean Tidy_unwrap(Bry_bfr bfr) {
		byte[] bfr_bry = bfr.Bfr();
		int find = Bry_find_.Find_fwd(bfr_bry, Gfh_tag_.Body_lhs); if (find == Bry_find_.Not_found) return false;
		bfr.Delete_rng_to_bgn(find + Gfh_tag_.Body_lhs.length);
		find = Bry_find_.Find_bwd(bfr_bry, Gfh_tag_.Body_rhs, bfr.Len()); if (find == Bry_find_.Not_found) return false;
		bfr.Delete_rng_to_end(find);
		return true;
	}
	private static final    byte[]	// MW:includes/parser/Tidy.php|getWrapped
	  Wrap_bgn = Bry_.new_a7
	( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
	+ "<html>"
	+   "<head>"
	+     "<title>test</title>"
	+   "</head>"
	+   "<body>"
	)
	, Wrap_end = Bry_.new_a7
	(   "</body>"
	+ "</html>"
	);
}
