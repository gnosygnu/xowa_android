package gplx.xowa.langs.numbers; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_num_mgr_ {
	public static Xol_num_mgr new_by_lang_id(int lang_id) {
		switch (lang_id) {
			case Xol_lang_stub_.Id_be_tarask:
			case Xol_lang_stub_.Id_bg:
			case Xol_lang_stub_.Id_ru:
			case Xol_lang_stub_.Id_pl:
			case Xol_lang_stub_.Id_uk:
			case Xol_lang_stub_.Id_es:
			case Xol_lang_stub_.Id_et:
			case Xol_lang_stub_.Id_hy:
			case Xol_lang_stub_.Id_kaa:
			case Xol_lang_stub_.Id_kk_cyrl:
			case Xol_lang_stub_.Id_ksh:
			// case Xol_lang_stub_.Id_ku_ku:
										return new Xol_num_mgr__commafy_5();
			case Xol_lang_stub_.Id_km:
			case Xol_lang_stub_.Id_my:	return new Xol_num_mgr__noop();
			default:					return new Xol_num_mgr();
		}
	}
}
