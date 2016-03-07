package gplx.dbs.qrys; import gplx.*; import gplx.dbs.*;
public class Db_arg {
	public Db_arg(String key, Object val, byte val_tid) {this.Key = key; this.Val = val; this.Val_tid = val_tid;}
	public final String Key;
	public final Object Val;
	public final byte Val_tid;
}
