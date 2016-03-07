package gplx.gfui; import gplx.*;
public class Mem_kit extends Gfui_kit_base {
	@Override public byte Tid() {return Gfui_kit_.Mem_tid;}
	@Override public String Key() {return "mem";}
	@Override public GxwElemFactory_base Factory() {return factory;} private GxwElemFactory_cls_mock factory = new GxwElemFactory_cls_mock();
	public void New_html_impl_prototype_(Gxw_html v) {html_impl_prototype = v;} private Gxw_html html_impl_prototype;
	@Override public Gfui_html New_html(String key, GfuiElem owner, Keyval... args) {
		if (html_impl_prototype == null)
			return super.New_html(key, owner, args);
		else {
			Gfui_html rv = Gfui_html.mem_(key, html_impl_prototype);
			return rv;
		}
	}
	@Override protected Gxw_html New_html_impl() {return html_impl_prototype == null ? new Mem_html(): html_impl_prototype;}
	@Override protected Gxw_tab_mgr New_tab_mgr_impl() {return new Mem_tab_mgr();}
	@Override protected Gxw_tab_itm New_tab_itm_impl() {return new Mem_tab_itm();}
	@Override protected GxwElem New_btn_impl() {return factory.control_();}
	@Override public ImageAdp New_img_load(Io_url url) {return ImageAdp_null.Instance;}
	public static final Mem_kit Instance = new Mem_kit(); Mem_kit() {}
}
