package gplx.xowa.apps.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.xowa.xtns.wbases.*;
public class Wdata_doc_cache {
	private Hash_adp_bry hash = Hash_adp_bry.cs();
	public void Add(byte[] qid, Wdata_doc doc) {hash.Add(qid, doc);}
	public Wdata_doc Get_or_null(byte[] qid) {return (Wdata_doc)hash.Get_by_bry(qid);}
	public void Free_mem_all() {this.Clear();}
	public void Clear() {hash.Clear();}
}
