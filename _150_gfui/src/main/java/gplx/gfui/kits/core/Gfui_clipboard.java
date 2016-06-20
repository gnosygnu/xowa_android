package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
public interface Gfui_clipboard extends Gfo_invk, Rls_able {
	void Copy(String s);
}
class Gfui_clipboard_null implements Gfui_clipboard {
	public void Copy(String s) {}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
	public void Rls() {}
}
