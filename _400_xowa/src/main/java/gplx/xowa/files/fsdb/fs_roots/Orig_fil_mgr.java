package gplx.xowa.files.fsdb.fs_roots; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*; import gplx.xowa.files.fsdb.*;
class Orig_fil_mgr {
	private Ordered_hash hash = Ordered_hash_.New_bry();
	public boolean Has(byte[] lnki_ttl) {return hash.Has(lnki_ttl);}
	public Orig_fil_itm Get_by_ttl(byte[] lnki_ttl) {return (Orig_fil_itm)hash.Get_by(lnki_ttl);}
	public void Add(Orig_fil_itm fil) {hash.Add(fil.Fil_name(), fil);}
}
