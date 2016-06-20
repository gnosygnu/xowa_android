package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.wkrs.bfr_args.*;
import gplx.xowa.files.*; import gplx.xowa.htmls.core.makes.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;	
import gplx.xowa.htmls.core.htmls.*;
import gplx.xowa.parsers.lnkis.*;
public class Xoh_file_html_fmtr__base implements Xoh_file_img_wkr {
	protected final    Xoh_arg_img_core arg_img_core;
	private Bry_bfr scratch_bfr = Bry_bfr_.Reset(128);
	private final    Bfr_arg__hatr_id thm_file_id = Bfr_arg__hatr_id.New_id("xowa_file_div_"), thm_play_id = Bfr_arg__hatr_id.New_id("xowa_file_play_");
	public Xoh_file_html_fmtr__base() {
		arg_img_core = New_arg_img_core();
	}
	@gplx.Internal @gplx.Virtual protected Xoh_arg_img_core New_arg_img_core() {return new Xoh_arg_img_core__basic();}
	@gplx.Virtual public void Html_full_media(Bry_bfr tmp_bfr, boolean mode_is_hdump, byte[] a_href, byte[] a_title, Bfr_arg html) {
		if (mode_is_hdump) a_href = Bry_.Empty;
		fmtr_full_media.Bld_bfr_many(tmp_bfr, a_href, a_title, html);
	}
	private final    Bry_fmtr fmtr_full_media = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "<a href=\"~{a_href}\" xowa_title=\"~{a_xowa_title}\">~{html}"
	, "</a>"
	), "a_href", "a_xowa_title", "html"
	);
	@gplx.Virtual public void Html_full_img(Bry_bfr tmp_bfr, Xoh_wtr_ctx hctx, Xoae_page page, byte[] src, Xof_file_itm xfer_itm, int uid
	, byte[] a_href, boolean a_href_is_file, byte a_cls, byte a_rel, byte[] a_title, byte[] a_xowa_title
	, int img_w, int img_h, byte[] img_src, byte[] img_alt, byte img_cls, byte[] img_cls_other
	) {
		if (Bry_.Len_eq_0(a_href))	// empty link should not create anchor; EX:[[File:A.png|link=|abc]]; PAGE:en.w:List_of_counties_in_New_York; DATE:2016-01-10
			fmtr_full_img_wo_link.Bld_bfr_many(tmp_bfr, uid, arg_img_core.Init(uid, img_src, img_w, img_h), img_alt, Xoh_img_cls_.To_html(img_cls, img_cls_other));			
		else
			fmtr_full_img.Bld_bfr_many(tmp_bfr, uid
			, a_href, Xoh_lnki_consts.A_cls_to_bry(a_cls), Xoh_lnki_consts.A_rel_to_bry(a_rel), a_title, a_xowa_title
			, arg_img_core.Init(uid, img_src, img_w, img_h), img_alt, Xoh_img_cls_.To_html(img_cls, img_cls_other));
	}
	private Bry_fmtr fmtr_full_img = Bry_fmtr.new_
	( "<a href=\"~{a_href}\"~{a_class}~{a_rel}~{a_title} xowa_title=\"~{a_xowa_title}\">"
	+ "<img id=\"xoimg_~{uid}\" alt=\"~{img_alt}\"~{img_core}~{img_class} /></a>"
	, "uid", "a_href", "a_class", "a_rel", "a_title", "a_xowa_title", "img_core", "img_alt", "img_class"
	);
	private Bry_fmtr fmtr_full_img_wo_link = Bry_fmtr.new_
	( "<img id=\"xoimg_~{uid}\" alt=\"~{img_alt}\"~{img_core}~{img_class} />"
	, "uid", "img_core", "img_alt", "img_class"
	);
	public byte[] Html_thumb_part_img(Bry_bfr tmp_bfr, Xoh_wtr_ctx hctx, Xoae_page page, byte[] src, Xof_file_itm xfer_itm, int uid, byte[] a_href, byte[] img_src, byte[] img_alt) {
		byte[] a_title_atr = Gfh_atr_.Make(tmp_bfr, Gfh_atr_.Bry__title, xfer_itm.Lnki_ttl());
		Html_full_img(tmp_bfr, hctx, page, src, xfer_itm, uid, a_href, Bool_.N, Xoh_lnki_consts.Tid_a_cls_image, Xoh_lnki_consts.Tid_a_rel_none, a_title_atr, Xoh_file_html_fmtr__base.Escape_xowa_title(xfer_itm.Lnki_ttl())
			, xfer_itm.Html_w(), xfer_itm.Html_h(), img_src, img_alt, Xoh_img_cls_.Tid__none, null);
		return tmp_bfr.To_bry_and_clear();
	}
	@gplx.Virtual public void Html_thumb_core(Bry_bfr tmp_bfr, boolean mode_is_hdump, int uid, byte[] div1_halign, int div2_width, byte[] div2_content) {
		scratch_bfr.Add(Bry_style_bgn);
		scratch_bfr.Add_int_variable(div2_width);
		scratch_bfr.Add(Bry_style_end);
		thm_file_id.Bfr_arg__clear();
		if (mode_is_hdump)
			thm_file_id.Bfr_arg__clear();
		else
			thm_file_id.Set(uid);
		fmtr_thumb_core.Bld_bfr_many(tmp_bfr, thm_file_id, div1_halign, scratch_bfr.To_bry_and_clear(), div2_content);
	}
	private static final    byte[] Bry_style_bgn = Bry_.new_a7("style=\"width:"), Bry_style_end = Bry_.new_a7("px;\"");
	protected Bry_fmtr fmtr_thumb_core = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last	// REF.MW: Linker.php|makeImageLink2
	( "<div class=\"thumb t~{div1_halign}\">"
	, "  <div~{div_id} class=\"thumbinner\" ~{style}>"
	,     "~{div2_content}"
	, "  </div>"
	, "</div>"
	, ""
	), "div_id", "div1_halign", "style", "div2_content"
	);
	public void Html_thumb_part_caption(Bry_bfr tmp_bfr, byte[] magnify_btn, Bfr_arg caption) {fmtr_thumb_part_caption.Bld_bfr_many(tmp_bfr, magnify_btn, caption);}
	private Bry_fmtr fmtr_thumb_part_caption = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "    <div class=\"thumbcaption\">~{magnify_btn}~{caption}"
	, "    </div>"
	), "magnify_btn", "caption");

	public void Html_thumb_file_image(Bry_bfr tmp_bfr, byte[] thumb_image, byte[] caption, byte[] alt) {fmtr_thumb_file_image.Bld_bfr_many(tmp_bfr, thumb_image, caption, alt);}
	private final    Bry_fmtr fmtr_thumb_file_image = Bry_fmtr.new_("    ~{thumb_image}~{caption}~{alt}", "thumb_image", "caption", "alt");

	public void Html_thumb_file_audio(Bry_bfr tmp_bfr, byte[] caption, byte[] alt, byte[] play_btn, byte[] audio_info) {fmtr_thumb_file_audio.Bld_bfr_many(tmp_bfr, caption, alt, play_btn, audio_info);}
	private Bry_fmtr fmtr_thumb_file_audio = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "    <div class=\"xowa_media_div\">~{play_btn}~{audio_info}"
	, "    </div>~{caption}~{alt}"
	), "caption", "alt", "play_btn", "audio_info");

	public void Html_thumb_file_video(Bry_bfr tmp_bfr, byte[] play_btn, byte[] video_thumb, byte[] caption, byte[] alt) {fmtr_thumb_file_video.Bld_bfr_many(tmp_bfr, caption, alt, play_btn, video_thumb);}
	private final    Bry_fmtr fmtr_thumb_file_video = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "    <div class=\"xowa_media_div\">"
	, "      <div>~{video_thumb}"
	, "      </div>~{play_btn}"
	, "    </div>~{caption}~{alt}"
	), "caption", "alt", "play_btn", "video_thumb");

	public void Html_thumb_part_alt(Bry_bfr tmp_bfr, byte[] alt_html) {fmtr_thumb_part_alt.Bld_bfr_many(tmp_bfr, alt_html);}
	public Bry_fmtr Html_thumb_part_alt_fmtr() {return fmtr_thumb_part_alt;} private Bry_fmtr fmtr_thumb_part_alt = Bry_fmtr.new_
	(String_.Concat_lines_nl_skip_last
	( ""
	, "    <hr/>"
	, "    <div class=\"thumbcaption\">~{html}"
	, "    </div>"
	), "html");

	@gplx.Virtual public void Html_thumb_part_magnify(Bry_bfr tmp_bfr, int uid, byte[] a_href, byte[] a_title, byte[] img_src) {
		fmtr_thumb_part_magnify.Bld_bfr_many(tmp_bfr, a_href, a_title);
	}
	private final    Bry_fmtr fmtr_thumb_part_magnify = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "<div class=\"magnify\"><a href=\"~{a_href}\" class=\"inte"+"rnal\" title=\"~{a_title}\"></a></div>"
	), "a_href", "a_title");

	@gplx.Virtual public void Html_thumb_part_info(Bry_bfr tmp_bfr, int uid, boolean mode_is_hdump, byte[] a_href, byte[] img_src) {
		if (mode_is_hdump)
			img_src = Bry_.Empty;
		fmtr_thumb_part_info.Bld_bfr_many(tmp_bfr, a_href, img_src);
	}
	private final    Bry_fmtr fmtr_thumb_part_info = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	,       "<div><a href=\"~{a_href}\" class=\"xowa_media_info\" title=\"About this file\"></a></div>" 
	), "a_href", "img_src");

	public void Html_thumb_part_play(Bry_bfr tmp_bfr, int uid, boolean mode_is_hdump, int a_width, int a_max_width, byte[] a_href, byte[] a_xowa_title, byte[] img_src) {
		if (mode_is_hdump) {
			thm_play_id.Bfr_arg__clear();
			a_href = Bry_.Empty;
			a_width = 218; a_max_width = 220;	// NOTE: hardcode widths; hdump will get actual file with from fsdb
		}
		else
			thm_play_id.Set(uid);
		fmtr_thumb_part_play.Bld_bfr_many(tmp_bfr, thm_play_id, a_width, a_max_width, a_href, a_xowa_title);
	}
	private final    Bry_fmtr fmtr_thumb_part_play = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	,       "<div><a~{id} href=\"~{a_href}\" xowa_title=\"~{a_xowa_title}\" class=\"xowa_media_play\" style=\"width:~{a_width}px;max-width:~{a_max_width}px;\" alt=\"Play sound\"></a></div>"
	), "id", "a_width", "a_max_width", "a_href", "a_xowa_title");

	public static final    Xoh_file_html_fmtr__base Base = new Xoh_file_html_fmtr__base();
	public static byte[] Escape_xowa_title(byte[] lnki_ttl) {
		return Xoa_ttl.Replace_spaces(gplx.langs.htmls.encoders.Gfo_url_encoder_.Href_quotes.Encode(lnki_ttl)); // must encode xowa_title, particularly quotes; EX: xowa_title="A"b.png"; PAGE:en.w:Earth DATE:2015-11-27
	}
}
