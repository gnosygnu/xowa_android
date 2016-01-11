package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
public class Db_arg {
	public Db_arg(String key, Object val) {this.key = key; this.val = val;}
	public String Key() {return key;} private String key;
	public Object Val() {return val;} public void Val_(Object v) {this.val = v;} private Object val;
	public byte Val_tid() {return val_tid;} public Db_arg Val_tid_(byte v) {val_tid = v; return this;} private byte val_tid = Db_val_type.Tid_null;		
}
