package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.htmls.modules.*; import gplx.xowa.files.*; import gplx.xowa.htmls.core.makes.*; import gplx.xowa.htmls.core.makes.imgs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*; import gplx.xowa.parsers.lnkis.*;
public abstract class Gallery_mgr_base {
	private Gallery_box_w_fmtr_arg__basic box_w_fmtr__basic = new Gallery_box_w_fmtr_arg__basic(); // private Gallery_box_w_fmtr_arg__hdump box_w_fmtr__hdump = new Gallery_box_w_fmtr_arg__hdump();
	private Gallery_img_pad_fmtr_arg__basic img_pad_fmtr__basic = new Gallery_img_pad_fmtr_arg__basic(); // private Gallery_img_pad_fmtr_arg__hdump img_pad_fmtr__hdump = new Gallery_img_pad_fmtr_arg__hdump();
	public abstract byte Tid();
	public abstract byte[] Tid_bry();
	@gplx.Virtual public boolean Tid_is_packed() {return false;}
	public int Itm_default_w() {return itm_default_w;} protected int itm_default_w;
	public int Itm_default_h() {return itm_default_h;} protected int itm_default_h;
	public int Itms_per_row() {return itms_per_row;} @gplx.Virtual public void Itms_per_row_(int v) {this.itms_per_row = v;} protected int itms_per_row;		
	@gplx.Virtual public int Get_thumb_padding() {return 30;}	// REF.MW: getThumbPadding; How much padding such the thumb have between image and inner div that that contains the border. This is both for verical and horizontal padding. (However, it is cut in half in the vertical direction).
	@gplx.Virtual public int Get_gb_padding() {return 5;}	// REF.MW: getGBPadding; GB stands for gallerybox (as in the <li class="gallerybox"> element)
	@gplx.Virtual public int Get_gb_borders() {return 8;}	// REF.MW: getGBBorders; Get how much extra space the borders around the image takes up. For this mode, it is 2px borders on each side + 2px implied padding on each side from the stylesheet, giving us 2*2+2*2 = 8.
	@gplx.Virtual public int Get_all_padding() {return this.Get_thumb_padding() + this.Get_gb_padding() + this.Get_gb_borders();} // REF.MW: getAllPadding; How many pixels of whitespace surround the thumbnail.
	@gplx.Virtual public int Get_vpad(int itm_h, int thm_h) {	// REF.MW: getVPad; Get vertical padding for a thumbnail; Generally this is the total height minus how high the thumb is.
		return (this.Get_thumb_padding() + itm_h - thm_h) / 2;
	}
	@gplx.Virtual public int Get_thumb_div_width(int thm_w) {	// REF.MW: getThumbDivWidth; Get the width of the inner div that contains the thumbnail in question. This is the div with the class of "thumb".
		return itm_default_w + this.Get_thumb_padding();
	}
	@gplx.Virtual public int Get_gb_width(int thm_w, int thm_h) {// REF.MW: getGBWidth; Width of gallerybox <li>. Generally is the width of the image, plus padding on image plus padding on gallerybox.s
		return itm_default_w + this.Get_thumb_padding() + this.Get_gb_padding();
	}
	@gplx.Virtual public void Get_modules(Xoae_page page) {		// REF.MW: getModules; Get a list of modules to include in the page.
		page.Html_data().Head_mgr().Itm__gallery_styles().Enabled_y_();	// enable styles or some galleries will show up as list items; PAGE:s.w:Gothic_architecture DATE:2015-11-06
	}
	@gplx.Virtual public void Init(int itms_per_row, int itm_default_w, int itm_default_h) {
		this.itms_per_row = itms_per_row;
		this.itm_default_w = itm_default_w;
		this.itm_default_h = itm_default_h;
	}
	@gplx.Virtual public void Wrap_gallery_text(Bry_bfr bfr, byte[] gallery_text, int thm_w, int thm_h) {
		bfr	.Add(Wrap_gallery_text_bgn)
			.Add(gallery_text)
			.Add(Wrap_gallery_text_end)
			;
	}
	public void Write_html(Bry_bfr bfr, Xowe_wiki wiki, Xoae_page page, Xop_ctx ctx, Xoh_wtr_ctx hctx, byte[] src, Gallery_xnde xnde) {
		boolean hctx_is_hdump = hctx.Mode_is_hdump();
		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_b512();			
		byte[] box_style = xnde.Atr_style();
		int gallery_uid = page.Html_data().Xtn_gallery_next_id();
		if (itms_per_row > 0) {
			int max_width = itms_per_row * (itm_default_w + this.Get_all_padding());
			box_style = Fmt_and_add(tmp_bfr, Gallery_html_wtr_.Fmtr__ul__style, box_style, max_width);
			page.Hdump_data().Imgs_add(new Xohd_img_itm__gallery_mgr(gallery_uid, max_width));
		}
		byte[] box_cls = Fmt_and_add(tmp_bfr, Gallery_html_wtr_.Fmtr__ul__cls, xnde.Atr_cls(), this.Tid_bry());
		byte[] gallery_ul_id = tmp_bfr.Add(Gallery_html_wtr_.Id__ul).Add_int_variable(gallery_uid).To_bry_and_clear();
		Box_hdr_write(bfr, wiki.Appe().Html_mgr().Whitelist_mgr(), src, gallery_ul_id, box_cls, box_style, xnde.Atrs_other(), hctx_is_hdump, gallery_uid);
		byte[] box_caption = xnde.Atr_caption();
		if (Bry_.Len_gt_0(box_caption)) Gallery_html_wtr_.Fmtr__li__txt.Bld_bfr_many(bfr, box_caption);

		Xoae_app app = wiki.Appe(); Xoh_html_wtr html_wtr = wiki.Html_mgr().Html_wtr();
		int itm_len = xnde.Itms_len();
		for (int i = 0; i < itm_len; i++) {
			Write_html_itm(bfr, tmp_bfr, app, wiki, page, ctx, html_wtr, hctx, src, xnde, gallery_ul_id, i, null, hctx_is_hdump);
		}
		bfr.Add(box_html_end_bry);
		tmp_bfr.Mkr_rls();
	}
	public static byte File_found_mode = Bool_.__byte;
	public void Write_html_itm(Bry_bfr bfr, Bry_bfr tmp_bfr, Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, byte[] src, Gallery_xnde xnde, byte[] gallery_ul_id, int i, Xof_file_itm xfer_itm, boolean hctx_is_hdump) {
		Gallery_itm itm = (Gallery_itm)xnde.Itms_get_at(i);
		Xoa_ttl ttl = itm.Ttl(); if (Bry_.Len_eq_0(ttl.Page_db())) return;	// if file ttl is invalid, do not write; EX:File:#A.png; DATE:2016-01-12
		byte[] itm_caption = itm.Caption_bry(); if (itm_caption == null) itm_caption = Bry_.Empty;

		Xop_lnki_tkn lnki = itm.Lnki_tkn();
		int lnki_w_orig = lnki.W(), lnki_h_orig = lnki.H(); // store orig lnki_w / lnki_w
		this.Get_thumb_size(lnki, itm.Ext());	// packed=expand by 1.5;
		if (xfer_itm == null) {	// will be null on 1st pass
			xfer_itm = html_wtr.Lnki_wtr().File_wtr().Lnki_eval(Xof_exec_tid.Tid_wiki_page, ctx, page, lnki);
			xfer_itm.Html_gallery_mgr_h_(xnde.Itm_h_or_default());
			xfer_itm.Html_elem_tid_(Xof_html_elem.Tid_gallery_v2);
		}
		int img_uid = xfer_itm.Html_uid();
		byte[] li_id = Gallery_html_wtr_.Bld_id(tmp_bfr, Gallery_html_wtr_.Id__li, img_uid);
		byte[] li_id_atr = Gallery_html_wtr_.Bld_id_atr(tmp_bfr, hctx_is_hdump, li_id);
		byte[] itm_html = Bry_.Empty;
		int html_w_expand = xfer_itm.Html_w();
		int html_h_expand = xfer_itm.Html_h();
		boolean file_found = xfer_itm.File_exists();
		if (File_found_mode != Bool_.__byte)
			file_found = File_found_mode == Bool_.Y_byte;
		int vpad = -1, img_div_w = -1;
		if (	!hctx_is_hdump				// always write img if hdump; DATE:2015-04-27
			&&	(	!ttl.Ns().Id_is_file()
				||	!file_found
				)
			) {	// itm is not a file, or is not found; write text
			itm_html = Gallery_html_wtr_.Fmtr__div1__missing.Bld_bry_many(tmp_bfr, this.Get_thumb_padding() + itm_default_h, ttl.Page_txt());
			itm.Html_prepare(wiki, ctx, src, xnde, xfer_itm, li_id, i);
			xfer_itm.Html_img_wkr_(itm);
			lnki.W_(lnki_w_orig).H_(lnki_h_orig);
			html_w_expand = lnki_w_orig; html_h_expand = lnki_h_orig;	// reset lnki_w_orig / lnki_h_orig else large captions
		}
		else {
			byte[] alt = itm.Alt_bgn() == Bry_find_.Not_found && Bry_.Len_eq_0(itm_caption)	//	if ( $alt == '' && $text == '' )  $imageParameters['alt'] = $nt->getText();
				? itm.Ttl().Page_txt()
				: Bry_.Mid(src, itm.Alt_bgn(), itm.Alt_end());
			alt = Xoh_html_wtr_escaper.Escape(app.Parser_amp_mgr(), tmp_bfr, alt);	// NOTE: need to handle situations wherein alt has quotes; EX:File:A"b.png; PAGE:en.w:Alexandria,_Romania DATE:2015-12-27
			Xoa_ttl href_ttl = itm.Link_bgn() == Bry_find_.Not_found
				? ttl
				: Xoa_ttl.parse(wiki, Bry_.Mid(src, itm.Link_bgn(), itm.Link_end()))
				;
			if (href_ttl == null) href_ttl = ttl;	// occurs when link is invalid; EX: A.png|link=<invalid>
			this.Adjust_image_parameters(xfer_itm);	// trad=noop; packed=reduce by 1.5
			int html_w_normal = xfer_itm.Html_w();
			int html_h_normal = xfer_itm.Html_h();
			xfer_itm.Init_at_gallery_bgn(html_w_normal, html_h_normal, html_w_expand);// NOTE: file_w should be set to expanded width so js can resize if gallery
			img_div_w = this.Get_thumb_div_width(html_w_expand);
			Gallery_html_wtr_.Fmtr__div1__img.Bld_bfr_many(tmp_bfr, img_div_w);
			// Gallery_img_pad_fmtr_arg vpad_fmtr = hctx_is_hdump ? (Gallery_img_pad_fmtr_arg)img_pad_fmtr__hdump : (Gallery_img_pad_fmtr_arg)img_pad_fmtr__basic;
			Gallery_img_pad_fmtr_arg vpad_fmtr = img_pad_fmtr__basic;
			vpad = this.Get_vpad(itm_default_h, html_h_expand);
			Gallery_html_wtr_.itm_div1_fmtr.Bld_bfr_many(tmp_bfr, vpad_fmtr.Init(img_uid, vpad));	// <div style="margin:~{vpad}px auto;">
			wiki.Html_mgr().Html_wtr().Lnki_wtr().Write_file(tmp_bfr, ctx, hctx, src, lnki, xfer_itm, alt);
			tmp_bfr.Add(itm_divs_end_bry);
			itm_html = tmp_bfr.To_bry_and_clear();
		}

		byte[] show_filenames_link = Bry_.Empty;
		if (xnde.Show_filename()) {
			wiki.Html_mgr().Html_wtr().Lnki_wtr().Write_plain_by_bry
			( tmp_bfr, src, lnki
			, Bry_.Limit(ttl.Page_txt(), 25)		// 25 is defined by captionLength in DefaultSettings.php					
			);										// MW:passes know,noclasses which isn't relevant to XO
		}
		int itm_div_width = this.Get_gb_width(html_w_expand, html_h_expand);
		// Gallery_box_w_fmtr_arg box_w_fmtr_arg = hctx_is_hdump ? (Gallery_box_w_fmtr_arg)box_w_fmtr__hdump : (Gallery_box_w_fmtr_arg)box_w_fmtr__basic;
		Gallery_box_w_fmtr_arg box_w_fmtr_arg = box_w_fmtr__basic;
		Gallery_html_wtr_.Fmtr__li__img.Bld_bfr_many(bfr, li_id_atr, box_w_fmtr_arg.Init(img_uid, itm_div_width));
		bfr.Add(itm_html);
		wiki.Parser_mgr().Main().Parse_text_to_html(tmp_bfr, page, hctx, true, itm_caption);
		itm_caption = tmp_bfr.To_bry_and_clear();
		itm_caption = tmp_bfr.Add(show_filenames_link).Add(itm_caption).To_bry_and_clear();
		Wrap_gallery_text(bfr, itm_caption, html_w_expand, html_h_expand);
		bfr.Add(itm_li_end_bry);
		if (hctx_is_hdump)
			page.Hdump_data().Imgs_add_img(new Xohd_img_itm__gallery_itm().Data_init_gallery(itm_div_width, img_div_w, vpad), xfer_itm, Xohd_img_itm__gallery_itm.Tid_gallery);
	}
	private static final byte[] 
	  Wrap_gallery_text_bgn = Bry_.new_a7("\n      <div class=\"gallerytext\">") // NOTE: The newline after <div class="gallerytext"> is needed to accommodate htmltidy
	, Wrap_gallery_text_end = Bry_.new_a7("\n      </div>")	// NOTE: prepend "\n"; will cause extra \n when caption exists, but needed when caption doesn't exists; EX: "<div class='caption'>    </div>"; \n puts
	;
	@gplx.Virtual public void Get_thumb_size(Xop_lnki_tkn lnki, Xof_ext ext) { // REF.MW: getThumbParams; Get the transform parameters for a thumbnail.
		lnki.W_(itm_default_w);
		lnki.H_(itm_default_h);
	}
	@gplx.Virtual public void Adjust_image_parameters(Xof_file_itm xfer_itm) {}	// REF.MW: Adjust the image parameters for a thumbnail. Used by a subclass to insert extra high resolution images.		
	private static final byte[] 
	  itm_li_end_bry	= Bry_.new_a7		  ( "\n    </div>"
											  + "\n  </li>")
	, box_html_end_bry	= Bry_.new_a7		  ( "\n</ul>")
	, itm_divs_end_bry	= Bry_.new_a7		  ( "\n        </div>\n      </div>")
	;
	private static byte[] Fmt_and_add(Bry_bfr tmp_bfr, Bry_fmtr fmtr, byte[] trailer, Object... fmtr_args) {
		fmtr.Bld_bfr_many(tmp_bfr, fmtr_args);
		if (Bry_.Len_gt_0(trailer)) {
			tmp_bfr.Add_byte_space();
			tmp_bfr.Add(trailer);
		}
		return tmp_bfr.To_bry_and_clear();
	}
	private static void Box_hdr_write(Bry_bfr bfr, Xop_xatr_whitelist_mgr whitelist_mgr, byte[] src, byte[] gallery_ul_uid, byte[] cls, byte[] style, List_adp xatr_list, boolean hctx_is_hdump, int uid) {
		bfr.Add_byte(Byte_ascii.Lt).Add(Gfh_tag_.Bry__ul);
		if (!hctx_is_hdump)
			Gfh_wtr.Write_atr_bry(bfr, Gfh_atr_.Bry__id, gallery_ul_uid);
		Gfh_wtr.Write_atr_bry(bfr, Gfh_atr_.Bry__class, cls);
		Gfh_wtr.Write_atr_bry(bfr, Gfh_atr_.Bry__style, style);
		if (xatr_list != null) {
			int len = xatr_list.Count();
			for (int i = 0; i < len; i++) {
				Mwh_atr_itm xatr = (Mwh_atr_itm)xatr_list.Get_at(i);
				if (!whitelist_mgr.Chk(Xop_xnde_tag_.Tid__ul, src, xatr)) continue;
				byte[] key = xatr.Key_bry();
				byte[] val = xatr.Val_as_bry();
				Gfh_wtr.Write_atr_bry(bfr, key, val);
			}
		}
		bfr.Add_byte(Byte_ascii.Gt);
	}
}
class Gallery_mgr_traditional extends Gallery_mgr_base {
	@Override public byte Tid() {return Gallery_mgr_base_.Traditional_tid;}		
	@Override public byte[] Tid_bry() {return Gallery_mgr_base_.Traditional_bry;}
}
class Gallery_mgr_nolines extends Gallery_mgr_base {
	@Override public byte Tid() {return Gallery_mgr_base_.Nolines_tid;}
	@Override public byte[] Tid_bry() {return Gallery_mgr_base_.Nolines_bry;}
	@Override public int Get_thumb_padding()				{return 0;}
	@Override public int Get_gb_padding()				{return 4;} // This accounts for extra space between <li> elements.
	@Override public int Get_vpad(int itm_h, int thm_h)	{return 0;}
}
