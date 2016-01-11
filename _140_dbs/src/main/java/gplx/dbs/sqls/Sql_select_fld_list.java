package gplx.dbs.sqls; import gplx.*; import gplx.dbs.*;
import gplx.core.strings.*; import gplx.core.gfo_ndes.*;
import gplx.dbs.engines.tdbs.*;
public class Sql_select_fld_list {
	public int Count() {return hash.Count();}
	public void Add(Sql_select_fld_base fld) {hash.Add(fld.Alias(), fld);}
	public Sql_select_fld_base Get_at(int i) {return (Sql_select_fld_base)hash.Get_at(i);}
	public Sql_select_fld_base FetchOrNull(String k) {return (Sql_select_fld_base)hash.Get_by(k);}
	public GfoFldList XtoGfoFldLst(TdbTable tbl) {
		GfoFldList rv = GfoFldList_.new_();
		for (int i = 0; i < this.Count(); i++) {
			Sql_select_fld_base selectFld = this.Get_at(i);
			GfoFld fld = tbl.Flds().FetchOrNull(selectFld.Fld());
			if (fld == null) throw Err_.new_wo_type("fld not found in tbl", "fldName", selectFld.Fld(), "tblName", tbl.Name(), "tblFlds", tbl.Flds().To_str());
			if (rv.Has(selectFld.Alias())) throw Err_.new_wo_type("alias is not unique", "fldName", selectFld.Fld(), "flds", rv.To_str());
			selectFld.GroupBy_type(fld);
			rv.Add(selectFld.Alias(), selectFld.ValType());
		}
		return rv;
	}
	public String[] To_str_ary() {
		int len = this.Count();
		String[] rv = new String[len];
		for (int i = 0; i < len; i++) {
			Sql_select_fld_base fld = this.Get_at(i);
			rv[i] = fld.Fld();
		}
		return rv;
	}
	public String To_str() {
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < this.Count(); i++) {
			Sql_select_fld_base fld = this.Get_at(i);
			sb.Add_fmt("{0},{1}|", fld.Fld(), fld.Alias());
		}
		return sb.To_str();
	}
	Ordered_hash hash = Ordered_hash_.New();
	public static Sql_select_fld_list new_() {return new Sql_select_fld_list();} Sql_select_fld_list() {}
}
