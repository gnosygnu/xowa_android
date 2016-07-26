package gplx.xowa.xtns.wdatas.hwtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.brys.fmtrs.*;
import gplx.langs.htmls.encoders.*;
import gplx.xowa.wikis.pages.wtxts.*;
class Wdata_fmtr__toc_div implements gplx.core.brys.Bfr_arg {
	private final    List_adp itms = List_adp_.New(); private final     Wdata_fmtr__toc_itm fmtr_itm = new Wdata_fmtr__toc_itm();
	private byte[] tbl_hdr; 
	public void Init_by_lang(Wdata_hwtr_msgs msgs)	{this.tbl_hdr = msgs.Toc_tbl_hdr();}
	public void Init_by_wdoc(Wdata_doc wdoc)		{itms.Clear();}
	public void Add(Wdata_toc_data toc_data)		{itms.Add(toc_data);}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int itms_len = itms.Count();
		if (itms_len <= Xopg_toc_mgr.Hdrs_min) return;
		fmtr_itm.Init_by_itm((Wdata_toc_data[])itms.To_ary_and_clear(Wdata_toc_data.class));
		fmtr.Bld_bfr_many(bfr, tbl_hdr, fmtr_itm);
	}
	private final    Bry_fmtr fmtr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "  <div id='toc' class='toc wb-toc'>"
	, "    <div id='toctitle'>"
	, "      <h2>~{hdr}</h2>"
	, "    </div>"
	, "    <ul>~{itms}"
	, "    </ul>"
	, "  </div>"
	), "hdr", "itms");
}
class Wdata_fmtr__toc_itm implements gplx.core.brys.Bfr_arg {
	private Wdata_toc_data[] ary;
	public void Init_by_itm(Wdata_toc_data[] v)				{this.ary = v;}
	public void Bfr_arg__add(Bry_bfr bfr) {
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Wdata_toc_data itm = ary[i];
			fmtr.Bld_bfr_many(bfr, i + List_adp_.Base1, itm.Href(), itm.Text());
		}
	}
	private final    Bry_fmtr fmtr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	, "      <li class='toclevel-1 tocsection-~{idx}'>"
	, "        <a href='#~{href}'>"
	, "          <span class='toctext'>~{text}</span>"
	, "        </a>"
	, "      </li>"
	), "idx", "href", "text");
}
