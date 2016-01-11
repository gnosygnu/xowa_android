package gplx.langs.dsvs; import gplx.*; import gplx.langs.*;
public class DsvDataWtr_ {
	public static DsvDataWtr csv_hdr_() {
		DsvDataWtr rv = new DsvDataWtr();
		rv.InitWtr(DsvStoreLayout.Key_const, DsvStoreLayout.csv_hdr_());
		return rv;
	}
	public static DsvDataWtr csv_dat_() {
		DsvDataWtr rv = new DsvDataWtr();
		rv.InitWtr(DsvStoreLayout.Key_const, DsvStoreLayout.csv_dat_());
		return rv;
	}
	public static DsvDataWtr new_() {return new DsvDataWtr();}
}
