package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
class Xomp_prog_mgr {
	private final    Object thread_lock = new Object();
	private int progress_interval;
	private int pages_done, pages_total;
	private long time_bgn, time_prv, time_done;		
	private final    Bry_bfr prog_bfr = Bry_bfr_.New();
	public void Init(int pages_total, int progress_interval) {
		this.progress_interval = progress_interval;
		this.time_bgn = this.time_prv = gplx.core.envs.Env_.TickCount();
		this.pages_total = pages_total;
	}
	public void Mark_done(int id) {
		synchronized (thread_lock) {
			pages_done += 1;
			if (pages_done % progress_interval == 0) {
				long time_cur = gplx.core.envs.Env_.TickCount();
				int pages_left = pages_total - pages_done;
				time_done += (time_cur - time_prv);
				double rate_cur = pages_done / (time_done / Time_span_.Ratio_f_to_s);
				String time_past = gplx.xowa.addons.bldrs.centrals.utils.Time_dhms_.To_str(prog_bfr, (int)((time_cur - time_bgn) / 1000), true, 0);
				String time_left = gplx.xowa.addons.bldrs.centrals.utils.Time_dhms_.To_str(prog_bfr, (int)(pages_left / rate_cur), true, 0);
				Gfo_usr_dlg_.Instance.Prog_many("", "", "done=~{1} left=~{2} rate=~{3} time_past=~{4} time_left=~{5}", id, pages_done, pages_left, (int)rate_cur, time_past, time_left);
				time_prv = time_cur;
			}
		}
	}
}
