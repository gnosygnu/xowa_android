package gplx.xowa.specials.randoms; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;
public class Xob_rnd_cmd implements Xob_cmd {
	private final    Xowe_wiki wiki;
	private int rndm_uid = 0; private String rndm_where_sql = "AND ~{page_namespace} = 0 AND ~{page_is_redirect} = 0"; private int rndm_interval = 1000;
	public Xob_rnd_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.wiki = wiki;}
	public String Cmd_key()		{return Xob_cmd_keys.Key_util_random;}
	public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	public void Cmd_run() {
		wiki.Special_mgr().Page_random().Mgr().Rebuild(rndm_uid, rndm_where_sql, rndm_interval);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_rndm_uid_))			rndm_uid = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_rndm_where_sql_))	rndm_where_sql = m.ReadStr("v");
		else if	(ctx.Match(k, Invk_rndm_interval_))		rndm_interval = m.ReadInt("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_rndm_uid_ = "rndm_uid_", Invk_rndm_where_sql_ = "rndm_where_sql_", Invk_rndm_interval_ = "rndm_interval_";

	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_end() {}
	public void Cmd_term() {}
}
