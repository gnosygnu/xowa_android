package gplx.gfui; import gplx.*;
public class TxtFindMgr {
	public String Text() {return text;}
	public TxtFindMgr Text_(String v) {
		if (!caseSensitive) v = String_.Lower(v);
		text = v;
		return this;
	}	String text;
	public boolean CaseSensitive() {return caseSensitive;} public TxtFindMgr CaseSensitive_(boolean v) {caseSensitive = v; return this;} private boolean caseSensitive = false;
	public int[] FindByUi(String findText, int selBgn, int selLen, boolean keyIsEnter) {
		int[] rv = new int[2];
		if (String_.Eq(findText, "")) return rv;		// make newSel = 0 b/c all text deleted; else, find will continue from last selBgn; easy way to "reset"
		rv[0] = selBgn; rv[1] = selLen;					// make newSel = curSel
		int adj = keyIsEnter ? 1 : 0;					// if enter, search next, else search from cur; else will add to selLen if at match; ex: ab->c at abc will keep same selBgn, but increase selLen to 3
		int findPos = FindNext(findText, selBgn + adj);
		if (findPos == String_.Find_none) {				// nothing found; set selLen to 0 and return
			rv[1] = 0;
			return rv;
		}
		rv[0] = findPos;
		rv[1] = String_.Len(findText);
		return rv;
	}
	public int FindNext(String find, int guiPos) {
		if (!caseSensitive) find = String_.Lower(find);
		int findPos = String_.FindFwd(text, find, guiPos);
		if (findPos == String_.Find_none && guiPos != 0)
			findPos = String_.FindFwd(text, find, 0);
		return findPos;
	}
}
