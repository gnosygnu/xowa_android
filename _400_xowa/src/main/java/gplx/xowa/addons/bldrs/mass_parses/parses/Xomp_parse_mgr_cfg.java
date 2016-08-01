package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.core.ios.streams.*;
class Xomp_parse_mgr_cfg implements Gfo_invk {
	public int Num_wkrs() {return num_wkrs;} private int num_wkrs = -1;	// use env.available_processors
	public int Num_pages_in_pool() {return num_pages_in_pool;} private int num_pages_in_pool = 1000;
	public int Num_pages_per_wkr() {return num_pages_per_wkr;} private int num_pages_per_wkr = 1000;
	public int Progress_interval() {return progress_interval;} private int progress_interval = 1000;
	public int Commit_interval() {return commit_interval;} private int commit_interval = 10000;
	public int Cleanup_interval() {return cleanup_interval;} private int cleanup_interval = 50;	// setting at 1000 uses lots of memory
	public boolean Hdump_enabled() {return hdump_enabled;} private boolean hdump_enabled = true;
	public boolean Hzip_enabled() {return hzip_enabled;} private boolean hzip_enabled = true;
	public boolean Hdiff_enabled() {return hdiff_enabled;} private boolean hdiff_enabled = true;
	public boolean Log_file_lnkis() {return log_file_lnkis;} private boolean log_file_lnkis = true;
	public boolean Load_all_templates() {return load_all_templates;} private boolean load_all_templates = true;
	public byte Zip_tid() {return zip_tid;} private byte zip_tid = Io_stream_.Tid_gzip;
	public void Init(Xowe_wiki wiki) {
		if (num_wkrs == -1) num_wkrs = gplx.core.envs.Env_.System_cpu_count();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__num_wkrs_))				num_wkrs = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_in_pool_))	num_pages_in_pool = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_per_wkr_))	num_pages_per_wkr = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__num_pages_per_wkr_))	num_pages_per_wkr = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__progress_interval_))	progress_interval = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__commit_interval_))		commit_interval = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__cleanup_interval_))		cleanup_interval = m.ReadInt("v");
		else if	(ctx.Match(k, Invk__hdump_enabled_))		hdump_enabled = m.ReadBool("v");
		else if	(ctx.Match(k, Invk__hzip_enabled_))			hzip_enabled = m.ReadBool("v");
		else if	(ctx.Match(k, Invk__hdiff_enabled_))		hdiff_enabled = m.ReadBool("v");
		else if	(ctx.Match(k, Invk__zip_tid_))				zip_tid = m.ReadByte("v");
		else if	(ctx.Match(k, Invk__load_all_templates_))	load_all_templates = m.ReadYn("v");
		else if	(ctx.Match(k, Invk__manual_now_))			Datetime_now.Manual_and_freeze_(m.ReadDate("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Invk__num_wkrs_ = "num_wkrs_", Invk__num_pages_in_pool_ = "num_pages_in_pool_", Invk__num_pages_per_wkr_ = "num_pages_per_wkr_"
	, Invk__progress_interval_ = "progress_interval_", Invk__commit_interval_ = "commit_interval_", Invk__cleanup_interval_ = "cleanup_interval_"
	, Invk__hdump_enabled_ = "hdump_enabled_", Invk__hzip_enabled_ = "hzip_enabled_", Invk__hdiff_enabled_ = "hdiff_enabled_", Invk__zip_tid_ = "zip_tid_"
	, Invk__load_all_templates_ = "load_all_templates_", Invk__manual_now_ = "manual_now_"
	;
}
