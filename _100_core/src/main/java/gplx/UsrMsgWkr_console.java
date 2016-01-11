package gplx;
import gplx.core.consoles.*;
public class UsrMsgWkr_console implements UsrMsgWkr {
	public void ExecUsrMsg(int type, UsrMsg umsg) {
		String text = umsg.To_str();
		if		(type == UsrMsgWkr_.Type_Warn)
			text = "!!!!" + text;
		else if (type == UsrMsgWkr_.Type_Stop)
			text = "****" + text;
		Console_adp__sys.Instance.Write_str(text);
	}
	public static void RegAll(UsrDlg dlg) {
		UsrMsgWkr wkr = new UsrMsgWkr_console();
		dlg.Reg(UsrMsgWkr_.Type_Note, wkr);
		dlg.Reg(UsrMsgWkr_.Type_Stop, wkr);
		dlg.Reg(UsrMsgWkr_.Type_Warn, wkr);
	}
}
