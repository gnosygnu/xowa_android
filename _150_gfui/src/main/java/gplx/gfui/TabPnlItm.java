package gplx.gfui; import gplx.*;
public class TabPnlItm {
	public String Key() {return key;} private String key;
	public String Name() {return name;}
	public TabPnlItm Name_(String val) {
		name = val;
		TabBoxEvt_nameChange.Send(mgr, this);
		return this;
	}	String name;
	public int Idx() {return idx;}
	@gplx.Internal protected TabPnlItm Idx_(int val) {
		idx = val;
		return this;
	}	int idx;
	TabBoxMgr mgr;
	public static TabPnlItm new_(TabBoxMgr mgr, String key) {
		TabPnlItm rv = new TabPnlItm();
		rv.mgr = mgr; rv.key = key;
		return rv;
	}	TabPnlItm() {}
}
