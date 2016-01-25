package gplx.xowa.htmls.css; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.envs.*;
import gplx.dbs.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.bldrs.css.*;
public class Xowd_css_core_mgr {
	public static void Set(Xowd_css_core_tbl core_tbl, Xowd_css_file_tbl file_tbl, Io_url css_dir, String key) {
		Db_conn conn = core_tbl.Conn();
		Io_url[] file_list = Io_mgr.Instance.QueryDir_args(css_dir).Recur_().ExecAsUrlAry();
		try {
			conn.Txn_bgn("schema__css_core__set");
			int css_id = core_tbl.Select_id_by_key(key);
			DateAdp updated_on = DateAdp_.Now().XtoUtc();
			if (css_id == -1)
				css_id = core_tbl.Insert(key, updated_on);
			else {
				core_tbl.Update(css_id, key, updated_on);
				file_tbl.Delete(css_id);
			}
			for (Io_url file : file_list) {
				String path = Op_sys.Fsys_path_to_lnx(file.GenRelUrl_orEmpty(css_dir));
				byte[] data = Io_mgr.Instance.LoadFilBry(file);
				file_tbl.Insert(css_id, path, data);
			}
			conn.Txn_end();
		}
		catch (Exception e) {conn.Txn_cxl(); throw e;}
	}
	public static boolean Get(Xowd_css_core_tbl core_tbl, Xowd_css_file_tbl file_tbl, Io_url css_dir, String key) {
		String dbg = "enter";
		try {
			int css_id = core_tbl.Select_id_by_key(key); 
			dbg += ";css_id";
			if (css_id == Xowd_css_core_tbl.Id_null) return false;	// unknown key; return false (not found)
			dbg += ";select_by_owner";
			Xowd_css_file_itm[] file_list = file_tbl.Select_by_owner(css_id);
			dbg += ";file_list:" + file_list.length;
			// Io_mgr.Instance.DeleteDirDeep(css_dir); // NOTE: do not delete existing files; just overwrite;
			int len = file_list.length;
			if (len == 0) return false;	// no css files in db
			for (int i = 0; i < len; ++i) {
				Xowd_css_file_itm file = file_list[i];
				dbg += ";file_url:" + file.Path();
				Io_url file_url = Io_url_.new_fil_(css_dir.Gen_sub_path_for_os(file.Path()));
				if (file.Data() == null) continue;	// NOTE: sqlite will return 0 length fields as NULL; if no data, just ignore, else error below
				Io_mgr.Instance.SaveFilBry(file_url, file.Data());
				dbg += ";file_data:" + file.Data().length;
			}
			return true;
		} catch (Exception e) {throw Err_.new_exc(e, "css", "Xowd_css_core_mgr.Get failed", "dbg", dbg, "err", Err_.Message_gplx_log(e));}
	}
	public static final String Key_default = "xowa.default", Key_mobile = "xowa.mobile";
}
