package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_rslt_row implements CompareAble {
	public Srch_rslt_row(byte[] key, byte[] wiki_bry, Xoa_ttl page_ttl, int page_ns, byte[] page_ttl_wo_ns, int page_id, int page_len, int page_score, int page_redirect_id, int rslt_stage) {
		this.Key = key;
		this.Wiki_bry = wiki_bry;
		this.Page_id = page_id;
		this.Page_ttl = page_ttl;
		this.Page_ns = page_ns;
		this.Page_ttl_wo_ns = page_ttl_wo_ns;
		this.Page_ttl_display = Xoa_ttl.Replace_unders(page_ttl.Full_db());
		this.Page_len = page_len;
		this.Page_redirect_id = page_redirect_id;
		this.Page_score = page_score;
		this.Rslt_stage = rslt_stage;
	}
	public final byte[]		Key;
	public final byte[]		Wiki_bry;
	public final int			Page_id;
	public final Xoa_ttl		Page_ttl;
	public final int			Page_ns;
	public final byte[]		Page_ttl_wo_ns;
	public byte[]				Page_ttl_display;
	public final int			Page_len;
	public final int			Page_redirect_id;
	public final int			Page_score;
	public final int			Rslt_stage;
	public int compareTo(Object obj) {Srch_rslt_row comp = (Srch_rslt_row)obj; return -Int_.Compare(Page_score, comp.Page_score);}

	public static byte[] Bld_key(byte[] wiki_domain, int page_id) {return Bry_.Add(wiki_domain, Byte_ascii.Pipe_bry, Int_.To_bry(page_id));}
	public static Srch_rslt_row New(byte[] wiki_bry, Xoa_ttl page_ttl, int page_id, int page_len, int page_score, int redirect_id, int rslt_stage) {
		return new Srch_rslt_row(Bld_key(wiki_bry, page_id), wiki_bry, page_ttl, page_ttl.Ns().Id(), page_ttl.Page_db(), page_id, page_len, page_score, redirect_id, rslt_stage);
	}
	public static final int Page_redirect_id_null = gplx.xowa.wikis.data.tbls.Xowd_page_itm.Redirect_id_null;
}
