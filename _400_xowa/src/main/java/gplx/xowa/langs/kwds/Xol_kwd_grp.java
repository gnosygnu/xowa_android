package gplx.xowa.langs.kwds; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_kwd_grp {// REF.MW: Messages_en.php; EX: 'redirect'               => array( 0,    '#REDIRECT'              )
	public Xol_kwd_grp(byte[] key) {this.key = key;}
	public byte[] Key() {return key;} private final byte[] key;
	public boolean Case_match() {return case_match;} private boolean case_match;
	public Xol_kwd_itm[] Itms() {return itms;} private Xol_kwd_itm[] itms = Xol_kwd_itm.Ary_empty;
	public void Srl_load(boolean case_match, byte[][] words) {
		this.case_match = case_match;
		int words_len = words.length;
		itms = new Xol_kwd_itm[words_len];
		for (int i = 0; i < words_len; i++) {
			byte[] word = words[i];
			Xol_kwd_itm itm = new Xol_kwd_itm(word);
			itms[i] = itm;
		}
	}
}
