package gplx.xowa.htmls.core.wkrs.imgs.atrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.imgs.*;
import gplx.core.brys.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
public class Xoh_img_cls_data implements Bfr_arg_clearable {
	private byte[] src;
	public byte Cls_tid() {return cls_tid;} private byte cls_tid;
	public int Other_bgn() {return other_bgn;} private int other_bgn;
	public int Other_end() {return other_end;} private int other_end;
	public boolean Other_exists() {return other_end > other_bgn;}
	public void Clear() {
		cls_tid = Xoh_img_cls_.Tid__none; other_bgn = other_end = -1;
		src = null;
	}
	public void Init_by_decode(byte[] src, int cls_tid, int other_bgn, int other_end) {
		this.src = src;
		this.cls_tid = (byte)cls_tid; this.other_bgn = other_bgn; this.other_end = other_end;
	}
	public void Init_by_parse(Bry_err_wkr err_wkr, byte[] src, Gfh_tag tag) {
		this.Clear();
		Gfh_atr atr = tag.Atrs__get_by_or_empty(Gfh_atr_.Bry__class);		// EX: class='thumbborder'
		int src_bgn = atr.Val_bgn(); 
		if (src_bgn == -1) {	// class does not exist; defaults to none and exit;
			cls_tid = Xoh_img_cls_.Tid__none;
			return;	
		}
		int src_end = atr.Val_end();
		this.cls_tid = Xoh_img_cls_.Trie.Match_byte_or(src, src_bgn, src_end, Xoh_img_cls_.Tid__manual);
		int pos = Xoh_img_cls_.Trie.Match_pos();
		if (pos < src_end && src[pos] == Byte_ascii.Space)
			++pos;
		if (cls_tid == Xoh_img_cls_.Tid__manual || pos < src_end) {
			this.other_bgn = pos;
			this.other_end = src_end;
		}
	}
	public void Bfr_arg__clear()	{this.Clear();}
	public boolean Bfr_arg__missing()	{return cls_tid == Xoh_img_cls_.Tid__none && other_end <= other_bgn;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (Bfr_arg__missing()) return;
		byte[] cls = null;
		switch (cls_tid) {
			case Xoh_img_cls_.Tid__thumbimage:	cls = Xoh_img_cls_.Bry__thumbimage; break;
			case Xoh_img_cls_.Tid__thumbborder:	cls = Xoh_img_cls_.Bry__thumbborder; break;
		}
		if (cls != null)
			bfr.Add(cls);
		if (other_end > other_bgn) {
			if (cls != null) bfr.Add_byte_space();
			bfr.Add_mid(src, other_bgn, other_end);
		}
	}
}
