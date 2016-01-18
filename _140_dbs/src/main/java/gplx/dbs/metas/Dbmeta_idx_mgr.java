package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
public class Dbmeta_idx_mgr {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int				Len()					{return hash.Count();}
	public boolean				Has(String name)		{return hash.Has(name);}
	public Dbmeta_idx_itm	Get_at(int idx)			{return (Dbmeta_idx_itm)hash.Get_at(idx);}
	public Dbmeta_idx_itm	Get_by(String name)		{return (Dbmeta_idx_itm)hash.Get_by(name);}
	public void				Add(Dbmeta_idx_itm itm)	{hash.Add(itm.Name(), itm);}
	public void				Clear()					{hash.Clear();}
	public Dbmeta_idx_itm[]	To_ary()				{return (Dbmeta_idx_itm[])hash.To_ary(Dbmeta_idx_itm.class);}
}
