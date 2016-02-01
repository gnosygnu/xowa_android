package gplx.xowa.xtns.pagebanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.mustaches.*;
public class Pgbnr_icon implements Mustache_doc_itm {
	private final byte[] name, title, href, html;
	public Pgbnr_icon(Bry_bfr tmp_bfr, byte[] name, byte[] title, byte[] href) {
		this.name = name; this.title = title; this.href = href;
		fmt.Bld_bfr_many(tmp_bfr, name, title);
		this.html = tmp_bfr.To_bry_and_clear();
	}
	public byte[] Get_prop(String key) {
		if		(String_.Eq(key, "name"))	return name;
		else if	(String_.Eq(key, "title"))	return title;
		else if	(String_.Eq(key, "url"))	return href;
		else if	(String_.Eq(key, "html"))	return html;
		return Mustache_doc_itm_.Null_val;
	}
	public Mustache_doc_itm[] Get_subs(String key) {return Mustache_doc_itm_.Ary__empty;}
	public static final Pgbnr_icon[] Ary_empty = new Pgbnr_icon[0];
	public static final Bry_fmt fmt = Bry_fmt.New
	( Bry_.New_u8_nl_apos("<span aria-disabled='false' title='~{title}' class='oo-ui-widget oo-ui-widget-enabled oo-ui-iconElement-icon oo-ui-icon-~{name} oo-ui-iconElement oo-ui-iconWidget'></span>")
	, "name", "title"
	);
}
