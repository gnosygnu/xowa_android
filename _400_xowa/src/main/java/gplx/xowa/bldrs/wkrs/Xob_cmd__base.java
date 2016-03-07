package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public abstract class Xob_cmd__base implements GfoInvkAble {
	protected final Xoae_app app; protected final Xob_bldr bldr; protected final Xowe_wiki wiki; protected final Gfo_usr_dlg usr_dlg;
	public Xob_cmd__base(Xob_bldr bldr, Xowe_wiki wiki) {this.bldr = bldr; this.wiki = wiki; this.app = bldr.App(); usr_dlg = bldr.Usr_dlg();}
	public abstract String Cmd_key();
	public abstract void Cmd_run();
	@gplx.Virtual public void Cmd_init(Xob_bldr bldr) {}
	@gplx.Virtual public void Cmd_bgn(Xob_bldr bldr) {}
	@gplx.Virtual public void Cmd_end() {}
	@gplx.Virtual public void Cmd_term() {}
	public abstract Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m);
}
