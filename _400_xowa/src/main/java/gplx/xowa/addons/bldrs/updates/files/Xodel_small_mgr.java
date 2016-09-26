package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.dbs.*;
import gplx.xowa.bldrs.*;
import gplx.fsdb.*; import gplx.fsdb.meta.*; import gplx.xowa.files.*;
class Xodel_small_mgr {
	public void Exec(Xowe_wiki wiki, int[] ext_max_ary) {
		wiki.Init_assert();
		// get atr_conn
		Fsdb_db_mgr db_core_mgr = Fsdb_db_mgr_.new_detect(wiki, wiki.Fsys_mgr().Root_dir(), wiki.Fsys_mgr().File_dir());
		Fsdb_db_file atr_db = db_core_mgr.File__atr_file__at(Fsm_mnt_mgr.Mnt_idx_main);
		Db_conn atr_conn = atr_db.Conn();

		// get deletion_db
		Xob_db_file deletion_db = Xob_db_file.New__deletion_db(wiki);
		atr_conn.Env_db_attach("deletion_db", deletion_db.Conn());

		// insert into deletion_db if too small
		int len = ext_max_ary.length;
		for (int i = 0; i < len; ++i) {
			Find_small_files(atr_conn, i, ext_max_ary[i]);
		}

		atr_conn.Env_db_detach("deletion_db");
	}
	private static void Find_small_files(Db_conn conn, int ext_id, int max) {
		String ext_name = String_.new_u8(Xof_ext_.Get_ext_by_id_(ext_id));
		String reason = "small:" + ext_name;
		conn.Exec_sql_concat_w_msg
		( String_.Format("finding small files; ext={0} max={1}", ext_name, max)
		, "INSERT  INTO deletion_db.delete_regy (fil_id, thm_id, reason)"
		, "SELECT  t.thm_owner_id, t.thm_id, '" + reason + "'"
		, "FROM    fsdb_thm t"
		, "        JOIN fsdb_fil f ON t.thm_owner_id = f.fil_id"
		, "WHERE   f.fil_ext_id = " + Int_.To_str(ext_id)
		, "AND     t.thm_size <= " + Int_.To_str(max)
		);
	}
} 
class Xobldr__fsdb_db__delete_small_files_ {
	public static int[] New_ext_max_ary() {
		int[] rv = new int[Xof_ext_.Id__max];
		Ext_max_(rv,   35, Xof_ext_.Id_svg);
		Ext_max_(rv,   40, Xof_ext_.Id_gif);
		Ext_max_(rv,  100, Xof_ext_.Id_png, Xof_ext_.Id_jpg, Xof_ext_.Id_jpeg);
		Ext_max_(rv,  500, Xof_ext_.Id_tif, Xof_ext_.Id_tiff);
		Ext_max_(rv,  500, Xof_ext_.Id_xcf);
		Ext_max_(rv, 1000, Xof_ext_.Id_bmp);
		Ext_max_(rv,  700, Xof_ext_.Id_webm);
		Ext_max_(rv, 1000, Xof_ext_.Id_ogv);
		Ext_max_(rv,  400, Xof_ext_.Id_pdf);
		Ext_max_(rv,  700, Xof_ext_.Id_djvu);
		return rv;
	}
	private static void Ext_max_(int[] ary, int max, int... exts) {for (int ext : exts) ary[ext] = max;}
}
