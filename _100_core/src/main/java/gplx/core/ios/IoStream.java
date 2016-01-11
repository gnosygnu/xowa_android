package gplx.core.ios; import gplx.*; import gplx.core.*;
public interface IoStream extends Rls_able {
	Object UnderRdr();
	Io_url Url();
	long Pos();
	long Len();

	int ReadAry(byte[] array);
	int Read(byte[] array, int offset, int count);
	long Seek(long pos);
	void WriteAry(byte[] ary);
	void Write(byte[] array, int offset, int count);
	void Transfer(IoStream trg, int bufferLength);
	void Flush();
	void Write_and_flush(byte[] bry, int bgn, int end);
}