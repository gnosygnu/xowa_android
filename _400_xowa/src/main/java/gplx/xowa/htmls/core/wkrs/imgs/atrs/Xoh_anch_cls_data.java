package gplx.xowa.htmls.core.wkrs.imgs.atrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.imgs.*;
import gplx.core.brys.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
public class Xoh_anch_cls_data implements Bfr_arg_clearable {
	private final Bry_rdr rdr = new Bry_rdr();
	public byte Tid() {return tid;} private byte tid;
	public void Clear() {
		tid = Xoh_anch_cls_.Tid__none;
	}
	public boolean Parse(Bry_err_wkr err_wkr, byte[] src, Gfh_tag tag) {
		Gfh_atr atr = tag.Atrs__get_by_or_empty(Gfh_atr_.Bry__class);		// EX: class='image'
		int src_bgn = atr.Val_bgn(); int src_end = atr.Val_end();
		if (src_bgn == -1) return false;
		rdr.Init_by_wkr(err_wkr, "anch.cls", src_bgn, src_end);
		this.tid = rdr.Chk_or(Xoh_anch_cls_.Trie, Byte_ascii.Max_7_bit);
		return tid != Byte_ascii.Max_7_bit;
	}
	public void Init_by_decode(int tid) {this.tid = (byte)tid;}
	public void Bfr_arg__clear()	{this.Clear();}
	public boolean Bfr_arg__missing()	{return false;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		bfr.Add(Xoh_anch_cls_.To_val(tid));
	}
}
