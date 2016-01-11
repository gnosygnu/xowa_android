package gplx.gfui; import gplx.*;
import gplx.core.lists.*; /*EnumerAble*/
public class GfuiListBox extends GfuiElemBase {
	@Override public GxwElem UnderElem_make(KeyValHash ctorArgs) {return GxwElemFactory_.Instance.listBox_();}
	@Override public void ctor_GfuiBox_base(KeyValHash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		this.listBox = (GxwListBox)UnderElem();
	}
	public void Items_Add(Object o) {listBox.Items_Add(o);}
	public void Items_Clear() {listBox.Items_Clear();}
	public Object Items_SelObj() {return listBox.Items_SelObj();}
	public void BindData(EnumerAble enumerable) {
		for (Object item : enumerable)
			this.listBox.Items_Add(item.toString());
	}
	public int Items_Count() {return listBox.Items_Count();}
	public int Items_SelIdx() {return listBox.Items_SelIdx();} public void Items_SelIdx_set(int v) {listBox.Items_SelIdx_set(v);}

	GxwListBox listBox;
	public static GfuiListBox new_() {
		GfuiListBox rv = new GfuiListBox();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_true_());
		return rv;
	}
}

