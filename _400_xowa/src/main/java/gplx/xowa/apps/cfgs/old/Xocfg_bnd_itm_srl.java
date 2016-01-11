package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
import gplx.gfui.*; import gplx.xowa.guis.bnds.*;
import gplx.langs.gfs.*; import gplx.xowa.apps.gfs.*;
public class Xocfg_bnd_itm_srl implements GfoInvkAble {
	private Xoae_app app;
	public Xocfg_bnd_itm_srl(Xoae_app app, String key) {
		this.app = app;
		this.key = key;
	}
	public String	Key() {return key;} private String key;
	public int		Box() {return box;} private int box;
	public IptArg	Ipt() {return ipt;} private IptArg ipt;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_src_))			Src_(app, this, m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_box_))			box = Xog_bnd_box_.Xto_sys_int(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_ipt_))			ipt = IptArg_.parse(m.ReadStr("v"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_src_ = "src_", Invk_box_ = "box_", Invk_ipt_ = "ipt_";
	public static String Src(Xoae_app app, int box, IptArg ipt) {	// box_('browser').ipt_('mod.c+key.q');
		Gfs_wtr wtr = app.Gfs_mgr().Wtr();
		wtr.Add_set_eq(Key_box, Bry_.new_a7(Xog_bnd_box_.Xto_sys_str(box)));
		wtr.Add_set_eq(Key_ipt, Bry_.new_a7(ipt.Key()));
		return wtr.Bfr().To_str_and_clear();			
	}	private static final byte[] Key_box = Bry_.new_a7("box"), Key_ipt = Bry_.new_a7("ipt");
	public static void Src_(Xoae_app app, Xocfg_bnd_itm_srl itm, String v) {
		Xoa_gfs_mgr gfs_mgr = app.Gfs_mgr();
		gfs_mgr.Run_str_for(itm, v);
		Xog_bnd_itm bnd = app.Gui_mgr().Bnd_mgr().Get_or_null(itm.Key());
		if (bnd != null)	// should not happen, but guard against backward compatibility issues (deprecating old bindings)
			app.Gui_mgr().Bnd_mgr().Set(bnd, itm.Box(), itm.Ipt());
	}
}
