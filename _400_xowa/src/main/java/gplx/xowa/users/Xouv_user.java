package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
import gplx.dbs.*;
import gplx.xowa.users.data.*;
import gplx.xowa.files.*; import gplx.xowa.files.caches.*;
import gplx.xowa.wikis.*; import gplx.xowa.wikis.domains.*;
import gplx.xowa.langs.genders.*;
public class Xouv_user implements Xou_user {
	private Xoa_wiki_mgr wiki_mgr;
	public Xouv_user(String key) {this.key = key;}
	public String					Key() {return key;} private String key;
	public Xou_db_mgr				User_db_mgr()  {return user_db_mgr;} private Xou_db_mgr user_db_mgr;
	public int						Gender() {return Xol_gender_.Tid_unknown;}
	public Xow_wiki					Wikii() {if (wiki == null) wiki = wiki_mgr.Get_by_or_make_init_y(Xow_domain_itm_.Bry__home); return wiki;} private Xow_wiki wiki;
	public void Init_db(Xoa_app app, Xoa_wiki_mgr wiki_mgr, Io_url db_url) {
		this.wiki_mgr = wiki_mgr;
		this.user_db_mgr = new Xou_db_mgr(app);
		user_db_mgr.Init_by_app(Bool_.Y, db_url);
	}
}
