package gplx.xowa.langs.commas; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public interface Xol_comma_wkr {
	void Evt_lang_changed(Xol_lang_itm lang_itm);
	void Comma__itm(Bry_bfr bfr, int itm_idx, int itms_len);
	void Comma__end(Bry_bfr bfr);
}
