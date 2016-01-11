package gplx.xowa.guis.urls; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.xowa.files.*; import gplx.xowa.files.repos.*; import gplx.xowa.files.origs.*;
import gplx.xowa.parsers.lnkis.*;
public class Xof_orig_file_downloader {
	public static Xof_fsdb_itm Make_fsdb(Xowe_wiki wiki, byte[] lnki_ttl, Xof_img_size img_size, Xof_url_bldr url_bldr) {
		Xof_fsdb_itm fsdb = new Xof_fsdb_itm();
		lnki_ttl = Xoa_ttl.Replace_spaces(gplx.langs.htmls.encoders.Gfo_url_encoder_.Http_url.Decode(lnki_ttl));
		fsdb.Init_at_lnki(Xof_exec_tid.Tid_viewer_app, wiki.Domain_itm().Abrv_xo(), lnki_ttl, Xop_lnki_type.Id_none, Xop_lnki_tkn.Upright_null, Xof_img_size.Size__neg1, Xof_img_size.Size__neg1, Xof_lnki_time.Null, Xof_lnki_page.Null, Xof_patch_upright_tid_.Tid_all);
		fsdb.Init_at_hdoc(Int_.Max_value, Xof_html_elem.Tid_img);// NOTE: set elem_id to "impossible" number, otherwise it will auto-update an image on the page with a super-large size; [[File:Alfred Sisley 062.jpg]]
		Xof_orig_itm orig = wiki.File__orig_mgr().Find_by_ttl_or_null(lnki_ttl); if (orig == Xof_orig_itm.Null) return null;	// orig not found; need orig in order to get repo
		Xof_repo_itm repo = wiki.File__repo_mgr().Get_trg_by_id_or_null(orig.Repo(), lnki_ttl, Bry_.Empty); if (repo == null) return null; // repo not found
		fsdb.Init_at_orig(orig.Repo(), repo.Wiki_domain(), orig.Ttl(), orig.Ext(), orig.W(), orig.H(), orig.Redirect());
		fsdb.Init_at_html(Xof_exec_tid.Tid_viewer_app, img_size, repo, url_bldr);
		fsdb.File_is_orig_(true);
		return fsdb;
	}
}
