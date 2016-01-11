package gplx.xowa; import gplx.*;
import gplx.xowa.langs.*; import gplx.xowa.wikis.pages.*;
import gplx.xowa.guis.*; import gplx.xowa.guis.views.*;
import gplx.xowa.files.*; import gplx.xowa.files.xfers.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.redlinks.*; import gplx.xowa.xtns.cites.*; import gplx.xowa.xtns.wdatas.*; import gplx.xowa.xtns.wdatas.pfuncs.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.tocs.*; import gplx.xowa.htmls.modules.popups.*;	
public class Xoae_page implements Xoa_page {
	Xoae_page(Xowe_wiki wiki, Xoa_ttl ttl) {
		this.wiki = wiki; this.ttl = ttl;
		this.lang = wiki.Lang();	// default to wiki.lang; can be override later by wikitext
		hdr_mgr = new Xow_hdr_mgr(this);
		redlink_lnki_list = new Xopg_redlink_lnki_list(ttl.Ns().Id_is_module());
		Ttl_(ttl);
	}	Xoae_page() {}	// called by Empty
	public Xow_wiki					Wiki() {return wiki;}
	public Xoa_ttl					Ttl() {return ttl;} public Xoae_page Ttl_(Xoa_ttl v) {ttl = v; url.Wiki_bry_(wiki.Domain_bry()).Page_bry_(v.Full_url()); return this;} private Xoa_ttl ttl;
	public Xoa_url					Url() {return url;} public Xoae_page Url_(Xoa_url v) {url = v; return this;} private Xoa_url url = Xoa_url.blank();
	public byte[]					Url_bry_safe() {return url == null ? Bry_.Empty : url.Raw();}
	public boolean						Exists() {return !Missing();}
	public void						Xtn_gallery_packed_exists_y_() {html_data.Xtn_gallery_packed_exists_y_();}

