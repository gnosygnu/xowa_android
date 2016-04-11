package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*;
public class Xob_db_file {
	Xob_db_file(Io_url url, Db_conn conn) {
		this.url = url; this.conn = conn;
		this.tbl__cfg = gplx.xowa.wikis.data.Xowd_cfg_tbl_.New(conn);
	}
	public Io_url			Url()		{return url;} private final    Io_url url;
	public Db_conn			Conn()		{return conn;} private final    Db_conn conn;
	public Db_cfg_tbl		Tbl__cfg()	{return tbl__cfg;} private final    Db_cfg_tbl tbl__cfg;
	public static Xob_db_file New__file_make(Io_url dir)			{return New(dir, Name__file_make);}
	public static Xob_db_file New__page_regy(Io_url dir)			{return New(dir, Name__page_regy);}
	public static Xob_db_file New__wiki_image(Io_url dir)			{return New(dir, Name__wiki_image);}
	public static Xob_db_file New__wiki_redirect(Io_url dir)		{return New(dir, Name__wiki_redirect);}
	public static Xob_db_file New__temp_log(Io_url dir)				{return New(dir, Name__temp_log);}
	public static Xob_db_file New__redlink(Io_url dir)				{return New(dir, Name__redlink);}
	public static Xob_db_file New__page_link(Xow_wiki wiki)			{return New(wiki.Fsys_mgr().Root_dir(), Name__page_link);}
	public static Xob_db_file New__deletion_db(Xow_wiki wiki)		{
		String name = String_.Format("{0}-file-core-deletion_db-{1}.xowa", wiki.Domain_str(), DateAdp_.Now().XtoStr_fmt("yyyy.MM"));
		return New(wiki.Fsys_mgr().Root_dir(), name);
	}
	public static Xob_db_file New(Io_url dir, String name) {
		Io_url url = dir.GenSubFil(name);
		Db_conn_bldr_data conn_data = Db_conn_bldr.Instance.Get_or_new(url);
		Db_conn conn = conn_data.Conn();
		Xob_db_file rv = new Xob_db_file(url, conn);
		if (conn_data.Created())
			rv.Tbl__cfg().Create_tbl();
		return rv;
	}
	public static final String 
	  Name__wiki_image = "xowa.wiki.image.sqlite3", Name__wiki_redirect = "xowa.wiki.redirect.sqlite3"
	, Name__file_make = "xowa.file.make.sqlite3", Name__temp_log = "xowa.temp.log.sqlite3"
	, Name__page_regy = "xowa.file.page_regy.sqlite3", Name__redlink = "xowa.temp.redlink.sqlite3"
	, Name__page_link = "xowa.wiki.pagelinks.sqlite3"
	;
}
