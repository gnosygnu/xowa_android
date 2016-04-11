package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xopg_tag_mgr {
	private final    List_adp list = List_adp_.new_();
	public int Len() {return list.Len();}
	public Xopg_tag_itm Get_at(int i) {return (Xopg_tag_itm)list.Get_at(i);}
	public void Add(Xopg_tag_itm... ary) {for (Xopg_tag_itm itm : ary) list.Add(itm);}
	public void Copy(Xopg_tag_mgr src) {
		int len = src.Len();
		for (int i = 0; i < len; ++i)
			this.Add(src.Get_at(i));
	}
	public byte[] To_html(Bry_bfr bfr) {
		int len = this.Len();
		for (int i = 0; i < len; ++i) {
			Xopg_tag_itm tag = this.Get_at(i);
			tag.To_html(bfr);
			bfr.Add_byte_nl();
		}
		return bfr.To_bry_and_clear();
	}
}
