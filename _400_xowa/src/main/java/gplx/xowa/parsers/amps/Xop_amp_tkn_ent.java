package gplx.xowa.parsers.amps; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.langs.htmls.entitys.*;
public class Xop_amp_tkn_ent extends Xop_tkn_itm_base {
	private Gfh_entity_itm html_ref_itm;
	public Xop_amp_tkn_ent(int bgn, int end, Gfh_entity_itm html_ref_itm) {
		this.html_ref_itm = html_ref_itm;
		this.Tkn_ini_pos(false, bgn, end);
	}
	@Override public byte Tkn_tid()			{return Xop_tkn_itm_.Tid_html_ref;}
	public int Char_int()					{return html_ref_itm.Char_int();}
	public byte[] Xml_name_bry()			{return html_ref_itm.Xml_name_bry();}
	public boolean Itm_is_custom()				{return html_ref_itm.Tid() == Gfh_entity_itm.Tid_name_xowa;}
	public void Print_ncr(Bry_bfr bfr)		{html_ref_itm.Print_ncr(bfr);}
	public void Print_literal(Bry_bfr bfr)	{html_ref_itm.Print_literal(bfr);}
}
