package gplx.dbs.stmts; import gplx.*; import gplx.dbs.*;
public class Db_stmt_arg {
	public Db_stmt_arg(boolean crt, int tid, String key, Object val) {this.Crt = crt; this.Tid = tid; this.Key = key; this.Val = val;}
	public final    boolean Crt;
	public final    int Tid;
	public final    String Key;
	public Object Val;
}
