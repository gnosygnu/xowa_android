package gplx.xowa.bldrs.css; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.btries.*; import gplx.core.primitives.*;
class Xob_css_parser {
	private final Bry_bfr bfr = Bry_bfr.new_(255);
	private final Xob_mirror_mgr mgr;
	private final Xob_css_parser__url url_parser; private final Xob_css_parser__import import_parser;
	public Xob_css_parser(Xob_mirror_mgr mgr) {
		this.mgr = mgr;
		this.url_parser = new Xob_css_parser__url(mgr.Site_url());
		this.import_parser = new Xob_css_parser__import(url_parser);
	}
	public void Parse(byte[] src) {
		int src_len = src.length; int pos = 0;
		while (pos < src_len) {
			byte b = src[pos];
			Object o = tkns_trie.Match_bgn_w_byte(b, src, pos, src_len);
			if (o == null) {
				bfr.Add_byte(b);
				++pos;
			}
			else {
				byte tkn_tid = ((Byte_obj_val)o).Val();
				int match_pos = tkns_trie.Match_pos();
				Xob_css_tkn__base tkn = null;
				switch (tkn_tid) {
					case Tkn_url:		tkn = url_parser.Parse(src, src_len, pos, match_pos); break;
					case Tkn_import:	tkn = import_parser.Parse(src, src_len, pos, match_pos); break;
				}
				tkn.Process(mgr);
				pos = tkn.Write(bfr, src);
			}
		}
	}
	private static final byte Tkn_import = 1, Tkn_url = 2;
	private static final Btrie_slim_mgr tkns_trie = Btrie_slim_mgr.ci_a7()
	.Add_str_byte("@import"		, Tkn_import)
	.Add_str_byte(" url("		, Tkn_url)
	;
}
