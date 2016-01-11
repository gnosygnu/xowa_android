package gplx.xowa.apps; import gplx.*; import gplx.xowa.*;
import gplx.core.net.*; import gplx.core.log_msgs.*; import gplx.langs.jsons.*; import gplx.core.brys.*;
import gplx.core.ios.*;
import gplx.dbs.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.fsys.*; import gplx.xowa.apps.metas.*; import gplx.xowa.parsers.amps.*; import gplx.xowa.langs.cases.*; import gplx.core.intls.*; import gplx.xowa.users.data.*;
import gplx.xowa.apps.site_cfgs.*; import gplx.xowa.apps.urls.*; import gplx.xowa.files.caches.*; import gplx.xowa.files.imgs.*;
import gplx.xowa.bldrs.css.*;
import gplx.xowa.apps.gfs.*;
import gplx.xowa.htmls.hrefs.*; import gplx.xowa.htmls.core.htmls.utls.*; import gplx.xowa.htmls.bridges.*;
import gplx.xowa.users.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.xwikis.*; import gplx.xowa.wikis.xwikis.parsers.*; import gplx.xowa.wikis.xwikis.sitelinks.*;
import gplx.xowa.langs.*;
import gplx.xowa.bldrs.wms.*;
import gplx.langs.htmls.encoders.*;
public class Xoav_app implements Xoa_app, GfoInvkAble {
	public Xoav_app(Gfo_usr_dlg usr_dlg, Xoa_app_mode mode, String plat_name, Io_url root_dir, Io_url file_dir, Io_url css_dir) {
		Xoa_app_.Usr_dlg_(usr_dlg); this.usr_dlg = usr_dlg; this.mode = mode;
		this.fsys_mgr = new Xoa_fsys_mgr(plat_name, root_dir, root_dir.GenSubDir("wiki"), file_dir, css_dir);
		this.lang_mgr = new Xoa_lang_mgr(this);
		this.meta_mgr = new Xoa_meta_mgr(this);
		this.gfs_mgr = new Xoa_gfs_mgr(this, fsys_mgr, null);
		this.file__cache_mgr = new Xof_cache_mgr(usr_dlg, null, null);
		this.file__img_mgr = new Xof_img_mgr();
		this.wiki_mgr = new Xoav_wiki_mgr(this, utl_case_mgr);
		this.utl_msg_log = Gfo_msg_log.Test();
		this.html__lnki_bldr = new Xoh_lnki_bldr(this, html__href_wtr);
		this.html__bridge_mgr = new Xoh_bridge_mgr(utl__json_parser);
		this.user = new Xouv_user("anonymous");
		this.api_root = null;
		this.site_cfg_mgr = new Xoa_site_cfg_mgr(this);
	}
	public boolean						Tid_is_edit()				{return Bool_.N;}
	public Xoa_app_mode				Mode()						{return mode;} private final Xoa_app_mode mode;
	public Xou_user					User()						{return user;} private final Xouv_user user;
	public Xoapi_root				Api_root()					{return api_root;} private final Xoapi_root api_root;
	public Xoa_fsys_mgr				Fsys_mgr()					{return fsys_mgr;} private final Xoa_fsys_mgr fsys_mgr;
	public Xoav_wiki_mgr			Wiki_mgr()					{return wiki_mgr;} private final Xoav_wiki_mgr wiki_mgr;
	public Xoa_wiki_mgr				Wiki_mgri()					{return wiki_mgr;}
	public Xoa_lang_mgr				Lang_mgr()					{return lang_mgr;} private final Xoa_lang_mgr lang_mgr;
	public Xoa_gfs_mgr				Gfs_mgr()					{return gfs_mgr;} private final Xoa_gfs_mgr gfs_mgr;
	public Xof_cache_mgr			File__cache_mgr()			{return file__cache_mgr;} private final Xof_cache_mgr file__cache_mgr;
	public Xof_img_mgr				File__img_mgr()				{return file__img_mgr;} private final Xof_img_mgr file__img_mgr;
	public Io_download_fmt			File__download_fmt()		{return file__download_fmt;} private final Io_download_fmt file__download_fmt = new Io_download_fmt();
	public Xoh_href_parser			Html__href_parser()			{return href_parser;} private final Xoh_href_parser href_parser = new Xoh_href_parser();
	public Xoh_href_wtr				Html__href_wtr()			{return html__href_wtr;} private final Xoh_href_wtr html__href_wtr = new Xoh_href_wtr();
	public Xoh_lnki_bldr			Html__lnki_bldr()			{return html__lnki_bldr;} private final Xoh_lnki_bldr html__lnki_bldr;
	public Xoa_css_extractor		Html__css_installer()		{return html__css_installer;} private final Xoa_css_extractor html__css_installer = new Xoa_css_extractor();
	public Xoh_bridge_mgr			Html__bridge_mgr()			{return html__bridge_mgr;} private final Xoh_bridge_mgr html__bridge_mgr;
	public Xoa_meta_mgr				Dbmeta_mgr()					{return meta_mgr;} private final Xoa_meta_mgr meta_mgr;
	public Gfo_inet_conn			Utl__inet_conn()			{return inet_conn;} private final Gfo_inet_conn inet_conn = Gfo_inet_conn_.new_();
	public Xoa_site_cfg_mgr			Site_cfg_mgr()				{return site_cfg_mgr;} private final Xoa_site_cfg_mgr site_cfg_mgr;
	public boolean						Xwiki_mgr__missing(byte[] domain)	{return wiki_mgr.Get_by_domain(domain) == null;}
	public Xoa_sitelink_mgr			Xwiki_mgr__sitelink_mgr()	{return xwiki_mgr__sitelink_mgr;} private final Xoa_sitelink_mgr xwiki_mgr__sitelink_mgr = new Xoa_sitelink_mgr();
	public Xow_xwiki_itm_parser		Xwiki_mgr__itm_parser()		{return xwiki_mgr__itm_parser;}	private final Xow_xwiki_itm_parser xwiki_mgr__itm_parser = new Xow_xwiki_itm_parser();

