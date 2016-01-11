package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public class GfuiStatusBarBnd implements InjectAble {
	public GfuiStatusBar Bar() {return statusBar;} GfuiStatusBar statusBar = GfuiStatusBar.new_();
	public void Inject(Object owner) {
		GfuiWin form = GfuiWin_.as_(owner); if (form == null) throw Err_.new_type_mismatch(GfuiWin.class, owner);
		statusBar.Owner_(form, "statusBar");
		IptBnd_.cmd_to_(IptCfg_.Null, form, statusBar, GfuiStatusBar.StatusBarFocus_cmd, IptKey_.add_(IptKey_.Ctrl, IptKey_.Alt, IptKey_.T));
		statusBar.MoveButton().TargetElem_set(form);
	}
	public static GfuiStatusBarBnd new_() {return new GfuiStatusBarBnd();} GfuiStatusBarBnd() {}
}
