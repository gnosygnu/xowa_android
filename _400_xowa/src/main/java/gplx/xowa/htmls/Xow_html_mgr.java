package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
import gplx.gfui.kits.core.*;
import gplx.xowa.langs.*;
import gplx.xowa.wikis.ctgs.*; import gplx.xowa.xtns.gallery.*;	
import gplx.xowa.parsers.xndes.*;
import gplx.xowa.htmls.portal.*; import gplx.xowa.addons.htmls.tocs.*; import gplx.xowa.wikis.modules.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.core.hzips.*; import gplx.xowa.htmls.core.htmls.tidy.*; import gplx.xowa.htmls.js.*;
public class Xow_html_mgr implements Gfo_invk {
	public Xow_html_mgr(Xowe_wiki wiki) {
		this.wiki = wiki;
		html_wtr = new Xoh_html_wtr(wiki, this);
		Xoae_app app = wiki.Appe();
		page_wtr_mgr = new Xoh_page_wtr_mgr(app.Gui_mgr().Kit().Tid() != Gfui_kit_.Swing_tid);	// reverse logic to handle swt,drd but not mem
		Io_url file_dir = app.Fsys_mgr().Bin_xowa_file_dir().GenSubDir_nest("mediawiki.file");
		img_media_play_btn = gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_lnx.Encode_to_file_protocol(file_dir.GenSubFil("play.png"));
		img_media_info_btn = gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_lnx.Encode_to_file_protocol(file_dir.GenSubFil("info.png"));
		img_thumb_magnify  = gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_lnx.Encode_to_file_protocol(file_dir.GenSubFil("magnify-clip.png"));
		img_xowa_protocol = gplx.langs.htmls.encoders.Gfo_url_encoder_.Fsys_lnx.Encode_to_file_protocol(app.Fsys_mgr().Bin_xowa_file_dir().GenSubFil_nest("app.general", "xowa_exec.png"));
		portal_mgr = new Xow_portal_mgr(wiki);
		imgs_mgr = new Xoh_imgs_mgr(this);
		module_mgr = new Xow_module_mgr(wiki);
		this.js_cleaner = new Xoh_js_cleaner(app);
	}
	public void Init_by_wiki(Xowe_wiki wiki) {
		html_wtr.Init_by_wiki(wiki);
		module_mgr.Init_by_wiki(wiki);
		tidy_mgr.Init_by_app(wiki.Appe());
	}
	public void Init_by_lang(Xol_lang_itm lang) {
		portal_mgr.Init_by_lang(lang);
	}
	public Xowe_wiki				Wiki() {return wiki;} private Xowe_wiki wiki;
	public Xoh_html_wtr				Html_wtr() {return html_wtr;} private Xoh_html_wtr html_wtr;
	public Xoh_page_wtr_mgr			Page_wtr_mgr() {return page_wtr_mgr;} private Xoh_page_wtr_mgr page_wtr_mgr;
	public Xow_tidy_mgr				Tidy_mgr()	{return tidy_mgr;} private final    Xow_tidy_mgr tidy_mgr = new Xow_tidy_mgr();
	public Xoh_js_cleaner			Js_cleaner() {return js_cleaner;} private final    Xoh_js_cleaner js_cleaner;
	public Xop_xatr_whitelist_mgr	Whitelist_mgr() {return whitelist_mgr;} private final    Xop_xatr_whitelist_mgr whitelist_mgr = new Xop_xatr_whitelist_mgr().Ini();
	public Xow_portal_mgr			Portal_mgr() {return portal_mgr;} private Xow_portal_mgr portal_mgr;
	public Xow_module_mgr			Head_mgr() {return module_mgr;} private Xow_module_mgr module_mgr;
	public boolean Importing_ctgs() {return importing_ctgs;} public void Importing_ctgs_(boolean v) {importing_ctgs = v;} private boolean importing_ctgs;
	public int Img_thumb_width() {return img_thumb_width;} private int img_thumb_width = 220;
	public byte[] Img_media_play_btn() {return img_media_play_btn;} private byte[] img_media_play_btn;
	public byte[] Img_media_info_btn() {return img_media_info_btn;} private byte[] img_media_info_btn;
	public byte[] Img_thumb_magnify() {return img_thumb_magnify;} private byte[] img_thumb_magnify;
	public byte[] Img_xowa_protocol() {return img_xowa_protocol;} private byte[] img_xowa_protocol;
	public boolean Img_suppress_missing_src() {return img_suppress_missing_src;} public Xow_html_mgr Img_suppress_missing_src_(boolean v) {img_suppress_missing_src = v; return this;} private boolean img_suppress_missing_src = true;
	public Xohp_ctg_grp_mgr Ctg_mgr() {return ctg_mgr;} private Xohp_ctg_grp_mgr ctg_mgr = new Xohp_ctg_grp_mgr();
	public Xoctg_html_mgr Ns_ctg() {return ns_ctg;} private final    Xoctg_html_mgr ns_ctg = new Xoctg_html_mgr();
	public Xoh_imgs_mgr Imgs_mgr() {return imgs_mgr;} private Xoh_imgs_mgr imgs_mgr;
	public void Copy_cfg(Xow_html_mgr html_mgr) {imgs_mgr.Copy_cfg(html_mgr.Imgs_mgr());}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_article))							return page_wtr_mgr;
		else if	(ctx.Match(k, Invk_portal))								return portal_mgr;
		else if	(ctx.Match(k, Invk_imgs))								return imgs_mgr;
		else if	(ctx.Match(k, Invk_ns_ctg))								return ns_ctg;
		else if	(ctx.Match(k, Invk_modules))							return module_mgr;
		else	return Gfo_invk_.Rv_unhandled;
	}
	public static final String 
	  Invk_article = "article"
	, Invk_portal = "portal", Invk_imgs = "imgs", Invk_ns_ctg = "ns_ctg"
	, Invk_modules = "modules"
	;
}
