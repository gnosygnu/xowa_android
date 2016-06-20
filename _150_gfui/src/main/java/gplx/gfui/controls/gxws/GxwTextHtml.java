package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface GxwTextHtml extends GxwTextMemo {
	Keyval[] Html_sel_atrs();
	void Html_enabled(boolean v);
	String Html_doc_html();
	void Html_css_set(String s);
}