	public Xowmf_mgr				Wmf_mgr()					{return wmf_mgr;} private final Xowmf_mgr wmf_mgr = new Xowmf_mgr();
	public Gfo_usr_dlg				Usr_dlg() {return usr_dlg;} public void Usr_dlg_(Gfo_usr_dlg v) {usr_dlg = v; Xoa_app_.Usr_dlg_(usr_dlg);} private Gfo_usr_dlg usr_dlg = Gfo_usr_dlg_.Noop;
	public Bry_bfr_mkr				Utl__bfr_mkr()				{return Xoa_app_.Utl__bfr_mkr();}
	public Json_parser				Utl__json_parser()			{return utl__json_parser;} private final Json_parser utl__json_parser = new Json_parser();
	public boolean						Bldr__running()				{return bldr__running;} public void Bldr__running_(boolean v) {this.bldr__running = v;} private boolean bldr__running;

	public Xop_amp_mgr Utl_amp_mgr() {return utl_amp_mgr;} private Xop_amp_mgr utl_amp_mgr = Xop_amp_mgr.Instance;
	public Xol_case_mgr Utl_case_mgr() {return utl_case_mgr;} private Xol_case_mgr utl_case_mgr = Xol_case_mgr_.U8();
//		public Gfo_url_encoder Utl_encoder_fsys() {return utl_encoder_fsys;} private Gfo_url_encoder utl_encoder_fsys = Gfo_url_encoder.New_fsys_lnx();
	public Gfo_msg_log Utl_msg_log() {return utl_msg_log;} private Gfo_msg_log utl_msg_log;
	public Xoav_url_parser Utl_url_parser_xo() {return utl_url_parser_xo;} private Xoav_url_parser utl_url_parser_xo = new Xoav_url_parser();
	public Gfo_url_parser Utl_url_parser_gfo() {return utl_url_parser_gfo;} private final Gfo_url_parser utl_url_parser_gfo = new Gfo_url_parser();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {throw Err_.new_unimplemented_w_msg("implemented for Xoa_cfg_mgr");}
	public void Init_by_app(Io_url user_db_url) {
		user.Init_db(this, wiki_mgr, user_db_url);
	}
}
