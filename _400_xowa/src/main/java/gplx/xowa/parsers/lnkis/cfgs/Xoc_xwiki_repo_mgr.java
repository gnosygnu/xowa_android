package gplx.xowa.parsers.lnkis.cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
public class Xoc_xwiki_repo_mgr implements Gfo_invk {
	private Ordered_hash hash = Ordered_hash_.New_bry();
	private Xowe_wiki wiki;
	public Xoc_xwiki_repo_mgr(Xowe_wiki wiki) {this.wiki = wiki;}
	public boolean Has(byte[] abrv) {
		Xoc_xwiki_repo_itm itm = (Xoc_xwiki_repo_itm)hash.Get_by(abrv);
		return itm != null;
	}
	public void Add_or_mod(byte[] abrv) {
		Xoc_xwiki_repo_itm itm = (Xoc_xwiki_repo_itm)hash.Get_by(abrv);
		if (itm == null) {
			itm = new Xoc_xwiki_repo_itm(abrv);
			hash.Add(abrv, itm);
			wiki.Cfg_parser_lnki_xwiki_repos_enabled_(true);
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_add))			Add_or_mod(m.ReadBry("xwiki"));
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String Invk_add = "add";
}
class Xoc_xwiki_repo_itm {
	public Xoc_xwiki_repo_itm(byte[] abrv) {this.abrv = abrv;}
	public byte[] Abrv() {return abrv;} private byte[] abrv;
}
