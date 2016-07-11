package gplx.xowa.wikis.pages.hdumps; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
import gplx.xowa.htmls.tocs.*;
public class Xopg_hdump_data {
	public List_adp					Imgs()		{return imgs;}		private final    List_adp imgs = List_adp_.New();
	public Xoh_toc_wtr				Toc_wtr()	{return toc_wtr;}	private final    Xoh_toc_wtr toc_wtr = new Xoh_toc_wtr(); 
	public void Clear() {
		imgs.Clear();
		toc_wtr.Clear();
	}
}
