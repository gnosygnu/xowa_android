package gplx.xowa.wikis.pages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_db_html {
	public byte[]		Html_bry()			{return html_bry;}	private byte[] html_bry = Bry_.Empty;	// NOTE: if null, will cause NullPointer exception on Special pages like Special:XowaDownloadCentral; DATE:2016-07-05
	public void			Html_bry_(byte[] v) {this.html_bry = v;}
	public int			Zip_tid()			{return zip_tid;}	private int zip_tid;
	public int			Hzip_tid()			{return hzip_tid;}	private int hzip_tid;
	public void			Zip_tids_(int zip_tid, int hzip_tid) {this.zip_tid = zip_tid; this.hzip_tid = hzip_tid;}
	public void Clear() {
		html_bry = null; zip_tid = -1; hzip_tid = -1;
	}
}
