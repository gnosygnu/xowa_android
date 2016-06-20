package gplx.xowa.guis.bnds; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.core.primitives.*; import gplx.langs.dsvs.*; import gplx.gfui.*; import gplx.gfui.ipts.*; import gplx.xowa.apps.cfgs.old.*;
public class Xog_bnd_mgr_srl extends Dsv_wkr_base {
	private Xoae_app app;
	private Xog_bnd_mgr bnd_mgr;
	private int uid; private byte[] box_bry, ipt_bry;
	public Xog_bnd_mgr_srl(Xoae_app app, Xog_bnd_mgr bnd_mgr) {
		this.app = app;
		this.bnd_mgr = bnd_mgr;
	}
	@Override public Dsv_fld_parser[] Fld_parsers() {return new Dsv_fld_parser[] {Dsv_fld_parser_.Int_parser, Dsv_fld_parser_.Bry_parser, Dsv_fld_parser_.Bry_parser};}
	@Override public boolean Write_int(Dsv_tbl_parser parser, int fld_idx, int pos, int val_int) {
		switch (fld_idx) {
			case 0: uid			= val_int; return true;
			default: return false;
		}
	}
	@Override public boolean Write_bry(Dsv_tbl_parser parser, int fld_idx, byte[] src, int bgn, int end) {
		switch (fld_idx) {
			case 1: box_bry			= Bry_.Mid(src, bgn, end); return true;
			case 2: ipt_bry			= Bry_.Mid(src, bgn, end); return true;
			default: return false;
		}
	}		
	private void Init() {
		uids = Ordered_hash_.New();
		int len = bnd_mgr.Len();
		for (int i = 0; i < len; i++) {
			Xog_bnd_itm bnd = (Xog_bnd_itm)bnd_mgr.Get_at(i);
			uids.Add(Int_obj_val.new_(bnd.Uid()), bnd);
		}
		bnd_parser = bnd_mgr.Bnd_parser();
	}	private Ordered_hash uids; private Gfui_bnd_parser bnd_parser;
	@Override public void Commit_itm(Dsv_tbl_parser parser, int pos) {
		if (uid		== -1)		throw parser.Err_row_bgn("bnd missing uid", pos);
		if (box_bry	== null)	throw parser.Err_row_bgn("bnd missing box", pos);
		if (ipt_bry	== null)	throw parser.Err_row_bgn("bnd missing ipt", pos);
		if (uids == null) Init();
		Xog_bnd_itm bnd = (Xog_bnd_itm)uids.Get_by(Int_obj_val.new_(uid));
		int box = Xog_bnd_box_.Xby_gui_str(String_.new_u8(box_bry));
		IptArg ipt = IptArg_.parse_or_none_(bnd_parser.Xto_gfui(String_.new_u8(ipt_bry)));
		bnd_mgr.Del(bnd, ipt);
		Xog_bnd_mgr_srl.Update_cfg(app, bnd, box, ipt);
		uid = -1; box_bry = ipt_bry = null;
	}
	public static void Update_cfg(Xoae_app app, Xog_bnd_itm bnd, int box, IptArg ipt) {
		String src = Xocfg_bnd_itm_srl.Src(app, box, ipt);
		String cfg_key = String_.Concat("app.cfg.get.gui.bnds.init('", bnd.Key(), "').src");
		app.Cfg_mgr().Set_by_app(cfg_key, src);
	}
}
