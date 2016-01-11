package gplx.xowa.htmls.core.wkrs.imgs.atrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.imgs.*;
import gplx.core.btries.*;
import gplx.langs.htmls.*;
public class Xoh_anch_cls_ {
	public static final byte	// SERIALIZED
	  Tid__image			= 0	// EX: [[File:A.png]]			-> "<a class='image'>"
	, Tid__none				= 1	// EX: [[File:A.png|link=A]]	-> "<a class=''>"
	;
	public static final String 
	  Str__image			= "image"
	;
	public static final byte[] 
	  Bry__image			= Bry_.new_a7(Str__image)
	;
	private static final byte[]
	  Html__image			= Bry_.Add(Gfh_bldr_.Bry__cls__nth, Bry__image)
	;
	public static final Btrie_slim_mgr Trie = Btrie_slim_mgr.cs()
	.Add_bry_byte(Bry__image	, Tid__image)
	;
	public static byte[] To_html(int tid) {
		switch (tid) {
			case Xoh_anch_cls_.Tid__none:	return Bry_.Empty;
			case Xoh_anch_cls_.Tid__image:	return Html__image;
			default:						throw Err_.new_unhandled(tid);
		}
	}
	public static byte[] To_val(int tid) {
		switch (tid) {
			case Xoh_anch_cls_.Tid__none:	return Bry_.Empty;
			case Xoh_anch_cls_.Tid__image:	return Bry__image;
			default:						throw Err_.new_unhandled(tid);
		}
	}
}
