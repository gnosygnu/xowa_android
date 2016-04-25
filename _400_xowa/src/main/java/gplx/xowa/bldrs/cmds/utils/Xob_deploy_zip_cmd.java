package gplx.xowa.bldrs.cmds.utils; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.ios.zips.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.sqls.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.wikis.tdbs.*;
public class Xob_deploy_zip_cmd extends Xob_itm_basic_base implements Xob_cmd {
	public Xob_deploy_zip_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Xob_cmd_keys.Key_deploy_zip;}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_run() {Exec(bldr, Xotdb_dir_info_.Name_page);}
	public void Cmd_end() {}
	public void Cmd_term() {}
	private void Exec(Xob_bldr bldr, String type_name) {
		Log("initing wiki");
		try {wiki.Init_assert();}
		catch (Exception exc) {Log("failed to init wiki: ~{0}", Err_.Message_gplx_full(exc));}
		Log("probing ns_dirs: ~{0}", wiki.Tdb_fsys_mgr().Ns_dir().Raw());
		Io_url[] ns_dirs = Io_mgr.Instance.QueryDir_args(wiki.Tdb_fsys_mgr().Ns_dir()).DirOnly_().ExecAsUrlAry();
		for (Io_url ns_dir : ns_dirs) {
			Log("zipping dir: ~{0}", ns_dir.Raw());
			String ns_num = ns_dir.NameOnly();
			Xow_ns ns_itm = wiki.Ns_mgr().Ids_get_or_null(Int_.parse(ns_num));
			Zip_ns(bldr, ns_dir, type_name, ns_itm.Name_db_str());
		}
	}
	private void Zip_ns(Xob_bldr bldr, Io_url root_dir, String type_name, String ns_name) {
		bldr.Usr_dlg().Prog_one(GRP_KEY, "scan", "scanning dir ~{0}", type_name);
		Io_url[] fils = Io_mgr.Instance.QueryDir_args(root_dir.GenSubDir(type_name)).Recur_().ExecAsUrlAry();
		int fils_len = fils.length;
		String fils_len_str = Int_.To_str_pad_bgn_zero(fils_len, 6);
		for (int i = 0; i < fils_len; i++) {
			Io_url fil = fils[i];
			bldr.Print_prog_msg(i, fils.length, -1, "zipping ~{0} ~{1} ~{2} of ~{3}", type_name, ns_name, Int_.To_str_pad_bgn_zero(i, 6), fils_len_str);
			Io_url trg_fil = Gen_trg(root_dir, fil, type_name);
			if (String_.Eq(fil.NameAndExt(), Xotdb_dir_info_.Name_reg_fil))	// do not zip reg.csv
				Io_mgr.Instance.CopyFil(fil, trg_fil, true);
			else
				zip_mgr.Zip_fil(fil, trg_fil.GenNewExt(Xotdb_dir_info_.Ext_zip));
		}
		if (delete_dirs_page) Io_mgr.Instance.DeleteDirDeep(root_dir.GenSubDir(type_name));
	}
	public boolean Delete_dirs_page() {return delete_dirs_page;} public Xob_deploy_zip_cmd Delete_dirs_page_(boolean v) {delete_dirs_page = v; return this;} private boolean delete_dirs_page = true;
	Io_url Gen_trg(Io_url root_dir, Io_url fil, String type_name) {
		String src_fil = fil.Xto_api();
		int pos = String_.FindBwd(src_fil, type_name);	// SEE:NOTE_1
		pos += String_.Len(type_name);					// get rest of String after type; EX: in "/page/000000" start with "/000000"
		return root_dir.GenSubFil(String_.Concat(type_name, Dir_zip_suffix, String_.Mid(src_fil, pos)));
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.MatchIn(k, Invk_delete_temp_, Invk_delete_dirs_page_))			delete_dirs_page = m.ReadYn("v");
		else	return super.Invk(ctx, ikey, k, m);
		return this;
	}	private static final String Invk_delete_temp_ = "delete_temp_", Invk_delete_dirs_page_ = "delete_dirs_page_";
	private void Log(String fmt, Object... args) {
		bldr.App().Usr_dlg().Log_many(GRP_KEY, "deploy.zip.msg", fmt, args);
	}
	Io_zip_mgr zip_mgr = Io_zip_mgr_base.Instance;
	public static final String Dir_zip_suffix = "_zip";
	static final String GRP_KEY = "xowa.bldr.zip";
}
/*
NOTE_1:generate trg url
find "type_name" to replace with "type_name_zip";
EX:
old: /xowa/ns/000/page/000000/000100/000123.xdat; 
new: /xowa/ns/000/page_zip/000000/000100/000123.xdat;
NOTE: FindBwd (and not Replace) b/c root_dir could be something like /page/xowa
*/