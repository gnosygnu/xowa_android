package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoEngine_xrg_openRead {
	public Io_url Url() {return url;} Io_url url;
	public String ErrMsg() {return errMsg;} private String errMsg;
	public IoStream ExecAsIoStreamOrFail() {return IoEnginePool.Instance.Get_by(url.Info().EngineKey()).OpenStreamRead(url);}
	public IoStream ExecAsIoStreamOrNull() {
		try {return IoEnginePool.Instance.Get_by(url.Info().EngineKey()).OpenStreamRead(url);}
		catch (Exception exc) {
			errMsg = Err_.Message_lang(exc);
			return IoStream_.Null;
		}
	}
	public static IoEngine_xrg_openRead new_(Io_url url) {
		IoEngine_xrg_openRead rv = new IoEngine_xrg_openRead();
		rv.url = url;
		return rv;
	}	IoEngine_xrg_openRead() {}
}
