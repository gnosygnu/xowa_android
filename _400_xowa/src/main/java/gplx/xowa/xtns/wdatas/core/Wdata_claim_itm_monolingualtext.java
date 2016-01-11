package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.hwtrs.*;
public class Wdata_claim_itm_monolingualtext extends Wdata_claim_itm_core { //#*inherit
	public Wdata_claim_itm_monolingualtext(int pid, byte snak_tid, byte[] lang, byte[] text) {
		this.Ctor(pid, snak_tid);
		this.lang = lang; this.text = text;
	}
	@Override public byte Val_tid() {return Wdata_dict_val_tid.Tid_monolingualtext;}
	public byte[] Lang() {return lang;} private final byte[] lang;
	public byte[] Text() {return text;} private final byte[] text;
	@Override public void Welcome(Wdata_claim_visitor visitor) {visitor.Visit_monolingualtext(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wdata_dict_snak_tid.Xto_str(this.Snak_tid()), Wdata_dict_val_tid.Xto_str(this.Val_tid()), String_.new_u8(lang), String_.new_u8(text));
	}
}
