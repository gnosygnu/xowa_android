package gplx.xowa.xtns.pagebanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.mustaches.*; import gplx.xowa.parsers.lnkis.*;
import gplx.xowa.files.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
public class Pgbnr_itm implements Mustache_doc_itm {
	public Xoa_ttl banner_ttl;
	public byte[] banner_img_src;
	public byte[] toc;
	public Xof_file_itm banner_file_itm;
	private byte[] banner_anch_title, banner_hdr_text, originx, banner_anch_href, srcset;
	private double data_pos_x, data_pos_y;
	private int maxWidth;
	private boolean bottomtoc, isHeadingOverrideEnabled;
	private byte[] file_ttl;
	private Pgbnr_icon[] icons;
	private byte[] img_id_atr, img_xottl_atr, img_xoimg_atr;
	public void Init_from_wtxt(Xoa_ttl banner_ttl, Xof_file_itm banner_file_itm, byte[] banner_anch_title, byte[] banner_hdr_text, boolean bottomtoc
		, byte[] toc, double data_pos_x, double data_pos_y, byte[] originx, Pgbnr_icon[] icons) {
		this.banner_ttl = banner_ttl; this.banner_file_itm = banner_file_itm;
		this.banner_anch_title = banner_anch_title; this.banner_hdr_text = banner_hdr_text; this.bottomtoc = bottomtoc; this.toc = toc; this.icons = icons;
		this.data_pos_x = data_pos_x; this.data_pos_y = data_pos_y; this.originx = originx;
		this.banner_img_src = banner_file_itm.Html_view_url().To_http_file_bry();
		this.file_ttl = banner_file_itm.Orig_ttl();
	}
	public void Init_from_html(int maxWidth, byte[] banner_anch_href, byte[] banner_img_src, byte[] srcset, boolean isHeadingOverrideEnabled, byte[] toc) {
		this.maxWidth = maxWidth;
		this.banner_anch_href = banner_anch_href;
		this.banner_img_src = banner_img_src;
		this.srcset = srcset;
		this.isHeadingOverrideEnabled = isHeadingOverrideEnabled;
		this.toc = toc;
	}
	public void Init_hdump(boolean mode_is_hdump) {
		if (mode_is_hdump) {
			this.img_id_atr = Bry_.Empty;
			Bry_bfr tmp_bfr = Bry_bfr_.New();
			this.img_xottl_atr = Gfh_atr_.Add_to_bry(tmp_bfr, Xoh_img_xoimg_data.Bry__data_xowa_title, file_ttl);
			this.img_xoimg_atr = Gfh_atr_.Add_to_bry(tmp_bfr, Xoh_img_xoimg_data.Bry__data_xowa_image, Xoh_img_xoimg_data.Bry__data_xowa_image__full);
		}
		else {
			this.img_id_atr = Bry__anch_atr_id;
			this.img_xottl_atr = this.img_xoimg_atr = Bry_.Empty;
		}
	}
	public boolean Mustache__write(String key, Mustache_bfr bfr) {
		if		(String_.Eq(key, "title"))							bfr.Add_bry(banner_hdr_text);
		else if	(String_.Eq(key, "tooltip"))						bfr.Add_bry(banner_anch_title);
		else if	(String_.Eq(key, "bannerfile"))						bfr.Add_bry(banner_anch_href);
		else if	(String_.Eq(key, "banner"))							bfr.Add_bry(banner_img_src);
		else if	(String_.Eq(key, "srcset"))							bfr.Add_bry(srcset == null ? Bry_.Empty : Bry_.Empty);
		else if	(String_.Eq(key, "originx"))						bfr.Add_bry(originx);
		else if	(String_.Eq(key, "data-pos-x"))						bfr.Add_double(data_pos_x);
		else if	(String_.Eq(key, "data-pos-y"))						bfr.Add_double(data_pos_y);
		else if	(String_.Eq(key, "maxWidth"))						bfr.Add_int(maxWidth);
		else if	(String_.Eq(key, "toc"))							bfr.Add_bry(toc);
		else if	(String_.Eq(key, "img_id_atr"))						bfr.Add_bry(img_id_atr);
		else if	(String_.Eq(key, "img_xottl"))						bfr.Add_bry(img_xottl_atr);
		else if	(String_.Eq(key, "img_xoimg"))						bfr.Add_bry(img_xoimg_atr);
		else if	(String_.Eq(key, "file_ttl"))						bfr.Add_bry(file_ttl);
		else														return false;
		return true;
	}
	public Mustache_doc_itm[] Mustache__subs(String key) {
		if		(String_.Eq(key, "icons"))							return icons;
		else if	(String_.Eq(key, "hasIcons"))						return Mustache_doc_itm_.Ary__bool(icons.length > 0);
		else if	(String_.Eq(key, "bottomtoc"))						return Mustache_doc_itm_.Ary__bool(bottomtoc);
		else if	(String_.Eq(key, "isHeadingOverrideEnabled"))		return Mustache_doc_itm_.Ary__bool(isHeadingOverrideEnabled);
		return Mustache_doc_itm_.Ary__empty;
	}
	public static final    byte[] Bry__anch_atr_id = Bry_.new_a7(" id=\"xoimg_pgbnr\"");
}
