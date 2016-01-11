package gplx.xowa.guis.bnds; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
public class Xog_bnd_box {
	private Ordered_hash bnds = Ordered_hash_.New();
	public Xog_bnd_box(int tid, String key) {this.tid = tid; this.key = key;}
	public int Tid() {return tid;} private int tid;
	public String Key() {return key;} private String key;
	public int Len() {return bnds.Count();}
	public void Add(Xog_bnd_itm itm) {bnds.Add_if_dupe_use_nth(itm.Key(), itm);}	// Add_if_dupe_use_nth, else Xou_user_tst.Run fails; DATE:2014-05-15
	public void Del(String key) {bnds.Del(key);}
	public Xog_bnd_itm Get_at(int i) {return (Xog_bnd_itm)bnds.Get_at(i);}
	public Xog_bnd_itm Get(String key) {return (Xog_bnd_itm)bnds.Get_by(key);}
}
