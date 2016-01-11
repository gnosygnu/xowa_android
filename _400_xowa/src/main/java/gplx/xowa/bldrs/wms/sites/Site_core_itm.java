package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
public class Site_core_itm {
	public Site_core_itm(byte[] site_abrv, byte[] site_domain, boolean json_completed, DateAdp json_date, byte[] json_text) {
		this.site_abrv = site_abrv;
		this.site_domain = site_domain;
		this.json_completed = json_completed;
		this.json_date = json_date;
		this.json_text = json_text;
	}
	public byte[] Site_abrv() {return site_abrv;} private final byte[] site_abrv;
	public byte[] Site_domain() {return site_domain;} private final byte[] site_domain;
	public boolean Json_completed() {return json_completed;} private final boolean json_completed;
	public DateAdp Json_date() {return json_date;} private final DateAdp json_date;
	public byte[] Json_text() {return json_text;} private byte[] json_text;
	public void Json_text_null_() {json_text = null;}
}
