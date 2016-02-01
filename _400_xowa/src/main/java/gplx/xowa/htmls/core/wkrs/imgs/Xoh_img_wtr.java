package gplx.xowa.htmls.core.wkrs.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.brys.*; import gplx.core.primitives.*; import gplx.core.brys.args.*; import gplx.core.brys.fmtrs.*; import gplx.core.threads.poolables.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*; import gplx.langs.htmls.encoders.*;
import gplx.xowa.htmls.core.hzips.*; import gplx.xowa.htmls.core.wkrs.bfr_args.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
import gplx.xowa.files.*; import gplx.xowa.xtns.imaps.*;
public class Xoh_img_wtr implements Bfr_arg, Xoh_wtr_itm {
	private final Bfr_arg_clearable[] arg_ary;
	private final Bfr_arg__hatr_arg img_xowa_image = new Bfr_arg__hatr_arg(Xoh_img_xoimg_data.Bry__data_xowa_image); 
	private final Bfr_arg__hatr_id img_id = Bfr_arg__hatr_id.New_id(Xoh_img_mgr.Bry__html_uid), vid_play_id = Bfr_arg__hatr_id.New_id("xowa_file_play_"), img_imap_usemap = new Bfr_arg__hatr_id(Imap_xtn_mgr.Bry__usemap__name, Imap_xtn_mgr.Bry__usemap__prefix);
	private final Bfr_arg__hatr_int img_w = new Bfr_arg__hatr_int(Gfh_atr_.Bry__width), img_h = new Bfr_arg__hatr_int(Gfh_atr_.Bry__height);
	private final Bfr_arg__hatr_bry
	  anch_href = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__href)
	, anch_rel = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__rel)
	, anch_xowa_title = new Bfr_arg__hatr_bry(Xoh_img_data.Bry__atr__xowa_title)
	, anch_cls = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__class)
	, anch_title = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__title)
	, img_alt = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__alt)
	, img_src = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__src)
	, img_cls = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__class)
	, img_xowa_title = new Bfr_arg__hatr_bry(Xoh_img_xoimg_data.Bry__data_xowa_title);
	private boolean img_is_vid; private boolean img_wo_anch;
	private byte[] xowa_root_dir;
	public Xoh_img_wtr() {
		arg_ary = new Bfr_arg_clearable[] 
		{ anch_href, anch_rel, anch_cls, anch_title, anch_xowa_title
		, img_id, img_xowa_title, img_xowa_image, img_src, img_w, img_h, img_cls, img_alt
		};
	}
	public Xof_fsdb_itm	Fsdb_itm() {return fsdb_itm;} private Xof_fsdb_itm fsdb_itm;
	public Xoh_img_wtr Anch_cls_(byte[] v)						{anch_cls.Set_by_bry(v); return this;}
	public Xoh_img_wtr Img_id_(int uid)							{img_id.Set(uid); return this;}
	public Xoh_img_wtr Clear() {			
		for (Bfr_arg_clearable arg : arg_ary)
			arg.Bfr_arg__clear();
		vid_play_id.Bfr_arg__clear();
		img_imap_usemap.Bfr_arg__clear();
		img_is_vid = false; img_wo_anch = false;
		return this;
	}
	public void Init_by_parse(Bry_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_img_data data) {
		Init_by_decode(hpg, hctx, src, data);
		this.Bfr_arg__add(bfr);
	}
	private static final byte[] Bry__qarg__esc = Bry_.new_a7("%3F");
	public boolean Init_by_decode(Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, Xoh_data_itm data_itm) {
		Xoh_img_data data = (Xoh_img_data)data_itm;
		this.Clear();
		this.img_is_vid = data.Img_is_vid();
		this.img_wo_anch = data.Img_wo_anch();
		this.fsdb_itm = hpg.Img_mgr().Make_img();
		byte[] file_ttl_bry = data.Img_src().File_ttl_bry();
		byte[] lnki_ttl = Xoa_ttl.Replace_spaces(Gfo_url_encoder_.Href_quotes.Decode(file_ttl_bry));	// NOTE: must decode for fsdb.lnki_ttl as well as xowa_title; EX: A%C3%A9b -> A�b
		if (data.Img_xoimg().Val_dat_exists()) {
			Xoh_img_xoimg_data img_xowa_image = data.Img_xoimg();
			fsdb_itm.Init_at_lnki(Xof_exec_tid.Tid_wiki_page, hpg.Wiki().Domain_itm().Abrv_xo(), lnki_ttl, gplx.xowa.parsers.lnkis.Xop_lnki_type.To_flag(img_xowa_image.Lnki_type()), img_xowa_image.Lnki_upright(), img_xowa_image.Lnki_w(), img_xowa_image.Lnki_h(), img_xowa_image.Lnki_time(), img_xowa_image.Lnki_page(), Xof_patch_upright_tid_.Tid_all);
			hctx.File__mgr().Find(hpg.Wiki(), hpg.Url_bry_safe(), fsdb_itm);
			this.img_xowa_image.Set_by_arg(img_xowa_image.Clone());	// NOTE: must clone b/c img_xowa_image is member of Xoh_img_data which is poolable (and cleared); PAGE:en.w:Almagest; DATE:2016-01-05
			img_w.Set_by_int(fsdb_itm.Html_w());
			img_h.Set_by_int(fsdb_itm.Html_h());
			byte[] src_bry = fsdb_itm.Html_view_url().To_http_file_bry();
			if (gplx.core.envs.Op_sys.Cur().Tid_is_drd()) src_bry = Bry_.Replace(src_bry, Byte_ascii.Question_bry, Bry__qarg__esc);	// NOTE: if drd, always escape "?" as "%3F" PAGE:en.w:Cleopatra en.w:Cave_painting; DATE:2016-01-31
			this.img_src.Set_by_bry(src_bry);
		}
		else if (data.Img_w() != -1) {
			img_w.Set_by_int(data.Img_w());
			img_h.Set_by_int(data.Img_h());
			this.img_src.Set_by_arg(data.Img_src());
		}			
		if (data.Anch_rel_nofollow_exists()) anch_rel.Set_by_bry(gplx.xowa.htmls.core.wkrs.lnkes.Xoh_lnke_dict_.Html__rel__nofollow);
		if (!hctx.Mode_is_diff()) {
			this.Img_id_(fsdb_itm.Html_uid());
			vid_play_id.Set(fsdb_itm.Html_uid());
		}
		anch_href.Set_by_mid(data.Anch_href().Rng_src(), data.Anch_href().Rng_bgn(), data.Anch_href().Rng_end());
		anch_cls.Set_by_arg(data.Anch_cls());
		anch_title.Set_by_mid_or_null(src, data.Anch_title_bgn(), data.Anch_title_end());
		if (	data.Img_wo_anch() 					// anchor-less image
			||	Bry_.Len_gt_0(file_ttl_bry))		// regular anch with image
			anch_xowa_title.Set_by_bry(file_ttl_bry);			
		img_xowa_title.Set_by_bry(file_ttl_bry);
		xowa_root_dir = hctx.Fsys__root();
		img_alt.Set_by_mid_or_empty(src, data.Img_alt_bgn(), data.Img_alt_end());
		img_cls.Set_by_arg(data.Img_cls());
		if (data.Img_imap_idx() != -1) img_imap_usemap.Set(data.Img_imap_idx());
		return true;
	}
	public void Init_by_gly(Bfr_arg_clearable href, byte[] xowa_title, Bfr_arg_clearable xoimg) {
		anch_href.Set_by_arg(href);
		anch_xowa_title.Set_by_bry(xowa_title);
		img_xowa_image.Set_by_arg(xoimg);
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (img_wo_anch)
			img_wo_anch_fmtr.Bld_bfr_many(bfr, img_id, img_xowa_title, img_xowa_image, img_src, img_w, img_h, img_cls, img_alt, img_imap_usemap);
		else {
			if (img_is_vid) bfr.Add(Vid__bry__bgn);
			img_fmtr.Bld_bfr_many(bfr, (Object[])arg_ary);
			if (img_is_vid)
				vid_fmtr.Bld_bfr_many(bfr, vid_play_id, anch_xowa_title, xowa_root_dir, 218, 220);	// TODO: hardcode widths; need to update via js from fsdb
		}
	}
	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Xoh_img_wtr rv = new Xoh_img_wtr(); rv.pool_mgr = mgr; rv.pool_idx = idx; return rv;}
	private static final byte[] Vid__bry__bgn = Bry_.new_a7("<div class=\"xowa_media_div\">\n<div>");
	private static final Bry_fmtr 
	  img_fmtr = Bry_fmtr.new_
	( "<a~{anch_href}~{anch_rel}~{anch_cls}~{anch_title}~{anch_xowa_title}><img~{img_id}~{img_xowa_title}~{img_xowa_image}~{img_src}~{img_w}~{img_h}~{img_cls}~{img_alt}></a>"
	, "anch_href", "anch_rel", "anch_cls", "anch_title", "anch_xowa_title", "img_id", "img_xowa_title", "img_xowa_image", "img_src", "img_w", "img_h", "img_cls", "img_alt")
	, img_wo_anch_fmtr = Bry_fmtr.new_
	( "<img~{img_id}~{img_xowa_title}~{img_xowa_image}~{img_src}~{img_w}~{img_h}~{img_cls}~{img_alt}~{img_imap_usemap}>"
	, "img_id", "img_xowa_title", "img_xowa_image", "img_src", "img_w", "img_h", "img_cls", "img_alt", "img_imap_usemap")
	, vid_fmtr = Bry_fmtr.new_
	( "</div>\n<div><a~{vid_play_id} href=\"\"~{xowa_title} class=\"xowa_media_play\" style=\"width:218px;max-width:220px;\" alt=\"Play sound\"></a></div>\n</div>"
	, "vid_play_id", "xowa_title", "xowa_root_dir", "width_neg_2", "width")	// NOTE: default to href='file:///'; will be filled in dynamically
	;
}
