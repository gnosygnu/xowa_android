package gplx.xowa.xtns.poems; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.parsers.*;
public class Poem_xtn_mgr extends Xox_mgr_base {
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("poem");
	@Override public Xox_mgr Xtn_clone_new() {return new Poem_xtn_mgr();}
	public Xop_parser Parser() {return parser;} private Xop_parser parser;
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		parser = Xop_parser.new_(wiki, Xop_lxr_mgr.new_tmpl_(), Xop_lxr_mgr.new_wiki_());
		parser.Init_by_wiki(wiki);
		parser.Init_by_lang(wiki.Lang());
	}
}
