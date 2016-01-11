package gplx.xowa.htmls.core.wkrs.lnkis.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.*;
import gplx.xowa.files.*; import gplx.xowa.htmls.core.makes.*;
import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
import gplx.xowa.parsers.lnkis.*;
public class Xoh_file_html_fmtr__hdump extends Xoh_file_html_fmtr__base {
	private final Bry_bfr tmp_bfr = Bry_bfr.reset_(128);
	@Override public void Html_full_img(Bry_bfr bfr, gplx.xowa.htmls.core.htmls.Xoh_wtr_ctx hctx, Xoae_page page, byte[] src, Xof_file_itm xfer_itm
		, int uid, byte[] a_href, boolean a_href_is_file, byte a_cls, byte a_rel, byte[] a_title, byte[] a_xowa_title
		, int img_w, int img_h, byte[] img_src, byte[] img_alt, byte img_cls, byte[] img_cls_other) {
		boolean link_is_empty = Bry_.Len_eq_0(a_href);
		if (a_href_is_file) a_href = Bry_.Empty;
		tmp_bfr.Add_str_a7(" data-xoimg=\"");
		tmp_bfr.Add_int_digits(1, Xop_lnki_type.To_tid(xfer_itm.Lnki_type())).Add_byte_pipe();
		tmp_bfr.Add_int_variable(xfer_itm.Lnki_w()).Add_byte_pipe();
		tmp_bfr.Add_int_variable(xfer_itm.Lnki_h()).Add_byte_pipe();
		tmp_bfr.Add_double(xfer_itm.Lnki_upright()).Add_byte_pipe();
		tmp_bfr.Add_double(xfer_itm.Lnki_time()).Add_byte_pipe();
		tmp_bfr.Add_int_variable(xfer_itm.Lnki_page()).Add_byte_quote();
		if (link_is_empty)
			fmtr__img__full_wo_anch.Bld_bfr_many(bfr
			, a_xowa_title, Gfh_utl.Escape_html_as_bry(img_alt), tmp_bfr.To_bry_and_clear(), arg_img_core.Init(uid, Bry_.Empty, 0, 0), Xoh_img_cls_.To_html(img_cls, img_cls_other));
		else
			fmtr__img__full.Bld_bfr_many(bfr
			, a_href, Xoh_lnki_consts.A_cls_to_bry(a_cls), Xoh_lnki_consts.A_rel_to_bry(a_rel), a_title, a_xowa_title
			, Gfh_utl.Escape_html_as_bry(img_alt), tmp_bfr.To_bry_and_clear(), arg_img_core.Init(uid, Bry_.Empty, 0, 0), Xoh_img_cls_.To_html(img_cls, img_cls_other));
	}
	private 
	  Bry_fmtr fmtr__img__full = Bry_fmtr.new_
	( "<a href=\"~{a_href}\"~{a_class}~{a_rel}~{a_title} xowa_title=\"~{a_xowa_title}\">"
	+   "<img~{img_xoimg}~{img_core}~{img_class} alt=\"~{img_alt}\"/>"
	+ "</a>"
	, "a_href", "a_class", "a_rel", "a_title", "a_xowa_title", "img_alt", "img_xoimg", "img_core", "img_class"
	)
	, fmtr__img__full_wo_anch = Bry_fmtr.new_
	( "<img xowa_title=\"~{a_xowa_title}\"~{img_xoimg}~{img_core}~{img_class} alt=\"~{img_alt}\"/>"
	, "a_xowa_title", "img_alt", "img_xoimg", "img_core", "img_class"
	);

//		public override void Html_full_media(Bry_bfr tmp_bfr, byte[] a_href, byte[] a_title, Bfr_arg html) {
//			fmtr_full_media.Bld_bfr_many(tmp_bfr, a_href, a_title, html);
//		}
//		public override void Html_thumb_core(Bry_bfr bfr, int uid, byte[] div1_halign, int div2_width, byte[] div2_content) {
//			tmp_bfr.Add(Xoh_make_trie_.Bry__img_style);
//			tmp_bfr.Add_int_variable(uid);
//			tmp_bfr.Add_byte_quote();
//			byte[] div2_width_repl = tmp_bfr.To_bry_and_clear();
//			fmtr_thumb_core.Bld_bfr_many(bfr, uid, div1_halign, div2_width_repl, div2_content);
//		}
//		public override void Html_thumb_part_magnify(Bry_bfr bfr, int uid, byte[] a_href, byte[] a_title, byte[] img_src)									{Write_xnde(bfr, Xoh_make_trie_.Bry__file_mgnf, uid);}
//		public override void Html_thumb_part_info(Bry_bfr bfr, int uid, byte[] a_href, byte[] img_src)														{Write_xnde(bfr, Xoh_make_trie_.Bry__file_info, uid);}
//		public override void Html_thumb_part_play(Bry_bfr bfr, int uid, int a_width, int a_max_width, byte[] a_href, byte[] a_xowa_title, byte[] img_src)	{Write_xnde(bfr, Xoh_make_trie_.Bry__file_play, uid);}
//		private static void Write_xnde(Bry_bfr bfr, byte[] key, int uid) {
//			bfr.Add(key);
//			bfr.Add_int_variable(uid);
//			bfr.Add(Bry_xnde_end);
//		}	private static final byte[] Bry_xnde_end = Bry_.new_a7("\"/>");
	public static final Xoh_file_html_fmtr__hdump Hdump = new Xoh_file_html_fmtr__hdump(); Xoh_file_html_fmtr__hdump() {}
}
