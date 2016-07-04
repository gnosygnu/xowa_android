package gplx.xowa.guis.cbks.js; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*; import gplx.xowa.guis.cbks.*;
public interface Xog_js_wkr {
	void Html_img_update				(String uid, String src, int w, int h);
	void Html_redlink					(String html_uid);

	void Html_atr_set					(String uid, String key, String val);
	void Html_elem_replace_html			(String uid, String html);
	void Html_elem_append_above			(String uid, String html);
	void Html_elem_delete				(String elem_id);
	void Html_gallery_packed_exec		();
	void Html_popups_bind_hover_to_doc	();
}
