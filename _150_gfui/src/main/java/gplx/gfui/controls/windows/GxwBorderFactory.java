package gplx.gfui.controls.windows; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
//#{GxwBorderFactory
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import gplx.*; import gplx.gfui.*;
import gplx.gfui.kits.core.GfuiEnv_;
import gplx.langs.gfs.GfsCore;
public class GxwBorderFactory {
	public static final javax.swing.border.Border Empty = new EmptyBorder(0, 0, 1, 0);	
}
class GfuiMenuBarItmCmd implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		try {
			GfsCore.Instance.ExecOne(GfsCtx.Instance, GfuiMenuBarItm.CmdMsg(itm));
		}
		catch (Exception e) {
			GfuiEnv_.ShowMsg(Err_.Message_gplx_full(e));
		}
	}
	public static GfuiMenuBarItmCmd new_(GfuiMenuBarItm itm) {
		GfuiMenuBarItmCmd cmd = new GfuiMenuBarItmCmd();
		cmd.itm = itm;
		return cmd;
	}	GfuiMenuBarItm itm;
}
//#}
