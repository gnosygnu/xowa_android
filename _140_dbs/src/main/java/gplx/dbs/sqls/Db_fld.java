package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
public class Db_fld {
	public Db_fld(String name, int type_tid) {this.name = name; this.type_tid = type_tid;}
	public String Name() {return name;} public Db_fld Name_(String v) {name = v; return this;} private String name;
	public int Type_tid() {return type_tid;} public Db_fld Type_tid_(int v) {type_tid = v; return this;} private int type_tid;
}
