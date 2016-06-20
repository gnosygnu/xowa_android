package gplx.xowa.addons.wikis.htmls.css.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.htmls.*; import gplx.xowa.addons.wikis.htmls.css.*;
public class Xowd_css_file_itm {
	public Xowd_css_file_itm(int css_id, String path, byte[] data) {this.css_id = css_id; this.path = path; this.data = data;}
	public int Css_id() {return css_id;} private final    int css_id;
	public String Path() {return path;} private final    String path;
	public byte[] Data() {return data;} private final    byte[] data;
}
