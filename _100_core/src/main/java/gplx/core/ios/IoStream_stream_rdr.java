package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoStream_stream_rdr implements IoStream {
	public int Read(byte[] bfr, int bfr_bgn, int bfr_len) {
		try {
			//#{Read
			return stream.read(bfr, bfr_bgn, bfr_len);
			//#}
		}
		catch (Exception e) {throw Err_.new_exc(e, "core", "failed to read from stream");}
	}
	public IoStream UnderRdr_(Object v) {this.stream = (java.io.InputStream)v; return this;}  java.io.InputStream stream;	//#<>System.IO.BinaryReader~java.io.InputStream
	public Object UnderRdr() {return stream;}
	public Io_url Url() {return Io_url_.Empty;}
	public long Pos() {return -1;}
	public long Len() {return -1;}
	public int ReadAry(byte[] array) {return -1;}
	public long Seek(long pos) {return -1;}
	public void WriteAry(byte[] ary) {}
	public void Write(byte[] array, int offset, int count) {}
	public void Transfer(IoStream trg, int bufferLength) {}
	public void Flush() {}
	public void Write_and_flush(byte[] bry, int bgn, int end) {}
	public void Rls() {}
}
