package gplx.gfui; import gplx.*;
public interface GxwComboBox extends GxwElem {
    int SelBgn(); void SelBgn_set(int v);
    int SelLen(); void SelLen_set(int v);
    void Sel_(int bgn, int end);
    Object SelectedItm(); void SelectedItm_set(Object v);
    String[] DataSource_as_str_ary();
    void DataSource_set(Object... ary);
    String Text_fallback(); void Text_fallback_(String v);
    int List_sel_idx(); void List_sel_idx_(int v);
    boolean List_visible(); void List_visible_(boolean v);
    void Items__update(String[] ary);
    void Items__size_to_fit(int count);
    void Items__visible_rows_(int v);
    void Items__jump_len_(int v);
    void Margins_set(int left, int top, int right, int bot);
}
