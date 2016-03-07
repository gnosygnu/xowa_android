package gplx.dbs; import gplx.*;
public class Db_mock_cell {
	public int Idx() {return idx;} public Db_mock_cell Idx_(int val) {idx = val; return this;} int idx = -1;
	public String Fld() {return fld;} public Db_mock_cell Fld_(String v) {fld = v; return this;} private String fld = null;
	public Object Val() {return val;} public Db_mock_cell Val_(Object v) {val = v; return this;} Object val = null;
	public static Db_mock_cell new_() {return new Db_mock_cell();} Db_mock_cell() {}
}
