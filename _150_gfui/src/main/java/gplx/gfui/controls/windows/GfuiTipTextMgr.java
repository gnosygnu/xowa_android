package gplx.gfui.controls.windows; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
//#{import
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
//#}
import gplx.gfui.controls.elems.*;
class GfuiTipTextMgr implements GfuiWinOpenAble {
	//#{lang
	public void Open_exec(GfuiWin form, GfuiElemBase owner, GfuiElemBase sub) {
		if (!(sub.UnderElem() instanceof JComponent)) return;
		if (String_.Eq(sub.TipText(), "")) return;	// don't register components without tooltips; will leave blue dots (blue tool tip windows with 1x1 size)
		JComponent jcomp = (JComponent)sub.UnderElem();
		ToolTipManager.sharedInstance().registerComponent(jcomp);
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(1000);
		ToolTipManager.sharedInstance().setReshowDelay(0);
		jcomp.setToolTipText(sub.TipText());
	}
	//#}
	public static final    GfuiTipTextMgr Instance = new GfuiTipTextMgr();
}
