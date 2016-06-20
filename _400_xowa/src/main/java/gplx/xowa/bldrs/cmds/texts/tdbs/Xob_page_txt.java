package gplx.xowa.bldrs.cmds.texts.tdbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.core.ios.*; import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.wtrs.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.parsers.utils.*;
import gplx.xowa.wikis.tdbs.*; import gplx.xowa.wikis.tdbs.xdats.*; import gplx.xowa.wikis.tdbs.stats.*;
public class Xob_page_txt extends Xob_itm_dump_base implements Xob_page_wkr, Gfo_invk {
	public Xob_page_txt(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Page_wkr__key() {return Xob_cmd_keys.Key_tdb_make_page;}
	public void Page_wkr__bgn() {
		redirect_mgr = wiki.Redirect_mgr(); page_storage_type = wiki.Appe().Setup_mgr().Dump_mgr().Data_storage_format();
		fsys_mgr = wiki.Tdb_fsys_mgr();			
		make_dir = fsys_mgr.Ns_dir();
		if (Io_mgr.Instance.QueryDir_args(make_dir).DirOnly_().ExecAsUrlAry().length > 0) throw bldr.Usr_dlg().Fail_many("xowa.bldr.itm", "dir_empty", "dir_must_be_empty: ~{0}", make_dir.Raw());
		this.Init_dump(Xob_cmd_keys.Key_tdb_make_page, make_dir);
		this.data_rpt_typ = stat_mgr.GetOrNew(Xotdb_dir_info_.Tid_page);
		ttl_wtr_mgr = new Xob_tmp_wtr_mgr(new Xob_tmp_wtr_wkr__ttl(temp_dir, dump_fil_len));			
	}	private Xotdb_fsys_mgr fsys_mgr; Xop_redirect_mgr redirect_mgr;
	int page_file_len = 512 * Io_mgr.Len_kb, title_file_len = 64 * Io_mgr.Len_kb; Xob_tmp_wtr_mgr ttl_wtr_mgr;
	Xob_xdat_file_wtr[] page_wtr_regy = new Xob_xdat_file_wtr[Ns_ordinal_max]; static final int Ns_ordinal_max = Xow_ns_mgr_.Ordinal_max;	// ASSUME: no more than 128 ns in a wiki
	Xob_stat_type data_rpt_typ; Xob_stat_mgr stat_mgr = new Xob_stat_mgr(); byte page_storage_type;		
	public void Page_wkr__run(Xowd_page_itm page) {
		int id = page.Id(); byte[] ttl_wo_ns = page.Ttl_page_db(), text = page.Text(); int ttl_len = ttl_wo_ns.length, text_len = text.length; Xow_ns ns = page.Ns();
		boolean redirect = redirect_mgr.Is_redirect(text, text_len);
		page.Redirected_(redirect);

		// page: EX: \t123\t2012-06-09\ttitle\ttext\n; NOTE: 512k * ~20 ns = 10 MB max memory; no need for intermediary flushing
		Xob_xdat_file_wtr page_wtr = Page_wtr_get(ns);
		if (page_wtr.FlushNeeded(Xotdb_page_itm_.Txt_page_len__fixed + ttl_len + text_len)) page_wtr.Flush(bldr.Usr_dlg());
		Xotdb_page_itm_.Txt_page_save(page_wtr.Bfr(), id, page.Modified_on(), ttl_wo_ns, text, false);
		page_wtr.Add_idx(Byte_ascii.Nl);
		
		// idx: EX: 00100|aB64|Ttl;
		Xob_tmp_wtr ttl_wtr = ttl_wtr_mgr.Get_or_new(ns);
		int file_idx = page_wtr.Fil_idx(), row_idx = page_wtr.Idx_pos() - 1;	// 1=Last_idx_offset
		page.Text_db_id_(file_idx).Tdb_row_idx_(row_idx);
		if (ttl_wtr.FlushNeeded(Xotdb_page_itm_.Txt_ttl_len__fixed + ttl_len)) ttl_wtr.Flush(bldr.Usr_dlg());
		Xotdb_page_itm_.Txt_ttl_save(ttl_wtr.Bfr(), id, file_idx, row_idx, redirect, text_len, ttl_wo_ns);
	}
	public void Page_wkr__run_cleanup() {}
	public void Page_wkr__end() {
		Flush_page(page_wtr_regy);
		ttl_wtr_mgr.Flush_all(bldr.Usr_dlg());
		Xobdc_merger.Ns(bldr.Usr_dlg(), ttl_wtr_mgr.Regy(), Xotdb_dir_info_.Name_title, temp_dir, make_dir, sort_mem_len, Io_line_rdr_key_gen_.last_pipe, new Io_sort_cmd_ns(bldr.Usr_dlg()));
		ttl_wtr_mgr.Rls_all();
		if (delete_temp) Io_mgr.Instance.DeleteDirDeep(temp_dir);
	}
	Xob_xdat_file_wtr Page_wtr_get(Xow_ns ns) {
		Xob_xdat_file_wtr rv = page_wtr_regy[ns.Ord()];
		if (rv == null) {
			rv = Xob_xdat_file_wtr.new_by_tid_(page_file_len, fsys_mgr.Ns_dir().GenSubDir_nest(ns.Num_str()), Xotdb_dir_info_.Tid_page, page_storage_type).Ns_ord_idx_(ns.Ord());
			page_wtr_regy[ns.Ord()] = rv;
		}
		return rv;
	}
	private void Flush_page(Xob_xdat_file_wtr[] regy) {
		for (int i = 0; i < Ns_ordinal_max; i++) {
			Xob_xdat_file_wtr wtr = regy[i];
			if (wtr != null) {
				Xow_ns ns_itm = wiki.Ns_mgr().Ords_get_at(wtr.Ns_ord_idx());
				Xob_stat_itm datRptItm = data_rpt_typ.GetOrNew(ns_itm.Name_db_str());
				datRptItm.Tally(wtr.Fil_len(), wtr.Fil_idx());
				wtr.Flush(bldr.Usr_dlg());
				wtr.Rls();
				regy[i] = null;
			}
		}
	}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_page_file_len_))		page_file_len = gplx.core.ios.Io_size_.Load_int_(m);
		else if (ctx.Match(k, Invk_title_file_len_))	title_file_len = gplx.core.ios.Io_size_.Load_int_(m);
		else	return super.Invk(ctx, ikey, k, m);
		return this;
	}	private static final String Invk_page_file_len_ = "page_file_len_", Invk_title_file_len_ = "title_file_len_";
	static final String GRP_KEY = "xowa.bldr.make_page";
}
