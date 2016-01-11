package gplx.dbs; import gplx.*;
import gplx.dbs.metas.*; import gplx.dbs.sqls.*;
public class Dbmeta_tbl_itm {
	public String Name() {return name;} private String name;
	public Dbmeta_idx_mgr Idxs() {return idxs;} private final Dbmeta_idx_mgr idxs = new Dbmeta_idx_mgr();
	public Dbmeta_fld_mgr Flds() {return flds;} private final Dbmeta_fld_mgr flds = new Dbmeta_fld_mgr();
	public String To_sql_create() {return Db_sqlbldr__sqlite.Instance.Bld_create_tbl(this);}

	public static Dbmeta_tbl_itm New(String name, Dbmeta_fld_list flds, Dbmeta_idx_itm... idxs)	{return New(name, flds.To_fld_ary(), idxs);}
	public static Dbmeta_tbl_itm New(String name, Dbmeta_fld_itm... flds)							{return New(name, flds, Dbmeta_idx_itm.Ary_empty);}
	public static Dbmeta_tbl_itm New(String name, Dbmeta_fld_itm[] flds, Dbmeta_idx_itm... idxs) {
		Dbmeta_tbl_itm rv = new Dbmeta_tbl_itm();
		rv.name = name;
		if (flds != null) {
			int flds_len = flds.length;
			for (int i = 0; i < flds_len; ++i)
				rv.flds.Add(flds[i]);
		}
		if (idxs != null) {
			int idxs_len = idxs.length;
			for (int i = 0; i < idxs_len; ++i)
				rv.idxs.Add(idxs[i]);
		}
		return rv;
	}
}
