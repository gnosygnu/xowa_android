package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.hwtrs.*;
public class Wdata_claim_itm_str extends Wdata_claim_itm_core { //#*inherit
	public Wdata_claim_itm_str(int pid, byte snak_tid, byte[] val) {
		this.Ctor(pid, snak_tid);
		this.val = val;
	}
	@Override public byte Val_tid() {return Wdata_dict_val_tid.Tid_string;}
	public byte[] Val_str() {return val;} private final byte[] val;		
	@Override public void Welcome(Wdata_claim_visitor visitor) {visitor.Visit_str(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wdata_dict_snak_tid.Xto_str(this.Snak_tid()), Wdata_dict_val_tid.Xto_str(this.Val_tid()), String_.new_u8(val));
	}
}
