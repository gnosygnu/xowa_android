package gplx.xowa.users.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.users.*;
public class Xoud_site_row {
	public Xoud_site_row(int id, int priority, String domain, String name, String path, String xtn) {
		this.id = id; this.priority = priority; this.domain = domain; this.name = name; this.path = path; this.xtn = xtn;
	}
	public int			Id()			{return id;} private final int id;
	public int			Priority()		{return priority;} private final int priority;
	public String		Domain()		{return domain;} private final String domain;
	public String		Name()			{return name;} private final String name;
	public String		Path()			{return path;} private final String path;
	public String		Xtn()			{return xtn;} private String xtn;
}
