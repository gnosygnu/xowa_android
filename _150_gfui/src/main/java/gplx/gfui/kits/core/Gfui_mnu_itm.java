package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
import gplx.gfui.imgs.*;
public interface Gfui_mnu_itm {
	int Tid();
	String Uid();
	boolean Enabled(); void Enabled_(boolean v);
	String Text(); void Text_(String v);
	ImageAdp Img(); void Img_(ImageAdp v);
	boolean Selected(); void Selected_(boolean v);
	Object Under();
}
class Gfui_mnu_itm_null implements Gfui_mnu_itm {
	public String Uid() {return "";}
	public int Tid() {return Gfui_mnu_itm_.Tid_btn;}
	public boolean Enabled() {return true;} public void Enabled_(boolean v) {}
	public String Text() {return text;} public void Text_(String v) {text = v;} private String text;
	public ImageAdp Img() {return img;} public void Img_(ImageAdp v) {img = v;} private ImageAdp img;
	public boolean Selected() {return true;} public void Selected_(boolean v) {}
	public Object Under() {return null;}
	public static final    Gfui_mnu_itm_null Null = new Gfui_mnu_itm_null(); Gfui_mnu_itm_null() {}
}
