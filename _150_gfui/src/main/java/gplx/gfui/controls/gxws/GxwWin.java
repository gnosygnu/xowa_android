package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.imgs.*;
public interface GxwWin extends GxwElem {
	IconAdp IconWin(); void IconWin_set(IconAdp v);

	void ShowWin();
	void HideWin();
	boolean Maximized(); void Maximized_(boolean v);
	boolean Minimized(); void Minimized_(boolean v);
	void CloseWin();
	boolean Pin(); void Pin_set(boolean val);

	void OpenedCmd_set(Gfo_invk_cmd v);
	void TaskbarVisible_set(boolean val);
	void TaskbarParkingWindowFix(GxwElem form);
}
