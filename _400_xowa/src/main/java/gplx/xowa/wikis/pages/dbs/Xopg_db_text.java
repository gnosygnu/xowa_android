package gplx.xowa.wikis.pages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_db_text {
	public byte[]		Text_bry()	{return text_bry;}	private byte[] text_bry = Bry_.Empty;	// NOTE: if null, will cause NullPointer exception on Special pages like Special:XowaDownloadCentral; DATE:2016-07-05
	public void			Text_bry_(byte[] v) {this.text_bry = v;}
}
