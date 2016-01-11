package gplx.xowa.htmls.core.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
public class Xoh_wtr_ctx {
	Xoh_wtr_ctx(int mode) {this.mode = mode;}
	public int Mode() {return mode;} private final int mode;
	public boolean Mode_is_alt()			{return mode == Mode_alt;}
	public boolean Mode_is_display_title() {return mode == Mode_display_title;}
	public boolean Mode_is_popup()			{return mode == Mode_popup;}
	public boolean Mode_is_hdump()			{return mode == Mode_hdump;}
	public static final int Mode_basic = 0, Mode_alt = 1, Mode_display_title = 2, Mode_popup = 3, Mode_hdump = 4;
	public static final Xoh_wtr_ctx
	  Basic				= new Xoh_wtr_ctx(Mode_basic)
	, Alt				= new Xoh_wtr_ctx(Mode_alt)
	, Display_title		= new Xoh_wtr_ctx(Mode_display_title)
	, Popup				= new Xoh_wtr_ctx(Mode_popup)
	, Hdump				= new Xoh_wtr_ctx(Mode_hdump)
	;
}
