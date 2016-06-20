package gplx;
public class UsrMsgWkr_test implements UsrMsgWkr {
	public void ExecUsrMsg(int type, UsrMsg m) {
		msgs.Add(m);
	}
	public boolean HasWarn(UsrMsg um) {
		for (int i = 0; i < msgs.Count(); i++) {
			UsrMsg found = (UsrMsg)msgs.Get_at(i);
			if (String_.Eq(um.To_str(), found.To_str())) return true;
		}
		return false;
	}
	public static UsrMsgWkr_test RegAll(UsrDlg dlg) {
		UsrMsgWkr_test wkr = new UsrMsgWkr_test();
		dlg.Reg(UsrMsgWkr_.Type_Note, wkr);
		dlg.Reg(UsrMsgWkr_.Type_Stop, wkr);
		dlg.Reg(UsrMsgWkr_.Type_Warn, wkr);
		return wkr;
	}
	List_adp msgs = List_adp_.New();
}
