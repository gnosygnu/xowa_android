package gplx.langs.htmls.styles; import gplx.*; import gplx.langs.*; import gplx.langs.htmls.*;
public class Gfh_style_itm implements To_str_able {
	public Gfh_style_itm(int idx, byte[] key, byte[] val) {this.idx = idx; this.key = key; this.val = val;}
	public int Idx() {return idx;} private final int idx;
	public byte[] Key() {return key;} private final byte[] key;
	public byte[] Val() {return val;} private final byte[] val;
	public String To_str() {return String_.new_u8(Bry_.Add(key, Byte_ascii.Colon_bry, val, Byte_ascii.Semic_bry));}
}
