package gplx.gfui; import gplx.*;
import gplx.gfml.*; import gplx.langs.gfs.*; import gplx.core.envs.*;
//#{import
import gplx.core.envs.Env_;
import gplx.core.envs.Op_sys;
import gplx.core.envs.Process_adp;
import gplx.core.threads.*;
import java.awt.AWTKeyStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//#}
public class GfuiEnv_ {
	//#{GfuiEnv_members
	static FontAdp system_font;
	//#}
	public static void Exit() {if (!Env_.Mode_testing()) System.exit(0);}	//#<>Application.Exit()~System.exit(0)
	public static void Init(String[] args, String appNameAndExt, Class<?> type) {Init(args, appNameAndExt, type, true);}
	public static void Init(String[] args, String appNameAndExt, Class<?> type, boolean swingHack) {
		Env_.Init(args, appNameAndExt, type);
		if (swingHack) {	// TODO: move to kit dependent functionality; WHEN: swing kit
		//#{InitFormsApp
		if (Op_sys.Cur().Tid_is_wnt()) {
			try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
			catch (InstantiationException e) {e.printStackTrace();}
			catch (IllegalAccessException e) {e.printStackTrace();}
			catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		}
		Set<AWTKeyStroke> fwdSet = new HashSet<AWTKeyStroke>(); fwdSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
		Set<AWTKeyStroke> bwdSet = new HashSet<AWTKeyStroke>(); bwdSet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, java.awt.event.InputEvent.SHIFT_DOWN_MASK ));		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, fwdSet);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, bwdSet);
		//#}
		}
		if (!Op_sys.Cur().Tid_is_drd())
			GxwElemFactory_.winForms_();

		// reg interruptLnr
		if (swingHack) {	// TODO: move to kit dependent functionality; WHEN: swing kit
			UsrDlg_.Instance.Reg(UsrMsgWkr_.Type_Warn, GfoConsoleWin.Instance);
			UsrDlg_.Instance.Reg(UsrMsgWkr_.Type_Stop, GfuiInterruptLnr.new_());
		}
		IptBndMgr_win = IptCfg_.new_("gplx.gfui.GfuiWin");

		// alias default dirs
		Io_mgr.Instance.AliasDir_sysEngine("app:\\", Env_.AppUrl().OwnerDir().Raw());

		GfsCore.Instance.MsgParser_(GfoMsgParser_gfml.Instance);
		GfsCore.Instance.AddLib(GfsLibIni_core.Instance);
		GfsCore.Instance.AddLib(GfsLibIni_gfui.Instance);
		Io_url iniFile = Env_.AppUrl().GenSubFil(".gfs");
		if (Io_mgr.Instance.ExistsFil(iniFile))
			GfsCore.Instance.ExecFile(iniFile);
	}
	public static void Init_swt(String[] args, Class<?> type) {
		Env_.Init_swt(args, type);
		if (!Op_sys.Cur().Tid_is_drd()) GxwElemFactory_.winForms_();
		GfsCore.Instance.MsgParser_(GfoMsgParser_gfml.Instance);
	}
	public static void Gfs_init() {GfsCore.Instance.MsgParser_(GfoMsgParser_gfml.Instance);}
	public static IptCfg IptBndMgr_win;
	public static void DoEvents() {;} //#<>Application.DoEvents()~
	public static void ShowMsg(String message) {javax.swing.JOptionPane.showMessageDialog(null, message, "", javax.swing.JOptionPane.INFORMATION_MESSAGE, null);}//#<>MessageBox.Show(message)~javax.swing.JOptionPane.showMessageDialog(null, message, "", javax.swing.JOptionPane.INFORMATION_MESSAGE, null)
	public static void BringToFront(Process_adp process) {} //#<>FormNpi.BringToFront(process.UnderProcess().MainWindowHandle);~
	public static void DoEvents(int milliseconds) {
		//#{DoEvents
		Thread_adp_.Sleep(milliseconds);
		//#}
	}
	public static void Run(GfuiWin form) {javax.swing.SwingUtilities.invokeLater(new GfuiFormRunner(form));}//#<>Application.Run((Form)form.UnderElem())~javax.swing.SwingUtilities.invokeLater(new GfuiFormRunner(form))
	public static FontAdp System_font() {
		//#{System_font
		try {
			if (system_font == null) {
	            Font label_font = (Font)UIManager.get("Label.font");
				system_font = FontAdp.new_(label_font.getFamily(), label_font.getSize(), FontStyleAdp_.Plain);
			}
			return system_font;
		} catch (Exception e) {return FontAdp.new_("Arial", 8, FontStyleAdp_.Plain);}
		//#}
	}
	public static final String Quit_commit_evt = "quit_commit_evt", Quit_notify_evt = "quit_notify_evt";
	public static final String Err_GfuiException = "gplx.dbs.GfuiException"; // TODO: used in JAVA. move
}
class GfuiInterruptLnr implements UsrMsgWkr {
	public void ExecUsrMsg(int type, UsrMsg umsg) {GfuiEnv_.ShowMsg(umsg.To_str());}
	public static GfuiInterruptLnr new_() {return new GfuiInterruptLnr();} GfuiInterruptLnr() {}
}
//#{GfuiFormRunner
class GfuiFormRunner implements Runnable {
	public GfuiFormRunner(GfuiWin form) {this.form = form;} GfuiWin form;
	public void run() {
		form.Show();
	}
}
//#}