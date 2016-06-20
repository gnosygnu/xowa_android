package gplx.xowa.files.gui; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public class Xog_js_wkr__log implements Xog_js_wkr {
	private final    List_adp log_list = List_adp_.New();
	public void Html_img_update			(String uid, String src, int w, int h)	{log_list.Add(Object_.Ary(Proc_img_update, uid, src, w, h));}
	public void Html_atr_set			(String uid, String key, String val)	{log_list.Add(Object_.Ary(Proc_atr_set, uid, key, val));}
	public void Html_redlink			(String uid)							{log_list.Add(Object_.Ary(Proc_redlink, uid));}
	public void Html_elem_replace_html	(String uid, String html)				{log_list.Add(Object_.Ary(Proc_replace_html, uid, html));}
	public void Html_elem_append_above	(String uid, String html)				{log_list.Add(Object_.Ary(Proc_append_above, uid, html));}
	public void Html_elem_delete		(String elem_id)						{log_list.Add(Object_.Ary(Proc_delete, elem_id));}
	public void Html_gallery_packed_exec()										{log_list.Add(Object_.Ary(Proc_gallery_packed_exec));}
	public void Html_popups_bind_hover_to_doc()									{log_list.Add(Object_.Ary(Proc_popups_bind_hover_to_doc));}

	public void Log__clear()			{log_list.Clear();}
	public int Log__len()				{return log_list.Count();}
	public Object[] Log__get_at(int i)	{return (Object[])log_list.Get_at(i);}

	public static final String
	  Proc_img_update = "img_update", Proc_atr_set = "atr_set", Proc_redlink = "redlink", Proc_replace_html = "replace_html"
	, Proc_append_above = "append_above", Proc_delete = "delete", Proc_gallery_packed_exec = "gallery_packed_exec", Proc_popups_bind_hover_to_doc = "popups_bind_hover_to_doc"
	;
}
