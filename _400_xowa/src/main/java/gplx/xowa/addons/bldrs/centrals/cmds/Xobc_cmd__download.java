package gplx.xowa.addons.bldrs.centrals.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.progs.*; import gplx.core.net.downloads.*;
public class Xobc_cmd__download extends Xobc_cmd__base {
	private final    String src_url; private final    Io_url trg_url;
	private final    long expd_size;
	private final    Http_download_wkr wkr;
	public Xobc_cmd__download(Xobc_task_mgr task_mgr, int task_id, int step_id, int cmd_id, String src_url, Io_url trg_url, long file_size_zip) {super(task_mgr, task_id, step_id, cmd_id);
		this.src_url = src_url; this.trg_url = trg_url; this.expd_size = file_size_zip;
		this.wkr = Http_download_wkr_.Proto.Make_new();
		this.Prog_data_end_(expd_size);
	}
	@Override public String		Cmd_type()	{return CMD_TYPE;}	public static final    String CMD_TYPE = "xowa.core.download";
	@Override public String		Cmd_name()	{return "download";}
	@Override public boolean		Cmd_suspendable() {return true;}

	@Override protected void Cmd_exec_hook(Xobc_cmd_ctx ctx) {
		if (wkr.Exec(this, src_url, trg_url, expd_size) == Gfo_prog_ui_.Status__fail)
			this.Cmd_exec_err_(wkr.Fail_msg());
		Gfo_log_.Instance.Info("xobc_cmd task download", "task_id", this.Task_id(), "step_id", this.Step_id(), "trg_url", trg_url, "trg_len", Io_mgr.Instance.QueryFil(trg_url).Size());
	}
	@Override public void Cmd_cleanup() {
		wkr.Exec_cleanup();
	}
	@Override protected long Load_checkpoint_hook() {
		return wkr.Checkpoint__load_by_trg_fil(trg_url);
	}
}
