package gplx.langs.phps; import gplx.*; import gplx.langs.*;
public class Php_line_assign implements Php_line {
	public Php_key Key() {return key;} public Php_line_assign Key_(Php_key v) {this.key = v; return this;} Php_key key;
	public Php_key[] Key_subs() {return key_subs;} public Php_line_assign Key_subs_(Php_key[] v) {this.key_subs = v; return this;} Php_key[] key_subs = Php_key_.Ary_empty;
	public Php_itm Val() {return val;} public Php_line_assign Val_(Php_itm v) {this.val = v; return this;} Php_itm val;
}