	public Xoa_page__commons_mgr	Commons_mgr() {return commons_mgr;} private final Xoa_page__commons_mgr commons_mgr = new Xoa_page__commons_mgr();
	public Xopg_revision_data		Revision_data() {return revision_data;} private Xopg_revision_data revision_data = new Xopg_revision_data();
	public Xowe_wiki				Wikie() {return wiki;} private Xowe_wiki wiki;
	public Xopg_redlink_lnki_list	Redlink_lnki_list() {return redlink_lnki_list;} private Xopg_redlink_lnki_list redlink_lnki_list;
	public Xol_lang_itm				Lang() {return lang;} public Xoae_page Lang_(Xol_lang_itm v) {lang = v; return this;} private Xol_lang_itm lang;
	public Xopg_html_data			Html_data() {return html_data;} private Xopg_html_data html_data = new Xopg_html_data();
	public Xopg_tab_data			Tab_data() {return tab_data;} private final Xopg_tab_data tab_data = new Xopg_tab_data();
	public Xopg_hdump_data			Hdump_data() {return hdump_data;} private final Xopg_hdump_data hdump_data = new Xopg_hdump_data();
	public boolean						Missing() {return missing;} public Xoae_page Missing_() {return Missing_(true);} public Xoae_page Missing_(boolean v) {missing = v; return this;}  private boolean missing;
	public boolean						Redirected() {return redirected;} public Xoae_page Redirected_(boolean v) {redirected = v; return this;} private boolean redirected;
	public List_adp					Redirected_ttls() {return redirected_ttls;} private List_adp redirected_ttls = List_adp_.new_();
	public byte[]					Redirected_ttls__itm_0() {return (byte[])redirected_ttls.Get_at(0);}
	public byte[]					Redirected_src() {return redirected_src;} public void Redirected_src_(byte[] v) {this.redirected_src = v;}  private byte[] redirected_src;
	public byte						Edit_mode() {return edit_mode;} private byte edit_mode; public void	Edit_mode_update_() {edit_mode = Xoa_page_.Edit_mode_update;}
	public Xop_root_tkn				Root() {return root;} public Xoae_page Root_(Xop_root_tkn v) {root = v; return this;} private Xop_root_tkn root;
	public byte[]					Data_raw() {return data_raw;} public Xoae_page Data_raw_(byte[] v) {data_raw = v; return this;} private byte[] data_raw = Bry_.Empty;
	public Xow_hdr_mgr				Hdr_mgr() {return hdr_mgr;} private Xow_hdr_mgr hdr_mgr;
	public Xoh_cmd_mgr				Html_cmd_mgr() {return html_cmd_mgr;} private Xoh_cmd_mgr html_cmd_mgr = new Xoh_cmd_mgr();
	public byte[][]					Category_list() {return category_list;} public Xoae_page Category_list_(byte[][] v) {category_list = v; return this;} private byte[][] category_list = new byte[0][];
	public List_adp					Lnki_list() {return lnki_list;} public void Lnki_list_(List_adp v) {this.lnki_list = v;} private List_adp lnki_list = List_adp_.new_();
	public Xof_xfer_queue			File_queue() {return file_queue;} private Xof_xfer_queue file_queue = new Xof_xfer_queue();
	public List_adp					File_math() {return file_math;} private List_adp file_math = List_adp_.new_();
	public Ref_itm_mgr				Ref_mgr() {return ref_mgr;} private Ref_itm_mgr ref_mgr = new Ref_itm_mgr(); public void Ref_mgr_(Ref_itm_mgr v) {this.ref_mgr = v;}
	public Xopg_popup_mgr			Popup_mgr() {return popup_mgr;} private Xopg_popup_mgr popup_mgr = new Xopg_popup_mgr();
	public List_adp					Slink_list() {return slink_list;} private List_adp slink_list = List_adp_.new_();
	public Wdata_external_lang_links_data Wdata_external_lang_links() {return wdata_external_lang_links;} private Wdata_external_lang_links_data wdata_external_lang_links = new Wdata_external_lang_links_data();
	public boolean						Pages_recursed() {return pages_recursed;} public void Pages_recursed_(boolean v) {pages_recursed = v; } private boolean pages_recursed;
	public int						Bldr__ns_ord() {return bldr__ns_ord;} public void Bldr__ns_ord_(int v) {bldr__ns_ord = v;} private int bldr__ns_ord;
	public Xopg_tmpl_prepend_mgr	Tmpl_prepend_mgr() {return tmpl_prepend_mgr;} private Xopg_tmpl_prepend_mgr tmpl_prepend_mgr = new Xopg_tmpl_prepend_mgr();
	public void						Tmpl_stack_del() {--tmpl_stack_ary_len;}
	public boolean						Tmpl_stack_add(byte[] key) {
		for (int i = 0; i < tmpl_stack_ary_len; i++) {
			if (Bry_.Match(key, tmpl_stack_ary[i])) return false;
		}
		int new_len = tmpl_stack_ary_len + 1;
		if (new_len > tmpl_stack_ary_max) {
			tmpl_stack_ary_max = new_len * 2;
			tmpl_stack_ary = (byte[][])Array_.Resize(tmpl_stack_ary, tmpl_stack_ary_max);
		}
		tmpl_stack_ary[tmpl_stack_ary_len] = key;
		tmpl_stack_ary_len = new_len;
		return true;
	}	private byte[][] tmpl_stack_ary = Bry_.Ary_empty; private int tmpl_stack_ary_len = 0, tmpl_stack_ary_max = 0;
	public void Clear_all() {Clear(true);}
	public void Clear(boolean clear_scrib) { // NOTE: this is called post-fetch but pre-parse; do not clear items set by post-fetch, such as id, ttl, redirected_ttls, data_raw
		hdr_mgr.Clear();
		lnki_list.Clear();
		file_math.Clear();
		file_queue.Clear();
		ref_mgr.Grps_clear();
		html_cmd_mgr.Clear();
		hdump_data.Clear();
		wdata_external_lang_links.Reset();
		if (clear_scrib) gplx.xowa.xtns.scribunto.Scrib_core.Core_page_changed(this);
		slink_list.Clear();
		html_data.Clear();
		tab_data.Clear();
		pages_recursed = false;
		tmpl_stack_ary = Bry_.Ary_empty;
		tmpl_stack_ary_len = tmpl_stack_ary_max = 0;
		popup_mgr.Clear();
		revision_data.Clear();
		tmpl_prepend_mgr.Clear();
		commons_mgr.Clear();
	}
	private Xoae_page Edit_mode_create_() {edit_mode = Xoa_page_.Edit_mode_create; return this;}
	public static final Xoae_page Empty = new Xoae_page().Missing_();
	public static Xoae_page New(Xowe_wiki wiki, Xoa_ttl ttl)		{return new Xoae_page(wiki, ttl);}
	public static Xoae_page New_test(Xowe_wiki wiki, Xoa_ttl ttl)	{return new Xoae_page(wiki, ttl);}
	public static Xoae_page New_edit(Xowe_wiki wiki, Xoa_ttl ttl)	{return new Xoae_page(wiki, ttl).Edit_mode_create_();}
}
