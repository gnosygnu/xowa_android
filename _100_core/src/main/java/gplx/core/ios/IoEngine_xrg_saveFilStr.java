package gplx.core.ios; import gplx.*; import gplx.core.*;
import gplx.core.texts.*;
public class IoEngine_xrg_saveFilStr {
	public Io_url Url() {return url;} public IoEngine_xrg_saveFilStr Url_(Io_url val) {url = val; return this;} Io_url url;
	public String Text() {return text;} public IoEngine_xrg_saveFilStr Text_(String val) {text = val; return this;} private String text = "";
	public boolean Append() {return append;}  public IoEngine_xrg_saveFilStr Append_() {return Append_(true);} public IoEngine_xrg_saveFilStr Append_(boolean val) {append = val; return this;} private boolean append = false;
	public void Exec() {
		if (String_.Eq(text, "") && append) return;	// no change; don't bother writing to disc
		IoEnginePool.Instance.Get_by(url.Info().EngineKey()).SaveFilText_api(this);
	}
	public static IoEngine_xrg_saveFilStr new_(Io_url url, String text) {
		IoEngine_xrg_saveFilStr rv = new IoEngine_xrg_saveFilStr();
		rv.url = url; rv.text = text;
		return rv;
	}	IoEngine_xrg_saveFilStr() {}
}
