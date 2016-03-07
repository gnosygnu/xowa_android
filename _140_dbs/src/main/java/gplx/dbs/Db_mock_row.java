package gplx.dbs; import gplx.*;
public class Db_mock_row {
	public int Idx() {return idx;} public Db_mock_row Idx_(int val) {idx = val; return this;} int idx = -1;
	public Db_mock_cell[] Dat() {return dat;} Db_mock_cell[] dat = null;
	public static Db_mock_row vals_only_(Object... ary) {
		Db_mock_row rv = new Db_mock_row();
		int len = Array_.Len(ary);
		rv.dat = new Db_mock_cell[len];
		for (int i = 0; i < len; i++)
			rv.dat[i] = Db_mock_cell.new_().Val_(ary[i]);
		return rv;
	}
	public static Db_mock_row new_() {return new Db_mock_row();} Db_mock_row() {}
}
