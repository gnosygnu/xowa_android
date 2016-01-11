package gplx.gfui;
//#{Swt_tab_itm
import gplx.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
public class Swt_tab_itm implements Gxw_tab_itm, Swt_control, FocusListener {
	public CTabFolder Tab_mgr() {return tab_mgr;} private CTabFolder tab_mgr;
	public Swt_tab_mgr Tab_mgr_swt() {return tab_mgr_swt;} private Swt_tab_mgr tab_mgr_swt;
	public Gfui_tab_itm_data Tab_data() {return (Gfui_tab_itm_data)tab_itm.getData();}
	public Swt_tab_itm(Swt_tab_mgr tab_mgr_swt, Swt_kit kit, CTabFolder tab_mgr, Gfui_tab_itm_data tab_data) {
		this.tab_mgr_swt = tab_mgr_swt; this.kit = kit; this.tab_mgr = tab_mgr;		
		tab_itm = new CTabItem(tab_mgr, SWT.CLOSE);
		tab_itm.setData(tab_data);
		// core = new Swt_core_cmds(tab_itm);
	}
	public Swt_kit Kit() {return kit;} private Swt_kit kit;
	@Override public Control Under_control() {return null;}
	@Override public Composite Under_composite() {return null;}
	@Override public Control Under_menu_control() {throw Err_.new_unimplemented();}
	@Override public String Tab_name() {return tab_itm.getText();} @Override public void Tab_name_(String v) {tab_itm.setText(v);}
	@Override public String Tab_tip_text() {return tab_itm.getToolTipText();} @Override public void Tab_tip_text_(String v) {tab_itm.setToolTipText(v);}
	public void Subs_add(GfuiElem sub) {
		Swt_control swt_control = Swt_control_.cast_or_fail(sub); 
		tab_itm.setControl(swt_control.Under_control());
	}
	public CTabItem Under_CTabItem() {return tab_itm;} private CTabItem tab_itm;	
	@Override public GxwCore_base Core() {return core;} GxwCore_base core;
	@Override public GxwCbkHost Host() {return host;} @Override public void Host_set(GxwCbkHost host) {this.host = host;} GxwCbkHost host;
	@Override public String TextVal() {return "not implemented";}
	@Override public void TextVal_set(String v) {}
	@Override public void EnableDoubleBuffering() {}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return GfoInvkAble_.Rv_unhandled;}
	@Override public void focusGained(FocusEvent arg0) {}
	@Override public void focusLost(FocusEvent arg0) {}
}
//#}