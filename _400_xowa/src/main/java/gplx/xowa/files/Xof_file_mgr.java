package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.dbs.*;
import gplx.xowa.files.commons.*; import gplx.xowa.files.exts.*; import gplx.xowa.files.caches.*; import gplx.xowa.files.imgs.*;
import gplx.xowa.bldrs.wms.*;
import gplx.xowa.xtns.math.*;
public class Xof_file_mgr implements GfoInvkAble {
	public Xoa_repo_mgr			Repo_mgr() {return repo_mgr;} private Xoa_repo_mgr repo_mgr;
	public Xof_img_mgr			Img_mgr() {return img_mgr;} private final Xof_img_mgr img_mgr = new Xof_img_mgr();
	public Xof_cache_mgr		Cache_mgr() {return cache_mgr;} private Xof_cache_mgr cache_mgr;
	public Xof_math_mgr			Math_mgr() {return math_mgr;} private final Xof_math_mgr math_mgr = new Xof_math_mgr();
	public Xof_rule_mgr			Ext_rules() {return ext_rules;} private final Xof_rule_mgr ext_rules = new Xof_rule_mgr();
	public Xoa_wmf_mgr			Wmf_mgr() {return wmf_mgr;} private Xoa_wmf_mgr wmf_mgr;
	public void Ctor_by_app(Xoae_app app) {
		Gfo_usr_dlg usr_dlg = app.Usr_dlg();
		this.cache_mgr = new Xof_cache_mgr(usr_dlg, app.Wiki_mgr(), repo_mgr);
		this.repo_mgr = new Xoa_repo_mgr(app.Fsys_mgr(), ext_rules); 
		this.wmf_mgr = new Xoa_wmf_mgr(usr_dlg, app.Wiki_mgr());
		img_mgr.Init_by_app(app.Wmf_mgr(), app.Prog_mgr());
		math_mgr.Init_by_app(app);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_repos))				return repo_mgr;
		else if	(ctx.Match(k, Invk_img_mgr))			return img_mgr;
		else if	(ctx.Match(k, Invk_ext_rules))			return ext_rules;
		else if	(ctx.Match(k, Invk_math))				return math_mgr;
		else if	(ctx.Match(k, Invk_download))			return wmf_mgr;	// NOTE: do not rename "download" to wmf_mgr
		else if	(ctx.Match(k, Invk_cache_mgr))			return cache_mgr;
		else											return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_repos = "repos", Invk_img_mgr= "img_mgr", Invk_ext_rules = "ext_rules", Invk_math = "math", Invk_download = "download", Invk_cache_mgr = "cache_mgr";
}
