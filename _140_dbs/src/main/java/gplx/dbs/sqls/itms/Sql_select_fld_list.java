package gplx.dbs.sqls.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.sqls.*;
public class Sql_select_fld_list {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int Len()						{return hash.Count();}
	public Sql_select_fld_list Clear()		{hash.Clear();return this;}
	public Sql_select_fld Get_at(int i)		{return (Sql_select_fld)hash.Get_at(i);}
	public void Add(Sql_select_fld fld)		{hash.Add(fld.Alias, fld);}
}
