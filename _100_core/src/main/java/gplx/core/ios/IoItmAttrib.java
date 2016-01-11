package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoItmAttrib {
	public boolean ReadOnly() {return readOnly;} public IoItmAttrib ReadOnly_() {return ReadOnly_(true);} public IoItmAttrib ReadOnly_(boolean val) {readOnly = val; return this;} private boolean readOnly;
	public boolean Hidden() {return hidden;} public IoItmAttrib Hidden_() {return Hidden_(true);} public IoItmAttrib Hidden_(boolean val) {hidden = val; return this;} private boolean hidden;
	public static IoItmAttrib readOnly_() {return new IoItmAttrib().ReadOnly_();}
	public static IoItmAttrib hidden_() {return new IoItmAttrib().Hidden_();}
	public static IoItmAttrib normal_() {return new IoItmAttrib().ReadOnly_(false).Hidden_(false);}
}
