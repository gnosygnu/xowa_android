package gplx.xowa.xtns.proofreadPage; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.htmls.*;
import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.xwikis.*;
public class Pp_xtn_mgr extends Xox_mgr_base {
	@Override public boolean Enabled_default() {return false;}
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final byte[] XTN_KEY = Bry_.new_a7("ProofreadPages");
	@Override public Xox_mgr Clone_new() {return new Pp_xtn_mgr();}
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		if (!this.Enabled_manually())
			this.Enabled_(wiki.Domain_tid() == Xow_domain_tid_.Int__wikisource);	// only enable for wikisource
	}
}
