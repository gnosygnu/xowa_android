package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
public class Xowd_css_core_itm {
	public Xowd_css_core_itm(int id, String key, DateAdp updated_on) {
		this.id = id; this.key = key; this.updated_on = updated_on;
	}
	public int Id() {return id;} private final int id;
	public String Key() {return key;} private final String key;
	public DateAdp Updated_on() {return updated_on;} private final DateAdp updated_on;
}
