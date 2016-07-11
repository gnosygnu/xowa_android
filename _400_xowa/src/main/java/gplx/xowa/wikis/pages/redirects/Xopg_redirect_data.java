package gplx.xowa.wikis.pages.redirects; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_redirect_data {
	private final    List_adp itms = List_adp_.New();
	public int					Itms__len()							{return itms.Len();}
	public byte[]				Itms__get_at_0th_or_null()			{return itms.Len() == 0 ? null : this.Itms__get_at(0).Wikitext();}
	public Xopg_redirect_itm	Itms__get_at_nth_or_null()			{return itms.Len() == 0 ? null : (Xopg_redirect_itm)itms.Get_at(itms.Len() - 1);}
	public Xopg_redirect_itm	Itms__get_at(int i)					{return (Xopg_redirect_itm)itms.Get_at(i);}
	public void					Itms__add__article(Xoa_url url, Xoa_ttl ttl, byte[] wikitext)	{Itms__add(url, ttl, wikitext);}
	public void					Itms__add__special(Xoa_url url, Xoa_ttl ttl)					{Itms__add(url, ttl, null);}
	private void				Itms__add(Xoa_url url, Xoa_ttl ttl, byte[] wikitext) {
		Xopg_redirect_itm itm = new Xopg_redirect_itm(url, ttl, wikitext);
		itms.Add(itm);
	}
	public void Clear() {
		itms.Clear();
	}
}
