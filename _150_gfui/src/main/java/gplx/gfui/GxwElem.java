package gplx.gfui; import gplx.*;
public interface GxwElem extends GfoInvkAble {
	GxwCore_base	Core();
	GxwCbkHost		Host(); void Host_set(GxwCbkHost host);
	String			TextVal(); void TextVal_set(String v);

	//#{Invoke
	//#}

	void			EnableDoubleBuffering();
}
