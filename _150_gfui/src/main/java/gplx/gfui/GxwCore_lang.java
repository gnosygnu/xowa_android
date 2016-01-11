package gplx.gfui; import gplx.*;
//#{import
import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
//#}
public class GxwCore_lang extends GxwCore_base {
	//#{lang
	@Override public int Width() {return control.getWidth();} @Override public void Width_set(int v) {control.setSize(v, control.getHeight());}
	@Override public int Height() {return control.getHeight();} @Override public void Height_set(int v) {control.setSize(control.getWidth(), v);}
	@Override public int X() {return control.getX();} @Override public void X_set(int v) {control.setLocation(v, control.getY());}
	@Override public int Y() {return control.getY();} @Override public void Y_set(int v) {control.setLocation(control.getX(), v);}
	@Override public SizeAdp Size() {return GxwCore_lang.XtoSizeAdp(control.getSize());} @Override public void Size_set(SizeAdp v) {control.setSize(GxwCore_lang.Xto_size(v));}
	@Override public PointAdp Pos() {return GxwCore_lang.XtoPointAdp(control.getLocation());} @Override public void Pos_set(PointAdp v) {control.setLocation(GxwCore_lang.XtoPoint(v));}
	@Override public RectAdp Rect() {return GxwCore_lang.XtoRectAdp(control.getBounds());} @Override public void Rect_set(RectAdp v) {
		control.setBounds(GxwCore_lang.XtoRect(v));
//		control.setLocation(v.Pos().XtoPoint());
//		control.setSize(v.Size().XtoDimension());
		}
	@Override public boolean Visible() {
		Container owner = control.getParent();
		// WORKAROUND:JAVA: .NET automatically propagates .Visible down ElementTree; .Visible needs to propagate else sub.Visible can be true though owner.Visible will be false
		while (owner != null) {
			if (!owner.isVisible())
				return false;	// handles case wherein sub is visible but owner is not -> sub should be marked not visible
			owner = owner.getParent();
		}
		return control.isVisible();
	}
	@Override public void Visible_set(boolean v) {
		control.setVisible(v);
	}
	@Override public ColorAdp BackColor() {return XtoColorAdp(control.getBackground());}
	@Override public void BackColor_set(ColorAdp v) {
		if (control instanceof JComponent) {
			((JComponent)control).setBackground(ColorAdpCache.Instance.GetNativeColor(v));
		}
		else if (control instanceof RootPaneContainer) {
			RootPaneContainer container = (RootPaneContainer)control;
			container.getContentPane().setBackground(ColorAdpCache.Instance.GetNativeColor(v));
		}
	}
	@Override public ColorAdp ForeColor() {return XtoColorAdp(control.getForeground());} @Override public void ForeColor_set(ColorAdp v) {control.setForeground(ColorAdpCache.Instance.GetNativeColor(v));}
	@Override public FontAdp TextFont() {
		if (prvFont != null) return prvFont;
		Font f = control.getFont();
		FontAdp curFont = FontAdp.new_(f.getFontName(), FontAdpCache.XtoOsDpi(f.getSize2D()), FontStyleAdp_.lang_(f.getStyle()));
		curFont.OwnerGxwCore_(this);
		prvFont = curFont;
		return prvFont;
	}	FontAdp prvFont;
	@Override public void TextFont_set(FontAdp v) {
		control.setFont(v.UnderFont());
		v.OwnerGxwCore_(this);
		prvFont = v;
	}
	@Override public String TipText() {return tipText;} @Override public void TipText_set(String v) {tipText = v;} String tipText;
	@Override public void Controls_add(GxwElem sub)	{
		try {
		JComponent component = (JComponent)sub;
		Container container = (Container)control;
		container.add(component);
		}catch (Exception e) {}
	}
	@Override public void Controls_del(GxwElem sub)	{
		JComponent component = (JComponent)sub;
		// clear out painted area, or residue will remain (see opal when navigating to empty grid)
		Graphics2D g2 = (Graphics2D) component.getGraphics();
		g2.setColor(component.getBackground());
		g2.fillRect(0, 0, component.getWidth(), component.getHeight());
		g2.dispose();
		Container container = (Container)control;
		container.remove(component);
	}
	@Override public boolean Focus_has() {return control.hasFocus();}
//	@Override public boolean Focus_able() {return control.isFocusable();}
//	@Override public void Focus_able_(boolean v) {control.setFocusable(v);}
	@Override public boolean Focus_able() {return focus_able;} boolean focus_able;
	@Override public void Focus_able_(boolean v) {focus_able = v;}
//	@Override public void Focus_able_force_(boolean v){control.setFocusable(v);}
	@Override public int Focus_index() {return focusIndex;} @Override public void Focus_index_set(int v) {focusIndex = v;} int focusIndex;
	@Override public void Focus() {
		if (Focus_able()) {	// NOTE: .Focus() only works if TabStop = true; otherwise, must call .Select()
//			FocusableThread t = new FocusableThread(control);
//			SwingUtilities.invokeLater(t);
//			control.requestFocus();
			if (!control.requestFocusInWindow()) {	// will fail when switching to imgApp with gallery open from opalMain
 //				control.requestFocus();	
			}			
		}
	}
	class FocusableThread implements Runnable {
		public void run() {
//			comp.requestFocusInWindow();
			comp.requestFocus();
			comp.repaint();
		}
		public FocusableThread(Component comp) {this.comp = comp;} Component comp;
	}
	@Override public void Select_exec() {
		control.requestFocus();
	}
	@Override public void Zorder_front() {
		 Container owner = control.getParent();
		 if (owner == null)return;
		 owner.setComponentZOrder(control, 0);	// NOTE: last component gets drawn last, so will be first visually
	}
	@Override public void Zorder_back() {
		 Container owner = control.getParent();
		 owner.setComponentZOrder(control, owner.getComponentCount() - 1);		
	}
	@Override public void Invalidate() {control.repaint();
	
	}
	@Override public void Dispose() {}	// JAVA: no Dispose for component? only for form
	protected Component control;
    public static GxwCore_lang new_(Component control) {
		GxwCore_lang rv = new GxwCore_lang();
		rv.control = control;
		return rv;
	}	GxwCore_lang() {}
	public static ColorAdp XtoColorAdp(java.awt.Color v) {return ColorAdp.new_((int)v.getAlpha(), (int)v.getRed(), (int)v.getGreen(), (int)v.getBlue());}
	//#}
	public static java.awt.Dimension Xto_size(SizeAdp v) {return new java.awt.Dimension(v.Width(), v.Height());}	//#<>System.Drawing.Size~java.awt.Dimension
	public static SizeAdp XtoSizeAdp(java.awt.Dimension val) { //#<>System.Drawing.Size~java.awt.Dimension
		return new SizeAdp(val.width, val.height);//#<>val.Width, val.Height~val.width, val.height
	}
	public static java.awt.Point XtoPoint(PointAdp v) {return new java.awt.Point(v.X(), v.Y());}	//#<>System.Drawing.Point~java.awt.Point
	public static PointAdp XtoPointAdp(java.awt.Point v) {//#<>System.Drawing.Point~java.awt.Point
		return new PointAdp(v.x, v.y);//#<>v.X, v.Y~v.x, v.y
	}
	public static RectAdp XtoRectAdp(java.awt.Rectangle rect) {//#<>System.Drawing.Rectangle~java.awt.Rectangle
		return new RectAdp(GxwCore_lang.XtoPointAdp(rect.getLocation())	//#<>.Location~.getLocation()
						, GxwCore_lang.XtoSizeAdp(rect.getSize()));		//#<>.Size~.getSize()
	}
	public static java.awt.Rectangle XtoRect(RectAdp rect) {return new java.awt.Rectangle(rect.X(), rect.Y(), rect.Width(), rect.Height());}	//#<>System.Drawing.Rectangle~java.awt.Rectangle
	public static java.awt.Rectangle XtoRect(int x, int y, int w, int h) {return new java.awt.Rectangle(x, y, w, h);}	//#<>System.Drawing.Rectangle~java.awt.Rectangle
}
