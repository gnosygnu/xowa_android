package gplx.gfui; import gplx.*;
interface GxwTextHtml extends GxwTextMemo {
	KeyVal[] Html_sel_atrs();
	void Html_enabled(boolean v);
	String Html_doc_html();
	void Html_css_set(String s);
}
