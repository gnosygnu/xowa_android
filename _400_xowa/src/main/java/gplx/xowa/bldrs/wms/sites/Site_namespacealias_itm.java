package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_namespacealias_itm implements To_str_able {
	public Site_namespacealias_itm(int id, byte[] alias) {
		this.id = id; this.alias = alias;
		this.key = Bry_.Add_w_dlm(Byte_ascii.Pipe, Int_.To_bry(id), alias);
	}
	public byte[] Key() {return key;} private final byte[] key;
	public int Id() {return id;} private final int id;
	public byte[] Alias() {return alias;} private final byte[] alias;
	public String To_str() {return String_.Concat_with_obj("|", id, alias);}
}
