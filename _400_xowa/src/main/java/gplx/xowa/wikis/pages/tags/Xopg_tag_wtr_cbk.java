package gplx.xowa.wikis.pages.tags; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public interface Xopg_tag_wtr_cbk {
	void Write_tag(Bry_bfr bfr, Xopg_tag_itm itm);
}
class Xopg_tag_wtr_cbk__basic implements Xopg_tag_wtr_cbk {
	public void Write_tag(Bry_bfr bfr, Xopg_tag_itm itm) {itm.To_html(bfr);}
}
