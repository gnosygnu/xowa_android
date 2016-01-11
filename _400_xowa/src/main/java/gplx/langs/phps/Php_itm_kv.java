package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_itm_kv implements Php_itm, Php_itm_sub {
	public byte Itm_tid() {return Php_itm_.Tid_kv;}
	public byte[] Val_obj_bry() {return null;}
	public Php_key Key() {return key;} public Php_itm_kv Key_(Php_key v) {this.key = v; return this;} Php_key key;
	public Php_itm Val() {return val;} public Php_itm_kv Val_(Php_itm v) {this.val = v; return this;} Php_itm val;
}
