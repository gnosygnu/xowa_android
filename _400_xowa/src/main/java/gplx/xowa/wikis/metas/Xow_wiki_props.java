package gplx.xowa.wikis.metas; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.langs.msgs.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
import gplx.xowa.htmls.hrefs.*;
public class Xow_wiki_props implements Gfo_invk {
	public byte[] Main_page() {return main_page;} private byte[] main_page = Xoa_page_.Main_page_bry;	// HACK: default to Main_Page b/c some code tries to do Xoa_ttl.parse() which will not work with ""; DATE:2014-02-16
	public Xow_wiki_props Main_page_(byte[] v) {main_page = v; return this;}
	public void Main_page_update(Xowe_wiki wiki) {
		siteinfo_mainpage = main_page;										// note that main_page came from <siteinfo>; store old value for record's sake
		main_page = Xow_mainpage_finder.Find_or(wiki, siteinfo_mainpage);	// get new main_page from mainpage_finder
	}
	public byte Protocol_tid() {return protocol_tid;} private final    byte protocol_tid = gplx.core.net.Gfo_protocol_itm.Tid_https;	// NOTE: default protocol to https; handles external links like [//a.org]; may need to be changed for wikia or other non-WMF wikis; DATE:2015-07-27

	public byte[] Site_name() {return site_name;} private byte[] site_name = Bry_.Empty;
	public byte[] Server_name() {return server_name;} private byte[] server_name = Bry_.new_a7("localhost");
	public byte[] Server() {return server;} private byte[] server = Bry_.new_a7("http://localhost");
	public byte[] ArticlePath() {return articlePath;} public Xow_wiki_props ArticlePath_(byte[] v) {articlePath = v; return this;} private byte[] articlePath = Xoh_href_.Bry__wiki;
	public byte[] ScriptPath() {return scriptPath;} public Xow_wiki_props ScriptPath_(byte[] v) {scriptPath = v; return this;} private byte[] scriptPath = Bry_.new_a7("/wiki");
	public byte[] StylePath() {return stylePath;} public Xow_wiki_props StylePath_(byte[] v) {stylePath = v; return this;} private byte[] stylePath = Bry_.new_a7("/wiki/skins");
	public byte[] ContentLanguage() {return contentLanguage;} public Xow_wiki_props ContentLanguage_(byte[] v) {contentLanguage = v; return this;} private byte[] contentLanguage = Bry_.Empty;
	public byte[] DirectionMark() {return directionMark;} public Xow_wiki_props DirectionMark_(byte[] v) {directionMark = v; return this;} private byte[] directionMark = Bry_.Empty;
	public byte[] Current_version() {return Current_version_const;}
	public byte[] Bldr_version() {return bldr_version;} public Xow_wiki_props Bldr_version_(byte[] v) {bldr_version = v; return this;} private byte[] bldr_version = Bry_.Empty;
	public int Css_version() {return css_version;} public Xow_wiki_props Css_version_(int v) {css_version = v; return this;} private int css_version = 1;
	public byte[] Siteinfo_misc() {return siteinfo_misc;}
	public byte[] Siteinfo_mainpage() {return siteinfo_mainpage;} private byte[] siteinfo_mainpage = Bry_.Empty;
	public DateAdp Modified_latest() {return modified_latest;} private DateAdp modified_latest;
	public String Modified_latest__yyyy_MM_dd() {return modified_latest == null ? "" : modified_latest.XtoStr_fmt_yyyy_MM_dd();}
	public Xow_wiki_props Init_props(int domain_tid, byte[] domain_bry) {
		this.site_name = Bry_.new_a7(String_.UpperFirst(String_.new_a7(Xow_domain_tid_.Get_type_as_bry(domain_tid))));	// EX: "Wikipedia"
		this.server_name = domain_bry;																	// EX: "en.wikipedia.org"
		this.server = Bry_.Add(gplx.core.net.Gfo_protocol_itm.Itm_https.Text_bry(), domain_bry);		// EX: "https://en.wikipedia.org"
		return this;
	}
	public Xow_wiki_props Siteinfo_misc_(byte[] v) {
		siteinfo_misc = v;
		int pipe_0 = Bry_find_.Find_fwd(v, Byte_ascii.Pipe);
		if (pipe_0 != Bry_find_.Not_found)
			site_name = Bry_.Mid(siteinfo_misc, 0, pipe_0);
		return this;
	}	private byte[] siteinfo_misc = Bry_.Empty;
	public void Init_by_load(Xoa_app app, gplx.dbs.cfgs.Db_cfg_tbl cfg_tbl) {
		if (app.Bldr__running()) return;	// never load main_page during bldr; note that Init_by_load is called by bldr cmds like css; DATE:2015-07-24
		this.main_page = cfg_tbl.Select_bry_or(Xow_cfg_consts.Grp__wiki_init, Xow_cfg_consts.Key__init__main_page, null);
		this.modified_latest = cfg_tbl.Select_date_or(Xow_cfg_consts.Grp__wiki_init, Xow_cfg_consts.Key__init__modified_latest, null);
		if	(main_page == null) {			// main_page not found
			Xoa_app_.Usr_dlg().Warn_many("", "", "mw_props.load; main_page not found; conn=~{0}", cfg_tbl.Conn().Conn_info().Db_api());
			this.main_page = Xoa_page_.Main_page_bry;
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_main_page_))						main_page = m.ReadBry("v");
		else if	(ctx.Match(k, Invk_bldr_version_))					bldr_version = m.ReadBry("v");
		else if	(ctx.Match(k, Invk_siteinfo_misc_))					Siteinfo_misc_(m.ReadBry("v"));
		else if	(ctx.Match(k, Invk_siteinfo_mainpage_))				siteinfo_mainpage = m.ReadBry("v");
		else if	(ctx.Match(k, Invk_css_version_))					css_version = m.ReadInt("v");
		else if	(ctx.Match(k, Invk_modified_latest_))				modified_latest = m.ReadDate("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	public static final String Invk_main_page_ = "main_page_"
	, Invk_bldr_version = "bldr_version", Invk_bldr_version_ = "bldr_version_", Invk_siteinfo_misc_ = "siteinfo_misc_", Invk_siteinfo_mainpage_ = "siteinfo_mainpage_"
	, Invk_css_version_ = "css_version_"
	, Invk_modified_latest_ = "modified_latest_"
	;
	private static final    byte[] Current_version_const = Bry_.new_a7("1.21wmf11");	// approximate level of compatibility
}
