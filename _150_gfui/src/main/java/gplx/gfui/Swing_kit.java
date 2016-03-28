package gplx.gfui; import gplx.*;
import gplx.core.brys.fmtrs.*;
public class Swing_kit extends Gfui_kit_base {
	private Bry_fmtr ask_fmtr = Bry_fmtr.new_(); private Bry_bfr ask_bfr = Bry_bfr.new_();
	@Override public byte Tid() {return Gfui_kit_.Swing_tid;}
	@Override public String Key() {return "swing";}
	@Override public GxwElemFactory_base Factory() {return factory;} private GxwElemFactory_cls_lang factory = new GxwElemFactory_cls_lang();
	@Override public void Ask_ok(String grp_key, String msg_key, String fmt, Object... args) {GfuiEnv_.ShowMsg(ask_fmtr.Bld_str_many(ask_bfr, fmt, args));}
	@Override public void Kit_run() {GfuiEnv_.Run(this.Main_win());}
	@Override public void Kit_term() {this.Kit_term_cbk().Invk(); GfuiEnv_.Exit();}
	@Override public ImageAdp New_img_load(Io_url url) {return ImageAdp_.file_(url);}
	@Override protected Gxw_html New_html_impl() {return new Mem_html();}
	@Override protected Gxw_tab_mgr New_tab_mgr_impl() {return new Mem_tab_mgr();}
	@Override protected Gxw_tab_itm New_tab_itm_impl() {return new Mem_tab_itm();}
	@Override protected GxwElem New_btn_impl() {return factory.control_();}
    @Override protected GxwElem New_combo_impl() {return factory.control_();}
	public static final Swing_kit Instance = new Swing_kit(); Swing_kit() {}
}
