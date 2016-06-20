package gplx.core.ios; import gplx.*; import gplx.core.*;
import gplx.core.ios.streams.*;
public class IoEngine_xrg_openWrite {
	public Io_url Url() {return url;} public IoEngine_xrg_openWrite Url_(Io_url val) {url = val; return this;} Io_url url;
	public boolean ReadOnlyIgnored() {return readOnlyIgnored;} public IoEngine_xrg_openWrite ReadOnlyIgnored_() {return ReadOnlyIgnored_(true);} public IoEngine_xrg_openWrite ReadOnlyIgnored_(boolean v) {readOnlyIgnored = v; return this;} private boolean readOnlyIgnored = false;
	public boolean MissingIgnored() {return missingIgnored;} public IoEngine_xrg_openWrite MissingIgnored_() {return MissingIgnored_(true);} public IoEngine_xrg_openWrite MissingIgnored_(boolean v) {missingIgnored = v; return this;} private boolean missingIgnored = false;
	public byte Mode() {return mode;} public IoEngine_xrg_openWrite Mode_(byte v) {mode = v; return this;} private byte mode = IoStream_.Mode_wtr_create;
	public IoEngine_xrg_openWrite Mode_update_() {return Mode_(IoStream_.Mode_wtr_update);}
	public IoStream Exec() {return IoEnginePool.Instance.Get_by(url.Info().EngineKey()).OpenStreamWrite(this);}
	public static IoEngine_xrg_openWrite new_(Io_url url) {
		IoEngine_xrg_openWrite rv = new IoEngine_xrg_openWrite();
		rv.url = url;
		return rv;
	}	IoEngine_xrg_openWrite() {}
}
