package gplx.xowa.xtns.translates; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*;
public class Xop_tvar_tkn extends Xop_tkn_itm_base {
	public Xop_tvar_tkn(int tkn_bgn, int tkn_end, int key_bgn, int key_end, int txt_bgn, int txt_end, byte[] wikitext) {
		this.Tkn_ini_pos(false, tkn_bgn, tkn_end);
		this.key_bgn = key_bgn; this.key_end = key_end;
		this.txt_bgn = txt_bgn; this.txt_end = txt_end;
		this.wikitext = wikitext;
	}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_tvar;}
	public int Key_bgn() {return key_bgn;} private int key_bgn;
	public int Key_end() {return key_end;} private int key_end;
	public int Txt_bgn() {return txt_bgn;} private int txt_bgn;
	public int Txt_end() {return txt_end;} private int txt_end;
	public byte[] Wikitext() {return wikitext;} private byte[] wikitext;
}
