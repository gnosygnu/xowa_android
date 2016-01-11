package gplx.xowa.files.fsdb.fs_roots; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*; import gplx.xowa.files.fsdb.*;
import gplx.core.primitives.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*; import gplx.gfui.*;
import gplx.fsdb.meta.*; import gplx.xowa.files.imgs.*;
class Fs_root_dir {
	private Gfo_usr_dlg usr_dlg; private Xof_img_wkr_query_img_size img_size_wkr;
	private Io_url url; private boolean recurse = true;
	private Orig_fil_mgr cache = new Orig_fil_mgr(), fs_fil_mgr;
	private Db_conn conn; private Db_cfg_tbl cfg_tbl; private Orig_fil_tbl fil_tbl;
	private int fil_id_next = 0;
	public void Init(Io_url url, Orig_fil_tbl fil_tbl, Gfo_usr_dlg usr_dlg, Xof_img_wkr_query_img_size img_size_wkr) {
		this.url = url;
		this.fil_tbl = fil_tbl; this.usr_dlg = usr_dlg; this.img_size_wkr = img_size_wkr;
	}
	public Orig_fil_itm Get_by_ttl(byte[] lnki_ttl) {
		Orig_fil_itm rv = (Orig_fil_itm)cache.Get_by_ttl(lnki_ttl);
		if (rv == null) {
			rv = Get_from_db(lnki_ttl);
			if (rv == null) {
				rv = Get_from_fs(lnki_ttl);
				if (rv == null) return Orig_fil_itm.Null;
			}
			cache.Add(rv);
		}
		return rv;
	}
	private Orig_fil_itm Get_from_db(byte[] lnki_ttl) {
		if (conn == null) conn = Init_db_fil_mgr();
		Orig_fil_itm rv = fil_tbl.Select_itm(lnki_ttl);
		if (rv == null) return Orig_fil_itm.Null;		// not in db
		return rv;
	}
	private Orig_fil_itm Get_from_fs(byte[] lnki_ttl) {
		if (fs_fil_mgr == null) fs_fil_mgr = Init_fs_fil_mgr();
		Orig_fil_itm rv = fs_fil_mgr.Get_by_ttl(lnki_ttl);
		if (rv == null) return Orig_fil_itm.Null;		// not in fs
		SizeAdp img_size = SizeAdp_.Zero;
		if (Xof_ext_.Id_is_image(rv.Fil_ext_id()))
			img_size = img_size_wkr.Exec(rv.Fil_url());
		rv.Init_by_size(++fil_id_next, img_size.Width(), img_size.Height());
		cfg_tbl.Update_int(Cfg_grp_root_dir, Cfg_key_fil_id_next, fil_id_next);
		fil_tbl.Insert(rv);
		return rv;
	}
	private Orig_fil_mgr Init_fs_fil_mgr() {	// NOTE: need to read entire dir, b/c ttl may be "A.png", but won't know which subdir
		Orig_fil_mgr rv = new Orig_fil_mgr();
		Io_url[] fils = Io_mgr.Instance.QueryDir_args(url).Recur_(recurse).ExecAsUrlAry();
		int fils_len = fils.length;
		for (int i = 0; i < fils_len; i++) {
			Io_url fil = fils[i];
			byte[] fil_name_bry = Xto_fil_bry(fil);
			Orig_fil_itm fil_itm = rv.Get_by_ttl(fil_name_bry);
			if (fil_itm != Orig_fil_itm.Null) {
				usr_dlg.Warn_many("", "", "file already exists: cur=~{0} new=~{1}", fil_itm.Fil_url().Raw(), fil.Raw());
				continue;
			}
			Xof_ext ext = Xof_ext_.new_by_ttl_(fil_name_bry);
			fil_itm = new Orig_fil_itm().Init_by_make(fil, fil_name_bry, ext.Id());
			rv.Add(fil_itm);
		}
		return rv;
	}
	private Db_conn Init_db_fil_mgr() {
		Io_url db_url = url.GenSubFil("^orig_regy.sqlite3");
		boolean created = false; boolean schema_is_1 = Bool_.Y;
		Db_conn conn = Db_conn_bldr.Instance.Get(db_url);
		if (conn == null) {
			conn = Db_conn_bldr.Instance.New(db_url);
			created = true;
		}
		cfg_tbl = new Db_cfg_tbl(conn, schema_is_1 ? "fsdb_cfg" : "xowa_cfg");
		fil_tbl.Conn_(conn, created, schema_is_1);
		if (created) {
			cfg_tbl.Create_tbl();
			cfg_tbl.Insert_int(Cfg_grp_root_dir, Cfg_key_fil_id_next, fil_id_next);
		}
		else {
			fil_id_next = cfg_tbl.Select_int(Cfg_grp_root_dir, Cfg_key_fil_id_next);
		}
		return conn;
	}
	public void Rls() {
		cfg_tbl.Rls();
		fil_tbl.Rls();
	}
	private static final String Cfg_grp_root_dir = "xowa.root_dir", Cfg_key_fil_id_next = "fil_id_next";
	public static byte[] Xto_fil_bry(Io_url url) {
		byte[] rv = Bry_.new_u8(url.NameAndExt());
		rv = Bry_.Replace(rv, Byte_ascii.Space, Byte_ascii.Underline);
		rv = Bry_.Ucase__1st(rv);
		return rv;
	}
}
