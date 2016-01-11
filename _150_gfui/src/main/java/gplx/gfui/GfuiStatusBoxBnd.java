package gplx.gfui; import gplx.*;
public class GfuiStatusBoxBnd implements GfoInvkAble {
	public GfuiStatusBox Box() {return statusBox;} GfuiStatusBox statusBox = GfuiStatusBox_.new_("statusBox");
	void ShowTime() {
		statusBox.ExecUsrMsg(UsrMsgWkr_.Type_Note, UsrMsg.new_(DateAdp_.Now().XtoStr_gplx_long()));
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_ShowTime))			ShowTime();
		else return GfoInvkAble_.Rv_unhandled;
		return this;
	}	public static final String Invk_ShowTime = "ShowTime";
	public static GfuiStatusBoxBnd gft_(GfuiWin owner) {
		GfuiStatusBoxBnd rv = new GfuiStatusBoxBnd();
		rv.ctor_GfuiStatusBoxBnd(owner);
		return rv;
	}
	void ctor_GfuiStatusBoxBnd(GfuiWin win) {
		IptBnd_.cmd_to_(IptCfg_.Null, win, this, GfuiStatusBoxBnd.Invk_ShowTime, IptKey_.add_(IptKey_.Ctrl, IptKey_.Shift, IptKey_.T));
		statusBox.Owner_(win).Visible_off_().BackColor_(ColorAdp_.Black).TextAlignH_right_().ForeColor_(ColorAdp_.Green);
		win.Lyt().SubLyts().Add(GftGrid.new_().Bands_dir_(DirInt.Bwd).Bands_add(GftBand.new_().Cells_num_(1).Len1_abs_(13)));
	}
}
