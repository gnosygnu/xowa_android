package gplx.core.ios.streams.rdrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_rdr__adp implements Io_stream_rdr {
	private java.io.InputStream strm;	//#<>System.IO.Stream~java.io.InputStream
	public Io_stream_rdr__adp(java.io.InputStream strm) {this.strm = strm;} //#<>System.IO.Stream~java.io.InputStream
	public Object Under() {return strm;}
	public byte Tid() {return Io_stream_tid_.Tid__raw;}
	public boolean Exists() {return len > 0;}
	public Io_url Url() {return url;} public Io_stream_rdr Url_(Io_url v) {this.url = v; return this;} private Io_url url;
	public long Len() {return len;} public Io_stream_rdr Len_(long v) {len = v; return this;} private long len = Io_mgr.Len_null;
	public void Open_mem(byte[] v) {}
	public Io_stream_rdr Open() {return this;}
	public int Read(byte[] bry, int bgn, int len) {
		try {return strm.read(bry, bgn, len);}	//#<>Read~read
		catch (Exception e) {throw Err_.new_exc(e, "io", "read failed", "bgn", bgn, "len", len);}
	}
	public long Skip(long len) {
		try {return strm.skip(len);}	//#<>strm.Seek(len, System.IO.SeekOrigin.Current)~strm.skip(len)
		catch (Exception e) {throw Err_.new_exc(e, "io", "skip failed", "len", len);}
	}	
	public void Rls() {
		try {strm.close();} //#<>strm.Close~strm.close
		catch (Exception e) {throw Err_.new_exc(e, "io", "close failed", "url", url.Xto_api());}
	}
}
