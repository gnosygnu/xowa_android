package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_itm_int implements Php_itm, Php_itm_sub, Php_key {
	public Php_itm_int(int v) {this.val_obj_int = v;}
	public byte Itm_tid() {return Php_itm_.Tid_int;}
	public byte[] Val_obj_bry() {return Bry_.new_by_int(val_obj_int);}
	public int Val_obj_int() {return val_obj_int;} private int val_obj_int;
}
