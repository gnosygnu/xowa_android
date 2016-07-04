package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Gallery_xtn_mgr extends Xox_mgr_base {
	@Override public byte[]		Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("gallery");
	@Override public Xox_mgr		Xtn_clone_new() {return new Gallery_xtn_mgr();}
	public Gallery_parser	Parser() {return parser;} private final    Gallery_parser parser = new Gallery_parser();
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		parser.Init_by_wiki(wiki);
	}
}
