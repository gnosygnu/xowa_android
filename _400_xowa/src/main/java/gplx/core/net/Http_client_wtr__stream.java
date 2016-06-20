package gplx.core.net; import gplx.*; import gplx.core.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
//#{imports
import java.io.*;
//#}
class Http_client_wtr__stream implements Http_client_wtr {	
	private final    byte[] tmp_stream_bry = new byte[1024];
	//#{members
	private DataOutputStream stream;
	//#}
	public void Stream_(Object o) {
		//#{Stream_
		this.stream = new DataOutputStream((OutputStream)o);
		//#}
	}
	public void Write_bry(byte[] bry) {
		//#{Write_bry
		try {stream.write(bry);}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Write_bry failed");} 			
		//#}
	}
	public void Write_str(String s) {
		//#{Write_str
		try {stream.writeBytes(s);}
		catch (Exception e) {throw Err_.new_exc(e, "net", "Write_str failed");} 
		//#}
	}
	public void Write_mid(byte[] bry, int bgn, int end) {
		//#{Write_mid
		try {stream.write(bry, bgn, end - bgn);}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Write_mid failed");} 			
		//#}
	}
	public void Write_stream(Io_stream_rdr stream_rdr) {
		synchronized (tmp_stream_bry) {
			int read = 0;
			while (true) {
				read = stream_rdr.Read(tmp_stream_bry, 0, 1024);
				if (read == -1) break;
				Write_mid(tmp_stream_bry, 0, read);
			}
		}
	}	
	public void Rls() {
		//#{Rls
		try {stream.close();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Rls failed");}			
		//#}
	}
}
