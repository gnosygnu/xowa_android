package gplx;
public interface UsrMsgWkr {//_20110212
	void ExecUsrMsg(int type, UsrMsg umsg);
}
class UsrMsgWkrList {
	public void Add(UsrMsgWkr v) {
		if (wkr == null && list == null)
			wkr = v;
		else {
			if (list == null) {
				list = List_adp_.new_();
				list.Add(wkr);
				wkr = null;
			}
			list.Add(v);
		}
	}
	public void Del(UsrMsgWkr v) {
//			list.Del(v);
	}
	public void Exec(UsrMsg umsg) {
		if (wkr != null)
			wkr.ExecUsrMsg(type, umsg);
		else if (list != null) {
			for (Object lObj : list) {
				UsrMsgWkr l = (UsrMsgWkr)lObj;
				l.ExecUsrMsg(type, umsg);
			}
		}
	}
	List_adp list; UsrMsgWkr wkr; int type;
        public UsrMsgWkrList(int type) {this.type = type;}
}
