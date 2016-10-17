package gplx.core.ios.streams.wtrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_wtr__raw implements Io_stream_wtr {
	public byte Tid() {return Io_stream_tid_.Tid__raw;}
	public Io_url Url() {return url;} public Io_stream_wtr Url_(Io_url v) {url = v; return this;} private Io_url url;
	public void Trg_bfr_(Bry_bfr v) {trg_bfr = v;} private Bry_bfr trg_bfr;

	//#{body
	private IoStream bry_stream;
	@Override public Io_stream_wtr Open() {
		try {
			if (trg_bfr == null)
				bry_stream = Io_mgr.Instance.OpenStreamWrite(url);
		}
		catch (Exception e) {throw Err_.new_exc(e, "io", "open failed", "url", url.Raw());}
		return this;
	}
	public void Write(byte[] bry, int bgn, int len) {
		if (trg_bfr == null) {
			try {bry_stream.Write(bry, bgn, len);}
			catch (Exception e) {throw Err_.new_exc(e, "io", "write failed", "url", url.Raw(), "bgn", bgn, "len", len);}
		}
		else
			trg_bfr.Add_mid(bry, bgn, bgn + len);
	}
	public byte[] To_ary_and_clear() {		 
		return trg_bfr == null ? Io_mgr.Instance.LoadFilBry(url) : trg_bfr.To_bry_and_clear();
	}
	public void Flush() {
		if (trg_bfr == null)
			bry_stream.Flush();
	}
	public void Rls() {
		try {
			if (trg_bfr == null)
				bry_stream.Rls();
		}
		catch (Exception e) {throw Err_.new_exc(e, "io", "close failed", "url", url.Raw());}
	}
	//#}
}
