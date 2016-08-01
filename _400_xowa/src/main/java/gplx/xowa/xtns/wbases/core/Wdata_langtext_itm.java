package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.langs.jsons.*;
public class Wdata_langtext_itm implements Wdata_lang_sortable {
	public Wdata_langtext_itm(byte[] lang, byte[] text) {this.lang = lang; this.text = text;}  
	public byte[] Lang() {return lang;} private final    byte[] lang;
	public byte[] Text() {return text;} private final    byte[] text;
	public byte[] Lang_code() {return lang;}
	public int Lang_sort() {return lang_sort;} public void Lang_sort_(int v) {lang_sort = v;} private int lang_sort = Wdata_lang_sorter.Sort_null;
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", String_.new_u8(lang), String_.new_u8(text));
	}
	public static byte[] Get_text_or_empty(Ordered_hash list, byte[][] langs) {
		Wdata_langtext_itm itm = Get_itm_or_null(list, langs);
		return itm == null ? Bry_.Empty : itm.Text();
	}
	public static Wdata_langtext_itm Get_itm_or_null(Ordered_hash list, byte[][] langs) {
		if (list == null) return null;
		int langs_len = langs.length;
		for (int i = 0; i < langs_len; ++i) {
			Object itm_obj = list.Get_by(langs[i]);
			if (itm_obj != null) return (Wdata_langtext_itm)itm_obj;
		}
		return null;
	}
}
