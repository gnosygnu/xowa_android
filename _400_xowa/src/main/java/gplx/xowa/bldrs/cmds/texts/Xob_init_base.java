package gplx.xowa.bldrs.cmds.texts; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.xmls.*; import gplx.xowa.bldrs.cmds.texts.xmls.*;
import gplx.xowa.bldrs.css.*; import gplx.xowa.wikis.domains.*;
public abstract class Xob_init_base implements Xob_cmd, Gfo_invk {
	private Xob_bldr bldr; private Xowe_wiki wiki; private Gfo_usr_dlg usr_dlg;
	private byte wbase_enabled = Bool_.__byte;
	public Xob_init_base Ctor(Xob_bldr bldr, Xowe_wiki wiki) {this.bldr = bldr; this.wiki = wiki; this.usr_dlg = wiki.Appe().Usr_dlg(); return this;}
	public abstract String Cmd_key();
	public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	public abstract void Cmd_ini_wdata(Xob_bldr bldr, Xowe_wiki wiki);
	public abstract void Cmd_run_end(Xowe_wiki wiki);
	@gplx.Virtual public void Cmd_init(Xob_bldr bldr) {		// add other cmds; EX: wikidata
		bldr.Import_marker().Bgn(wiki);
		if (wbase_enabled == Bool_.__byte) wbase_enabled = wiki.Domain_tid() == Xow_domain_tid_.Int__wikidata ? Bool_.Y_byte : Bool_.N_byte;	// if wbase_enabled not explicitly set, set it to y if wiki is "www.wikidata.org"
		if (wbase_enabled == Bool_.Y_byte)		// if wbase_enabled, auto-add wdata_wkrs bldr
			this.Cmd_ini_wdata(bldr, wiki);
	}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_run() {						// parse site_info
		gplx.core.ios.streams.Io_stream_rdr src_rdr = wiki.Import_cfg().Src_rdr(); usr_dlg.Plog_many("", "", "reading dump header: ~{0}", src_rdr.Url().Raw());
		Xob_siteinfo_parser_.Parse(Xob_siteinfo_parser_.Extract(src_rdr), wiki);
		this.Cmd_run_end(wiki);					// save site info
	}
	public void Cmd_end() {
		wiki.Appe().Gui_mgr().Html_mgr().Portal_mgr().Wikis().Itms_reset();	// dirty wiki list so that next refresh will load itm
		if (wiki.Appe().Setup_mgr().Dump_mgr().Css_wiki_update()) {
			Io_url url = wiki.Appe().Fsys_mgr().Wiki_css_dir(wiki.Domain_str()).GenSubFil(Xoa_css_extractor.Css_wiki_name);
			usr_dlg.Log_many("", "", "deleting css: ~{0}", url.Raw());
			Io_mgr.Instance.DeleteFil_args(url).MissingFails_off().Exec();
		}
	}
	@gplx.Virtual public void Cmd_term() {}
	@gplx.Virtual public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_src_xml_fil_))				wiki.Import_cfg().Src_fil_xml_(m.ReadIoUrl("v"));
		else if	(ctx.Match(k, Invk_src_bz2_fil_))				wiki.Import_cfg().Src_fil_bz2_(m.ReadIoUrl("v"));
		else if	(ctx.Match(k, Invk_wdata_enabled_))				wbase_enabled = m.ReadYn("v") ? Bool_.Y_byte : Bool_.N_byte;
		else if	(ctx.Match(k, Invk_owner))						return bldr.Cmd_mgr();
		else return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_src_xml_fil_ = "src_xml_fil_", Invk_src_bz2_fil_ = "src_bz2_fil_", Invk_owner = "owner", Invk_wdata_enabled_ = "wdata_enabled_";
}
