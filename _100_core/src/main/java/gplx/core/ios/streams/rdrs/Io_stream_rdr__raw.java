package gplx.core.ios.streams.rdrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
//#{inherit
public class Io_stream_rdr__raw extends Io_stream_rdr__base {
//#}
	public byte Tid() {return Io_stream_tid_.Tid__raw;}
	//#{body
	public Io_stream_rdr Open() {
		Io_url url = this.Url();
		try {
			if (!Io_mgr.Instance.Exists(url))
				stream = Wrap_stream(new java.io.ByteArrayInputStream(Bry_.Empty));
			else {
				if (url.Info().EngineKey() == IoEngine_.MemKey)
					stream = Wrap_stream(new java.io.ByteArrayInputStream(Io_mgr.Instance.LoadFilBry(url.Xto_api())));
				else
					stream = Wrap_stream(new java.io.FileInputStream(url.Xto_api()));
			}
		}
		catch (Exception e) {throw Err_.new_exc(e, "io", "open failed", "url", url.Xto_api());}
		return this;
	}
	@Override public java.io.InputStream Wrap_stream(java.io.InputStream stream) {return stream;}
	//#}
}
