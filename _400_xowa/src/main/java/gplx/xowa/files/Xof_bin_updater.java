package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.ios.*;
import gplx.fsdb.data.*; import gplx.fsdb.meta.*; import gplx.xowa.files.fsdb.*;
public class Xof_bin_updater {
	private final Fsd_img_itm tmp_img_itm = new Fsd_img_itm(); private final Fsd_thm_itm tmp_thm_itm = Fsd_thm_itm.new_(); private final Fsd_fil_itm tmp_fil_itm = new Fsd_fil_itm();		
	public int Save_bin(Fsm_mnt_itm mnt, Fsm_atr_fil atr_fil, Fsm_bin_fil bin_fil, Xof_fsdb_itm fsdb, Io_stream_rdr rdr, long rdr_len) {
		int db_uid = -1;
		int orig_ext_id = fsdb.Orig_ext().Id();
		if (fsdb.File_is_orig()) {
			if (fsdb.Orig_ext().Id_is_image()) {	// does not add .pdf and .djvu b/c latter do not have w,h for fsdb_img
				mnt.Insert_img(tmp_img_itm, atr_fil, bin_fil, fsdb.Orig_repo_name(), fsdb.Orig_ttl(), orig_ext_id, fsdb.Orig_w(), fsdb.Orig_h(), rdr_len, rdr);
				db_uid = tmp_img_itm.Fil_id();
			}
			else {
				mnt.Insert_fil(tmp_fil_itm, atr_fil, bin_fil, fsdb.Orig_repo_name(), fsdb.Orig_ttl(), orig_ext_id, rdr_len, rdr);
				db_uid = tmp_fil_itm.Fil_id();
			}
		}
		else {
			mnt.Insert_thm(tmp_thm_itm, atr_fil, bin_fil, fsdb.Orig_repo_name(), fsdb.Orig_ttl(), orig_ext_id, fsdb.Html_w(), fsdb.Html_h(), fsdb.Lnki_time(), fsdb.Lnki_page(), rdr_len, rdr);
			db_uid = tmp_thm_itm.Thm_id();
		}
		mnt.Cfg_mgr().Next_id_commit();
		return db_uid;
	}
}
