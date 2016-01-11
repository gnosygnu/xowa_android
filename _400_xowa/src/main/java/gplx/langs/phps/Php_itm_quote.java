package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_itm_quote implements Php_itm, Php_itm_sub, Php_key {
	public Php_itm_quote(byte[] v) {this.val_obj_bry = v;}	// NOTE: use Php_text_itm_parser to parse \" and related
	public byte Itm_tid() {return Php_itm_.Tid_quote;}
	public byte[] Val_obj_bry() {return val_obj_bry;} private byte[] val_obj_bry;
}
