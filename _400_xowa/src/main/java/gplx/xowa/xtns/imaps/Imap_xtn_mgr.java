package gplx.xowa.xtns.imaps; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.btries.*; import gplx.xowa.wikis.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.wkrs.lnkis.htmls.*;
public class Imap_xtn_mgr extends Xox_mgr_base implements GfoInvkAble {
	private boolean init;
	@Override public boolean Enabled_default() {return true;}
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final byte[] XTN_KEY = Bry_.new_a7("imageMap");
	public Xowe_wiki Wiki() {return wiki;} private Xowe_wiki wiki;
        @gplx.Internal protected Imap_parser Parser() {return parser;} private Imap_parser parser;
	public void Desc_assert() {
		if (desc_trie != null) return;
		desc_trie = Imap_desc_tid.trie_(wiki);
		desc_msg = wiki.Msg_mgr().Val_by_key_obj("imagemap_description");
		desc_icon_url = wiki.Appe().Fsys_mgr().Bin_xtns_dir().GenSubFil_nest("ImageMap", "imgs", "desc-20.png").To_http_file_bry();
	}
	public Btrie_slim_mgr Desc_trie() {return desc_trie;}	private Btrie_slim_mgr desc_trie;
	public byte[] Desc_msg() {return desc_msg;} private byte[] desc_msg;
	public byte[] Desc_icon_url() {return desc_icon_url;} private byte[] desc_icon_url;
	public final Xoh_arg_img_core Img_core_fmtr = new Xoh_arg_img_core__basic();
	public final Xoh_file_html_fmtr__hdump Img_html_hdump_bldr = Xoh_file_html_fmtr__hdump.Hdump;
	public final Bry_bfr Tmp_bfr = Bry_bfr.new_(255);
	@Override public Xox_mgr Clone_new() {return new Imap_xtn_mgr();}
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		this.wiki = wiki;
	}
	public void Xtn_assert() {
		if (init) return;
		parser = new Imap_parser(this);
		init = true;
	}
	public static final byte[]
	  Bry__usemap__html		= Bry_.new_a7(" usemap=\"#imagemap_1_")
	, Bry__usemap__name		= Bry_.new_a7("usemap")
	, Bry__usemap__prefix	= Bry_.new_a7("#imagemap_1_")
	;
}
