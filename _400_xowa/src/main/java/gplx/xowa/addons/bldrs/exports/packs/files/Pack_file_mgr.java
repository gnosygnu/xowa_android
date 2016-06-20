package gplx.xowa.addons.bldrs.exports.packs.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.packs.*;
import gplx.core.progs.*; import gplx.core.ios.zips.*; import gplx.core.ios.streams.*; import gplx.core.security.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.*; import gplx.fsdb.*; import gplx.fsdb.meta.*;
import gplx.xowa.addons.bldrs.centrals.dbs.*; import gplx.xowa.addons.bldrs.centrals.dbs.datas.*; import gplx.xowa.addons.bldrs.centrals.dbs.datas.imports.*; import gplx.xowa.addons.bldrs.centrals.steps.*;
class Pack_file_mgr {
	public void Exec(Xowe_wiki wiki) {
		// init
		wiki.Init_assert();
		Io_url wiki_dir = wiki.Fsys_mgr().Root_dir();
		Io_url pack_dir = wiki_dir.GenSubDir_nest("tmp", "pack");
		Io_mgr.Instance.DeleteDirDeep(pack_dir); Io_mgr.Instance.CreateDirIfAbsent(pack_dir);
		String wiki_date = wiki.Props().Modified_latest().XtoStr_fmt("yyyy.MM");
		Pack_hash hash = Pack_hash_bldr.Bld(wiki, wiki_dir, pack_dir, wiki_date);

		// get import_tbl
		byte[] wiki_abrv = wiki.Domain_itm().Abrv_xo();
		Xobc_data_db bc_db = new Xobc_data_db(wiki.App().Fsys_mgr().Bin_addon_dir().GenSubFil_nest("bldr", "central", "bldr_central.data_db.xowa"));
		Db_conn bc_conn = bc_db.Conn();
		bc_db.Delete_by_import(wiki_abrv, wiki_date);
		bc_conn.Txn_bgn("xobc_import_insert");

		// build zip packs
		Hash_algo hash_algo = Hash_algo_.New__md5();
		Bry_bfr tmp_bfr = Bry_bfr_.New();
		int hash_len = hash.Len();
		for (int i = 0; i < hash_len; ++i) {
			Pack_list list = (Pack_list)hash.Get_at(i);
			int list_len = list.Len();
			for (int j = 0; j < list_len; ++j) {
				Pack_itm itm = (Pack_itm)list.Get_at(j);
				Make_pack(wiki, wiki_dir, wiki_abrv, wiki_date, bc_db, hash_algo, tmp_bfr, itm, 0);
			}
		}

		// build tasks
		Make_task(tmp_bfr, wiki, wiki_date, bc_db, hash, "html", Xobc_import_type.Tid__wiki__core, Xobc_import_type.Tid__wiki__srch, Xobc_import_type.Tid__wiki__html);
		Make_task(tmp_bfr, wiki, wiki_date, bc_db, hash, "file", Xobc_import_type.Tid__file__core, Xobc_import_type.Tid__file__data);
		bc_conn.Txn_end();
	}
	private static void Make_task(Bry_bfr tmp_bfr, Xow_wiki wiki, String wiki_date, Xobc_data_db bc_db, Pack_hash hash, String task_type, int... list_tids) {
		// get packs
		List_adp pack_list = List_adp_.New();
		int list_tids_len = list_tids.length;
		long raw_len = 0;
		for (int i = 0; i < list_tids_len; ++i) {
			Pack_list list = hash.Get_by(list_tids[i]);
			if (list == null) continue; // no lists for that tid;
			int list_len = list.Len();
			for (int j = 0; j < list_len; ++j) {
				Pack_itm itm = (Pack_itm)list.Get_at(j);
				raw_len += itm.Raw_size();
				pack_list.Add(itm);
			}
		}
		int pack_list_len = pack_list.Len();

		// create task
		String task_key = wiki.Domain_str() + "|" + wiki_date + "|" + task_type;
		String task_name = Build_task_name(tmp_bfr, wiki, wiki_date, task_type, raw_len);
		Xobc_task_regy_tbl task_regy_tbl = bc_db.Tbl__task_regy();
		int task_id = bc_db.Conn().Sys_mgr().Autonum_next("task_regy.task_id");
		task_regy_tbl.Insert(task_id, task_id, pack_list_len, task_key, task_name);

		// map steps
		for (int i = 0; i < pack_list_len; ++i) {
			Pack_itm itm = (Pack_itm)pack_list.Get_at(i);
			int sm_id = bc_db.Conn().Sys_mgr().Autonum_next("step_map.sm_id");
			bc_db.Tbl__step_map().Insert(sm_id, task_id, itm.Step_id(), i);
		}
	}
	public static String Build_task_name(Bry_bfr tmp_bfr, Xow_wiki wiki, String wiki_date, String task_type, long raw_len) {// Simple Wikipedia - Articles (2016-06) [420.31 MB]
		byte[] lang_name = gplx.xowa.langs.Xol_lang_stub_.Get_by_key_or_null(wiki.Domain_itm().Lang_orig_key()).Canonical_name();	// EX: Deutsch
		byte[] wiki_name = wiki.Domain_itm().Domain_type().Display_bry();															// EX: Wikipedia
		String type_name = String_.Eq(task_type, "html") ? "Articles" : "Images";
		wiki_date = String_.Replace(wiki_date, ".", "-");
		String file_size = gplx.core.ios.Io_size_.To_str_new(tmp_bfr, raw_len, 2);
		return String_.Format("{0} {1} - {2} ({3}) [{4}]", lang_name, wiki_name, type_name, wiki_date, file_size);
	}
	private static void Make_pack(Xowe_wiki wiki, Io_url wiki_dir, byte[] wiki_abrv, String wiki_date, Xobc_data_db bc_db, Hash_algo hash_algo, Bry_bfr tmp_bfr, Pack_itm itm, int task_id) {
		// hash raws			
		Io_url zip_url = itm.Zip_url();
		Gfo_log_.Instance.Prog("hashing raw: " + zip_url.NameAndExt());
		Io_url md5_url = wiki_dir.GenSubFil(zip_url.NameOnly() + ".md5");
		long raw_size = 0;
		Io_url[] raw_urls = itm.Raw_urls();
		int raw_urls_len = raw_urls.length;
		for (int i = 0; i < raw_urls_len; ++i) {
			Io_url raw_url = raw_urls[i];
			IoStream raw_stream = Io_mgr.Instance.OpenStreamRead(raw_url);
			byte[] raw_md5 = null;
			try {raw_md5 = hash_algo.Hash_stream_as_bry(Gfo_prog_ui_.Noop, raw_stream);}
			finally {raw_stream.Rls();}
			tmp_bfr.Add(raw_md5).Add_byte_space().Add_byte(Byte_ascii.Star).Add_str_a7(raw_url.NameAndExt()).Add_byte_nl();
			raw_size += raw_stream.Len();
		}
		Io_mgr.Instance.SaveFilBfr(md5_url, tmp_bfr);
		itm.Raw_size_(raw_size);

		// compress raws
		Gfo_log_.Instance.Prog("compressing raw");
		Io_zip_compress_cmd__jre zip_cmd = new Io_zip_compress_cmd__jre();
		raw_urls = (Io_url[])Array_.Insert(raw_urls, new Io_url[] {md5_url}, 0);	// add ".md5" to .zip
		zip_cmd.Exec_hook(Gfo_prog_ui_.Noop, raw_urls, zip_url, "", 0, 0);

		// hash zip
		Gfo_log_.Instance.Prog("hashing zip");
		IoStream zip_stream = Io_mgr.Instance.OpenStreamRead(zip_url);
		byte[] zip_md5 = null;
		try		{zip_md5 = hash_algo.Hash_stream_as_bry(Gfo_prog_ui_.Noop, zip_stream);}
		finally {zip_stream.Rls();}
		long zip_len = Io_mgr.Instance.QueryFil(zip_url).Size();

		// cleanup
		Io_mgr.Instance.DeleteFil(md5_url);

		// generate import
		Gfo_log_.Instance.Prog("generating tasks");
		int step_id = bc_db.Conn().Sys_mgr().Autonum_next("step_regy.step_id");
		itm.Step_id_(step_id);
		bc_db.Tbl__step_regy().Insert(step_id, Xobc_step_itm.Type__wiki_import);
		bc_db.Tbl__import_step().Insert(step_id, gplx.xowa.addons.bldrs.centrals.dbs.datas.Xobc_host_regy_tbl.Host_id__archive_org, wiki_abrv, wiki_date, zip_url.NameAndExt(), itm.Tid(), Xobc_zip_type.Type__zip, zip_md5, zip_len, raw_size, 0, 0);
	}
}
class Pack_hash_bldr {
	public static Pack_hash Bld(Xow_wiki wiki, Io_url wiki_dir, Io_url pack_dir, String wiki_date) {
		Pack_hash rv = new Pack_hash();
		Pack_zip_name_bldr zip_name_bldr = new Pack_zip_name_bldr(pack_dir, wiki.Domain_str(), String_.new_a7(wiki.Domain_itm().Abrv_wm()), wiki_date);

		// bld html pack
		Xow_db_mgr db_mgr = wiki.Data__core_mgr();
		int len = db_mgr.Dbs__len();
		for (int i = 0; i < len; ++i) {
			Xow_db_file file = db_mgr.Dbs__get_at(i);
			int pack_tid = Get_pack_tid(file.Tid());
			if (pack_tid == Xobc_import_type.Tid__ignore) continue;
			rv.Add(zip_name_bldr, pack_tid, file.Url());
		}
		rv.Consolidate(Xobc_import_type.Tid__wiki__srch);

		// bld file pack
		Fsm_mnt_itm mnt_itm = wiki.File__mnt_mgr().Mnts__get_at(Fsm_mnt_mgr.Mnt_idx_main);
		rv.Add(zip_name_bldr, Xobc_import_type.Tid__file__core, wiki_dir.GenSubFil(mnt_itm.Atr_mgr().Db__core().Url_rel()));
		if (db_mgr.Props().Layout_file().Tid_is_lot()) {
			Fsm_bin_mgr bin_mgr = mnt_itm.Bin_mgr();
			int bin_len = bin_mgr.Dbs__len();
			for (int i = 0; i < bin_len; ++i) {
				Fsm_bin_fil bin_fil = bin_mgr.Dbs__get_at(i);
				rv.Add(zip_name_bldr, Xobc_import_type.Tid__file__data, bin_fil.Url());
			}
		}
		return rv;
	}
	private static int Get_pack_tid(byte db_file_tid) {
		switch (db_file_tid) {
			case Xow_db_file_.Tid__core:		return Xobc_import_type.Tid__wiki__core;
			case Xow_db_file_.Tid__search_core:	
			case Xow_db_file_.Tid__search_link:	return Xobc_import_type.Tid__wiki__srch;
			case Xow_db_file_.Tid__html_solo:
			case Xow_db_file_.Tid__html_data:	return Xobc_import_type.Tid__wiki__html;
			case Xow_db_file_.Tid__file_core:	return Xobc_import_type.Tid__file__core;
			case Xow_db_file_.Tid__file_solo:
			case Xow_db_file_.Tid__file_data:	return Xobc_import_type.Tid__file__data;
			default:							return Xobc_import_type.Tid__ignore;
		}
	}
}
class Pack_zip_name_bldr {	// en.wikipedia.org-file-ns.000-db.001.xowa -> Xowa_enwiki_2016-05_file_ns.000_db.001.zip
	private final    Io_url pack_dir;
	private final    byte[] wiki_domain, zip_name_prefix;

	public Pack_zip_name_bldr(Io_url pack_dir, String wiki_domain_str, String wiki_abrv, String wiki_date) {
		this.pack_dir = pack_dir;
		this.wiki_domain = Bry_.new_u8(wiki_domain_str);
		this.zip_name_prefix = Bry_.new_u8("Xowa_" + wiki_abrv + "_" + String_.Replace(wiki_date, ".", "-"));
	}
	public Io_url Bld(Io_url orig_url) {
		String orig_str = String_.Replace(orig_url.NameAndExt(), ".xowa", ".zip");
		byte[] orig_bry = Bry_.new_u8(orig_str);
		orig_bry = Bry_.Replace(orig_bry, Byte_ascii.Dash, Byte_ascii.Underline);
		orig_bry = Bry_.Replace(orig_bry, wiki_domain, zip_name_prefix);
		return pack_dir.GenSubFil(String_.new_u8(orig_bry));
	}
}
