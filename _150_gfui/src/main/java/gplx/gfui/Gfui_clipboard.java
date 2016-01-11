package gplx.gfui; import gplx.*;
public interface Gfui_clipboard extends GfoInvkAble, Rls_able {
	void Copy(String s);
}
class Gfui_clipboard_null implements Gfui_clipboard {
	public void Copy(String s) {}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
	public void Rls() {}
}
