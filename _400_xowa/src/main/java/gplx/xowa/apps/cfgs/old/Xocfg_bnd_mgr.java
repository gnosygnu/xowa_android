package gplx.xowa.apps.cfgs.old; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.cfgs.*;
import gplx.gfui.*; import gplx.xowa.guis.bnds.*; import gplx.xowa.guis.cmds.*;
import gplx.xowa.apps.fmtrs.*;
public class Xocfg_bnd_mgr implements GfoInvkAble, Gfo_sort_able {
	private Xog_bnd_mgr_srl bnd_mgr_srl; private Xog_cmd_mgr cmd_mgr;
	private Xoa_fmtr_sort_mgr sorter;
	public Xocfg_bnd_mgr(Xoae_app app) {
		this.app = app; this.bnd_mgr = app.Gui_mgr().Bnd_mgr(); this.cmd_mgr = app.Gui_mgr().Cmd_mgr();
		bnd_mgr_srl = new Xog_bnd_mgr_srl(app, bnd_mgr);
		sorter = new Xoa_fmtr_sort_mgr(this);
	}
	public Xoae_app App() {return app;} private Xoae_app app;
	public Xog_bnd_mgr Bnd_mgr() {return bnd_mgr;} private Xog_bnd_mgr bnd_mgr;
	private Ordered_hash regy;
	public void Init() {
		regy = Ordered_hash_.New();
		int len = bnd_mgr.Len();
		for (int i = 0; i < len; i++) {
			Xog_bnd_itm bnd = bnd_mgr.Get_at(i);
			Xog_cmd_itm cmd = cmd_mgr.Get_or_null(bnd.Cmd()); if (cmd == null) throw Err_.new_unhandled(bnd.Cmd());
			Xocfg_bnd_itm cfg_itm = new Xocfg_bnd_itm(this, cmd, bnd);
			regy.Add(bnd.Key(), cfg_itm);
		}
	}
	private Xocfg_bnd_itm_srl Init(String key) {return new Xocfg_bnd_itm_srl(app, key);}
	public Xocfg_bnd_itm Get_at(int i)		{return (Xocfg_bnd_itm)regy.Get_at(i);}
	public int Len() {return regy.Count();}
	public void Sort(gplx.core.lists.ComparerAble comparer) {regy.Sort_by(comparer);}
	private void Set_bulk(byte[] src) {
		try {
			bnd_mgr_srl.Load_by_bry(src);
		}
		catch (Exception e) {	// catch errors, so that next cmd (which is page.reload) can still execute
			app.Usr_dlg().Warn_many("", "", "failed to set bnds; src=~{0} err=~{1}", String_.new_u8(src), Err_.Message_gplx_full(e));
		}
	}
	private void Show_shortcut_win(String uid, String name, String binding) {
		Xog_bnd_win win = new Xog_bnd_win();			
		win.Show(app.Gui_mgr().Kit(), app.Gui_mgr().Browser_win().Win_box(), bnd_mgr.Bnd_parser(), name, binding);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Xoa_fmtr_itm.Invk_get_at))			return this.Get_at(m.ReadInt("v"));
		else if	(ctx.Match(k, Xoa_fmtr_itm.Invk_len))				return this.Len();
		else if	(ctx.Match(k, Xoa_fmtr_itm.Invk_sorter))			return sorter;
		else if	(ctx.Match(k, Invk_set_bulk))						Set_bulk(m.ReadBry("v"));
		else if	(ctx.Match(k, Invk_init))							return Init(m.ReadStr("v"));
		else if	(ctx.Match(k, Invk_show_shortcut_win))				Show_shortcut_win(m.ReadStr("uid"), m.ReadStr("name"), m.ReadStr("binding"));
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_set_bulk = "set_bulk", Invk_init = "init", Invk_show_shortcut_win = "show_shortcut_win";
}
