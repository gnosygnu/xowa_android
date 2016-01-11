package gplx.xowa.htmls.core.makes.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.makes.*;
public class Xohd_img_itm__gallery_mgr implements Xohd_img_itm {
	public Xohd_img_itm__gallery_mgr(int uid, int box_max) {
		this.uid = uid;
		this.box_max = box_max;
	}
	public int Data_tid() {return Xohd_img_tid.Tid_gallery;}
	public void Data_write(Bry_bfr bfr) {
		bfr	.Add_int_variable(Xohd_img_tid.Tid_gallery).Add_byte_pipe()
			.Add_int_variable(uid).Add_byte_pipe()
			.Add_int_variable(box_max).Add_byte_pipe()
			;
		bfr.Add_byte_nl();
	}
	public String Data_print() {return Int_.To_str(box_max);}
	public int Uid() {return uid;} private int uid;
	public int Box_max() {return box_max;} private int box_max;
}
