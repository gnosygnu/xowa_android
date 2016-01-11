package gplx.gfui; import gplx.*;
//#{import
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
//#}
class GfuiTipTextMgr implements GfuiWinOpenAble {
	//#{lang
	public void Open_exec(GfuiWin form, GfuiElemBase owner, GfuiElemBase sub) {
		if (!(sub.underElem instanceof JComponent)) return;
		if (String_.Eq(sub.TipText(), "")) return;	// don't register components without tooltips; will leave blue dots (blue tool tip windows with 1x1 size)
		JComponent jcomp = (JComponent)sub.underElem;
		ToolTipManager.sharedInstance().registerComponent(jcomp);
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(1000);
		ToolTipManager.sharedInstance().setReshowDelay(0);
		jcomp.setToolTipText(sub.TipText());
	}
	//#}
	public static final GfuiTipTextMgr Instance = new GfuiTipTextMgr();
}
