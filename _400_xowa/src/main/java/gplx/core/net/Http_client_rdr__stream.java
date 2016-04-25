package gplx.core.net; import gplx.*; import gplx.core.*;
//#{imports
import java.io.*;
//#}
class Http_client_rdr__stream implements Http_client_rdr {
	//#{members
	private BufferedReader br;
	//#}
	public void Stream_(Object o) {
		//#{Stream_
		this.br = new BufferedReader(new InputStreamReader((InputStream)o, java.nio.charset.Charset.forName("UTF-8")));
		//#}
	}
	public String Read_line() {
		//#{Read_line
		try {return br.readLine();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Read_line failed");}			
		//#}
	}
	public int Read_char_ary(char[] ary, int bgn, int len) {
		//#{Read_char_ary
		try {return br.read(ary, bgn, len);}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Read_line failed");}			
		//#}
	}
	public byte[] Read_line_as_bry() {return Bry_.new_u8(Read_line());}
	public void Rls() {
		//#{Rls
		try {br.close();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Rls failed");}			
	//#}
	}
}
