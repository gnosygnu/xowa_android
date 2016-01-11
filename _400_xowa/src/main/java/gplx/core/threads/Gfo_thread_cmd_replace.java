package gplx.core.threads; import gplx.*; import gplx.core.*;
import gplx.core.brys.fmtrs.*;
import gplx.gfui.*;
public class Gfo_thread_cmd_replace implements Gfo_thread_cmd {
	public Gfo_thread_cmd Init(Gfo_usr_dlg usr_dlg, Gfui_kit kit, Io_url fil) {
		this.usr_dlg = usr_dlg; this.kit = kit; this.fil = fil;
		return this;
	}	Gfui_kit kit; Gfo_usr_dlg usr_dlg; Io_url fil;
	public GfoInvkAble Owner() {return owner;} public Gfo_thread_cmd_replace Owner_(GfoInvkAble v) {owner = v; return this;} GfoInvkAble owner;
	public Bry_fmtr_eval_mgr Url_eval_mgr() {return url_eval_mgr;} public Gfo_thread_cmd_replace Url_eval_mgr_(Bry_fmtr_eval_mgr v) {url_eval_mgr = v; return this;} Bry_fmtr_eval_mgr url_eval_mgr;
	public String Async_key() {return KEY;}
	public void Cmd_ctor() {}
	public Gfo_thread_cmd Async_next_cmd() {return next_cmd;} public void Async_next_cmd_(Gfo_thread_cmd v) {next_cmd = v;} Gfo_thread_cmd next_cmd;
	public int Async_sleep_interval()	{return Gfo_thread_cmd_.Async_sleep_interval_1_second;}
	public boolean Async_prog_enabled()	{return false;}
	@gplx.Virtual public byte Async_init() {
		if (!Io_mgr.Instance.ExistsFil(fil)) {kit.Ask_ok(GRP_KEY, "file_missing", "File does not exist: '~{0}'", fil.Raw()); return Gfo_thread_cmd_.Init_cancel_step;}
		return Gfo_thread_cmd_.Init_ok;
	}
	public boolean Async_term() {return true;}
	public void Async_prog_run(int async_sleep_sum) {}
	public boolean Async_running() {return false;} 
	@gplx.Virtual public void Async_run() {Exec_find_replace();}	// NOTE: do not run async; if multiple commands for same file then they will not always work
	public void Exec_find_replace() {
		String raw = Io_mgr.Instance.LoadFilStr(fil);
		int pairs_len = pairs.Count();
		for (int i = 0; i < pairs_len; i++) {
			KeyVal kv = (KeyVal)pairs.Get_at(i);
			raw = String_.Replace(raw, kv.Key(), kv.Val_to_str_or_null());
		}
		Io_mgr.Instance.SaveFilStr(fil, raw);
		usr_dlg.Prog_many(GRP_KEY, "done", "replace completed: ~{0} ~{1}", fil.Raw(), pairs_len);
	}
	public List_adp pairs = List_adp_.new_();
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_async_bgn))				Exec_find_replace();
		else if	(ctx.Match(k, Invk_owner))					return owner;
		else if	(ctx.Match(k, Invk_fil_))					fil = Bry_fmtr_eval_mgr_.Eval_url(url_eval_mgr, m.ReadBry("v"));
		else if	(ctx.Match(k, Invk_add))					pairs.Add(KeyVal_.new_(m.ReadStr("find"), m.ReadStr("replace")));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_async_bgn = "async_bgn", Invk_owner = "owner", Invk_fil_ = "fil_", Invk_add = "add";
	static final String GRP_KEY = "gfo.thread.file.download";
	public static final String KEY = "text.replace";
}
