package gplx;
public class UsrDlg {
	public int Verbosity() {return verbosity;} public UsrDlg Verbosity_(int v) {verbosity = v; return this;} int verbosity = UsrMsgWkr_.Type_Note;
	public void Note(String text, Object... ary)		{Exec(text, ary, noteWkrs);}
	public void Warn(String text, Object... ary)		{Exec(text, ary, warnWkrs);}
	public void Stop(String text, Object... ary)		{Exec(text, ary, stopWkrs);}
	public void Note(UsrMsg msg)							{Exec(UsrMsgWkr_.Type_Note, msg);}
	public void Warn(UsrMsg msg)							{Exec(UsrMsgWkr_.Type_Warn, msg);}
	public void Stop(UsrMsg msg)							{Exec(UsrMsgWkr_.Type_Stop, msg);}
	public void Exec(int type, UsrMsg umsg) {
		UsrMsgWkrList list = GetList(type);
		list.Exec(umsg);
	}
	void Exec(String text, Object[] ary, UsrMsgWkrList list) {
		String msg = String_.Format(text, ary);
		list.Exec(UsrMsg.new_(msg));
	}
	public void Reg(int type, UsrMsgWkr wkr) {
		UsrMsgWkrList list = GetList(type);
		list.Add(wkr);
	}
	public void RegOff(int type, UsrMsgWkr wkr) {
		UsrMsgWkrList list = GetList(type);
		list.Del(wkr);
	}
	UsrMsgWkrList GetList(int type) {
		if		(type == UsrMsgWkr_.Type_Note)			return noteWkrs;
		else if (type == UsrMsgWkr_.Type_Warn)			return warnWkrs;
		else if (type == UsrMsgWkr_.Type_Stop)			return stopWkrs;
		else throw Err_.new_unhandled(type);
	}
	UsrMsgWkrList noteWkrs = new UsrMsgWkrList(UsrMsgWkr_.Type_Note), warnWkrs = new UsrMsgWkrList(UsrMsgWkr_.Type_Warn), stopWkrs = new UsrMsgWkrList(UsrMsgWkr_.Type_Stop);
	public static UsrDlg new_() {return new UsrDlg();}
}
