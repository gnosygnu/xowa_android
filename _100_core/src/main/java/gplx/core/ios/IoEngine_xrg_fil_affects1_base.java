package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoEngine_xrg_fil_affects1_base {
	public Io_url Url() {return url;} public void Url_set(Io_url v) {url = v;} Io_url url;
	public IoEngine_xrg_fil_affects1_base Url_(Io_url v) {url = v; return this;}
	public boolean MissingFails() {return missingFails;} public void MissingFails_set(boolean v) {missingFails = v;} private boolean missingFails = true;
	public boolean ReadOnlyFails() {return readOnlyFails;} public void ReadOnlyFails_set(boolean v) {readOnlyFails = v;} private boolean readOnlyFails = true;
	@gplx.Virtual public void Exec() {}
}
