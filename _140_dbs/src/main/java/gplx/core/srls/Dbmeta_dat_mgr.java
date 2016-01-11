package gplx.core.srls; import gplx.*; import gplx.core.*;
import gplx.dbs.metas.*;
public class Dbmeta_dat_mgr {
	private final Ordered_hash hash = Ordered_hash_.New();
	public Dbmeta_dat_mgr Clear() {hash.Clear(); return this;}
	public int Len() {return hash.Count();}
	public Dbmeta_dat_itm Get_at(int idx) {return (Dbmeta_dat_itm)hash.Get_at(idx);}
	public Dbmeta_dat_mgr Add_int(String key, int val) {
		Dbmeta_dat_itm itm = new Dbmeta_dat_itm(Dbmeta_fld_tid.Tid__int, key, val);
		hash.Add(key, itm);
		return this;
	}
}
