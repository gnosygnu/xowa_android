package gplx.xowa.langs.grammars; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public interface Xol_grammar {
	boolean Grammar_eval(Bry_bfr bfr, Xol_lang_itm lang, byte[] word, byte[] type);
}
