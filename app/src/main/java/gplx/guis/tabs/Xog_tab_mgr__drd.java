package gplx.guis.tabs;

import org.wikipedia.page.PageActivity;

import gplx.GfoMsg;
import gplx.Gfo_invk_;
import gplx.GfsCtx;
import gplx.xowa.apps.Xoav_app;
import gplx.xowa.guis.tabs.Xog_tab_mgr;
import gplx.xowa.guis.tabs.Xog_tab_mgr_;

public class Xog_tab_mgr__drd implements Xog_tab_mgr, Runnable {
    private final PageActivity activity; private Xoav_app app;
    private boolean focus; private String site, page;
    public Xog_tab_mgr__drd(PageActivity activity) {this.activity = activity;}
    public void Ctor(Xoav_app app) {this.app = app;}
    @Override public void New_tab(boolean focus, String site, String page) {
        activity.displayNewPageByUrl(site, page, PageActivity.TabPosition.NEW_TAB_FOREGROUND);
    }
    @Override public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
        if		(ctx.Match(k, Xog_tab_mgr_.Invk__new_tab))		{
            this.focus = m.ReadBool("focus");
            this.site = m.ReadStr("site");
            this.page = m.ReadStr("page");
            activity.runOnUiThread(this);
        }
        else	return Gfo_invk_.Rv_unhandled;
        return this;
    }
    @Override public void run() {this.New_tab(focus, site, page);}
}
