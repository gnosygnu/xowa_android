package gplx.core.ios.streams.rdrs; import gplx.*; import gplx.core.*; import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Io_stream_rdr__noop implements Io_stream_rdr {
	public Object Under() {return null;}
	public byte Tid() {return Io_stream_tid_.Tid__null;}
	public boolean Exists() {return false;}
	public Io_url Url() {return Io_url_.Empty;} public Io_stream_rdr Url_(Io_url v) {return this;}
	public long Len() {return Io_mgr.Len_null;} public Io_stream_rdr Len_(long v) {return this;}
	public void Open_mem(byte[] v) {}
	public Io_stream_rdr Open() {return this;}
	public int Read(byte[] bry, int bgn, int len) {return Io_stream_rdr_.Read_done;}
	public long Skip(long len) {return Io_stream_rdr_.Read_done;}
	public void Rls() {}
}
