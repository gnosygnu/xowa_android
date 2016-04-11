package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public abstract class Xob_cmd__base implements Xob_cmd, GfoInvkAble {
	protected final    Xoae_app app; protected final    Xob_bldr bldr; protected Xowe_wiki wiki; protected final    Gfo_usr_dlg usr_dlg;
	public Xob_cmd__base(Xob_bldr bldr, Xowe_wiki wiki) {
		this.bldr = bldr;
		this.wiki = wiki;
		this.app = bldr == null ? null : bldr.App();;
		this.usr_dlg = bldr == null ? null : bldr.Usr_dlg();
	}
	public abstract String	Cmd_key();
	@gplx.Virtual public Xob_cmd	Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	public abstract void	Cmd_run();
	@gplx.Virtual public void		Cmd_init(Xob_bldr bldr) {}
	@gplx.Virtual public void		Cmd_bgn(Xob_bldr bldr) {}
	@gplx.Virtual public void		Cmd_end() {}
	@gplx.Virtual public void		Cmd_term() {}
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return GfoInvkAble_.Rv_unhandled;}
}
