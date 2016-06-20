package gplx.xowa.parsers.lnkis.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
public class Xoc_lnki_cfg implements Gfo_invk {
	public Xoc_lnki_cfg(Xowe_wiki wiki) {xwiki_repo_mgr = new Xoc_xwiki_repo_mgr(wiki);}
	public Xoc_xwiki_repo_mgr Xwiki_repo_mgr() {return xwiki_repo_mgr;} private Xoc_xwiki_repo_mgr xwiki_repo_mgr;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_xwiki_repos))			return xwiki_repo_mgr;
		else return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_xwiki_repos = "xwiki_repos";
}
