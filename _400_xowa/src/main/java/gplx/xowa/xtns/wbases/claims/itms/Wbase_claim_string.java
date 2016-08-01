package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_string extends Wbase_claim_base {
	public Wbase_claim_string(int pid, byte snak_tid, byte[] val) {super(pid, snak_tid);
		this.val = val;
	}
	@Override public byte	Val_tid() {return Wbase_claim_type_.Tid__string;}
	public byte[]			Val_str() {return val;} private final    byte[] val;

	@Override public void Welcome(Wbase_claim_visitor visitor) {visitor.Visit_str(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wbase_claim_value_type_.To_str_or_fail(this.Snak_tid()), Wbase_claim_type_.To_key_or_unknown(this.Val_tid()), String_.new_u8(val));
	}
}
