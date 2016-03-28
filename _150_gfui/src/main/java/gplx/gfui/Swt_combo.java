package gplx.gfui;

import gplx.GfoMsg;
import gplx.GfsCtx;
import gplx.Keyval_hash;
import gplx.String_;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

class Swt_combo implements GxwElem, GxwComboBox, Swt_control {
    private final Combo combo;
    public Swt_combo(Swt_control owner, Keyval_hash ctorArgs) {
        combo = new Combo(owner.Under_composite(), SWT.DROP_DOWN);
        core = new Swt_core_cmds(combo);
        combo.addKeyListener(new Swt_lnr_key(this));
        combo.addMouseListener(new Swt_lnr_mouse(this));
    }
    @Override public Control Under_control() {return combo;}
    @Override public Control Under_menu_control() {return combo;}
    @Override public String TextVal() {return combo.getText();} @Override public void TextVal_set(String v) {combo.setText(v);}
    @Override public GxwCore_base Core() {return core;} GxwCore_base core;
    @Override public GxwCbkHost Host() {return host;} @Override public void Host_set(GxwCbkHost host) {this.host = host;} GxwCbkHost host;
    @Override public Composite Under_composite() {return null;}
    @Override public void EnableDoubleBuffering() {}
    @Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return null;}
    @Override public int SelBgn() {return combo.getSelection().x;} @Override public void SelBgn_set(int v) {combo.setSelection(new Point(combo.getSelection().y, v));}
    @Override public int SelLen() {return combo.getSelection().y;} @Override public void SelLen_set(int v) {combo.setSelection(new Point(v, combo.getSelection().x));}
    @Override public void Sel_(int bgn, int end) {combo.setSelection(new Point(bgn, end));}
    @Override public Object SelectedItm() {return null;}
    @Override public void SelectedItm_set(Object v) {}
    public String[] DataSource_as_str_ary() {return String_.Ary_empty;}
    @Override public void DataSource_set(Object... ary) {combo.setItems((String[])ary);}
    @Override public String Text_fallback() {return "";} @Override public void Text_fallback_(String v) {}
    @Override public int List_sel_idx() {return -1;} @Override public void List_sel_idx_(int v) {}
    public void Items__update(String[] ary) {}
    public void Items__size_to_fit(int count) {}
    public void Items__visible_rows_(int v) {}
    public void Items__jump_len_(int v) {}
    public void Margins_set(int left, int top, int right, int bot) {}
    @Override public boolean List_visible() {return combo.getListVisible();}
    @Override public void List_visible_(boolean v) {
        combo.setListVisible(v);
        int text_len = combo.getText().length();
        combo.setSelection(new Point(text_len, text_len));
    }
}
