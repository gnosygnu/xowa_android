package gplx.xowa.addons.apps.scripts.apis; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.scripts.*;
public class Xoscript_url {
	public Xoscript_url(String wiki_name_var, String page_name_var) {this.wiki_name_var = wiki_name_var; this.page_name_var = page_name_var;}
	public String wiki_name() {return wiki_name_var;} private final    String wiki_name_var;
	public String page_name() {return page_name_var;} private final    String page_name_var;
}
