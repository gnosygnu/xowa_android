package gplx.gfui.kits.swts; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
//#{swt
import gplx.gfui.controls.gxws.GxwElem;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
public interface Swt_control extends GxwElem {
	Control Under_control();
	Composite Under_composite();
	Control Under_menu_control();
}
//#}
