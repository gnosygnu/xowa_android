package gplx.xowa.addons.apps.scripts.xtns; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.scripts.*;
import gplx.core.scripts.*;
public class Xoscript_xtn_itm {
	public Xoscript_xtn_itm(String key, Io_url url, Gfo_script_engine engine) {
		this.key = key;
		this.url = url;
		this.engine = engine;
	}
	public String Key() {return key;} private final    String key;
	public Io_url Url() {return url;} private final    Io_url url;
	public Gfo_script_engine Engine() {return engine;} private final    Gfo_script_engine engine;
}
