package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.hwtrs.*;
public abstract class Wdata_claim_itm_base implements CompareAble {
	public Wdata_claim_itm_base Ctor(int pid, byte snak_tid) {
		this.pid = pid;
		this.snak_tid = snak_tid;
		return this;
	}
	public int Pid() {return pid;} private int pid;
	public abstract byte Val_tid();
	public byte Snak_tid() {return snak_tid;} private byte snak_tid = Wdata_dict_snak_tid.Tid_value;
	public byte Rank_tid() {return rank_tid;} public void Rank_tid_(byte v) {this.rank_tid = v;} private byte rank_tid = Wdata_dict_rank.Tid_normal;	// TEST: default to normal for tests
	public abstract void Welcome(Wdata_claim_visitor visitor);
	public int compareTo(Object obj) {
		Wdata_claim_itm_base comp = (Wdata_claim_itm_base)obj;
		return Int_.Compare(pid, comp.pid);
	}
}
