package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
public abstract class Xob_cmd_base implements Xob_cmd {
	public abstract String Cmd_key();
	@gplx.Virtual public void Cmd_init(Xob_bldr bldr) {}
	@gplx.Virtual public void Cmd_bgn(Xob_bldr bldr) {}
	@gplx.Virtual public void Cmd_run() {}
	@gplx.Virtual public void Cmd_end() {}
	@gplx.Virtual public void Cmd_term() {}
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return GfoInvkAble_.Rv_unhandled;}
}
