package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public abstract class Xob_itm_basic_base implements GfoInvkAble {
	protected Xoae_app app; protected Xob_bldr bldr; protected Xowe_wiki wiki; protected Gfo_usr_dlg usr_dlg;
	public void Cmd_ctor(Xob_bldr bldr, Xowe_wiki wiki) {
		this.bldr = bldr;
		this.wiki = wiki;
		this.app = bldr == null ? null : bldr.App();
		this.usr_dlg = bldr == null ? null : bldr.Usr_dlg();
		if (bldr != null && wiki != null)
			this.Cmd_ctor_end(bldr, wiki);
	}
	@gplx.Virtual public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	@gplx.Virtual protected void Cmd_ctor_end(Xob_bldr bldr, Xowe_wiki wiki) {}
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_owner))				return bldr.Cmd_mgr();
		else	return GfoInvkAble_.Rv_unhandled;
	}	private static final String Invk_owner = "owner";
}
