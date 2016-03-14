package gplx.core.enums; import gplx.*; import gplx.core.*;
class Gfo_enum_grp {
//		private Ordered_hash itms = Ordered_hash_.New();
	public Gfo_enum_grp(Guid_adp uid, String key, int id, String name, int sort, String xtn) {
		this.uid = uid; this.key = key; this.id = id; this.name = name; this.sort = sort; this.xtn = xtn;
	}
	public Guid_adp Uid() {return uid;} private Guid_adp uid;
	public String Key() {return key;} private String key;
	public int Id() {return id;} private int id;
	public String Name() {return name;} private String name;
	public int Sort() {return sort;} private int sort;
	public String Xtn() {return xtn;} private String xtn;
}
class Gfo_enum_itm {
	public Gfo_enum_itm(Guid_adp uid, String key, int id, String name, int sort, String xtn) {
		this.uid = uid; this.key = key; this.id = id; this.name = name; this.sort = sort; this.xtn = xtn;
	}
	public Guid_adp Uid() {return uid;} private Guid_adp uid;
	public String Key() {return key;} private String key;
	public int Id() {return id;} private int id;
	public String Name() {return name;} private String name;
	public int Sort() {return sort;} private int sort;
	public String Xtn() {return xtn;} private String xtn;
}
