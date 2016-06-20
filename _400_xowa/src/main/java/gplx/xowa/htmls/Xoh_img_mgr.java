package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
import gplx.xowa.files.*;
public class Xoh_img_mgr {
	private final    List_adp list = List_adp_.New();
	private int uid_nxt = -1;
	public void Clear() {
		this.uid_nxt = -1;
		list.Clear();
	}
	public int Len() {return list.Count();}
	public Xof_fsdb_itm Get_at(int i) {return (Xof_fsdb_itm)list.Get_at(i);}
	public Xof_fsdb_itm Make_img() {
		Xof_fsdb_itm itm = new Xof_fsdb_itm();
		itm.Init_at_hdoc(++uid_nxt, Xof_html_elem.Tid_img);
		list.Add(itm);
		return itm;
	}
	public void To_bfr(Bry_bfr bfr) {
		int len = this.Len();
		for (int i = 0; i < len; ++i) {
			Xof_fsdb_itm itm = this.Get_at(i);
			itm.To_bfr(bfr);
		}
	}
	public static final String Str__html_uid = "xoimg_";
	public static final    byte[] Bry__html_uid = Bry_.new_a7(Str__html_uid);
}
