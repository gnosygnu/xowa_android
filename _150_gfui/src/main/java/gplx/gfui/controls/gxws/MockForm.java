package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.imgs.*;
public class MockForm extends GxwElem_mock_base implements GxwWin {
	public IconAdp IconWin() {return null;} public void IconWin_set(IconAdp v) {}
	public void ShowWin() {}
	public void CloseWin() {}
	public void HideWin() {}
	public boolean Maximized() {return false;} public void Maximized_(boolean v) {}
	public boolean Minimized() {return false;} public void Minimized_(boolean v) {}
	public boolean Pin() {return pin;} public void Pin_set(boolean val) {pin = val;} private boolean pin;
	public void OpenedCmd_set(Gfo_invk_cmd v) {}
	public void TaskbarVisible_set(boolean val) {}
	public void TaskbarParkingWindowFix(GxwElem form) {}
	public static final    MockForm Instance = new MockForm(); MockForm() {}
}
