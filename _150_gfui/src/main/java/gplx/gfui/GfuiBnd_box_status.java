package gplx.gfui; import gplx.*;
public class GfuiBnd_box_status implements GfoInvkAble, UsrMsgWkr {
	public GfuiElem Box() {return box;} GfuiElem box;
	public void ExecUsrMsg(int type, UsrMsg umsg) {
		box.Invoke(GfoInvkAbleCmd.arg_(this, WriteText_cmd, umsg.To_str()));
	}
	public void WriteText(String text) {
		GfuiElem lastFocus = GfuiFocusMgr.Instance.FocusedElem();	// HACK:WINFORMS:.Visible=true will automatically transfer focus to textBox; force Focus back to original
		box.Text_(text);
		GfuiWin ownerWin = box.OwnerWin();
		if (ownerWin != null && !ownerWin.Visible()) {
			ownerWin.Visible_set(true);
			ownerWin.Zorder_front();
		}
		timer.Enabled_off(); timer.Enabled_on(); // reset time
		GfuiEnv_.DoEvents();	// WORKAROUND:WIN32: needed, else text will not refresh (ex: splash screen); will cause other timers to fire! (ex: mediaPlaylistMgr)
		if (lastFocus != null) lastFocus.Focus();
	}
	public void HideWin() {
		if (box.OwnerWin() != null)
			box.OwnerWin().Visible_off_();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, WriteText_cmd))		WriteText(m.ReadStr("v"));
		else if	(ctx.Match(k, TimerTick_evt))		HideWin();
		return this;
	}	static final String TimerTick_evt = "TimerTick", WriteText_cmd = "WriteText";
	TimerAdp timer;
	public static GfuiBnd_box_status new_(String key) {
		GfuiBnd_box_status rv = new GfuiBnd_box_status();
		GfuiTextBox txtBox = GfuiTextBox_.new_();
		txtBox.Key_of_GfuiElem_(key);
		txtBox.BackColor_(ColorAdp_.Black).ForeColor_(ColorAdp_.Green).TextAlignH_right_();
		rv.box = txtBox;
		rv.timer = TimerAdp.new_(rv, TimerTick_evt, 2000, false);
		return rv;
	}
}
