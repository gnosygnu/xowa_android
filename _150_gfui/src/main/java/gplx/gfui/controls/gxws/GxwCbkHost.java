package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.ipts.*; import gplx.gfui.gfxs.*;
public interface GxwCbkHost {
	boolean KeyDownCbk(IptEvtDataKey data);
	boolean KeyHeldCbk(IptEvtDataKeyHeld data);
	boolean KeyUpCbk(IptEvtDataKey data);
	boolean MouseDownCbk(IptEvtDataMouse data);
	boolean MouseUpCbk(IptEvtDataMouse data);
	boolean MouseMoveCbk(IptEvtDataMouse data);
	boolean MouseWheelCbk(IptEvtDataMouse data);
	boolean PaintCbk(PaintArgs paint);
	boolean PaintBackgroundCbk(PaintArgs paint);
	boolean DisposeCbk();
	boolean SizeChangedCbk();
	boolean FocusGotCbk();
	boolean FocusLostCbk();
	boolean VisibleChangedCbk();
	//#{WndProcCbk
	//boolean WndProcCbk(WindowMessage windowMessage);
	//#}
}
