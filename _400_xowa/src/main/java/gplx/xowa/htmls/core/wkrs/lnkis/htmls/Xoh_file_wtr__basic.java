package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.core.brys.*; import gplx.core.bits.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.encoders.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.core.makes.imgs.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.msgs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.files.*; 	
import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*; import gplx.xowa.parsers.tmpls.*;
public class Xoh_file_wtr__basic {		
	private final Xowe_wiki wiki; private final Xow_html_mgr html_mgr; private final Xoh_html_wtr html_wtr; private final Bry_bfr_mkr bfr_mkr; private final Bry_bfr scratch_bfr = Bry_bfr.reset_(Io_mgr.Len_kb);
	private final Xoh_lnki_text_fmtr media_alt_fmtr, caption_fmtr;
	private final Xop_link_parser tmp_link_parser = new Xop_link_parser(); private Xoa_url tmp_url = Xoa_url.blank(); private final Xoh_lnki_title_fmtr anchor_title_wkr = new Xoh_lnki_title_fmtr();
	private Xoh_file_html_fmtr__base html_fmtr = Xoh_file_html_fmtr__base.Base;
	private Xoae_page page; private boolean cfg_alt_defaults_to_caption;
	public Xoh_file_wtr__basic(Xowe_wiki wiki, Xow_html_mgr html_mgr, Xoh_html_wtr html_wtr) {
		this.wiki = wiki; this.html_mgr = html_mgr; this.html_wtr = html_wtr; this.bfr_mkr = wiki.Utl__bfr_mkr();
		this.media_alt_fmtr = new Xoh_lnki_text_fmtr(bfr_mkr, html_wtr);
		this.caption_fmtr	= new Xoh_lnki_text_fmtr(bfr_mkr, html_wtr);
	}
	public Xoh_file_html_fmtr__base Html_fmtr() {return html_fmtr;}
	public void Init_by_page(Xoh_wtr_ctx hctx, Xoae_page page) {
		this.page = page;
		this.cfg_alt_defaults_to_caption = wiki.Appe().Usere().Wiki().Html_mgr().Imgs_mgr().Alt_defaults_to_caption().Val();
		html_fmtr = hctx.Mode_is_hdump() ? Xoh_file_html_fmtr__hdump.Hdump : Xoh_file_html_fmtr__hdump.Base;
	}
	public void Write_file(Bry_bfr bfr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, Xof_file_itm xfer_itm, byte[] img_alt) {
		int uid = xfer_itm.Html_uid();
		int div_width = xfer_itm.Html_w();
		if (div_width < 1 && wiki.File_mgr().Version_2_y()) // NOTE: html_w is -1 for v2 and missing files; use lnki_w if available; primarily affects audio files with specified width; [[File:A.oga|30px]]; DATE:2014-05-03
			div_width = xfer_itm.Lnki_w();	
		if (div_width < 1)
			div_width = wiki.Html_mgr().Img_thumb_width();
		int lnki_halign = lnki.Align_h();
		if (lnki_halign == Xop_lnki_align_h_.Null)
			lnki_halign = wiki.Lang().Img_thumb_halign_default();	// if halign is not supplied, then default to align for language
		byte[] lnki_halign_bry = Xop_lnki_align_h_.Html_names[lnki_halign];
		byte[] lnki_href = wiki.Appe().Html__href_wtr().Build_to_bry(wiki, lnki.Ttl());
		byte[] img_view_src = xfer_itm.Html_view_url().To_http_file_bry();
		byte[] img_orig_src = xfer_itm.Html_orig_url().To_http_file_bry();
		byte[] lnki_ttl = lnki.Ttl().Page_txt();				
		Xof_ext orig_ext = xfer_itm.Orig_ext();
		boolean lnki_is_thumbable = Xop_lnki_type.Id_is_thumbable(lnki.Lnki_type());
		if (lnki_is_thumbable && !xfer_itm.File_exists())			// "non-found" thumbs should default to 220; otherwise large "non-found" thumbs will create large boxes; PAGE:en.w:Wikipedia:Featured_picture_candidates/September_Morn "|1000000x260px"; DATE:2014-09-24
			div_width = Xof_img_size.Thumb_width_img;
		if (	html_mgr.Img_suppress_missing_src()					// option to suppress src when file is missing
			&&	!xfer_itm.File_exists()								// file is missing; wipe values and wait for "correct" info before regenerating; mostly to handle unknown redirects
			&&	!orig_ext.Id_is_media()								// file is media; never suppress; src needs to be available for "click" on play; note that most media will be missing (not downloaded)
			&&	lnki.Ns_id() != Xow_ns_.Tid__media					// ns is media; never suppress; "src" will use only orig_src; DATE:2014-01-30
			) {						
			img_orig_src = img_view_src = Bry_.Empty;				// null out src
		}
		if		(lnki.Ns_id() == Xow_ns_.Tid__media)				// NOTE: regardless of ext (ogg vs jpeg) and literal status (Media vs :Media), [[Media]] links are always rendered the same way; REF.MW:Linker.php|makeMediaLinkObj; PAGE:en.w:Beethoven; EX: [[:Media:De-Ludwig_van_Beethoven.ogg|listen]]); [[File:Beethoven 3.jpg|The [[Media:BeethovenWithLyreGuitar( W. J. Mahler - 1804).jpg|complete painting]]...]]
			this.Write_file_ns_media(bfr, ctx, hctx, src, lnki, img_orig_src);
		else {
			if	(	Xof_ext_.Id_is_video_strict(orig_ext.Id())		// id is .ogv or .webm
					||	(	orig_ext.Id_is_ogg()					// id is ogg
						&&	wiki.File_mgr().Version_1_y()			// version is v1 (v2 always marks ogg as aud); DATE:2014-02-01
						&&	(	xfer_itm.File_exists()				// NOTE: xfer_itm.Html_pass() checks for video .ogg files (ext = .ogg and thumb is available); EX: WWI;
							||	xfer_itm.Dbmeta_is_new()			// NOTE: State_new() will always assume that ogg is video; needed for 1st load and dynamic updates
							)
						)
					) {	
				xfer_itm.Html_elem_tid_(Xof_html_elem.Tid_vid);
				this.Write_file_video(bfr, ctx, hctx, src, lnki, uid, div_width, lnki_halign_bry, lnki_href, img_view_src, img_orig_src, img_alt, xfer_itm);
			}
			else if  (orig_ext.Id_is_audio())						// audio
				this.Write_file_audio(bfr, ctx, hctx, src, lnki, uid, div_width, lnki_halign_bry, lnki_href, img_orig_src, img_alt);
			else													// image
				this.Write_file_image(bfr, ctx, hctx, src, lnki, xfer_itm, uid, lnki_is_thumbable, div_width, lnki_halign, lnki_halign_bry, lnki_ttl, orig_ext, lnki_href, img_view_src, img_orig_src, img_alt);
		}
		if (hctx.Mode_is_hdump() && Xof_html_elem.Tid_is_file(xfer_itm.Html_elem_tid())) {
			page.Hdump_data().Imgs_add_img(new Xohd_img_itm__img(), xfer_itm, Xohd_img_itm__gallery_itm.Tid_basic);
		}
	}
	private void Write_file_ns_media(Bry_bfr bfr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, byte[] img_orig_src) {
		html_fmtr.Html_full_media(bfr, hctx.Mode_is_hdump(), img_orig_src, lnki.Ttl().Page_txt(), Arg_caption(ctx, src, Xoh_wtr_ctx.Basic, lnki));	// NOTE: use orig_src not view_src; DATE:2014-01-19
	}
	private void Write_file_audio(Bry_bfr bfr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, int uid, int div_width, byte[] lnki_halign_bry, byte[] lnki_href, byte[] img_orig_src, byte[] alt) {
		byte[] content = Arg_content_audio(lnki, ctx, hctx, src, uid, lnki_href, img_orig_src, alt);
		if (lnki.Media_icon())
			html_fmtr.Html_thumb_core(bfr, hctx.Mode_is_hdump(), uid, lnki_halign_bry, div_width, content);
		else
			bfr.Add(content);
	}
	private void Write_file_video(Bry_bfr bfr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, int uid, int div_width, byte[] lnki_halign_bry, byte[] lnki_href, byte[] img_view_src, byte[] img_orig_src, byte[] alt, Xof_file_itm xfer_itm) {
		xfer_itm.Html_elem_tid_(Xof_html_elem.Tid_vid);
		boolean video_is_thumb = Xop_lnki_type.Id_defaults_to_thumb(lnki.Lnki_type());
		byte[] content = Arg_content_video(ctx, hctx, src, lnki, xfer_itm, uid, video_is_thumb, lnki_href, img_view_src, img_orig_src, alt);
		if (video_is_thumb)
			html_fmtr.Html_thumb_core(bfr, hctx.Mode_is_hdump(), uid, lnki_halign_bry, div_width, content);
		else
			bfr.Add(content);
	}
	private void Write_file_image(Bry_bfr bfr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, Xof_file_itm xfer_itm, int uid, boolean lnki_is_thumbable, int div_width, int lnki_halign, byte[] lnki_halign_bry
		, byte[] lnki_ttl, Xof_ext orig_ext, byte[] lnki_href, byte[] img_view_src, byte[] img_orig_src, byte[] alt) {
		if (lnki_halign == Xop_lnki_align_h_.Center) bfr.Add(Div_center_bgn);
		Bry_bfr tmp_bfr = bfr_mkr.Get_k004();
		byte[] anchor_title = html_wtr.Cfg().Lnki__title()
			? Arg_anchor_title(tmp_bfr, src, lnki, lnki_ttl, anchor_title_wkr)	// NOTE: Arg_anchor_title should only be called if there is no caption, else refs may not show; DATE:2014-03-05
			: Bry_.Empty;	
		Xoh_file_img_wkr lnki_file_wkr = lnki.Lnki_file_wkr(); if (lnki_file_wkr == null) lnki_file_wkr = html_fmtr;
		if (lnki_is_thumbable) {	// is "thumb"
			if (bfr.Len() > 0) bfr.Add_byte_nl();
			byte[] content = Arg_content_thumb(lnki_file_wkr, ctx, hctx, src, lnki, xfer_itm, uid, lnki_href, img_view_src, img_orig_src, alt, lnki_ttl, anchor_title);
			html_fmtr.Html_thumb_core(bfr, hctx.Mode_is_hdump(), uid, lnki_halign_bry, div_width, content);
		}
		else {
			if	(	cfg_alt_defaults_to_caption
				&& 	Bry_.Len_eq_0(alt)					// NOTE: if no alt, always use caption; DATE:2013-07-22
				&& 	!lnki.Alt_exists()					// unless blank alt exists; EX: [[File:A.png|a|alt=]] should have alt of "", not "a" 
				) {
				Arg_caption(ctx, src, Xoh_wtr_ctx.Alt, lnki).Bfr_arg__add(tmp_bfr);
				alt = tmp_bfr.To_bry_and_clear();
			}
			boolean div_align_exists = false;
			switch (lnki.Align_h()) {
				case Xop_lnki_align_h_.Left:	bfr.Add(Div_float_left)	.Add_byte_nl();	div_align_exists = true; break;
				case Xop_lnki_align_h_.Right:	bfr.Add(Div_float_right).Add_byte_nl();	div_align_exists = true; break;
				case Xop_lnki_align_h_.None:	bfr.Add(Div_float_none)	.Add_byte_nl();	div_align_exists = true; break;
			}
			byte img_cls_tid = lnki.Border() == Bool_.Y_byte ? Xoh_img_cls_.Tid__thumbborder : Xoh_img_cls_.Tid__none;
			byte[] img_cls_other = lnki.Lnki_cls(); // PAGE:en.s:Page:Notes_on_Osteology_of_Baptanodon._With_a_Description_of_a_New_Species.pdf/3; DATE:2014-09-06
			Arg_nde_tkn lnki_link_tkn = lnki.Link_tkn();
			if (lnki_link_tkn == Arg_nde_tkn.Null)		// full
				lnki_file_wkr.Html_full_img(bfr, hctx, page, src, xfer_itm, uid, lnki_href, Bool_.N, Xoh_lnki_consts.Tid_a_cls_image, Xoh_lnki_consts.Tid_a_rel_none, anchor_title, Xoh_file_html_fmtr__base.Escape_xowa_title(lnki_ttl), xfer_itm.Html_w(), xfer_itm.Html_h(), img_view_src, alt, img_cls_tid, img_cls_other);
			else {										// thumb
				Arg_itm_tkn link_tkn = lnki_link_tkn.Val_tkn();
				byte[] link_arg = Xoa_ttl.Replace_spaces(link_tkn.Dat_to_bry(src));	// replace spaces with unders, else "/wiki/File:A b.ogg" instead of "A_b.ogg"; DATE:2015-11-27
				if (Bry_.Has_at_bgn(link_arg, Xop_tkn_.Anchor_byte)) link_arg = Bry_.Add(ctx.Page().Ttl().Page_db(), link_arg);
				byte[] link_arg_html = tmp_link_parser.Parse(tmp_bfr, tmp_url, wiki, link_arg, lnki_href);
				byte[] xowa_title_bry = tmp_link_parser.Html_xowa_ttl();			// NOTE: xowa_title_bry will be link arg; [[File:A.png|link=file:///A.ogg]] -> A.ogg x> A.png
				boolean a_href_is_file = true;
				if (xowa_title_bry == null) {xowa_title_bry = lnki_ttl; a_href_is_file = false;}
				link_arg = link_arg_html == null ? lnki_href: link_arg_html;		// if parse fails, then assign to lnki_href; EX:link={{{1}}}
				link_arg = Gfo_url_encoder_.Href_qarg.Encode(link_arg);				// must encode quotes; PAGE:en.w:List_of_cultural_heritage_sites_in_Punjab,_Pakistan; DATE:2014-07-16
				// if (Bry_.Len_gt_0(tmp_link_parser.Html_xowa_ttl())) lnki_ttl = tmp_link_parser.Html_xowa_ttl(); // DELETE: not sure why this is here; breaks test; DATE:2015-11-28
				lnki_file_wkr.Html_full_img(bfr, hctx, page, src, xfer_itm, uid, link_arg, a_href_is_file, tmp_link_parser.Html_anchor_cls(), tmp_link_parser.Html_anchor_rel(), anchor_title, Xoh_file_html_fmtr__base.Escape_xowa_title(xowa_title_bry), xfer_itm.Html_w(), xfer_itm.Html_h(), img_view_src, alt, img_cls_tid, img_cls_other);
			}
			if (div_align_exists) bfr.Add(Gfh_tag_.Div_rhs);	// close div from above
		}
		if (lnki_halign == Xop_lnki_align_h_.Center) bfr.Add(Gfh_tag_.Div_rhs);
		tmp_bfr.Mkr_rls();
	}
	private byte[] Arg_content_thumb(Xoh_file_img_wkr lnki_file_wkr, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, Xof_file_itm xfer_itm, int uid, byte[] lnki_href, byte[] view_src, byte[] img_orig_src, byte[] lnki_alt_text, byte[] lnki_ttl, byte[] anchor_title) {
		byte[] lnki_alt_html = wiki.Html_mgr().Imgs_mgr().Alt_in_caption().Val() ? Arg_alt_html(ctx, hctx, src, lnki) : Bry_.Empty;
		byte img_cls_tid = xfer_itm.File_exists() ? Xoh_img_cls_.Tid__thumbimage : Xoh_img_cls_.Tid__none;
		Bry_bfr tmp_bfr = bfr_mkr.Get_k004();
		lnki_file_wkr.Html_full_img(tmp_bfr, hctx, page, src, xfer_itm, uid, lnki_href, Bool_.N, Xoh_lnki_consts.Tid_a_cls_image, Xoh_lnki_consts.Tid_a_rel_none, anchor_title, Xoh_file_html_fmtr__base.Escape_xowa_title(lnki_ttl), xfer_itm.Html_w(), xfer_itm.Html_h(), view_src, lnki_alt_text, img_cls_tid, Xoh_img_cls_.Bry__none);
		byte[] thumb = tmp_bfr.To_bry_and_clear();
		html_fmtr.Html_thumb_file_image(tmp_bfr, thumb, Arg_caption_div(ctx, hctx, src, lnki, uid, img_orig_src, lnki_href), lnki_alt_html);
		return tmp_bfr.To_bry_and_rls();
	}
	private byte[] Arg_content_audio(Xop_lnki_tkn lnki, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, int uid, byte[] lnki_href, byte[] img_orig_src, byte[] alt) {
		byte[] info_btn = Bry_.Empty;
		if (lnki.Media_icon()) {
			html_fmtr.Html_thumb_part_info(scratch_bfr, uid, hctx.Mode_is_hdump(), lnki_href, html_mgr.Img_media_info_btn());
			info_btn = scratch_bfr.To_bry_and_clear();
		}
		int play_btn_width = lnki.W(); if (play_btn_width < 1) play_btn_width = html_mgr.Img_thumb_width();	// if no width set width to default img width
		html_fmtr.Html_thumb_file_audio(scratch_bfr, Arg_caption_div(ctx, hctx, src, lnki, uid, img_orig_src, lnki_href), Arg_alt_html(ctx, hctx, src, lnki), Arg_play_btn(hctx.Mode_is_hdump(), uid, play_btn_width, Play_btn_max_width, img_orig_src, Xoh_file_html_fmtr__base.Escape_xowa_title(lnki.Ttl().Page_txt())), info_btn);
		return scratch_bfr.To_bry_and_clear();
	}
	private byte[] Arg_content_video(Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, Xof_file_itm xfer_itm, int uid, boolean lnki_thumb, byte[] a_href, byte[] view_src, byte[] orig_src, byte[] img_alt) {
		int thumb_w = xfer_itm.Html_w();
		int play_btn_width = thumb_w; if (play_btn_width < 1) play_btn_width = wiki.Html_mgr().Img_thumb_width();
		byte[] caption_html = Bry_.Empty, alt_html = Bry_.Empty;
		if (lnki_thumb) {
			caption_html = Arg_caption_div(ctx, hctx, src, lnki, uid, orig_src, a_href);
			alt_html = Arg_alt_html(ctx, hctx, src, lnki);
		}
		html_fmtr.Html_thumb_file_video(scratch_bfr, Arg_play_btn(hctx.Mode_is_hdump(), uid, play_btn_width, play_btn_width, orig_src, Xoh_file_html_fmtr__base.Escape_xowa_title(lnki.Ttl().Page_txt()))
			, html_fmtr.Html_thumb_part_img(scratch_bfr, hctx, page, src, xfer_itm, uid, a_href, view_src, img_alt)
			, caption_html, alt_html);
		return scratch_bfr.To_bry_and_clear();
	}
	private byte[] Arg_caption_div(Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Xop_lnki_tkn lnki, int uid, byte[] img_orig_src, byte[] lnki_href) {
		Bfr_arg caption = Arg_caption(ctx, src, hctx, lnki);
		byte[] magnify_btn = Bry_.Empty;
		if (lnki.Media_icon()) {
			if (msg_file_enlarge == null) msg_file_enlarge = wiki.Msg_mgr().Val_by_id(Xol_msg_itm_.Id_file_enlarge);
			html_fmtr.Html_thumb_part_magnify(scratch_bfr, uid, lnki_href, msg_file_enlarge, html_mgr.Img_thumb_magnify());
			magnify_btn = scratch_bfr.To_bry_and_clear();
		}
		html_fmtr.Html_thumb_part_caption(scratch_bfr, magnify_btn, caption);
		return scratch_bfr.To_bry_and_clear();				
	}	private byte[] msg_file_enlarge;
	private Bfr_arg Arg_caption(Xop_ctx ctx, byte[] src, Xoh_wtr_ctx hctx_for_caption, Xop_lnki_tkn lnki) {
		return lnki.Caption_exists() ? caption_fmtr.Set(ctx, hctx_for_caption, src, lnki.Caption_val_tkn(), Xoh_lnki_text_fmtr.Null_fmtr, Bool_.N) : Bfr_arg_.Noop;
	}
	public byte[] Arg_alt_text(Xop_ctx ctx, byte[] src, Xop_lnki_tkn lnki) {
		if (!lnki.Alt_exists()) return Bry_.Empty;
		media_alt_fmtr.Set(ctx, Xoh_wtr_ctx.Alt, src, lnki.Alt_tkn().Val_tkn(), Xoh_lnki_text_fmtr.Null_fmtr, Bool_.N);
		Bry_bfr tmp_bfr = bfr_mkr.Get_k004();
		media_alt_fmtr.Bfr_arg__add(tmp_bfr);
		return tmp_bfr.To_bry_and_rls(); 
	}		
	private byte[] Arg_alt_html(Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] alt_src, Xop_lnki_tkn lnki) {
		if (!lnki.Alt_exists()) return Bry_.Empty;
		media_alt_fmtr.Set(ctx, hctx, alt_src, lnki.Alt_tkn().Val_tkn(), html_fmtr.Html_thumb_part_alt_fmtr(), Bool_.Y);
		Bry_bfr tmp_bfr = bfr_mkr.Get_k004();
		media_alt_fmtr.Bfr_arg__add(tmp_bfr);
		return tmp_bfr.To_bry_and_rls(); 
	}
	private byte[] Arg_play_btn(boolean mode_is_hdump, int uid, int width, int max_width, byte[] a_href, byte[] a_xowa_title) {
		html_fmtr.Html_thumb_part_play(scratch_bfr, uid, mode_is_hdump, width - 2, max_width, a_href, a_xowa_title, html_mgr.Img_media_play_btn());	// NOTE: -2 is fudge factor else play btn will jut out over video thumb; see Earth and ISS video
		return scratch_bfr.To_bry_and_clear();				
	}
	private static byte[] Arg_anchor_title(Bry_bfr tmp_bfr, byte[] src, Xop_lnki_tkn lnki, byte[] lnki_ttl, Xoh_lnki_title_fmtr anchor_title_wkr) {
		if		(	Bitmask_.Has_int(lnki.Lnki_type(), Xop_lnki_type.Id_thumb)
				||	Bitmask_.Has_int(lnki.Lnki_type(), Xop_lnki_type.Id_frame)			// If the image is a thumb, do not add a title / alt, even if a caption is available
				)
					return Bry_.Empty;
		else if	(	Bitmask_.Has_int(lnki.Lnki_type(), Xop_lnki_type.Id_frameless)) {	// If the image is frameless, add the caption as a title / alt. If no caption is available, do not add a title / alt
		}
		Xop_tkn_itm anchor_title_tkn = lnki.Caption_tkn();
		if (anchor_title_tkn == Xop_tkn_null.Null_tkn) return Bry_.Empty; // no caption; return empty; (do not use lnki); DATE:2013-12-31
		tmp_bfr.Add(Atr_title);
		anchor_title_wkr.Set(src, anchor_title_tkn).Bfr_arg__add(tmp_bfr);
		tmp_bfr.Add_byte(Byte_ascii.Quote);
		return tmp_bfr.To_bry_and_clear();
	}
	public static final int Play_btn_max_width = 1024;
	private static final byte[]
	  Div_center_bgn			= Bry_.new_a7("<div class=\"center\">")
	, Div_float_none			= Bry_.new_a7("<div class=\"floatnone\">")
	, Div_float_left			= Bry_.new_a7("<div class=\"floatleft\">")
	, Div_float_right			= Bry_.new_a7("<div class=\"floatright\">")
	, Atr_title					= Bry_.new_a7(" title=\"")
	;
}
