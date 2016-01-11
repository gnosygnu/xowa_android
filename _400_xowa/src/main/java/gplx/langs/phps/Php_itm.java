package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public interface Php_itm {
	byte Itm_tid();
	byte[] Val_obj_bry();
}
class Php_itm_null implements Php_itm, Php_itm_sub {
	public byte Itm_tid() {return Php_itm_.Tid_null;}
	public byte[] Val_obj_bry() {return null;}
	public static final Php_itm_null Instance = new Php_itm_null(); Php_itm_null() {}
}
class Php_itm_bool_true implements Php_itm, Php_itm_sub {
	public byte Itm_tid() {return Php_itm_.Tid_bool_true;}
	public byte[] Val_obj_bry() {return Bry_true;}
	public static final Php_itm_bool_true Instance = new Php_itm_bool_true(); Php_itm_bool_true() {}
	private static final byte[] Bry_true = Bry_.new_a7("true");
}
class Php_itm_bool_false implements Php_itm, Php_itm_sub {
	public byte Itm_tid() {return Php_itm_.Tid_bool_false;}
	public byte[] Val_obj_bry() {return Bry_true;}
	public static final Php_itm_bool_false Instance = new Php_itm_bool_false(); Php_itm_bool_false() {}
	private static final byte[] Bry_true = Bry_.new_a7("false");
}
class Php_itm_var implements Php_itm, Php_itm_sub, Php_key {
	public Php_itm_var(byte[] v) {this.val_obj_bry = v;}
	public byte Itm_tid() {return Php_itm_.Tid_var;}
	public byte[] Val_obj_bry() {return val_obj_bry;} private byte[] val_obj_bry;	
}
