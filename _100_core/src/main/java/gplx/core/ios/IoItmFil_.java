package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoItmFil_ {
	public static IoItmFil as_(Object obj) {return obj instanceof IoItmFil ? (IoItmFil)obj : null;}
	public static final String 
	  Prop_Size			= "size"
	, Prop_Modified		= "modified";
	public static IoItmFil new_(Io_url url, long size, DateAdp created, DateAdp modified) {return new IoItmFil().ctor_IoItmFil(url, size, modified);}	
	public static IoItmFil sub_(String name, long size, DateAdp modified) {
		IoItmFil rv = new IoItmFil();
		rv.ctor_IoItmFil(Io_url_.mem_fil_("mem/" + name), size, modified);
		rv.Name_(name);
		return rv;
	}
}
