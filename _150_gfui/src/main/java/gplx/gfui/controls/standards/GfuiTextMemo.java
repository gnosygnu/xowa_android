package gplx.gfui.controls.standards; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.gfui.controls.gxws.*;
public class GfuiTextMemo extends GfuiTextBox {	//#*inherit
	public int LinesPerScreen() {return textBox.LinesPerScreen();}
	public int LinesTotal() {return textBox.LinesTotal();}
	public int ScreenCount() {return Int_.DivAndRoundUp(this.LinesTotal(), this.LinesPerScreen());}
	public int CharIndexOfFirst() {return textBox.CharIndexOfFirst();}
	public int CharIndexOf(int lineIndex) {return textBox.CharIndexOf(lineIndex);}
	public int CharIndexAtLine(int lineIndex) {return textBox.CharIndexOf(lineIndex);}
	public int LineIndexOfFirst() {return textBox.LineIndexOfFirst();}
	public int LineIndexOf(int charIndex) {return textBox.LineIndexOf(charIndex);}
	public PointAdp PosOf(int charIndex) {return textBox.PosOf(charIndex);}
	public void ScrollLineUp() {textBox.ScrollLineUp();}
	public void ScrollLineDown() {textBox.ScrollLineDown();}
	public void ScrollScreenUp() {textBox.ScrollScreenUp();}
	public void ScrollScreenDown() {textBox.ScrollScreenDown();}
	public void SelectionStart_toFirstChar() {textBox.SelectionStart_toFirstChar(); Gfo_evt_mgr_.Pub(this, SelectionStartChanged_evt);}
	public void ScrollTillSelectionStartIsFirstLine() {textBox.ScrollTillSelectionStartIsFirstLine();}

	@Override public GxwElem UnderElem_make(Keyval_hash ctorArgs) {return GxwElemFactory_.Instance.text_memo_();}
	@Override public void ctor_GfuiBox_base(Keyval_hash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		textBox = (GxwTextMemo)UnderElem();
		this.SetTextBox(textBox);
	}	GxwTextMemo textBox;
}
