package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
public class Imap_part_ {
	public static final byte 
	  Tid_invalid		= 0
	, Tid_img			= 1
	, Tid_desc			= 2
	, Tid_comment		= 3
	, Tid_dflt			= 4
	, Tid_shape_rect	= 5
	, Tid_shape_circle	= 6
	, Tid_shape_poly	= 7
	;
	public static final    byte[] 
	  Key_dflt				= Bry_.new_a7("default")
	, Key_shape_rect		= Bry_.new_a7("rect")
	, Key_shape_circle		= Bry_.new_a7("circle")
	, Key_shape_poly		= Bry_.new_a7("poly")
	;

	public static byte[] To_shape_key(byte v) {
		switch (v) {
			case Tid_shape_rect		: return Key_shape_rect;
			case Tid_shape_circle	: return Key_shape_circle;
			case Tid_shape_poly		: return Key_shape_poly;
			default					: throw Err_.new_unhandled(v);
		}
	}
}
