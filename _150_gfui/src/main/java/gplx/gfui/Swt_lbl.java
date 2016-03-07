package gplx.gfui; import gplx.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

class Swt_lbl implements GxwElem, Swt_control {
	private Label lbl;
	public Swt_lbl(Swt_control owner, Keyval_hash ctorArgs) {
		lbl = new Label(owner.Under_composite(), SWT.CENTER);
		core = new Swt_core_cmds(lbl);
		lbl.addKeyListener(new Swt_lnr_key(this));
		lbl.addMouseListener(new Swt_lnr_mouse(this));
	}
	@Override public Control Under_control() {return lbl;}
	@Override public Control Under_menu_control() {return lbl;}
	@Override public String TextVal() {return lbl.getText();} @Override public void TextVal_set(String v) {
		lbl.setText(v);
		}
	@Override public GxwCore_base Core() {return core;} GxwCore_base core;
	@Override public GxwCbkHost Host() {return host;} @Override public void Host_set(GxwCbkHost host) {this.host = host;} GxwCbkHost host;
	@Override public Composite Under_composite() {return null;}
	@Override public void EnableDoubleBuffering() {}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return null;}
}
