package gplx.gfui; import gplx.*;
public interface Gxw_html extends GxwElem {
	void		Html_doc_html_load_by_mem(String html);
	void		Html_doc_html_load_by_url(Io_url path, String html);
	byte		Html_doc_html_load_tid(); void Html_doc_html_load_tid_(byte v);
	void		Html_js_enabled_(boolean v);
	String		Html_js_eval_proc_as_str	(String name, Object... args);
	boolean		Html_js_eval_proc_as_bool	(String name, Object... args);
	String		Html_js_eval_script			(String script);
	void		Html_js_cbks_add			(String js_func_name, GfoInvkAble invk);
	void		Html_invk_src_(GfoEvObj v);
	void		Html_dispose();
}
