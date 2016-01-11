package gplx.xowa.bldrs.cmds.diffs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.brys.*; import gplx.xowa.wikis.*;
class Bfr_arg__dump_dir implements Bfr_arg {	// .dump_dir = "C:\xowa\wiki\en.wikipedia.org"
	private final Xow_wiki wiki;
	public Bfr_arg__dump_dir(Xow_wiki wiki) {this.wiki = wiki;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(wiki.Fsys_mgr().Root_dir().RawBry());
	}
}
class Bfr_arg__dump_core implements Bfr_arg {// .dump_core = "en.wikipedia.org-core.xowa"
	private final Xow_wiki wiki;
	public Bfr_arg__dump_core(Xow_wiki wiki) {this.wiki = wiki;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(wiki.Data__core_mgr().Db__core().Url().RawBry());
	}
}
class Bfr_arg__dump_domain implements Bfr_arg {// .dump_domain = en.wikipedia.org
	private final Xow_wiki wiki;
	public Bfr_arg__dump_domain(Xow_wiki wiki) {this.wiki = wiki;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(wiki.Domain_bry());
	}
}
