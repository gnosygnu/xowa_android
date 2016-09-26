package gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*;
public class Xoctg_catpage_ctg {
	public Xoctg_catpage_ctg(byte[] name) {this.name = name;}
	public byte[] Name() {return name;} private final    byte[] name;
	public Xoctg_catpage_grp	Subcs() {return subcs;} private final    Xoctg_catpage_grp subcs = new Xoctg_catpage_grp(Xoa_ctg_mgr.Tid__subc);
	public Xoctg_catpage_grp	Pages() {return pages;} private final    Xoctg_catpage_grp pages = new Xoctg_catpage_grp(Xoa_ctg_mgr.Tid__page);
	public Xoctg_catpage_grp	Files() {return files;} private final    Xoctg_catpage_grp files = new Xoctg_catpage_grp(Xoa_ctg_mgr.Tid__file);
	public int					Total() {return subcs.Itms__len() + pages.Itms__len() + files.Itms__len();}
	public Xoctg_catpage_grp Grp_by_tid(byte tid) {
		switch (tid) {
			case Xoa_ctg_mgr.Tid__subc: return subcs;
			case Xoa_ctg_mgr.Tid__page: return pages;
			case Xoa_ctg_mgr.Tid__file: return files;
			default: throw Err_.new_unhandled(tid);
		}
	}
	public void Make_itms() {
		for (byte i = 0; i < Xoa_ctg_mgr.Tid___max; ++i) {
			Xoctg_catpage_grp grp = Grp_by_tid(i);
			grp.Itms__make();
		}
	}
}
