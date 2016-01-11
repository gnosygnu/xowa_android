package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
public class Xowd_css_file_itm {
	public Xowd_css_file_itm(int css_id, String path, byte[] data) {this.css_id = css_id; this.path = path; this.data = data;}
	public int Css_id() {return css_id;} private final int css_id;
	public String Path() {return path;} private final String path;
	public byte[] Data() {return data;} private final byte[] data;
}
