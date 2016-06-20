package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public class GxwElemFactory_ {
	public static GxwElemFactory_base Instance = new GxwElemFactory_cls_mock();
	public static void winForms_() {Instance = new GxwElemFactory_cls_lang();}
	//#{swt
	public static void swt_(org.eclipse.swt.widgets.Display display) {Instance = new GxwElemFactory_swt(display);}
	//#}
}
