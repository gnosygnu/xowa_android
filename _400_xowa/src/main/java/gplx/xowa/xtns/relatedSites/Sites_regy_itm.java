package gplx.xowa.xtns.relatedSites; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.wikis.xwikis.*;
public class Sites_regy_itm {
	public Sites_regy_itm(Xow_xwiki_itm xwiki_itm, Xoa_ttl ttl) {
		this.xwiki_itm = xwiki_itm; this.ttl = ttl;
		this.cls = Bry_.Lcase__all(Bry_.Copy(xwiki_itm.Key_bry()));
	}
	public Xow_xwiki_itm		Xwiki_itm() {return xwiki_itm;} private Xow_xwiki_itm xwiki_itm;
	public Xoa_ttl				Ttl()		{return ttl;} private Xoa_ttl ttl;
	public byte[]				Cls()		{return cls;} private byte[] cls;
}
