package gplx.gfui; import gplx.*;
//#{import
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//#}
//#{lang
public class GxwComboBox_lang extends JComboBox implements GxwComboBox, GxwElem, ActionListener {
    public String[] DataSource_as_str_ary() {return String_.Ary_empty;}
    public void DataSource_update(Object... ary) {}
    public void DataSource_set(Object... ary) {
        for (Object o : ary)
            this.insertItemAt(o, this.getItemCount());
		/*
			Get current value
			Object obj = cb.getSelectedItem();

			Set a new valu
			cb.setSelectedItem("item2");
			obj = cb.getSelectedItem();
 		 */
    }
    public int SelBgn() {return -1;} public void SelBgn_set(int v) {}
    public int SelLen() {return 0;}  public void SelLen_set(int v) {}
    public void Sel_(int bgn, int end) {}
    public Object SelectedItm() {return this.getEditor().getItem();} public void SelectedItm_set(Object v) {
        this.getEditor().setItem(v);
    }
    @Override public String Text_fallback() {return "";} @Override public void Text_fallback_(String v) {}
    @Override public int List_sel_idx() {return -1;} @Override public void List_sel_idx_(int v) {}
    public boolean List_visible() {return false;} public void List_visible_(boolean v) {}
    public void Items__update(String[] ary) {}
    public void Items__size_to_fit(int count) {}
    public void Items__visible_rows_(int v) {}
    public void Items__jump_len_(int v) {}
    public void Margins_set(int left, int top, int right, int bot) {}
	void ctor_() {
		ctrlMgr = GxwCore_lang.new_(this);
		this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK);
		GxwBoxListener lnr = new GxwBoxListener(this);
//		this.setFocusTraversalKeysEnabled(false);
//		this.addKeyListener(this);
		this.addComponentListener(lnr);
		this.addFocusListener(lnr);
		this.setEditable(true);
//		this.addActionListener(this);
		this.setFocusable(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Component jc = this.getEditor().getEditorComponent();	// WORKAROUND:SWING:JComboBox does not reroute events from editor; will not handle canceling key
		jc.addKeyListener(new ComboBox_keylistener(this));
	}
	public GxwCore_base Core() {return ctrlMgr;} GxwCore_base ctrlMgr;
	public GxwCbkHost Host() {return host;} public void Host_set(GxwCbkHost host) {this.host = host;} GxwCbkHost host = GxwCbkHost_.Null;
	// need to validate, else dropDownArrow will not get redrawn in correct pos
	@Override public void setBounds(Rectangle r) {super.setBounds(r); this.validate();}
	@Override public void setSize(int w, int h) {super.setSize(w, h); this.validate();}
	@Override public void setSize(Dimension d) {super.setSize(d); this.validate();}
	public String TextVal() {return Object_.Xto_str_strict_or_empty(this.SelectedItm());} public void TextVal_set(String v) {this.SelectedItm_set(v);}
	@Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		return this;
	}
	public void SendKeyDown(IptKey key)								{}
	public void SendMouseMove(int x, int y)							{}
	public void SendMouseDown(IptMouseBtn btn)						{}
	public void SendMouseWheel(IptMouseWheel direction)				{}
//	@Override public void keyPressed(KeyEvent arg0) {this.processKeyEvent(arg0);}
//	@Override public void keyReleased(KeyEvent arg0) {this.processKeyEvent(arg0);}
//	@Override public void keyTyped(KeyEvent arg0) {this.processKeyEvent(arg0);}
	@Override public void processKeyEvent(KeyEvent e) 					{
		if (GxwCbkHost_.ExecKeyEvent(host, e)) 
			super.processKeyEvent(e);
		}
//	@Override public void actionPerformed(ActionEvent e) {
//	}
	@Override public void processMouseEvent(MouseEvent e) 				{if (GxwCbkHost_.ExecMouseEvent(host, e))	super.processMouseEvent(e);}
	@Override public void processMouseWheelEvent(MouseWheelEvent e) 	{if (GxwCbkHost_.ExecMouseWheel(host, e))	super.processMouseWheelEvent(e);}
	@Override public void processMouseMotionEvent(MouseEvent e)			{if (host.MouseMoveCbk(IptEvtDataMouse.new_(IptMouseBtn_.None, IptMouseWheel_.None, e.getX(), e.getY()))) super.processMouseMotionEvent(e);}
	@Override public void paint(Graphics g) {
		if (host.PaintCbk(PaintArgs.new_(GfxAdpBase.new_((Graphics2D)g), RectAdp_.Zero)))	// ClipRect not used by any clients; implement when necessary	
			super.paint(g);	// Reevaluate if necessary: super.paint might need to (a) always happen and (b) go before PaintCbk (had issues with drawing text on images) 
	}
	public void EnableDoubleBuffering() {	// eliminates flickering during OnPaint
	}
	public void CreateControlIfNeeded() {
	}
	public static GxwComboBox_lang new_() {
		GxwComboBox_lang rv = new GxwComboBox_lang();
		rv.ctor_();
		return rv;
	}	GxwComboBox_lang() {}
}
class ComboBox_keylistener implements KeyListener {
	public ComboBox_keylistener(GxwComboBox_lang host) {this.host = host;} GxwComboBox_lang host;
	@Override public void keyPressed(KeyEvent arg0) {
//		if (arg0.getKeyChar() == 'f')
//			System.out.println('h');
		host.processKeyEvent(arg0);
		}
	@Override public void keyReleased(KeyEvent arg0) {
		//host.processKeyEvent(arg0);
		}
	@Override public void keyTyped(KeyEvent arg0) {
		//host.processKeyEvent(arg0);
		}	
}
//#}

