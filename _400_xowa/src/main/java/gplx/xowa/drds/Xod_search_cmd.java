package gplx.xowa.drds; import gplx.*; import gplx.xowa.*;
import gplx.xowa.specials.search.*;
public interface Xod_search_cmd {
	void Search(Cancelable cancelable, Xow_wiki wiki, Xows_ui_async ui_async, String search);
}
