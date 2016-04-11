package gplx.xowa.addons.apps.searchs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Srch_bldr_cmd extends Xob_cmd__base implements Xob_cmd {
	private int commit_interval = 100000, progress_interval = 10000;
	public Srch_bldr_cmd(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_text_search_cmd;}
	@Override public void Cmd_run() {
		if (!gplx.core.envs.Env_.Mode_testing()) wiki.Init_assert();
		new Srch_temp_tbl_wkr().Exec_by_cmd(wiki, commit_interval, progress_interval);
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_commit_interval_))		commit_interval = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_progress_interval_))		progress_interval = m.ReadInt("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final    String Invk_progress_interval_ = "progress_interval_", Invk_commit_interval_ = "commit_interval_";
}
