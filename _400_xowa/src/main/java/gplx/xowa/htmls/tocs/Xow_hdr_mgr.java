package gplx.xowa.htmls.tocs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.brys.*; import gplx.core.primitives.*; import gplx.langs.htmls.encoders.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.amps.*; import gplx.xowa.parsers.hdrs.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.lnkis.*;
public class Xow_hdr_mgr {
	private final Xoae_page page;
	private final Hash_adp hdrs_hash = Hash_adp_.new_(); private final Bry_bfr hdrs_bfr = Bry_bfr.reset_(255); private final Bry_obj_ref hdrs_ref = Bry_obj_ref.New_empty();
	private Xop_hdr_tkn[] hdrs_ary = new Xop_hdr_tkn[0]; private int hdrs_max, hdrs_len;
	public Xow_hdr_mgr(Xoae_page page) {this.page = page;}
	public boolean Toc_enabled() {
		return	!toc_hide				// check for __NOTOC__
			&&	hdrs_len != 0			// never show TOC if 0 headers, even when __FORCETOC__
			&&	(	hdrs_len > Toc_min 	// show TOC automatically if 4 or more headers
				||	toc_manual			// or when __TOC__ specified (EX: 2 headers)
				||	toc_force			// or when __FORCETOC__ specified; presumably to (a) show TOC when < 4 headers and (b) let TOC show at default position; __TOC__ would force TOC to show at __TOC__; __FORCETOC__ can be placed at bottom of page
				)
			;
	}
	public boolean Toc_manual()	{return toc_manual;}
	public void Toc_manual_()	{toc_manual = true;} private boolean toc_manual;	// __TOC__
	public void Toc_force_()	{toc_force = true;} private boolean toc_force;		// __FORCETOC__
	public void Toc_hide_()		{toc_hide = true;} private boolean toc_hide;		// __NOTOC__
	public int Len() {return hdrs_len;}
	public Xop_hdr_tkn Get_at(int i) {return hdrs_ary[i];}
	public void Add(Xop_ctx ctx, Xop_hdr_tkn hdr, byte[] src) {
		int new_len = hdrs_len + 1;
		if (new_len > hdrs_max) {
			hdrs_max = (new_len * 2) + 1;
			hdrs_ary = (Xop_hdr_tkn[])Array_.Resize(hdrs_ary, hdrs_max);
		}
		Reg(ctx, hdr, src);
		hdrs_ary[hdrs_len] = hdr;
		hdrs_len = new_len;
	}
	public void Clear() {
		hdrs_len = 0;
		if (hdrs_max > 32) {
			hdrs_ary = new Xop_hdr_tkn[32];
			hdrs_max = 32;
		}
		hdrs_hash.Clear();
		toc_manual = toc_force = toc_hide = false;
	}
	private void Reg(Xop_ctx ctx, Xop_hdr_tkn hdr, byte[] src) {
		if (hdrs_len == 0) hdr.Hdr_html_first_y_();
		Bry_bfr_mkr bfr_mkr = Xoa_app_.Utl__bfr_mkr();
		Bry_bfr raw_bfr = bfr_mkr.Get_b128(), enc_bfr = bfr_mkr.Get_b128();
		Id_bld_recurse(raw_bfr, src, hdr);
		Gfo_url_encoder encoder = gplx.langs.htmls.encoders.Gfo_url_encoder_.Id;
		encoder.Encode(enc_bfr, raw_bfr.Bfr(), 0, raw_bfr.Len());
		byte[] hdrs_id = enc_bfr.To_bry();
		Object o = hdrs_hash.Get_by(hdrs_ref.Val_(hdrs_id));
		if (o != null) {
			Xop_hdr_tkn hdr_0 = (Xop_hdr_tkn)o;
			enc_bfr.Add_byte(Byte_ascii.Underline).Add_int_variable(hdr_0.Hdr_html_dupe_idx_next());
			hdrs_id = enc_bfr.To_bry_and_clear();
		}
		else {
			hdrs_bfr.Clear();
			hdrs_hash.Add(Bry_obj_ref.New(hdrs_id), hdr);
		}
		hdr.Hdr_html_id_(hdrs_id);
		hdr.Hdr_toc_text_(gplx.xowa.htmls.tocs.Xow_toc_mgr.Toc_text(ctx, page, src, hdr));
		raw_bfr.Mkr_rls(); enc_bfr.Mkr_rls();
	}
	private void Id_bld_recurse(Bry_bfr raw_bfr, byte[] src, Xop_tkn_itm tkn) {
		boolean txt_seen = false; int ws_pending = 0;
		int subs_len = tkn.Subs_len();
		for (int i = 0; i < subs_len; i++) {
			Xop_tkn_itm sub = tkn.Subs_get(i);
			byte sub_tid = sub.Tkn_tid();
			if (	sub_tid != Xop_tkn_itm_.Tid_space
				&&	sub_tid != Xop_tkn_itm_.Tid_para) {
				if (ws_pending > 0) {
					raw_bfr.Add_byte_repeat(Byte_ascii.Underline, ws_pending);
					ws_pending = 0;
				}
				txt_seen = true;
			}
			switch (sub.Tkn_tid()) {
				case Xop_tkn_itm_.Tid_space:
					if (txt_seen) ws_pending = sub.Src_end() - sub.Src_bgn();
					break;
				case Xop_tkn_itm_.Tid_apos: break; // noop; ignore apos in id
				case Xop_tkn_itm_.Tid_txt:
					raw_bfr.Add_mid(src, sub.Src_bgn(), sub.Src_end());
					break;
				case Xop_tkn_itm_.Tid_xnde:
					Xop_xnde_tkn xnde = (Xop_xnde_tkn)sub;
					Id_bld_recurse(raw_bfr, src, xnde);
					break;
				case Xop_tkn_itm_.Tid_lnki:
					Xop_lnki_tkn lnki = (Xop_lnki_tkn)sub;
					if (	lnki.Ns_id() == Xow_ns_.Tid__category	// Category text should not print; DATE:2013-12-09
						&&	!lnki.Ttl().ForceLiteralLink()) {}		// unless it is literal link; EX: [[:Category:A]]; PAGE:s.w:Wikipedia:Requests_for_deletion/Log_1 DATE:2015-11-29
					else {
						if (lnki.Caption_exists())
							Id_bld_recurse(raw_bfr, src, lnki.Caption_val_tkn());
						else
							raw_bfr.Add(lnki.Ttl_ary());
					}
					break;
				case Xop_tkn_itm_.Tid_html_ncr:
					Xop_amp_tkn_num html_ncr = (Xop_amp_tkn_num)sub;
					raw_bfr.Add(html_ncr.Str_as_bry());
					break;
				default:						
					raw_bfr.Add_mid(src, sub.Src_bgn(), sub.Src_end());
					break;
			}
		}
	}
	public static final int Toc_min = 3;
}
