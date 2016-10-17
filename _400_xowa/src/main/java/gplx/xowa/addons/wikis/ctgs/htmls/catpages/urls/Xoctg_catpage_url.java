package gplx.xowa.addons.wikis.ctgs.htmls.catpages.urls; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*;
public class Xoctg_catpage_url {
	public Xoctg_catpage_url(byte[][] keys, boolean[] fwds) {this.keys = keys; this.fwds = fwds;}
	public byte[][]		Grp_keys() {return keys;} private final    byte[][]  keys;
	public boolean[]	Grp_fwds() {return fwds;} private final    boolean[] fwds;

	public static Xoctg_catpage_url New__blank() {
		byte[][] keys = new byte[Xoa_ctg_mgr.Tid___max][];
		boolean[] fwds = new boolean[Xoa_ctg_mgr.Tid___max];

		// for blank url, all fwds are true; EX: "Category:A" -> keys {"", "", ""}, fwds {true, true, true}
		for (int i = 0; i < Xoa_ctg_mgr.Tid___max; ++i) {
			fwds[i] = Bool_.Y;
			keys[i] = Bry_.Empty;
		}
		return new Xoctg_catpage_url(keys, fwds);
	}
}
