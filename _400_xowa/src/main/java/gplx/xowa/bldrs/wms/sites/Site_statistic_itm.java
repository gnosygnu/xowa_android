package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
public class Site_statistic_itm implements To_str_able {
	public Site_statistic_itm Ctor(long pages, long articles, long edits, long images, long users, long activeusers, long admins, long jobs, long queued_massmessages) {
		this.pages = pages;
		this.articles = articles;
		this.edits = edits;
		this.images = images;
		this.users = users;
		this.activeusers = activeusers;
		this.admins = admins;
		this.jobs = jobs;
		this.queued_massmessages = queued_massmessages;
		return this;
	}
	public long Pages() {return pages;} private long pages;
	public long Articles() {return articles;} private long articles;
	public long Edits() {return edits;} private long edits;
	public long Images() {return images;} private long images;
	public long Users() {return users;} private long users;
	public long Activeusers() {return activeusers;} private long activeusers;
	public long Admins() {return admins;} private long admins;
	public long Jobs() {return jobs;} private long jobs;
	public long Queued_massmessages() {return queued_massmessages;} private long queued_massmessages;
	public String To_str() {return String_.Concat_with_obj("|", pages, articles, edits, images, users, activeusers, admins, queued_massmessages);}
}
