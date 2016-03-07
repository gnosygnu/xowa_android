package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Xows_paging_itm {
	public Xows_paging_itm(byte[] wiki, int bgn, int end) {this.wiki = wiki; this.bgn = bgn; this.end = end;}
	public byte[] Wiki() {return wiki;} private final byte[] wiki;
	public int Bgn() {return bgn;} private final int bgn;
	public int End() {return end;} private final int end;
}
