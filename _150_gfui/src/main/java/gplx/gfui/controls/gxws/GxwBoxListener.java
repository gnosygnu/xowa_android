package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
//#{import
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//#}
//#{lang
public class GxwBoxListener implements ComponentListener, FocusListener {
	@Override public void componentShown(ComponentEvent e) 		{host.Host().VisibleChangedCbk();}
	@Override public void componentHidden(ComponentEvent e) 	{host.Host().VisibleChangedCbk();}
	@Override public void componentMoved(ComponentEvent e) 		{}
	@Override public void componentResized(ComponentEvent e) 	{host.Host().SizeChangedCbk();}
	@Override public void focusGained(FocusEvent e) 			{host.Host().FocusGotCbk();}
	@Override public void focusLost(FocusEvent e) 				{host.Host().FocusLostCbk();}
	public GxwBoxListener(GxwElem host) {this.host = host;} GxwElem host;
}
//#}
