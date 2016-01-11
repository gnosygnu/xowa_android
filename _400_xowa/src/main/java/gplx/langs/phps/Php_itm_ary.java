package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_itm_ary implements Php_itm, Php_itm_sub {
	public Php_itm_ary() {}
	public byte Itm_tid() {return Php_itm_.Tid_ary;}
	public byte[] Val_obj_bry() {return null;}
	public int Subs_len() {return subs_len;} private int subs_len;
	public Php_itm_sub Subs_get(int i) {return ary[i];}
	public Php_itm_sub Subs_pop() {return ary[--subs_len];}
	public void Subs_add(Php_itm_sub v) {
		int new_len = subs_len + 1;
		if (new_len > subs_max) {	// ary too small >>> expand
			subs_max = new_len * 2;
			Php_itm_sub[] new_ary = new Php_itm_sub[subs_max];
			Array_.Copy_to(ary, 0, new_ary, 0, subs_len);
			ary = new_ary;
		}
		ary[subs_len] = v;
		subs_len = new_len;
	}	Php_itm_sub[] ary = Php_itm_sub_.Ary_empty; int subs_max;
}
