package gplx.langs.dsvs; import gplx.*; import gplx.langs.*;
public class DsvStoreLayout {
	public DsvHeaderList HeaderList() {return headerList;} DsvHeaderList headerList = DsvHeaderList.new_();
	@gplx.Internal protected boolean WriteCmdSequence() {return writeCmdSequence;} @gplx.Internal protected DsvStoreLayout WriteCmdSequence_(boolean val) {writeCmdSequence = val; return this;} private boolean writeCmdSequence;

	static DsvStoreLayout new_() {return new DsvStoreLayout();}
	public static DsvStoreLayout csv_dat_() {return new_();}
	public static DsvStoreLayout csv_hdr_() {
		DsvStoreLayout rv = new_();
		rv.HeaderList().Add_LeafNames();
		return rv;
	}
	public static DsvStoreLayout dsv_brief_() {
		DsvStoreLayout rv = new_();
		rv.writeCmdSequence = true;
		return rv;
	}
	public static DsvStoreLayout dsv_full_() {
		DsvStoreLayout rv = DsvStoreLayout.new_();
		rv.writeCmdSequence = true;
		rv.HeaderList().Add_BlankLine();
		rv.HeaderList().Add_BlankLine();
		rv.HeaderList().Add_Comment("================================");
		rv.HeaderList().Add_TableName();
		rv.HeaderList().Add_Comment("================================");
		rv.HeaderList().Add_LeafTypes();
		rv.HeaderList().Add_LeafNames();
		rv.HeaderList().Add_Comment("================================");
		return rv;
	}
	public static DsvStoreLayout as_(Object obj) {return obj instanceof DsvStoreLayout ? (DsvStoreLayout)obj : null;}
	public static DsvStoreLayout cast(Object obj) {try {return (DsvStoreLayout)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, DsvStoreLayout.class, obj);}}
	public static final String Key_const = "StoreLayoutWtr";
}
