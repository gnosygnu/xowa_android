package gplx.xowa.bldrs.css; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
class Xobc_download_itm {
	public Xobc_download_itm(int tid, String http_str, byte[] fsys_url) {this.tid = tid; this.http_str = http_str; this.fsys_url = fsys_url;}
	public int Tid() {return tid;} private final int tid;
	public String Http_str() {return http_str;} private final String http_str;
	public byte[] Fsys_url() {return fsys_url;} private final byte[] fsys_url;
	public static final int Tid_file = 1, Tid_html = 2, Tid_css = 3;
}
