package gplx.dbs; import gplx.*;
public class DbTstRow {
	public int Idx() {return idx;} public DbTstRow Idx_(int val) {idx = val; return this;} int idx = -1;
	public DbTstDat[] Dat() {return dat;} DbTstDat[] dat = null;
	public static DbTstRow vals_only_(Object... ary) {
		DbTstRow rv = new DbTstRow();
		int len = Array_.Len(ary);
		rv.dat = new DbTstDat[len];
		for (int i = 0; i < len; i++)
			rv.dat[i] = DbTstDat.new_().Val_(ary[i]);
		return rv;
	}
	public static DbTstRow new_() {return new DbTstRow();} DbTstRow() {}
}
