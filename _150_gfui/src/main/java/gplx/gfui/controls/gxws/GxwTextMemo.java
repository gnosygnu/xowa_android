package gplx.gfui.controls.gxws; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public interface GxwTextMemo extends GxwTextFld {
	int LinesPerScreen();
	int LinesTotal();
	int ScreenCount();
	int CharIndexOf(int lineIndex);
	int CharIndexOfFirst();
	int LineIndexOfFirst();
	int LineIndexOf(int charIndex);
	PointAdp PosOf(int charIndex);
	void ScrollLineUp();
	void ScrollLineDown();
	void ScrollScreenUp();
	void ScrollScreenDown();
	void SelectionStart_toFirstChar();
	void ScrollTillSelectionStartIsFirstLine();
	void ScrollTillCaretIsVisible();
}
