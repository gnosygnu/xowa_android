package gplx.xowa.langs.plurals; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public interface Xol_plural {
	byte[] Plural_eval(Xol_lang_itm lang, int count, byte[][] words);
}
