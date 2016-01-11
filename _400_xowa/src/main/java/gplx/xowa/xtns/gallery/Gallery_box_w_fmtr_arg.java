package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.core.makes.*;
public interface Gallery_box_w_fmtr_arg extends gplx.core.brys.Bfr_arg {
	Gallery_box_w_fmtr_arg Init(int uid, int width);
}
class Gallery_box_w_fmtr_arg__basic implements gplx.core.brys.Bfr_arg, Gallery_box_w_fmtr_arg {
	private int width;
	public Gallery_box_w_fmtr_arg Init(int uid, int width) {this.width = width; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(Style_bgn);
		bfr.Add_int_variable(width);
		bfr.Add(Style_end);
	}
	private static final byte[] Style_bgn = Bry_.new_a7("style=\"width:"), Style_end = Bry_.new_a7("px;\"");
}
//	class Gallery_box_w_fmtr_arg__hdump : gplx.core.brys.Bfr_arg, Gallery_box_w_fmtr_arg {
//		private int uid;
//		public Gallery_box_w_fmtr_arg Init(int uid, int width) {this.uid = uid; return this;}
//		public void Bfr_arg__add(Bry_bfr bfr) {
//			bfr.Add(Xoh_make_trie_.Bry__gallery_box_w);
//			bfr.Add_int_variable(uid);
//			bfr.Add_byte_apos();
//		}
//	}
interface Gallery_img_pad_fmtr_arg extends gplx.core.brys.Bfr_arg {
	Gallery_img_pad_fmtr_arg Init(int uid, int vpad);
}
class Gallery_img_pad_fmtr_arg__basic implements gplx.core.brys.Bfr_arg, Gallery_img_pad_fmtr_arg {
	private int vpad;
	public Gallery_img_pad_fmtr_arg Init(int uid, int vpad) {this.vpad = vpad; return this;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		bfr.Add(Style_bgn);
		bfr.Add_int_variable(vpad);
		bfr.Add(Style_end);
	}
	private static final byte[] Style_bgn = Bry_.new_a7("style=\"margin:"), Style_end = Bry_.new_a7("px auto;\"");
}
//	class Gallery_img_pad_fmtr_arg__hdump : gplx.core.brys.Bfr_arg, Gallery_img_pad_fmtr_arg {
//		private int uid;
//		public Gallery_img_pad_fmtr_arg Init(int uid, int width) {this.uid = uid; return this;}
//		public void Bfr_arg__add(Bry_bfr bfr) {
//			bfr.Add(Xoh_make_trie_.Bry__gallery_img_pad);
//			bfr.Add_int_variable(uid);
//			bfr.Add_byte_apos();
//		}
//	}
