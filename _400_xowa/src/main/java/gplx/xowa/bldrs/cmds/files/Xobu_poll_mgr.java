package gplx.xowa.bldrs.cmds.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
public class Xobu_poll_mgr implements GfoInvkAble {
	public Xobu_poll_mgr(Xoae_app app) {this.app = app;} private Xoae_app app;
	public int Poll_interval() {return poll_interval;} private int poll_interval = 1000;
	private Io_url poll_file;
	public void Poll() {
		if (poll_file == null) poll_file = app.Fsys_mgr().Root_dir().GenSubFil("bldr_poll.gfs");
		if (!Io_mgr.Instance.ExistsFil(poll_file)) return; // file doesn't exist
		String poll_text = Io_mgr.Instance.LoadFilStr(poll_file);
		Io_mgr.Instance.DeleteFil(poll_file);
		app.Usr_dlg().Note_many("", "", "poll file found: ~{0}", poll_file.Raw());
		app.Gfs_mgr().Run_str(poll_text);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_poll_interval_))		poll_interval = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_poll_file_))			poll_file = m.ReadIoUrl("v");
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_poll_interval_ = "poll_interval_", Invk_poll_file_ = "poll_file_";
}
