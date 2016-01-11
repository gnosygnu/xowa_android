package gplx.gfui; import gplx.*;
public interface GxwCheckListBox extends GxwElem {
	int Items_count();
	List_adp Items_getAll();
	List_adp Items_getChecked();

	void Items_add(Object obj, boolean v);
	void Items_reverse();
	boolean Items_getCheckedAt(int i);
	void Items_setCheckedAt(int i, boolean v);
	void Items_setAll(boolean v);

	void Items_clear();
}
