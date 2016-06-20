package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface Gxw_html extends GxwElem {
	void		Html_doc_html_load_by_mem(String html);
	void		Html_doc_html_load_by_url(Io_url path, String html);
	byte		Html_doc_html_load_tid(); void Html_doc_html_load_tid_(byte v);
	void		Html_js_enabled_(boolean v);
	String		Html_js_eval_proc_as_str	(String name, Object... args);
	boolean		Html_js_eval_proc_as_bool	(String name, Object... args);
	String		Html_js_eval_script			(String script);
	Object		Html_js_eval_script_as_obj	(String script);
	void		Html_js_cbks_add			(String js_func_name, Gfo_invk invk);
	String		Html_js_send_json			(String name, String data);
	void		Html_invk_src_(Gfo_evt_itm v);
	void		Html_dispose();
}
