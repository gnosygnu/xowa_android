package gplx.xowa.htmls.core.makes.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.makes.*;
import gplx.core.brys.*; import gplx.xowa.files.*;
public class Xohd_img_itm__gallery_itm extends Xohd_img_itm__base {
	@Override public int Img_tid() {return Xohd_img_itm__base.Tid_gallery;}
	@Override public byte Html_elem_tid() {return Xof_html_elem.Tid_img;}
	public int Box_w() {return box_w;} private int box_w;
	public int Img_w() {return img_w;} private int img_w;
	public int Img_pad() {return img_pad;} private int img_pad;
	public Xohd_img_itm__gallery_itm Data_init_gallery(int box_w, int img_w, int img_pad) {
		this.box_w = box_w;
		this.img_w = img_w;
		this.img_pad = img_pad;
		return this;
	}
	@Override public void Data_write_hook(Bry_bfr bfr) {
		bfr	.Add_int_variable(box_w).Add_byte_pipe()
			.Add_int_variable(img_w).Add_byte_pipe()
			.Add_int_variable(img_pad).Add_byte_pipe()
			;
	}
	@Override public void Data_parse(Bry_rdr_old rdr) {
		super.Data_parse(rdr);
		this.box_w = rdr.Read_int_to_pipe();
		this.img_w = rdr.Read_int_to_pipe();
		this.img_pad = rdr.Read_int_to_pipe();
	}
}
