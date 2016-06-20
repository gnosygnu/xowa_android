package gplx.core.threads; import gplx.*; import gplx.core.*;
class Thread_halt_wkr implements Gfo_invk {
	private final    Thread_adp_mgr mgr;
	private final    Thread_adp thread; private final    String thread_key;  private final    Thread_halt_cbk cbk;
	private final    long bgn_time;
	private final    int sleep_time, quit_time;
	public Thread_halt_wkr(Thread_adp_mgr mgr, Thread_halt_itm itm, Thread_halt_cbk cbk, int sleep_time, int quit_time) {
		this.mgr = mgr; this.thread = itm.Thread; this.thread_key = itm.Key; this.cbk = cbk;
		this.sleep_time = sleep_time; this.quit_time = quit_time;
		this.bgn_time = gplx.core.envs.Env_.TickCount();
	}
	private void Halt() {
		// first, cancel the thread
		thread.Thread__cancel();

		// now check if canceled; interrupt if not;
		while (true) {
			long time_now = gplx.core.envs.Env_.TickCount();
			boolean halted = false, interrupted = false;
			if (thread.Thread__is_alive()) {					// thread is still alive
				if	(	!thread.Thread__cancelable()			// itm is not cancelable
					||	time_now > bgn_time + quit_time			// itm is cancelable, but too much time passed
				) {
					thread.Thread__interrupt();					// interrupt it
					halted = interrupted = true;
				}
			}
			else
				halted = true;

			if (halted) {		// thread halted; call cbk;
				cbk.Thread__on_halt(interrupted);
				mgr.Del(thread_key);
				break;
			}
			else				// else sleep and try again
				Thread_adp_.Sleep(sleep_time);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__halt))		Halt();
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	public static final String Invk__halt = "halt";
}
