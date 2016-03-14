package gplx.xowa.addons.searchs.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_word_itm {
	public Srch_word_itm(byte[] word) {
		this.Word = word;
		this.count = 0;
	}
	public final byte[] Word;
	public int Count() {return count;} private int count; 
	public void Count_add_1_() {++count;}
	@gplx.Internal protected Srch_word_itm Count_(int v) {this.count = v; return this;}
}
