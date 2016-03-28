package gplx.xowa.addons.searchs.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public class Srch_word_hash {
	private final    Ordered_hash hash = Ordered_hash_.New_bry();
	public void Clear()					{hash.Clear();}
	public int Len()					{return hash.Count();}
	public boolean Has(byte[] word)		{return hash.Has(word);}
	public Srch_word_itm Get_at(int i)	{return (Srch_word_itm)hash.Get_at(i);}
	public void Add(byte[] word) {
		Srch_word_itm itm = (Srch_word_itm)hash.Get_by(word);
		if (itm == null) {
			itm = new Srch_word_itm(word);
			hash.Add(word, itm);
		}
		itm.Count_add_1_();
	}
}
