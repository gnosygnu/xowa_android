package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.langs.jsons.*;
public class Wdata_alias_itm implements Wdata_lang_sortable {
	public Wdata_alias_itm(byte[] lang, byte[][] vals) {this.lang = lang; this.vals = vals;} 
	public byte[] Lang() {return lang;} private byte[] lang;
	public byte[][] Vals() {return vals;} private byte[][] vals;
	public byte[] Lang_code() {return lang;}
	public int Lang_sort() {return lang_sort;} public void Lang_sort_(int v) {lang_sort = v;} private int lang_sort = Wdata_lang_sorter.Sort_null;
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", String_.new_u8(lang), String_.Concat_with_str("~", String_.Ary(vals)));
	}
}
