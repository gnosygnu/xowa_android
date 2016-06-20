package gplx.gfui.envs; import gplx.*; import gplx.gfui.*;
import gplx.gfui.controls.gxws.*;
public class CursorAdp {
	public static PointAdp Pos() {
		if (testing) return testing_pos;
		return GxwCore_lang.XtoPointAdp(java.awt.MouseInfo.getPointerInfo().getLocation());//#<>System.Windows.Forms.Cursor.Position~java.awt.MouseInfo.getPointerInfo().getLocation()
	}
	public static void Pos_set(PointAdp p) {
		if (testing)	// while in testing mode, never set Cursor.Position
			testing_pos = p;
		//#{Pos_set
		else {
			java.awt.Robot robot = null;
			try {robot = new java.awt.Robot();}
			catch (java.awt.AWTException e) {throw Err_.new_exc(e, "ui", "cursor pos set failed");}
			robot.mouseMove(p.X(), p.Y());
		}
		//#}
	}		
	@gplx.Internal protected static void Pos_set_for_tests(PointAdp point) {
		testing = point != PointAdp_.Null;
		testing_pos = point;
	}	static PointAdp testing_pos = PointAdp_.Null; static boolean testing = false;
}
