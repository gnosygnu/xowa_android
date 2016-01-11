package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.criterias.*;
public class Db_obj_ary_crt implements gplx.core.criterias.Criteria {
	public byte Tid() {return Criteria_.Tid_db_obj_ary;}
	public Db_fld[] Flds() {return flds;} public Db_obj_ary_crt Flds_(Db_fld[] v) {this.flds = v; return this;} private Db_fld[] flds;
	public Object[][]		Vals() {return vals;} public void Vals_(Object[][] v) {this.vals = v;} private Object[][] vals;
	public void				Val_from_args(Hash_adp args) {throw Err_.new_unimplemented();}
	public void				Val_as_obj_(Object v) {throw Err_.new_unimplemented();}
	public boolean				Matches(Object obj) {return false;}
	public String			To_str() {return "";}
	public static Db_obj_ary_crt new_(Db_fld... flds) {return new Db_obj_ary_crt().Flds_(flds);}
	public static Db_obj_ary_crt new_by_type(byte type_tid, String... names) {
		int len = names.length;
		Db_fld[] flds = new Db_fld[len];
		for (int i = 0; i < len; i++)
			flds[i] = new Db_fld(names[i], type_tid);
		return new Db_obj_ary_crt().Flds_(flds);
	}
}
