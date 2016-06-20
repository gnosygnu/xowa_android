package gplx.xowa.addons.bldrs.centrals.dbs.datas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*; import gplx.xowa.addons.bldrs.centrals.dbs.datas.*;
public class Xobc_zip_type {
	public static String To_str(byte type) {
		switch (type) {
			case Type__zip: return "zip";
			default: throw Err_.new_unhandled_default(type);
		}
	}
	public static final byte Type__zip = 1;	// SERIALIZED
}
