package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
public class Xowd_search_word_row {
	public Xowd_search_word_row(int id, byte[] text, int page_count) {this.id = id; this.text = text; this.page_count = page_count;}
	public int Id() {return id;} private final int id;
	public byte[] Text() {return text;} private final byte[] text;
	public int Page_count() {return page_count;} private final int page_count;
        public static final Xowd_search_word_row Null = null;
}
