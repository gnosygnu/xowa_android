package gplx.xowa.htmls.tocs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.apos.*; import gplx.xowa.parsers.amps.*; import gplx.xowa.parsers.hdrs.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.lnkis.*;
public class Xow_toc_mgr implements gplx.core.brys.Bfr_arg {
	private static final int Toc_levels = 32; // assume 6 max levels * 5 max heading (9999.); add 2 for good measure
	private Xoae_page page; private Xop_toc_itm[] path_ary; private Bry_bfr path_bfr = Bry_bfr.reset_(Toc_levels);
	public Xow_toc_mgr() {
		path_ary = new Xop_toc_itm[Toc_levels];
		for (int i = 0; i < Toc_levels; i++)
			path_ary[i] = new Xop_toc_itm();
	}
	public void Clear() {
		for (int i = 0; i < Toc_levels; i++)
			path_ary[i].Lvl_idx_(0);
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int path_idx = 0, toc_idx = 0, lvl_idx = 1, eq_prv = 0;
		path_bfr.Clear();
		Xow_hdr_mgr hdr_mgr = page.Hdr_mgr();
		int hdrs_len = hdr_mgr.Len();
		for (int i = 0; i < hdrs_len; i++) {
			Xop_hdr_tkn hdr = hdr_mgr.Get_at(i);
			int eq_cur = hdr.Hdr_level();
			switch (CompareAble_.Compare(eq_cur, eq_prv)) {
				case CompareAble_.More:	// always increase slot
					if (eq_prv != 0) {	// add to path_bfr, unless 1st
						path_bfr.Add_int_variable(lvl_idx).Add_byte(Byte_ascii.Dot);		// build path; EX: 1.2.3
					}
					if (path_idx >= Toc_levels) return;										// HACK:invalid <div><h2>a</h2></div> sequence causes invalid toc level generation; for now, exit; WHEN:removing dangling <div> logic; DATE:2014-06-08
					path_ary[path_idx].Lvl_idx_(lvl_idx).Eq_len_((byte)eq_cur);				// store cur value in path ary; EX: path_ary[1] = 2
					bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_list_bgn);	// <ul>
					++path_idx;
					lvl_idx = 1;
					eq_prv = eq_cur;
					break;
				case CompareAble_.Less:	// decrease slot, only if <= last slot
					int path_new = 0;
					for (int j = path_idx - 1; j > -1; j--) {
						if 		(eq_cur == path_ary[j].Eq_len()) {
							path_new = j;
							break;
						}
						else if (eq_cur > path_ary[j].Eq_len()) {
							path_new = j + 1;
							break;
						}
					}
					if (path_new == path_idx) {
						bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_item_end);	// add "</li>"
						++lvl_idx;
					}
					else {
						for (int j = path_idx; j > path_new + 1; j--) {
							bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_item_end);	// </li>
							--path_idx;
							if (path_idx == -1) path_idx = 0;
							bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_list_end);	// </ul>
							lvl_idx = path_ary[path_idx].Lvl_idx();
							path_bfr.Del_by(Int_.DigitCount(lvl_idx) + 1);	// + 1 for dot
						}
						++lvl_idx;
						bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_item_end);	// <li>
					}
					eq_prv = eq_cur;
					break;
				case CompareAble_.Same:	// keep slot the same
					bfr.Add_byte_repeat(Byte_ascii.Space, path_idx * 2).Add(Bry_item_end);	// add "</li>"
					++lvl_idx;
					break;
			}
			byte[] text = hdr.Hdr_toc_text();
			int lvl_idx_digits = Int_.DigitCount(lvl_idx);
			path_bfr.Add_int_fixed(lvl_idx, lvl_idx_digits);			// add current level
			bfr.Add_byte_repeat(Byte_ascii.Space, (path_idx - 1) * 2);	// indent
			bfmtr_line.Bld_bfr_many(bfr, path_idx, ++toc_idx, hdr.Hdr_html_id(), path_bfr, text);
			path_bfr.Del_by(lvl_idx_digits);
		}
		for (int i = path_idx - 1; i > -1; i--) {
			bfr.Add_byte_repeat(Byte_ascii.Space, (i + 1) * 2);
			bfr.Add(Bry_item_end);
			bfr.Add_byte_repeat(Byte_ascii.Space, i * 2);
			bfr.Add(Bry_list_end);
		}
	}
	public static byte[] Toc_text(Xop_ctx ctx, Xoae_page page, byte[] src, Xop_tkn_itm hdr) {
		try {
			Xowe_wiki wiki = page.Wikie();
			Bry_bfr bfr = Xoa_app_.Utl__bfr_mkr().Get_b128();
			Xoh_wtr_ctx hctx = Xoh_wtr_ctx.Basic;
			Xoh_html_wtr html_wtr = wiki.Html_mgr().Html_wtr(); html_wtr.Init_by_page(ctx, hctx, src, page);
			Toc_text_recurse(ctx, bfr, src, html_wtr, hctx, hdr, 0);
			bfr.Mkr_rls();
			return bfr.To_bry_and_clear();
		} catch (Exception e) {
			Gfo_usr_dlg_.Instance.Warn_many("", "", "failed to write toc: url=~{0} err=~{1}", page.Url().To_str(), Err_.Message_gplx_full(e));
			return Bry_.Empty;
		}
	}
	private static void Toc_text_recurse(Xop_ctx ctx, Bry_bfr bfr, byte[] src, Xoh_html_wtr html_wtr, Xoh_wtr_ctx html_wtr_opts, Xop_tkn_itm tkn, int depth) {
		int subs_len = tkn.Subs_len();
		boolean txt_seen = false; int ws_pending = 0;
		for (int i = 0; i < subs_len; i++) {
			Xop_tkn_itm sub = tkn.Subs_get(i);
			byte sub_tid = sub.Tkn_tid();
			if (sub_tid != Xop_tkn_itm_.Tid_space) {
				if (ws_pending > 0) {
					bfr.Add_byte_repeat(Byte_ascii.Space, ws_pending);
					ws_pending = 0;
				}
				txt_seen = true;
			}
			switch (sub.Tkn_tid()) {
				case Xop_tkn_itm_.Tid_space:
					if (txt_seen) ws_pending = sub.Src_end() - sub.Src_bgn();
					break;
				case Xop_tkn_itm_.Tid_lnki:
					Xop_lnki_tkn lnki = (Xop_lnki_tkn)sub;
					if (lnki.Ns_id() == Xow_ns_.Tid__category
						&& !lnki.Ttl().ForceLiteralLink())		{}	// Category text should not print; DATE:2013-12-09
					else {
						if (lnki.Caption_exists())
							Toc_text_recurse(ctx, bfr, src, html_wtr, html_wtr_opts, lnki.Caption_val_tkn(), depth);
						else {
							if (lnki.Ns_id() == Xow_ns_.Tid__file) {}	// do not print lnk_ttl in TOC; if file; tr.w:Dünya_Mirasları; DATE:2014-06-06
							else
								bfr.Add(lnki.Ttl_ary());
						}
					}
					break;
				case Xop_tkn_itm_.Tid_xnde:
					Xop_xnde_tkn xnde = (Xop_xnde_tkn)sub;
					switch (xnde.Tag().Id()) {
						case Xop_xnde_tag_.Tid_br:	// always ignore in TOC text; EX: en.wikipedia.org/wiki/Magnetic_resonance_imaging; ====''T''<span style="display:inline-block; margin-bottom:-0.3em; vertical-align:-0.4em; line-height:1.2em;  font-size:85%; text-align:left;">*<br />2</span>-weighted MRI====
						case Xop_xnde_tag_.Tid_hr:	// assumed, based on above
						case Xop_xnde_tag_.Tid_h1: case Xop_xnde_tag_.Tid_h2: case Xop_xnde_tag_.Tid_h3: case Xop_xnde_tag_.Tid_h4: case Xop_xnde_tag_.Tid_h5: case Xop_xnde_tag_.Tid_h6:
						case Xop_xnde_tag_.Tid_ul: case Xop_xnde_tag_.Tid_ol: case Xop_xnde_tag_.Tid_dd: case Xop_xnde_tag_.Tid_dt: case Xop_xnde_tag_.Tid_li:
						case Xop_xnde_tag_.Tid_table: case Xop_xnde_tag_.Tid_tr: case Xop_xnde_tag_.Tid_td: case Xop_xnde_tag_.Tid_th: case Xop_xnde_tag_.Tid_thead: case Xop_xnde_tag_.Tid_tbody: case Xop_xnde_tag_.Tid_caption:
						case Xop_xnde_tag_.Tid_ref:	// NOTE: don't bother printing references
//							case Xop_xnde_tag_.Tid_pre: case Xop_xnde_tag_.Tid_blockquote:
							break;
						case Xop_xnde_tag_.Tid_b:
						case Xop_xnde_tag_.Tid_i:
							html_wtr.Write_tkn(bfr, ctx, html_wtr_opts, src, tkn, Xoh_html_wtr.Sub_idx_null, sub);
							break;
						case Xop_xnde_tag_.Tid_translate:
							gplx.xowa.xtns.translates.Xop_translate_xnde translate_xnde = (gplx.xowa.xtns.translates.Xop_translate_xnde)xnde.Xnde_xtn();
							html_wtr.Write_tkn(bfr, ctx, html_wtr_opts, translate_xnde.Xtn_root().Data_mid(), tkn, Xoh_html_wtr.Sub_idx_null, translate_xnde.Xtn_root());
							break;
						default:
							if (depth > 0 && (xnde.CloseMode() == Xop_xnde_tkn.CloseMode_pair || xnde.CloseMode() == Xop_xnde_tkn.CloseMode_inline))	// do not render dangling xndes; EX: Casualties_of_the_Iraq_War; ===<small>Iraqi Health Ministry<small>===
								bfr.Add_mid(src, xnde.Tag_open_bgn(), xnde.Tag_open_end());
							Toc_text_recurse(ctx, bfr, src, html_wtr, html_wtr_opts, xnde, depth + 1);
							if (depth > 0 && xnde.CloseMode() == Xop_xnde_tkn.CloseMode_pair)	// do not render (a) dangling xndes or (b) inline (which will have negative bgn)
								bfr.Add_mid(src, xnde.Tag_close_bgn(), xnde.Tag_close_end());
							break;
					}
					break;
				case Xop_tkn_itm_.Tid_txt:
					html_wtr.Write_tkn(bfr, ctx, html_wtr_opts, src, tkn, Xoh_html_wtr.Sub_idx_null, sub);
					break;
				case Xop_tkn_itm_.Tid_apos:				html_wtr.Apos		(ctx, html_wtr_opts, bfr, src, (Xop_apos_tkn)sub); break;
				case Xop_tkn_itm_.Tid_html_ncr:			html_wtr.Html_ncr	(ctx, html_wtr_opts, bfr, src, (Xop_amp_tkn_num)sub); break;
				case Xop_tkn_itm_.Tid_html_ref:			html_wtr.Html_ref	(ctx, html_wtr_opts, bfr, src, (Xop_amp_tkn_txt)sub); break;
				default:
					if (sub.Subs_len() == 0)	// NOTE: never call html_wtr directly unless leaf elem; DATE:2014-06-22
						html_wtr.Write_tkn(bfr, ctx, html_wtr_opts, src, tkn, Xoh_html_wtr.Sub_idx_null, sub);
					else
						Toc_text_recurse(ctx, bfr, src, html_wtr, html_wtr_opts, sub, depth + 1);
					break;
			}
		}
	}
	public void Html(Xoae_page page, Xoh_wtr_ctx hctx, byte[] src, Bry_bfr bfr, boolean write_toc_cls) {
		if (!page.Hdr_mgr().Toc_enabled()) return;	// REF.MW: Parser.php|formatHeadings
		if (hctx.Mode_is_hdump()) return;
		this.page = page;
		byte[] bry_contents = page.Wikie().Msg_mgr().Val_by_id(Xol_msg_itm_.Id_toc);
		bfmtr_main.Bld_bfr_many(bfr, write_toc_cls ? Bry_toc_cls : Bry_.Empty, Bfr_arg_.New_bry(bry_contents), this);
	}
	private static final byte[]
	  Bry_list_bgn = Bry_.new_a7("  <ul>\n")
	, Bry_list_end = Bry_.new_a7("  </ul>\n")
	, Bry_item_end = Bry_.new_a7("  </li>\n")
	, Bry_toc_cls = Bry_.new_a7(" id=\"toc\" class=\"toc\"")
	;
	private Bry_fmtr
	  bfmtr_main = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "<div~{toc}>"
	, "  <div id=\"toctitle\">"
	, "    <h2>~{contents_title}</h2>"
	, "  </div>"
	, "~{toc_list}</div>"
	, ""
	)
	, "toc", "contents_title", "toc_list"
	)
	, bfmtr_line = Bry_fmtr.new_
	( "    <li class=\"toclevel-~{level} tocsection-~{toc_idx}\"><a href=\"#~{anchor}\"><span class=\"tocnumber\">~{heading}</span> <span class=\"toctext\">~{text}</span></a>\n"
	, "level", "toc_idx", "anchor", "heading", "text"
	);
}
class Xop_toc_itm {
	public int Eq_len() {return eq_len;} public Xop_toc_itm Eq_len_(int v) {eq_len = v; return this;} private int eq_len;
	public int Lvl_idx() {return lvl_idx;} public Xop_toc_itm Lvl_idx_(int v) {lvl_idx = v; return this;} private int lvl_idx;
}
