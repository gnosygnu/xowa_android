package gplx.xowa.parsers.lnkis.redlinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
import gplx.core.primitives.*;
import gplx.xowa.wikis.data.tbls.*;
import gplx.xowa.langs.vnts.*; import gplx.xowa.guis.views.*; import gplx.xowa.wikis.pages.*; import gplx.xowa.htmls.core.makes.imgs.*;
import gplx.xowa.parsers.tmpls.*;
public class Xog_redlink_mgr implements GfoInvkAble {
	private Xog_win_itm win; private Xog_html_itm html_itm; private Xowe_wiki wiki; private Xoae_page page;
	private Xopg_redlink_lnki_list redlink_lnki_list; private List_adp lnki_list; private boolean log_enabled; private Gfo_usr_dlg usr_dlg; private int thread_id;		
	public Xog_redlink_mgr(Xog_win_itm win, Xoae_page page, boolean log_enabled) {
		this.win = win; this.page = page; this.wiki = page.Wikie();
		this.html_itm = page.Tab_data().Tab().Html_itm();	// NOTE: caching locally b/c page.Tab() is sometimes null
		this.redlink_lnki_list = page.Redlink_lnki_list();
		this.lnki_list = redlink_lnki_list.Lnki_list();
		this.thread_id = redlink_lnki_list.Thread_id();
		this.log_enabled = log_enabled; this.usr_dlg = log_enabled ? Gfo_usr_dlg_.Instance : Gfo_usr_dlg_.Noop;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_run)) Redlink();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	public static final String Invk_run = "run";
	public void Redlink() {
		synchronized (this) {	// NOTE: attempt to eliminate random IndexBounds errors; DATE:2014-09-02
			if (redlink_lnki_list.Disabled()) return;
			List_adp work_list = List_adp_.new_();
			Ordered_hash page_hash = Ordered_hash_.New_bry();
			page_hash.Clear(); // NOTE: do not clear in Page_bgn, else will fail b/c of threading; EX: Open Page -> Preview -> Save; DATE:2013-11-17
			work_list.Clear();
			int len = lnki_list.Count();
			if (log_enabled) usr_dlg.Log_many("", "", "redlink.redlink_bgn: page=~{0} total_links=~{1}", String_.new_u8(page.Ttl().Raw()), len);
			for (int i = 0; i < len; i++) {	// make a copy of list else thread issues
				if (win.Usr_dlg().Canceled()) return;
				if (redlink_lnki_list.Thread_id() != thread_id) return;
				work_list.Add(lnki_list.Get_at(i));
			}
			for (int i = 0; i < len; i++) {
				if (win.Usr_dlg().Canceled()) return;
				if (redlink_lnki_list.Thread_id() != thread_id) return;
				Xop_lnki_tkn lnki = (Xop_lnki_tkn)work_list.Get_at(i);
				Xoa_ttl ttl = lnki.Ttl();
				Xowd_page_itm db_page = new Xowd_page_itm().Ttl_(ttl);
				byte[] full_txt = ttl.Full_db();
				if (!page_hash.Has(full_txt))
					page_hash.Add(full_txt, db_page);
			}
			int page_len = page_hash.Count();
			for (int i = 0; i < page_len; i += Batch_size) {
				if (win.Usr_dlg().Canceled()) return;
				if (redlink_lnki_list.Thread_id() != thread_id) return;
				int end = i + Batch_size;
				if (end > page_len) end = page_len;
				wiki.Db_mgr().Load_mgr().Load_by_ttls(win.Usr_dlg(), page_hash, Bool_.Y, i, end);
			}
			int redlink_count = 0;
			Bry_bfr bfr = null;
			boolean variants_enabled = wiki.Lang().Vnt_mgr().Enabled();
			Xol_vnt_mgr vnt_mgr = wiki.Lang().Vnt_mgr();
			Xopg_redlink_idx_list redlink_mgr = page.Hdump_data().Redlink_mgr();
			for (int j = 0; j < len; j++) {
				Xop_lnki_tkn lnki = (Xop_lnki_tkn)work_list.Get_at(j);
				byte[] full_db = lnki.Ttl().Full_db();
				Xowd_page_itm db_page = (Xowd_page_itm)page_hash.Get_by(full_db);
				if (db_page == null) continue;	// pages shouldn't be null, but just in case
				if (!db_page.Exists()) {
					String lnki_id = Xopg_redlink_lnki_list.Lnki_id_prefix + Int_.To_str(lnki.Html_uid());
					if (variants_enabled) {
						Xowd_page_itm vnt_page = vnt_mgr.Convert_mgr().Convert_ttl(wiki, lnki.Ttl());
						if (vnt_page != null) {
							Xoa_ttl vnt_ttl = Xoa_ttl.parse(wiki, lnki.Ttl().Ns().Id(), vnt_page.Ttl_page_db());
							html_itm.Html_atr_set(lnki_id, "href", "/wiki/" + String_.new_u8(vnt_ttl.Full_url()));
							if (!String_.Eq(vnt_mgr.Html__lnki_style(), ""))
								html_itm.Html_atr_set(lnki_id, "style", vnt_mgr.Html__lnki_style());
							continue;
						}
					}
					if (log_enabled) {
						if (bfr == null) bfr = Bry_bfr.new_();
						bfr.Add_int_variable(lnki.Html_uid()).Add_byte_pipe().Add(Xop_tkn_.Lnki_bgn).Add(full_db).Add(Xop_tkn_.Lnki_end).Add_byte(Byte_ascii.Semic).Add_byte_space();
					}
					if (win.Usr_dlg().Canceled()) return;
					if (redlink_lnki_list.Thread_id() != thread_id) return;
					int uid = lnki.Html_uid();
					gplx.xowa.files.gui.Js_img_mgr.Update_link_missing(html_itm, Xopg_redlink_lnki_list.Lnki_id_prefix + Int_.To_str(uid));
					redlink_mgr.Add(uid);
					++redlink_count;
				}
			}
			if (log_enabled)
				usr_dlg.Log_many("", "", "redlink.redlink_end: redlinks_run=~{0} links=~{1}", redlink_count, bfr == null ? String_.Empty : bfr.To_str_and_clear());
		}
	}
	public static final Xog_redlink_mgr Null = new Xog_redlink_mgr(); Xog_redlink_mgr() {}
	private static final int Batch_size = 32;
}
class Xog_redlink_wkr {
	public static void Redlink(Xog_html_itm html_itm, Int_list list) {
		int len = list.Len();
		for (int i = 0; i < len; ++i) {
			int uid = list.Get_at(i);
			Redlink(html_itm, uid);
		}
	}
	public static void Redlink(Xog_html_itm html_itm, int uid) {
		gplx.xowa.files.gui.Js_img_mgr.Update_link_missing(html_itm, Xopg_redlink_lnki_list.Lnki_id_prefix + Int_.To_str(uid));
	}
}
