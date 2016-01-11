package gplx.xowa.langs.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public interface Xol_lang_transform {
	byte[] Kwd_transform(byte[] lang_key, byte[] kwd_key, byte[] kwd_word);
}
class Xol_lang_transform_null implements Xol_lang_transform {
	public byte[] Kwd_transform(byte[] lang_key, byte[] kwd_key, byte[] kwd_word) {return kwd_word;}
	public static final Xol_lang_transform_null Instance = new Xol_lang_transform_null(); Xol_lang_transform_null() {}
}
