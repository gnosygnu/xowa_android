package gplx.langs.dsvs; import gplx.*; import gplx.langs.*;
import gplx.core.gfo_ndes.*;
public class DsvDataRdrOpts {
	public boolean HasHeader() {return hasHeader;} public DsvDataRdrOpts HasHeader_(boolean val) {hasHeader = val; return this;} private boolean hasHeader = false;
	public String NewLineSep() {return newLineSep;} public DsvDataRdrOpts NewLineSep_(String val) {newLineSep = val; return this;} private String newLineSep = String_.CrLf;
	public String FldSep() {return fldSep;} public DsvDataRdrOpts FldSep_(String val) {fldSep = val; return this;} private String fldSep = ",";
	public GfoFldList Flds() {return flds;} public DsvDataRdrOpts Flds_(GfoFldList val) {flds = val; return this;} GfoFldList flds = GfoFldList_.Null;
	public static DsvDataRdrOpts new_() {return new DsvDataRdrOpts();} DsvDataRdrOpts() {}
}
