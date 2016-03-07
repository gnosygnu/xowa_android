package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.parsers.*;
public class Srch_rslt_reg {	// per search request; EX: 'Earth* AND (History OR Future) AND -"middle earth"'
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	private final Srch2_text_parser parser = new Srch2_text_parser();
	public Srch2_crt_node	Ttl_matcher()	{return ttl_matcher;}	private Srch2_crt_node ttl_matcher;
	public Srch1_word_row[]	Words()			{return words;}			private Srch1_word_row[] words;		// words to search db; EX: earth, history, future but not "middle earth" (since not'ed)
	public boolean Done() {return done;} public void Done_y_() {done = true;} private boolean done;			// true if all words searched
	public int Total_count() {return hash.Count();}	// total # of results found; note that results may be out of order
	public int Itms_end() {return itms_end;} public void Itms_end_(int v) {itms_end = v;} private int itms_end;
	public void Init_by_db(Cancelable cxl, byte[] raw, gplx.xowa.addons.searchs.dbs.Srch_word_tbl word_tbl) {
		this.ttl_matcher	= new Srch2_crt_parser(parser).Parse(raw);
		this.words			= new Xows_db_matcher_bldr().Gather_words_from_db(cxl, ttl_matcher, word_tbl);
	}
	public void Clear() {hash.Clear();}
	public boolean Has(byte[] key)			{return hash.Has(key);}
	public Srch_rslt_itm Get_at(int i)	{return (Srch_rslt_itm)hash.Get_at(i);}
	public void Add(Srch_rslt_itm itm)	{hash.Add(itm.page_ttl.Full_db(), itm);}
	public void Get_between(Srch_rslt_list search_ui, int bgn, int end) {
		if (bgn >= hash.Count()) return;	// requested start not in cache; exit
		for (int i = bgn; i < end; ++i) {
			if (i >= hash.Count()) break;
			search_ui.Add((Srch_rslt_itm)hash.Get_at(i));
		}
	}
	public void Sort() {hash.Sort_by(Srch_rslt_itm_sorter.Page_len_dsc);}
}
