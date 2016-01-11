package gplx.dbs; import gplx.*;
public class DbTstDat {
	public int Idx() {return idx;} public DbTstDat Idx_(int val) {idx = val; return this;} int idx = -1;
	public String Fld() {return fld;} public DbTstDat Fld_(String v) {fld = v; return this;} private String fld = null;
	public Object Val() {return val;} public DbTstDat Val_(Object v) {val = v; return this;} Object val = null;
	public static DbTstDat new_() {return new DbTstDat();} DbTstDat() {}
}
