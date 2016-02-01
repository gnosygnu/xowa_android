package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
public class Dbmeta_fld_mgr {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int				Len()					{return hash.Count();}
	public void				Clear()					{hash.Clear();}
	public void				Add(Dbmeta_fld_itm itm)	{hash.Add(itm.Name(), itm);}
	public boolean				Has(String name)		{return hash.Has(name);}
	public Dbmeta_fld_itm	Get_at(int idx)			{return (Dbmeta_fld_itm)hash.Get_at(idx);}
	public Dbmeta_fld_itm	Get_by(String name)		{return (Dbmeta_fld_itm)hash.Get_by(name);}
	public Dbmeta_fld_itm[]	To_ary()				{return hash.Count() == 0 ? Dbmeta_fld_itm.Ary_empty : (Dbmeta_fld_itm[])hash.To_ary(Dbmeta_fld_itm.class);}
}