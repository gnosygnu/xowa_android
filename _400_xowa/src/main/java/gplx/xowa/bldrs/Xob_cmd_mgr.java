package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.core.primitives.*;
import gplx.xowa.wikis.*; import gplx.xowa.xtns.wdatas.imports.*;
import gplx.xowa.bldrs.cmds.texts.*; import gplx.xowa.bldrs.cmds.texts.sqls.*; import gplx.xowa.bldrs.cmds.texts.tdbs.*; import gplx.xowa.bldrs.cmds.files.*; import gplx.xowa.bldrs.cmds.ctgs.*; import gplx.xowa.bldrs.cmds.utils.*; import gplx.xowa.bldrs.cmds.wikis.*;
import gplx.xowa.files.origs.*; import gplx.xowa.htmls.core.bldrs.*;
public class Xob_cmd_mgr implements GfoInvkAble {
	public Xob_cmd_mgr(Xob_bldr bldr) {this.bldr = bldr;} private Xob_bldr bldr;
	public void Clear() {list.Clear(); dump_rdrs.Clear();}
	public int Len() {return list.Count();} List_adp list = List_adp_.new_();
	public Xob_cmd Get_at(int i) {return (Xob_cmd)list.Get_at(i);} 
	public Xob_cmd Add(Xob_cmd cmd) {list.Add(cmd); return cmd;}
	public GfoInvkAble Add_cmd(Xowe_wiki wiki, String cmd_key) {
		if		(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_init))					return Add(new Xob_init_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_page))					return Xml_rdr_direct_add(wiki, new Xob_page_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_css))					return Add(new Xob_css_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_search_wkr))				return Xml_rdr_direct_add(wiki, new Xob_search_sql_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_search_cmd))				return Add(new Xob_search_sql_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_cat_core_v1))			return Xml_rdr_parser_add(wiki, new Xob_ctg_v1_sql().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_cat_core))				return Add(new Xob_category_registry_sql(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_cat_link))				return Add(new Xob_categorylinks_sql(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_cat_hidden))				return Add(new Xoctg_hiddencat_parser_sql(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_term))					return Add(new Xob_term_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_text_delete_page))			return Add(new Xob_page_delete_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wiki_page_dump_make))			return Add(new Xob_page_dump_cmd_make(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wiki_page_dump_drop))			return Add(new Xob_page_dump_cmd_drop(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wiki_redirect))				return Add(new Xob_redirect_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wiki_image))					return Add(new Xob_image_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wiki_pagelink))				return Add(new Xob_pagelinks_parser_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_lnki_temp))				return Add(new Xob_lnki_temp_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_lnki_regy))				return Add(new Xob_lnki_regy_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_page_regy))				return Add(new Xob_page_regy_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_orig_regy))				return Add(new Xob_orig_regy_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_xfer_temp_thumb))		return Add(new Xob_xfer_temp_cmd_thumb(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_xfer_temp_orig))			return Add(new Xob_xfer_temp_cmd_orig(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_xfer_regy))				return Add(new Xob_xfer_regy_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_xfer_regy_update))		return Add(new Xob_xfer_regy_update_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_fsdb_make))				return Add(new Xob_fsdb_make_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_orig_reg))				return Add(new Xob_orig_tbl_bldr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_file_xfer_update))			return Add(new Xob_xfer_update_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_html_redlinks))				return Add(new Xob_redlink_mkr_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_util_cleanup))				return Add(new Xob_cleanup_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_util_download))				return Add(new Xob_download_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_util_xml_dump))				return Add(new Xob_xml_dumper_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wbase_json_dump))				return Add(new Xob_wbase_json_dump_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wbase_qid))					return Xml_rdr_direct_add(wiki, new Xob_wdata_qid_sql().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wbase_pid))					return Xml_rdr_direct_add(wiki, new Xob_wdata_pid_sql().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_wbase_db))					return Add(new Xob_wdata_db_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_site_meta))					return Add(new Xob_site_meta_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_text_init))				return Add(new Xob_init_tdb(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_make_page))				return Xml_rdr_direct_add(wiki, new Xob_page_txt(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_make_id))					return Xml_rdr_direct_add(wiki, new Xob_make_id_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_make_search_title))		return Xml_rdr_direct_add(wiki, new Xob_search_tdb(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_make_category))			return Xml_rdr_parser_add(wiki, new Xob_ctg_v1_txt().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_calc_stats))				return Add(new Xob_calc_stats_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_text_cat_link))			return Add(new Xob_categorylinks_txt(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_ctg_link_idx))			return Add(new Xoctg_link_idx_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_cat_hidden_sql))			return Add(new Xoctg_hiddencat_parser_txt(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_cat_hidden_ttl))			return Add(new Xoctg_hiddencat_ttl_wkr(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_core_term))				return Add(new Xob_term_txt(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_text_wdata_qid))			return Xml_rdr_direct_add(wiki, new Xob_wdata_qid_txt().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_tdb_text_wdata_pid))			return Xml_rdr_direct_add(wiki, new Xob_wdata_pid_txt().Ctor(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_diff_regy_exec))				return Add(new Xob_diff_regy_exec_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_diff_regy_make))				return Add(new Xob_diff_regy_make_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_exec_sql))					return Add(new Xob_exec_sql_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_decompress_bz2))				return Add(new Xob_decompress_bz2_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_deploy_zip))					return Add(new Xob_deploy_zip_cmd(bldr, wiki));
		else if	(String_.Eq(cmd_key, Xob_cmd_keys.Key_deploy_copy))					return Add(new Xob_deploy_copy_cmd(bldr, wiki));
		else 																		throw Err_.new_unhandled(cmd_key);
	}
	private Xobd_wkr Xml_rdr_direct_add(Xowe_wiki wiki, Xobd_wkr wkr) {
		Xobd_rdr dump_rdr = Xml_rdr_get(wiki);
		dump_rdr.Wkr_add(wkr);
		return wkr;
	}
	private Xobd_parser_wkr Xml_rdr_parser_add(Xowe_wiki wiki, Xobd_parser_wkr wkr) {
		Xobd_rdr dump_rdr = Xml_rdr_get(wiki);
		dump_rdr.Page_parser_assert().Wkr_add(wkr);
		return wkr;
	}
	private Xobd_rdr Xml_rdr_get(Xowe_wiki wiki) {
		byte[] wiki_key = wiki.Domain_bry();
		Xobd_rdr rv = (Xobd_rdr)dump_rdrs.Get_by(dump_rdrs_ref.Val_(wiki_key));
		if (rv == null) {
			rv = new Xobd_rdr(bldr, wiki);
			dump_rdrs.Add(Bry_obj_ref.New(wiki_key), rv);
			this.Add(rv);
		}
		return rv;
	}
	private Hash_adp dump_rdrs = Hash_adp_.new_(); private Bry_obj_ref dump_rdrs_ref = Bry_obj_ref.New_empty();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if			(ctx.Match(k, Invk_add))				return Add_cmd(Wiki_get_or_make(m), m.ReadStr("v"));
		else if		(ctx.Match(k, Invk_add_many))			return Add_many(m);
		else if		(ctx.Match(k, Invk_get_first))			return Get_first(m);
		else if		(ctx.Match(k, Invk_new_batch))			return new Xob_core_batch_utl(bldr, m.ReadBry("v"));
		else	return GfoInvkAble_.Rv_unhandled;
	}
	private static final String Invk_add = "add", Invk_add_many = "add_many", Invk_new_batch = "new_batch", Invk_get_first = "get_first";
	private Object Get_first(GfoMsg m) {
		String cmd_key = m.ReadStr("v");
		int cmds_len = list.Count();
		for (int i = 0;i < cmds_len; i++) {
			Xob_cmd cmd = (Xob_cmd)list.Get_at(i);
			if (String_.Eq(cmd.Cmd_key(), cmd_key)) return cmd;
		}
		throw Err_.new_wo_type("cmd not found", "key", cmd_key);
	}
	private Object Add_many(GfoMsg m) {
		Xowe_wiki wiki = Wiki_get_or_make(m);
		wiki.Lang().Init_by_load_assert();	// NOTE: must check that lang is loaded; else case_mgr will not initialize; DATE:2013-05-11
		int args_len = m.Args_count();
		String[] cmds = new String[args_len - 1];	// -1 b/c 1st arg is wiki
		for (int i = 1; i < args_len; i++) {
			KeyVal kv = m.Args_getAt(i);
			cmds[i - 1] = kv.Val_to_str_or_empty();
		}
		return Add_many(wiki, cmds);
	}
	public Object Add_many(Xowe_wiki wiki, String... cmds) {
		int len = cmds.length; if (len == 0) throw Err_.new_wo_type("add_many cannot have 0 cmds");
		Object rv = null;
		for (int i = 0; i < len; i++)
			rv = Add_cmd(wiki, cmds[i]);
		return rv;
	}
	private Xowe_wiki Wiki_get_or_make(GfoMsg m) {
		byte[] wiki_key = m.ReadBry("v");
		Xoae_wiki_mgr wiki_mgr = bldr.App().Wiki_mgr();
		Xowe_wiki rv = wiki_mgr.Get_by_key_or_make(wiki_key);
		rv.Lang().Init_by_load();
		return rv;
	}
	public static final String GRP_KEY = "xowa.bldr.cmds";
}
