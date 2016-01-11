package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Scrib_lua_proc {
	public Scrib_lua_proc(String key, int id) {this.key = key; this.id = id;}
	public String Key() {return key;} private String key;
	public int Id() {return id;} private int id;
	@Override public String toString() {return key + ":" + id;}
	public static Scrib_lua_proc cast_or_null_(Object o) {	// NOTE: maxStringLength and maxPatternLength return d:INF; ignore these
		return Type_adp_.ClassOf_obj(o) == Scrib_lua_proc.class ? (Scrib_lua_proc)o : null;
	}
}
