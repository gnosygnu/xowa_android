package gplx.fsdb; import gplx.*;
import gplx.dbs.*; import gplx.xowa.files.origs.*;
public interface Fsdb_db_mgr {
	boolean				File__schema_is_1();
	boolean				File__solo_file();
	String				File__cfg_tbl_name();
	Xof_orig_tbl[]		File__orig_tbl_ary();
	Fsdb_db_file		File__mnt_file();
	Fsdb_db_file		File__abc_file__at(int mnt_id);
	Fsdb_db_file		File__atr_file__at(int mnt_id);
	Fsdb_db_file		File__bin_file__at(int mnt_id, int bin_id, String file_name);
	Fsdb_db_file		File__bin_file__new(int mnt_id, String file_name);
}
