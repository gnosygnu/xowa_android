package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
import gplx.core.stores.*;
import gplx.core.stores.xmls.*; /*XmlDataRdr*/
import gplx.langs.dsvs.*; /*DsvDataWtr*/
import gplx.core.lists.*; /*GfoNdeRdr*/
class TdbStores {
	public static final String Dsv = "dsv";
	public static final String Xml = "xml";
	public static DataRdr rdr_(String text) {return DsvDataRdr_.dsv_(text);}
	public static DataWtr wtr_() {return DsvDataWtr_.new_();}
	@gplx.Internal protected static DsvStoreLayout FetchLayout(DataRdr rdr) {
		GfoNdeRdr ndeRdr = GfoNdeRdr_.as_(rdr); if (ndeRdr == null) return null;	// can happen for non-Dsv Rdrs (ex: Xml)
		return DsvStoreLayout.as_(ndeRdr.UnderNde().EnvVars().Get_by(DsvStoreLayout.Key_const));
	}
}