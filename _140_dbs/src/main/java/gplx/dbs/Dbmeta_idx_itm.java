package gplx.dbs; import gplx.*;
import gplx.dbs.metas.*; import gplx.dbs.sqls.*; import gplx.dbs.sqls.wtrs.*;
public class Dbmeta_idx_itm {
	public Dbmeta_idx_itm(boolean unique, String tbl, String name, Dbmeta_idx_fld[] flds) {
		this.tbl = tbl; this.name = name; this.unique = unique; this.Flds = flds;
	}
	public String Tbl() {return tbl;} private final String tbl;
	public String Name() {return name;} private final String name;		
	public boolean Unique() {return unique;} private final boolean unique;
	public final Dbmeta_idx_fld[] Flds;
	public String To_sql_create(Sql_qry_wtr sql_wtr) {return sql_wtr.Schema_wtr().Bld_create_idx(this);}
	public boolean Eq(Dbmeta_idx_itm comp) {
		return String_.Eq(name, comp.name)
			&& unique == comp.unique
			&& tbl == comp.tbl
			&& Dbmeta_idx_fld.Ary_eq(Flds, comp.Flds);
	}
	public static Dbmeta_idx_itm new_unique_by_name			(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.Y, tbl, name, To_fld_ary(flds));}
	public static Dbmeta_idx_itm new_normal_by_name			(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.N, tbl, name, To_fld_ary(flds));}
	public static Dbmeta_idx_itm new_unique_by_tbl			(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.Y, tbl, Bld_idx_name(tbl, name), To_fld_ary(flds));}
	public static Dbmeta_idx_itm new_normal_by_tbl			(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.N, tbl, Bld_idx_name(tbl, name), To_fld_ary(flds));}
	public static Dbmeta_idx_itm new_unique_by_tbl_wo_null	(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.Y, tbl, Bld_idx_name(tbl, name), To_fld_ary(flds));}
	public static Dbmeta_idx_itm new_normal_by_tbl_wo_null	(String tbl, String name, String... flds)	{return new Dbmeta_idx_itm(Bool_.N, tbl, Bld_idx_name(tbl, name), To_fld_ary(flds));}
	public static String Bld_idx_name(String tbl, String suffix) {return String_.Concat(tbl, "__", suffix);}
	public static final Dbmeta_idx_itm[] Ary_empty = new Dbmeta_idx_itm[0];
	public static Dbmeta_idx_fld[] To_fld_ary(String[] ary) {
		int len = ary.length;
		Dbmeta_idx_fld[] rv = new Dbmeta_idx_fld[len];
		int order = -1;
		for (int i = 0; i < len; ++i) {
			String itm = ary[i]; if (itm == null) continue;
			rv[i] = new Dbmeta_idx_fld(++order, itm, Dbmeta_idx_fld.Sort_tid__none);
		}
		return rv;
	}
}
