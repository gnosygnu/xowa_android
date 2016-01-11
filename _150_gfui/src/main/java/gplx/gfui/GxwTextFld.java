package gplx.gfui; import gplx.*;
public interface GxwTextFld extends GxwElem {
	boolean Border_on(); void Border_on_(boolean v);
	int SelBgn(); void SelBgn_set(int v);
	int SelLen(); void SelLen_set(int v);
	void CreateControlIfNeeded();
	boolean OverrideTabKey(); void OverrideTabKey_(boolean v);
	void Margins_set(int left, int top, int right, int bot);
}
interface GxwTextMemo extends GxwTextFld {
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
