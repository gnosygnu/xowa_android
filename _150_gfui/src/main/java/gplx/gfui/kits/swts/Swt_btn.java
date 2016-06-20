package gplx.gfui.kits.swts; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
//#{swt
import gplx.gfui.controls.gxws.GxwCbkHost;
import gplx.gfui.controls.gxws.GxwCore_base;
import gplx.gfui.controls.gxws.GxwElem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
public class Swt_btn implements GxwElem, Swt_control {
	private Button btn;
	public Swt_btn(Swt_control owner, Keyval_hash ctorArgs) {
		btn = new Button(owner.Under_composite(), SWT.FLAT | SWT.PUSH);
		core = new Swt_core_cmds(btn);
		btn.addKeyListener(new Swt_lnr_key(this));
		btn.addMouseListener(new Swt_lnr_mouse(this));
	}
	@Override public Control Under_control() {return btn;}
	@Override public Control Under_menu_control() {return btn;}
	@Override public String TextVal() {return btn.getText();} @Override public void TextVal_set(String v) {btn.setText(v);}
	@Override public GxwCore_base Core() {return core;} GxwCore_base core;
	@Override public GxwCbkHost Host() {return host;} @Override public void Host_set(GxwCbkHost host) {this.host = host;} GxwCbkHost host;
	@Override public Composite Under_composite() {return null;}
	@Override public void EnableDoubleBuffering() {}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return null;}
}
class Swt_clabel_lnr_focus implements FocusListener {
	public Swt_clabel_lnr_focus(Control v) {this.surrogate = v;} Control surrogate;
	@Override public void focusGained(org.eclipse.swt.events.FocusEvent e) {
		surrogate.forceFocus();
	}
	@Override public void focusLost(org.eclipse.swt.events.FocusEvent arg0) {}
}
//#}
