package gplx.xowa.specials.xowa.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.encoders.*;
import gplx.core.ios.*; import gplx.xowa.apps.*; import gplx.xowa.wikis.*;
interface Xosp_fbrow_cmd {
	Xosp_fbrow_cmd Make_new();
	Xosp_fbrow_rslt Write_html(Xoa_url_arg_mgr arg_mgr, GfoInvkAble select_invkable);
}
abstract class Xosp_fbrow_cmd__base implements Xosp_fbrow_cmd {
	public abstract Xosp_fbrow_cmd Make_new();
	public Xosp_fbrow_rslt Write_html(Xoa_url_arg_mgr arg_mgr, GfoInvkAble select_invkable) {
		// app.I18n_mgr_Get_txt_many("xowa.specials.file_browsers.errs.unknown_path", url.Raw());
		Xosp_fbrow_html html_wtr = new Xosp_fbrow_html();
		Bry_bfr tmp_bfr = Bry_bfr.reset_(255);
		String selected_str = arg_mgr.Read_str_or_null("selected");
		if (selected_str == null) {
			String path_str = arg_mgr.Read_str_or_null("path"); if (path_str == null) return Xosp_fbrow_rslt.err_("url has unknown path");
			// if (Op_sys.Cur().Tid_is_wnt()) path_str = String_.Replace(path_str, "/", "\\");
			Io_url path_url = Io_url_.new_any_(path_str);
			IoItmDir dir = Io_mgr.Instance.QueryDir_args(path_url).DirInclude_(true).ExecAsDir();
			dir.SubDirs().Sort(); dir.SubFils().Sort();
			Xosp_fbrow_data_dir dir_itm = Xosp_fbrow_data_dir.new_(dir);
			Process_itms(dir_itm);
			String url_enc = String_.Replace(path_url.Raw(), "\\", "\\\\");
			byte[] cmd_src = this.Cmd_src();
			byte[] cmd_row = Html_body_cmd_row.Bld_bry_many(tmp_bfr, url_enc, cmd_src, this.Cmd_gui());
			html_wtr.Write(tmp_bfr, cmd_src, cmd_row, dir_itm);
			return new Xosp_fbrow_rslt(Html_head_default, tmp_bfr.To_bry_and_clear());
		}
		else
			return Write_html_selected(arg_mgr, selected_str, select_invkable);
	}
	private void Process_itms(Xosp_fbrow_data_dir dir_itm) {
		int len = dir_itm.Count();
		for (int i = 0; i < len; ++i) {
			Xosp_fbrow_data_sub itm = dir_itm.Get_at(i);
			this.Process_itm(itm);
		}
	}
	protected abstract byte[] Cmd_src();
	protected abstract String Cmd_gui();
	protected abstract Xosp_fbrow_rslt Write_html_selected(Xoa_url_arg_mgr arg_mgr, String selected, GfoInvkAble select_invkable);
	protected abstract void Process_itm(Xosp_fbrow_data_sub itm);
	private static final byte[] Html_head_default = Bry_.new_a7(String_.Concat_lines_nl_skip_last
	( "  <style type='text/css'>"
	, "    .xowa_filebrowser_tbl     {border: 1px solid #AAAAAA;}"
	, "    .xowa_filebrowser_tbl td  {padding: 0 1em 0 1em;}"
	, "    .xowa_filebrowser_td_size {text-align:right;}"
	, "  </style>"
	, "  <script type='text/javascript'>"
	, "    function Xo__file_browser__get_selected_chk(url_base, chk_prefix) {"
	, "		var list = document.getElementsByTagName('input');"
	, "		var list_len = list.length;"
	, "		var selected = '';"
	, "		var chk_prefix_len = chk_prefix.length;"
	, "		for (var i = 0; i < list_len; ++i) {"
	, "			var chk = list[i];"
	, "			if (chk.id.indexOf(chk_prefix) == 0 && chk.checked == true) {"
	, "			  selected = selected + chk.id.substring(chk_prefix_len) + ';';"
	, "			}"
	, "		}"
	, "		window.location.href = url_base + selected;"
	, "  }"
	, "  </script>"
	))
	;
	private static final Bry_fmtr Html_body_cmd_row = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "  <tr>"
	, "    <td>"
	, "      <table width='100%'>"
	, "        <tr>"
	, "          <td align='left'><a href='javascript:Xo__file_browser__get_selected_chk(\"/wiki/Special:XowaFileBrowser?cmd=~{cmd_src}&amp;path=~{url_enc}&amp;selected=\", \"chk_\");'>~{cmd_gui}</a>"
	, "      	 </td>"
	, "        </tr>"
	, "      </table>"
	, "    </td>"
	, "  </tr>"
	), "url_enc", "cmd_src", "cmd_gui")
	;
}
class Xosp_fbrow_html implements gplx.core.brys.Bfr_arg {
	private final Gfo_url_encoder encoder = Gfo_url_encoder_.Href; private Xosp_fbrow_data_dir dir; private byte[] cmd_src;
	public void Write(Bry_bfr bfr, byte[] cmd_src, byte[] cmd_row, Xosp_fbrow_data_dir dir) {
		this.dir = dir; this.cmd_src = cmd_src;
		// app.I18n_mgr_Get_txt_none("xowa.specials.file_browsers.html.hdr.name");
		// i18n.Get_txt_none("html.hdrs.name");
		fmtr_hdr.Bld_bfr_many(bfr
		, cmd_src, cmd_row
		, "name", "size", "modified"
		, dir.Url().Raw(), String_.Replace(dir.Url().Raw(), "\\", "\\\\"), encoder.Encode_str(dir.Url().OwnerDir().Raw())
		, this
		);
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int len = dir.Count();
		for (int i = 0; i < len; ++i) {
			Xosp_fbrow_data_sub itm = (Xosp_fbrow_data_sub)dir.Get_at(i);				
			String selected_html = itm.Selectable() ? ("<input type='checkbox' id='chk_" + itm.Url().NameAndExt_noDirSpr() + "'/>") : "&nbsp;&nbsp;&nbsp;";
			if (itm.Tid_is_dir())
				fmtr_row_dir.Bld_bfr_many(bfr, selected_html, encoder.Encode_str(itm.Url().Raw()), itm.Url().NameOnly(), cmd_src);
			else {
				fmtr_row_fil.Bld_bfr_many(bfr, selected_html, encoder.Encode_str(itm.Url().Raw()), itm.Url().NameAndExt()
				, Io_size_.To_str(itm.Size(), 1, "#,###", " ", Bool_.Y), itm.Modified().XtoStr_fmt_yyyy_MM_dd_HH_mm_ss());
			}
		}
	}
	private static final Bry_fmtr 
	fmtr_hdr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "<div class='xowa_filebrowser_help'>Please navigate to the directory containing the XOWA wiki. For example, choose 'storage' -> 'external_SD' -> 'xowa' -> 'wiki' -> 'en.wikipedia.org'</div>"
	, "<table>~{cmd_row}"
	, "  <tr>"
	, "    <td>"
	, "      <table class='xowa_filebrowser_tbl'>"
	, "        <caption>~{dir_url_raw}</caption>"
	, "        <tr>"
	, "          <td class='xowa_filebrowser_th'><!--selected--></td>"
	, "          <td class='xowa_filebrowser_th'>~{hdr_name}</td>"
	, "          <td class='xowa_filebrowser_th'>~{hdr_size}</td>"
	, "          <td class='xowa_filebrowser_th'>~{hdr_modified}</td>"
	, "        </tr>"
	, "        <tr>"
	, "          <td class='xowa_filebrowser_td_chkd'></td>"
	, "          <td class='xowa_filebrowser_td_name'><a href='/wiki/Special:XowaFileBrowser?cmd=~{cmd_src}&amp;path=~{owner_url_enc}'>[ parent directory ]</a></td>"
	, "          <td class='xowa_filebrowser_td_size'></td>"
	, "          <td class='xowa_filebrowser_td_date'></td>"
	, "        </tr>~{itms}"
	, "      </table>"
	, "    </td>"
	, "  </tr>"
	, "</table>"
	), "cmd_src", "cmd_row", "hdr_name", "hdr_size", "hdr_modified", "dir_url_raw", "dir_url_enc", "owner_url_enc", "itms"
	)
	, fmtr_row_dir = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "        <tr>"
	, "          <td class='xowa_filebrowser_td_chkd'>~{selected_html}</td>"
	, "          <td class='xowa_filebrowser_td_name'><a  class='xowa_filebrowser_anch' href='/wiki/Special:XowaFileBrowser?cmd=~{cmd_src}&amp;path=~{path_enc}'>~{name}</a></td>"
	, "          <td class='xowa_filebrowser_td_size'></td>"
	, "          <td class='xowa_filebrowser_td_date'></td>"
	, "        </tr>"
	), "selected_html", "path_enc", "name", "cmd_src"
	)
	, fmtr_row_fil = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "        <tr>"
	, "          <td class='xowa_filebrowser_td_chkd'>~{selected_html}</td>"
	, "          <td class='xowa_filebrowser_td_name'>~{name}</td>"
	, "          <td class='xowa_filebrowser_td_size'>~{size}</td>"
	, "          <td class='xowa_filebrowser_td_date'>~{modified}</td>"
	, "        </tr>"
	), "selected_html", "path_enc", "name", "size", "modified"
	);
}
class Xosp_fbrow_cmd__err implements Xosp_fbrow_cmd {
	public Xosp_fbrow_cmd Make_new() {return this;}
	public Xosp_fbrow_rslt Write_html(Xoa_url_arg_mgr arg_mgr, GfoInvkAble select_invkable) {return Rslt;}
	private static final Xosp_fbrow_rslt Rslt = new Xosp_fbrow_rslt(Bry_.Empty, Bry_.new_a7("url has unknown cmd"));
		public static final Xosp_fbrow_cmd__err Instance = new Xosp_fbrow_cmd__err(); Xosp_fbrow_cmd__err() {}
}
class Xosp_fbrow_cmd__wiki_add extends Xosp_fbrow_cmd__base {
	@Override protected byte[] Cmd_src() {return Regy_key;}
	@Override protected String Cmd_gui() {return "import";}
	@Override public Xosp_fbrow_cmd Make_new() {return new Xosp_fbrow_cmd__wiki_add();}
	@Override protected void Process_itm(Xosp_fbrow_data_sub itm) {
		byte[] url_bry = itm.Url().RawBry();
		if (!Bry_.Has_at_end(url_bry, Ext_xowa)) itm.Selectable_(false);
	}
	@Override protected Xosp_fbrow_rslt Write_html_selected(Xoa_url_arg_mgr arg_mgr, String selected, GfoInvkAble select_invkable) {
		String[] wikis = String_.Split(selected, ";");
		String path_str = arg_mgr.Read_str_or_null("path");
		Xowv_wiki wiki = (Xowv_wiki)GfoInvkAble_.InvkCmd_val(select_invkable, Xoav_wiki_mgr.Invk_import_by_fil, path_str + wikis[0]);			
		Bry_bfr bfr = Bry_bfr.reset_(255);
		done_fmtr.Bld_bfr_many(bfr, wiki.Domain_str(), wiki.Props().Main_page());
		return new Xosp_fbrow_rslt(Bry_.Empty, bfr.To_bry_and_clear());
	}
	private static final Bry_fmtr done_fmtr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "<a href='/site/~{domain}/wiki/~{main_page}'>~{domain} Main Page</a>"
	), "domain", "main_page");
	private static final byte[] Ext_xowa = Bry_.new_a7(".xowa");
	public static final byte[] Regy_key = Bry_.new_a7("xowa.wiki.add");
		public static final Xosp_fbrow_cmd__wiki_add Instance = new Xosp_fbrow_cmd__wiki_add();
}
class Xosp_fbrow_cmd__root_set extends Xosp_fbrow_cmd__base {
	@Override protected byte[] Cmd_src() {return Regy_key;}
	@Override protected String Cmd_gui() {return "set root dir";}
	@Override public Xosp_fbrow_cmd Make_new() {return new Xosp_fbrow_cmd__root_set();}
	@Override protected void Process_itm(Xosp_fbrow_data_sub itm) {
		if (itm.Url().Type_fil()) itm.Selectable_(false);
	}
	@Override protected Xosp_fbrow_rslt Write_html_selected(Xoa_url_arg_mgr arg_mgr, String selected, GfoInvkAble select_invkable) {
		return new Xosp_fbrow_rslt(Bry_.Empty, Bry_.new_u8(selected));
	}
	public static final byte[] Regy_key = Bry_.new_a7("xowa.fsys.root_");
		public static final Xosp_fbrow_cmd__root_set Instance = new Xosp_fbrow_cmd__root_set();
}
