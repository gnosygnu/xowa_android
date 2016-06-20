package gplx.xowa.addons.bldrs.centrals.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.gfobjs.*; import gplx.core.progs.*; import gplx.core.progs.rates.*;
public abstract class Xobc_cmd__base implements Xobc_cmd_itm {
	private final    Xobc_task_mgr task_mgr; 		
	private final    Gfo_rate_list rate_list; private final    long notify_delay = 1000; 
	private final    double delta_threshold = .25d;	// allow variance of up to 25% before updating rate
	private long time_prv;
	private double rate_cur;
	public Xobc_cmd__base(Xobc_task_mgr task_mgr, int task_id, int step_id, int cmd_id) {
		this.task_mgr = task_mgr; this.task_id = task_id; this.step_id = step_id; this.cmd_id = cmd_id;
		this.cmd_uid = String_.Concat_with_str(":", Int_.To_str(task_id), Int_.To_str(step_id), Int_.To_str(cmd_id));
		this.rate_list = task_mgr == null ? null : task_mgr.Rate_mgr().Get_or_new(this.Cmd_type());
	}
	public long				Prog_data_cur()	{return data_cur;}		private long data_cur;								public void Prog_data_cur_(long v) {this.data_cur = v;}
	public long				Prog_data_end()	{return data_end;}		private long data_end;								public void Prog_data_end_(long v) {this.data_end = v;}
	public byte				Prog_status()	{return status;}		private byte status = Gfo_prog_ui_.Status__init;	public void Prog_status_(byte v) {status = v;}
	public boolean			Canceled()		{return status == Gfo_prog_ui_.Status__suspended;}
	public void				Cancel()		{status = Gfo_prog_ui_.Status__suspended;}

	public int				Task_id()		{return task_id;} private final    int task_id;
	public int				Step_id()		{return step_id;} private final    int step_id;
	public int				Cmd_id()		{return cmd_id;} private final    int cmd_id;
	public abstract String	Cmd_type();
	public abstract String	Cmd_name();
	@gplx.Virtual public boolean		Cmd_suspendable() {return false;}
	public String			Cmd_uid()		{return cmd_uid;} private final    String cmd_uid;
	@gplx.Virtual public String	Cmd_fallback()	{return this.Cmd_type();}
	@gplx.Virtual public void		Cmd_clear()		{// called when restarting failed task
		this.status = Gfo_prog_ui_.Status__init;
		this.cmd_exec_err = null;
	}	

	public void Cmd_exec(Xobc_cmd_ctx ctx) {
		// rate_list.Clear();
		// this.rate_cur = 0;
		this.time_prv = gplx.core.envs.Env_.TickCount();
		this.status = Gfo_prog_ui_.Status__working;
		this.Cmd_exec_hook(ctx);
		switch (status) {
			case Gfo_prog_ui_.Status__suspended:	task_mgr.Work_mgr().On_suspended(this); break;
			case Gfo_prog_ui_.Status__fail:			task_mgr.Work_mgr().On_fail(this, cmd_exec_err); break;
			case Gfo_prog_ui_.Status__working:
				this.Prog_notify_and_chk_if_suspended(data_end, data_end);	// fire one more time for 100%; note that 100% may not fire due to timer logic below
				task_mgr.Work_mgr().On_done(this); 
				break;
		}
	}
	protected abstract void Cmd_exec_hook(Xobc_cmd_ctx ctx);
	protected void Cmd_exec_err_(String v) {this.status = Gfo_prog_ui_.Status__fail; this.cmd_exec_err = v;} private String cmd_exec_err;
	@gplx.Virtual public void Cmd_cleanup() {}

	public Gfobj_nde Save_to(Gfobj_nde nde) {
		nde.Add_int ("task_id"				, task_id);
		nde.Add_int ("step_id"				, step_id);
		nde.Add_int	("cmd_id"				, cmd_id);
		nde.Add_str	("cmd_type"				, this.Cmd_type());
		nde.Add_str	("cmd_name"				, this.Cmd_name());
		nde.Add_bool("cmd_suspendable"		, this.Cmd_suspendable());
		nde.Add_byte("prog_status"			, this.Prog_status());
		nde.Add_long("prog_data_cur"		, this.Prog_data_cur());
		nde.Add_long("prog_data_end"		, this.Prog_data_end());
		nde.Add_long("prog_time_end"		, 0);
		return nde;
	}
	public void				Load_checkpoint() {
		long data_cur = this.Load_checkpoint_hook();
		this.Prog_data_cur_(data_cur);
		if (data_cur > 0)
			this.Prog_status_(Gfo_prog_ui_.Status__working);	// set status to working, else js won't warn about accidental removal
	}
	@gplx.Virtual protected long	Load_checkpoint_hook() {return 0;}

	public boolean Prog_notify_and_chk_if_suspended(long new_data_cur, long new_data_end) {
		if (status == Gfo_prog_ui_.Status__suspended) return true;	// task paused by ui; exit now;
		long time_cur = gplx.core.envs.Env_.TickCount();
		if (time_cur < time_prv + notify_delay) return false;		// message came too soon. ignore it

		// update rate
		double rate_now = (rate_list.Add(new_data_cur - data_cur, (time_cur - time_prv))) * 1000;
		double delta = Math_.Abs_double((rate_now - rate_cur) / rate_cur);
		if (	rate_cur == 0					// rate not set
			||	delta > delta_threshold) {		// rate_now is at least 25% different than rate_prv
			if (delta > delta_threshold * 2)	// rate_now is > 50% different
				rate_cur = rate_now;			// update it now
			else {
				double rate_new = ((rate_now - rate_cur) * .05) + rate_cur;	// calc new rate as 5% of difference
                    Tfds.Dbg(delta, rate_now, rate_cur, rate_new);
				rate_cur = rate_new;
			}
		}

		// update prog vals
		this.time_prv = time_cur;
		this.data_cur = new_data_cur;
		this.data_end = new_data_end;

		task_mgr.Send_json("xo.bldr.work.prog__update__recv", Gfobj_nde.New()
			.Add_int ("task_id", task_id).Add_long("prog_data_cur", data_cur).Add_long("prog_data_end", data_end).Add_int("prog_rate", (int)rate_cur));
		return false;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk__exec))				Cmd_exec((Xobc_cmd_ctx)m.ReadObj("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	public static final    String Invk__exec = "exec";
	public static final int Seqn__0 = 0;
	public static Gfo_rate_mgr New_rate_mgr() {
		Gfo_rate_mgr rv = new Gfo_rate_mgr(128);
		rv.Add_new(Xobc_cmd__verify_dir.CMD_TYPE);
		rv.Add_new(Xobc_cmd__verify_fil.CMD_TYPE);
		rv.Add_new(Xobc_cmd__download.CMD_TYPE);
		rv.Add_new(Xobc_cmd__unzip.CMD_TYPE);
		return rv;
	}
}
