package gplx.dbs; import gplx.*;
public class Db_null implements gplx.core.brys.Bfr_arg {
	public void Bfr_arg__add(Bry_bfr bfr) {bfr.Add_str_a7(Null_str);}
	@Override public String toString() {return Null_str;}
	public static final String Null_str = "NULL";
        public static final Db_null Instance = new Db_null(); Db_null() {}
}
