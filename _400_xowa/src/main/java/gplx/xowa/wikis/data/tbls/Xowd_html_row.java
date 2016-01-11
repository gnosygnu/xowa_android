package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
public class Xowd_html_row {
	public Xowd_html_row(int page_id, int tid, byte[] data) {this.page_id = page_id; this.tid = tid; this.data = data;}
	public int		Page_id()	{return page_id;}	private final int page_id;
	public int		Tid()		{return tid;}		private final int tid;
	public byte[]	Data()		{return data;}		private final byte[] data;
	public static final int // SERIALIZED
	  Tid__html		= 0
	, Tid__img		= 1
	, Tid__redlink	= 2
	;
}
