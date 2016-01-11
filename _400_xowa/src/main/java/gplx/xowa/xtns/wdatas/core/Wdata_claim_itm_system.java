package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.hwtrs.*;
public class Wdata_claim_itm_system extends Wdata_claim_itm_core { //#*inherit
	public Wdata_claim_itm_system(int pid, byte val_tid, byte snak_tid) {
		this.Ctor(pid, snak_tid);
		this.val_tid = val_tid;
	}
	@Override public byte Val_tid() {return val_tid;} private byte val_tid;
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wdata_dict_snak_tid.Xto_str(this.Snak_tid()), Wdata_dict_val_tid.Xto_str(this.Val_tid()));
	}
	@Override public void Welcome(Wdata_claim_visitor visitor) {visitor.Visit_system(this);}
	public static Wdata_claim_itm_system new_novalue(int pid)		{return new Wdata_claim_itm_system(pid, Wdata_dict_val_tid.Tid_unknown	, Wdata_dict_snak_tid.Tid_novalue);}
	public static Wdata_claim_itm_system new_somevalue(int pid)		{return new Wdata_claim_itm_system(pid, Wdata_dict_val_tid.Tid_unknown	, Wdata_dict_snak_tid.Tid_somevalue);}
}
