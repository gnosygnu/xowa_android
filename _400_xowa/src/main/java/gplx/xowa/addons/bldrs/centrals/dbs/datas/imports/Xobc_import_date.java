package gplx.xowa.addons.bldrs.centrals.dbs.datas.imports; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*; import gplx.xowa.addons.bldrs.centrals.dbs.datas.*;
public class Xobc_import_date {
	public static String To_str__yyyy_mm(String raw, String dlm) {
		return String_.Mid(raw, 0, 4) + dlm + String_.Mid(raw, 4, 6);
	}
}
