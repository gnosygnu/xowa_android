package gplx.xowa.files.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public class Xof_img_mode_ {
	public static final byte 
	  Tid__orig = 0
	, Tid__thumb = 1
	;
	public static byte By_bool(boolean is_thumb) {return is_thumb ? Tid__thumb : Tid__orig;}
	public static final    byte[][] Names_ary = new byte[][] {Bry_.new_a7("orig"), Bry_.new_a7("thumb")};
}
