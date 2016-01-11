package gplx.xowa.files.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public interface Xog_js_wkr {
	void Html_img_update		(String uid, String src, int w, int h);
	void Html_redlink			(String html_uid);

	void Html_atr_set			(String uid, String key, String val);
	void Html_elem_replace_html	(String uid, String html);
	void Html_elem_append_above	(String uid, String html);
}
