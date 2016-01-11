package gplx.gfui; import gplx.*;
public interface Gxw_tab_mgr extends GxwElem {
	ColorAdp Btns_selected_color(); void Btns_selected_color_(ColorAdp v);
	ColorAdp Btns_unselected_color(); void Btns_unselected_color_(ColorAdp v);
	int Btns_height(); void Btns_height_(int v);
	boolean Btns_place_on_top(); void Btns_place_on_top_(boolean v);
	boolean Btns_curved(); void Btns_curved_(boolean v);
	boolean Btns_close_visible(); void Btns_close_visible_(boolean v);
	boolean Btns_unselected_close_visible(); void Btns_unselected_close_visible_(boolean v);
	Gxw_tab_itm Tabs_add(Gfui_tab_itm_data tab_data);
	void Tabs_select_by_idx(int i);
	void Tabs_close_by_idx(int i);
	void Tabs_switch(int src, int trg);
}
