package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.primitives.*; import gplx.dbs.cfgs.*;
import gplx.fsdb.meta.*;
import gplx.xowa.parsers.logs.*;
import gplx.xowa.htmls.core.htmls.*; import gplx.xowa.files.*; import gplx.xowa.files.fsdb.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
public class Gallery_xnde implements Xox_xnde, Mwh_atr_itm_owner1 {
	private Gallery_xtn_mgr xtn_mgr;
	public byte Mode()						{return mode;} private byte mode;
	public int Itm_w()						{return itm_w;} private int itm_w = Null;
	public int Itm_h()						{return itm_h;} private int itm_h = Null;
	public int Itms_per_row()				{return itms_per_row;} private int itms_per_row = Null;
	public boolean Show_filename()				{return show_filename;} private boolean show_filename = false;
	public byte[] Atr_caption()				{return atr_caption;} private byte[] atr_caption = Bry_.Empty;
	public byte[] Atr_style()				{return atr_style;} private byte[] atr_style = Bry_.Empty;
	public byte[] Atr_cls()					{return atr_cls;} private byte[] atr_cls = Bry_.Empty;
	public List_adp Atrs_other()				{return atrs_other;} private List_adp atrs_other;
	public int Itm_w_or_default()			{return itm_w == Null ? Default : itm_w;}
	public int Itm_h_or_default()			{return itm_h == Null ? Default : itm_h;}
	public int Itms_len()					{return itms.Count();} private List_adp itms = List_adp_.New();
	public Gallery_itm Itms_get_at(int i)	{return (Gallery_itm)itms.Get_at(i);}
	public Gallery_mgr_base Gallery_mgr()	{return gallery_mgr;} private Gallery_mgr_base gallery_mgr;
	private boolean html_wtr_v1 = false;
	public void Xatr__set(Xowe_wiki wiki, byte[] src, Mwh_atr_itm xatr, Object xatr_id_obj) {
		if (xatr_id_obj != null) {
			Byte_obj_val xatr_id = (Byte_obj_val)xatr_id_obj;
			switch (xatr_id.Val()) {
				case Gallery_xnde_atrs.Mode_tid:			mode = Gallery_mgr_base_.Get_or_traditional(xatr.Val_as_bry()); break;
				case Gallery_xnde_atrs.Perrow_tid:			itms_per_row = xatr.Val_as_int_or(Null); break;
				case Gallery_xnde_atrs.Widths_tid:			itm_w = xatr.Val_as_int_or(Null); break;
				case Gallery_xnde_atrs.Heights_tid:			itm_h = xatr.Val_as_int_or(Null); break;
				case Gallery_xnde_atrs.Showfilename_tid:	show_filename = xatr.Val_as_bool(); break;
				case Gallery_xnde_atrs.Caption_tid:			if (xatr.Key_exists()) atr_caption = xatr.Val_as_bry(); break;	// NOTE: do not create caption for key only; EX:<gallery caption=> PAGE:fr.w:Chronologie_du_si�ge_de_Paris_(1870); DATE:2014-08-15
				case Gallery_xnde_atrs.Style_tid:			atr_style = xatr.Val_as_bry(); break;
				case Gallery_xnde_atrs.Class_tid:			atr_cls = xatr.Val_as_bry(); break;
			}
		}
		else {
			if (atrs_other == null) atrs_other = List_adp_.New();
			atrs_other.Add(xatr);
		}
	}
	public void Xtn_parse(Xowe_wiki wiki, Xop_ctx ctx, Xop_root_tkn root, byte[] src, Xop_xnde_tkn xnde) {
		try {
			ctx.Para().Process_block__xnde(xnde.Tag(), Xop_xnde_tag.Block_bgn);			// cancel pre for <gallery>; DATE:2014-03-11
			Xox_xnde_.Xatr__set(wiki, this, Gallery_xnde_atrs.Key_hash, src, xnde);
			xtn_mgr = (Gallery_xtn_mgr)wiki.Xtn_mgr().Get_or_fail(Gallery_xtn_mgr.XTN_KEY);
			Init_atrs(wiki);
			gallery_mgr.Get_modules(ctx.Page());
			Gallery_itm_parser parser = xtn_mgr.Parser();
			if (parser.Parse_in_progress()) parser = new Gallery_itm_parser().Init_by_wiki(wiki);	// handle nested galleries; EX: <gallery><ref><gallery>; PAGE:es.w:Arquitectura_medieval DATE:2015-07-10
			parser.Parse_all(itms, gallery_mgr, this, src, xnde.Tag_open_end(), xnde.Tag_close_bgn());
			boolean log_wkr_enabled = Log_wkr != Xop_log_basic_wkr.Null; if (log_wkr_enabled) Log_wkr.Log_end_xnde(ctx.Page(), Xop_log_basic_wkr.Tid_gallery, src, xnde);
			ctx.Para().Process_block__xnde(xnde.Tag(), Xop_xnde_tag.Block_end);			// cancel pre for <gallery>; DATE:2014-03-11
		} catch (Exception exc) {
			wiki.Appe().Usr_dlg().Warn_many("", "", "failed to write gallery; src=~{0} err=~{1}", String_.new_u8(src, xnde.Src_bgn(), xnde.Src_end()), Err_.Message_gplx_full(exc));
		}
	}	public static Xop_log_basic_wkr Log_wkr = Xop_log_basic_wkr.Null;
	public void Xtn_write(Bry_bfr bfr, Xoae_app app, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, Xop_xnde_tkn xnde, byte[] src) {
		Xowe_wiki wiki = ctx.Wiki();
		try {
			if (html_wtr_v1)
				xtn_mgr.Html_wtr().Write_html(bfr, app, wiki, ctx, html_wtr, hctx, ctx.Page(), this, src);
			else {
				gallery_mgr.Write_html(bfr, wiki, ctx.Page(), ctx, hctx, src, this);
			}
		} catch (Exception exc) {
			wiki.Appe().Usr_dlg().Warn_many("", "", "failed to write gallery; src=~{0} err=~{1}", String_.new_u8(src, xnde.Src_bgn(), xnde.Src_end()), Err_.Message_gplx_full(exc));
		}
	}
	private void Init_atrs(Xowe_wiki wiki) {
		Db_cfg_hash cfg_grp = wiki.File_mgr().Cfg_get(Xof_fsdb_mgr_cfg.Grp_xowa);
		if (cfg_grp.Get_by(Xof_fsdb_mgr_cfg.Key_gallery_fix_defaults).To_yn_or_n()) {
			if (itm_w == Gallery_xnde.Null && itm_h == Gallery_xnde.Null)	// if no w/h specified, set both to default (just like v1)
				itm_w = itm_h = Gallery_xnde.Default;
		}
		else {
			if (itm_w == Gallery_xnde.Null) itm_w = Gallery_xnde.Default;
			if (itm_h == Gallery_xnde.Null) itm_h = Gallery_xnde.Default;
		}
		gallery_mgr = Gallery_mgr_base_.New_by_mode(mode);
		if (	!wiki.File_mgr().Version_1_y()											// v2: fsdb
			&&	!cfg_grp.Get_by(Xof_fsdb_mgr_cfg.Key_gallery_packed).To_yn_or_n() 			// packed not supported
			) {
			gallery_mgr = Gallery_mgr_base_.New_by_mode(Gallery_mgr_base_.Traditional_tid);	// always go to traditional
			html_wtr_v1 = true;
		}
		gallery_mgr.Init(itms_per_row, this.Itm_w_or_default(), this.Itm_h_or_default());
	}
	public static final int Default = 120, Null = -1;
}
class Gallery_xnde_atrs {
	public static final byte 
	  Mode_tid			= 0
	, Perrow_tid		= 1
	, Widths_tid		= 2
	, Heights_tid		= 3
	, Caption_tid		= 4
	, Showfilename_tid	= 5
	, Style_tid			= 6
	, Class_tid			= 7
	;
	public static Hash_adp_bry Key_hash = Hash_adp_bry.ci_a7()
	.Add_str_byte("mode"			, Mode_tid)
	.Add_str_byte("perrow"			, Perrow_tid)
	.Add_str_byte("widths"			, Widths_tid)
	.Add_str_byte("heights"			, Heights_tid)
	.Add_str_byte("caption"			, Caption_tid)
	.Add_str_byte("showfilename"	, Showfilename_tid)
	.Add_str_byte("style"			, Style_tid)
	.Add_str_byte("class"			, Class_tid)
	;
}
