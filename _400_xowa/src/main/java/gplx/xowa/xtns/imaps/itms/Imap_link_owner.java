package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
import gplx.xowa.parsers.*;
public interface Imap_link_owner {
	int				Link_tid(); void Link_tid_(int v, Xop_tkn_itm tkn);
	Xop_tkn_itm		Link_tkn();
	void			Link_href_(byte[] v);
	void			Link_text_(byte[] v);
}
