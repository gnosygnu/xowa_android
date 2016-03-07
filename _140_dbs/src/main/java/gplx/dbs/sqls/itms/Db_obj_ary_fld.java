package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Db_obj_ary_fld {
	public Db_obj_ary_fld(String name, int type_tid) {this.name = name; this.type_tid = type_tid;}
	public String Name() {return name;} public Db_obj_ary_fld Name_(String v) {name = v; return this;} private String name;
	public int Type_tid() {return type_tid;} public Db_obj_ary_fld Type_tid_(int v) {type_tid = v; return this;} private int type_tid;
}
