package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface GxwElem extends Gfo_invk {
	GxwCore_base	Core();
	GxwCbkHost		Host(); void Host_set(GxwCbkHost host);
	String			TextVal(); void TextVal_set(String v);

	//#{Invoke
	//#}

	void			EnableDoubleBuffering();
}
