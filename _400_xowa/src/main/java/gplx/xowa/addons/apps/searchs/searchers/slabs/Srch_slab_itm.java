package gplx.xowa.addons.apps.searchs.searchers.slabs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*; import gplx.xowa.addons.apps.searchs.searchers.*;
public class Srch_slab_itm {
	public Srch_slab_itm(byte[] wiki, int bgn, int end) {this.wiki = wiki; this.bgn = bgn; this.end = end;}
	public byte[] Wiki() {return wiki;} private final    byte[] wiki;
	public int Bgn() {return bgn;} private final    int bgn;
	public int End() {return end;} private final    int end;
}
