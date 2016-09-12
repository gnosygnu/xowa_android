package gplx.xowa.users; import gplx.*; import gplx.xowa.*;
import gplx.xowa.users.data.*;
import gplx.xowa.users.history.*;
import gplx.xowa.files.caches.*;
public interface Xou_user {
	String					Key();
	int						Gender();
	Xou_db_mgr				User_db_mgr();
	Xou_history_mgr			History_mgr();
	Xow_wiki				Wikii();
}
