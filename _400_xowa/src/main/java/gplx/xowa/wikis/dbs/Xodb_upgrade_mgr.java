package gplx.xowa.wikis.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*; import gplx.xowa.wikis.data.tbls.*;
class Xodb_upgrade_mgr {
	public static void Upgrade(Xodb_mgr_sql db_mgr, Db_cfg_hash cfg_hash, String version_key, String version_val) {
//			String version_new = null;
//			if	(String_.Eq(version_val, "0.6.2.0")) {
//				Xodb_upgrade_mgr_v0_6_2_0.Upgrade(db_mgr, kv_ary);
//				version_new = "0.6.2.1";
//			}
//			if (version_new != null) {
//				db_mgr.Tbl_xowa_cfg().Update(Xodb_mgr_sql.Grp__wiki_init, version_key, version_new);
//			}
	}
}
//	class Xodb_upgrade_mgr_v0_6_2_0 {
//		public static void Upgrade(Xodb_mgr_sql db_mgr, KeyVal[] kv_ary) {
//			Db_conn p = db_mgr.Fsys_mgr().Core_provider();
//			Fix_storage_format(p, db_mgr, kv_ary);
//			Fix_category_version(p, db_mgr);
//		}
//		private static void Fix_storage_format(Db_conn p, Xodb_mgr_sql db_mgr, KeyVal[] kv_ary) {	// storage_format saved incorrectly as int
//			int len = kv_ary.length;
//			String gfs_data_storage_format = Xoa_gfs_mgr.Build_code(Xowe_wiki.Invk_db_mgr, Xodb_mgr_sql.Invk_data_storage_format);
//			for (int i = 0; i < len; i++) {
//				KeyVal kv = kv_ary[i];
//				String kv_key = kv.Key();
//				if (String_.Eq(kv_key, gfs_data_storage_format)) {
//					byte data_storage_format_byte = Byte_.parse(kv.Val_to_str_or_empty());
//					String data_storage_format_name = Xoi_dump_mgr.Wtr_tid_to_str(data_storage_format_byte);
//					kv.Val_(data_storage_format_name);	// update memory
//					db_mgr.Tbl_xowa_cfg().Update(Xodb_mgr_sql.Grp__wiki_init, gfs_data_storage_format, data_storage_format_name); // update_database
//					break;
//				}
//			}			
//		}
//		private static void Fix_category_version(Db_conn p, Xodb_mgr_sql db_mgr) {
//			Db_qry qry = Db_qry_.select_().From_(Xodb_categorylinks_tbl.Tbl_name).Cols_(Xodb_categorylinks_tbl.Fld_cl_type_id).Where_(Db_crt_.eq_(Xodb_categorylinks_tbl.Fld_cl_type_id, ));
//			Db_stmt stmt = Db_stmt_.Null;
//			DataRdr rdr = DataRdr_.Null; 
//			int types = 0;
//			try {
//				stmt = db_mgr.Fsys_mgr().Category_provider().Prepare(qry);
//				rdr = stmt.Exec_select();
//				while (rdr.MoveNextPeer()) {
//					++types;
//				}
//			}	finally {rdr.Rls(); stmt.Rls();}
//			boolean schema_is_1 = types <= 1;	// if 0 or 1 types assume version_1 (1=page only; 0=not set up)
//			db_mgr.Category_version_update(true);	// assume version_1; will be wrong if user actually did version_2, but currently version_1 vs version_2 has no 
//		}
//	}
