package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
public class Xows_db_row {
	public Xows_db_row(byte[] wiki_domain, Xoa_ttl page_ttl, int page_id, int page_len) {
		this.wiki_domain = wiki_domain;
		this.page_ttl = page_ttl;
		this.page_id = page_id;
		this.page_len = page_len;
		this.key = Bry_.Add(wiki_domain, Byte_ascii.Pipe_bry, page_ttl.Full_db());
	}
	public byte[]		Key()				{return key;}				private final byte[] key;
	public byte[]		Wiki_domain()		{return wiki_domain;}		private final byte[] wiki_domain;
	public Xoa_ttl		Page_ttl()			{return page_ttl;}			private final Xoa_ttl page_ttl;
	public int			Page_id()			{return page_id;}			private final int page_id;
	public int			Page_len()			{return page_len;}			private final int page_len;
	public static Xows_db_row[] Ary(Xows_db_row... v) {return v;}
}
class Xows_db_row_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Xows_db_row lhs = (Xows_db_row)lhsObj;
		Xows_db_row rhs = (Xows_db_row)rhsObj;
		return -Int_.Compare(lhs.Page_len(), rhs.Page_len());
	}
        public static final Xows_db_row_sorter Page_len_dsc = new Xows_db_row_sorter(); Xows_db_row_sorter() {}
}
