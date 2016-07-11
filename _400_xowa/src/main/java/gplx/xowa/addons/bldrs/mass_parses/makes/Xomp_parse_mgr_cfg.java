package gplx.xowa.addons.bldrs.mass_parses.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
class Xomp_parse_mgr_cfg implements Gfo_invk {
	public Io_url Root_dir() {return root_dir;} private Io_url root_dir;
	public int Num_wkrs() {return num_wkrs;} private int num_wkrs = -1;
	public int Num_pages_in_pool() {return num_pages_in_pool;} private int num_pages_in_pool = 1000;
	public int Num_pages_per_wkr() {return num_pages_per_wkr;} private int num_pages_per_wkr = 1000;
	public void Init(Xowe_wiki wiki) {
		if (root_dir == null) root_dir = wiki.Fsys_mgr().Root_dir().GenSubDir_nest("tmp", "xomp");
		if (num_wkrs == -1) num_wkrs = gplx.core.envs.Env_.System_cpu_count();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__num_wkrs_))				num_wkrs = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_in_pool_))	num_pages_in_pool = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_per_wkr_))	num_pages_per_wkr = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_per_wkr_))	num_pages_per_wkr = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__root_dir_))				root_dir = m.ReadIoUrl("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk__num_wkrs_ = "num_wkrs_", Invk__num_pages_in_pool_ = "num_pages_in_pool_", Invk__num_pages_per_wkr_ = "num_pages_per_wkr_"
	, Invk__root_dir_ = "root_dir_"
	;
}
