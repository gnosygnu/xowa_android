package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.langs.htmls.*; import gplx.xowa.files.gui.*;
class Xows_ui_async__html implements Xows_ui_async {
	private final Xows_html_row html_row; private final Xog_js_wkr js_wkr;
	private final Xows_db_row[] rows; private final int rows_len;
	private final Bry_bfr bfr = Bry_bfr.new_(255);
	private final byte[] insert_new_key;
	private final Cancelable cxl;
	public Xows_ui_async__html(Cancelable cxl, Xows_html_row html_row, Xog_js_wkr js_wkr, int paging_len, byte[] wiki) {			
		this.cxl = cxl;
		this.html_row = html_row; this.js_wkr = js_wkr;
		this.rows = new Xows_db_row[paging_len];
		this.rows_len = paging_len;
		this.insert_new_key = Gen_insert_key(wiki);
	}
	public void Add(Xows_db_row new_row) {
		Xows_db_row last_row = rows[rows_len - 1];
		if (last_row != null) {
			if (Compare(new_row, last_row) == CompareAble_.MoreOrSame) return;	// new_row is < last_row; exit
		}
		int new_row_slot = Find_insert_slot(new_row); if (new_row_slot == -1) return;
		Xows_db_row insert_row = rows[new_row_slot];
		byte[] insert_key = insert_row == null ? insert_new_key : insert_row.Key();
		Displace(new_row_slot, new_row);
		html_row.Gen_html(bfr, new_row);
		String html_tbl = bfr.To_str_and_clear();
		if (cxl.Canceled()) return;
		js_wkr.Html_elem_append_above(Gfh_utl.Encode_id_as_str(insert_key), html_tbl);
		if (last_row != null) {
			if (cxl.Canceled()) return;
			js_wkr.Html_elem_replace_html(Gfh_utl.Encode_id_as_str(last_row.Key()), "");
		}
	}
	private int Find_insert_slot(Xows_db_row new_row) {
		for (int i = 0; i < rows_len; ++i) {
			Xows_db_row cur_row = rows[i];
			if (cur_row == null) return i;
			if (Compare(new_row, cur_row) == CompareAble_.Less) return i;
		}
		return -1;
	}
	private void Displace(int new_row_slot, Xows_db_row new_row) {
		for (int i = rows_len - 2; i >= new_row_slot; --i) {
			rows[i + 1] = rows[i];
		}
		rows[new_row_slot] = new_row;
	}
	private int Compare(Xows_db_row lhs, Xows_db_row rhs) {
		return -Int_.Compare(lhs.Page_len(), rhs.Page_len());
	}
	public static byte[] Gen_insert_key(byte[] wiki) {return Bry_.Add(Bry_insert_key, wiki);}
	private static final byte[] Bry_insert_key = Bry_.new_a7("xowa_insert_");
}
