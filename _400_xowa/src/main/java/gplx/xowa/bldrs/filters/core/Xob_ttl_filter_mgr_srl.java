package gplx.xowa.bldrs.filters.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.filters.*;
import gplx.langs.dsvs.*;
class Xob_ttl_filter_mgr_srl extends Dsv_wkr_base {
	private byte[] ttl; private Hash_adp_bry hash;
	public Xob_ttl_filter_mgr_srl Init(Hash_adp_bry hash) {this.hash = hash; return this;}
	@Override public Dsv_fld_parser[] Fld_parsers() {return new Dsv_fld_parser[] {Dsv_fld_parser_.Line_parser__comment_is_pipe};}
	@Override public boolean Write_bry(Dsv_tbl_parser parser, int fld_idx, byte[] src, int bgn, int end) {
		switch (fld_idx) {
			case 0: 
				if (end - bgn == 0) return true;					// ignore blank lines
				if (src[bgn] == Byte_ascii.Pipe) return true;		// ignore lines starting with pipe; EX: "| some comment"
				ttl = Bry_.Mid(src, bgn, end);
				return true;
			default: return false;
		}
	}
	@Override public void Commit_itm(Dsv_tbl_parser parser, int pos) {
		if (ttl == null)	return;
		hash.Add(ttl, ttl);
		ttl = null;
	}
}
