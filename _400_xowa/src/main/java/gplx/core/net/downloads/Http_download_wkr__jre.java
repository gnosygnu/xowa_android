package gplx.core.net.downloads; import gplx.*; import gplx.core.*; import gplx.core.net.*;
//#{import
import java.io.*;
import java.net.*;
import gplx.core.progs.*;
//#}
public class Http_download_wkr__jre extends Http_download_wkr__base {
	//#{Http_download_wkr
	public Http_download_wkr Make_new() {return this;}
    @Override public byte Exec_hook(Gfo_prog_ui prog_ui, String src_url, Io_url trg_url, long downloaded) {
        long prog_data_cur = downloaded;
        boolean prog_resumed = prog_data_cur > 0;
   	
        // get trg stream first to handle bad paths / permission errors
    	Io_mgr.Instance.CreateDirIfAbsent(trg_url.OwnerDir());
        if (prog_resumed) 
        	Io_mgr.Instance.Truncate_fil(trg_url, downloaded);
        File trg_fil = new File(trg_url.Xto_api());
        FileOutputStream trg_stream = null;
        try     {trg_stream = new FileOutputStream(trg_fil.getPath(), prog_resumed);}	// pass true for append
        catch   (FileNotFoundException e) {throw Err_.new_("download_file", "write failed; permission error?", "trg", trg_url, "err", e.toString());}
         
        // open src stream
        InputStream src_stream = null;
        URL src_url_itm = null;
        try     {src_url_itm = new URL(src_url);}
        catch   (MalformedURLException e) {throw Err_.new_("download_file", "bad url", "src", src_url, "err" + e.toString());}
        HttpURLConnection src_conn = null;
        try {
        	// open connection
            src_conn = (HttpURLConnection)src_url_itm.openConnection();
            if (prog_resumed)
            	src_conn.addRequestProperty("Range", "bytes=" + Long_.To_str(prog_data_cur) + "-");
            src_conn.connect();
            
            // check response code
            int response_code = src_conn.getResponseCode();
            if (prog_resumed) {
	            if (response_code != HttpURLConnection.HTTP_PARTIAL)
	                throw Err_.new_("download_file", "server returned non-partial response code", "src", src_url, "code", src_conn.getResponseCode(), "msg", src_conn.getResponseMessage());
            }
            else {
	            if (response_code != HttpURLConnection.HTTP_OK)
	                throw Err_.new_("download_file", "server returned non-OK response code", "src", src_url, "code", src_conn.getResponseCode(), "msg", src_conn.getResponseMessage());
            }
            src_stream = src_conn.getInputStream();
        } catch (Exception e) {
            throw Err_.new_("download_file", "src connection failed", "src", src_url, "err",e.toString());
        }

        // do downloading
        try {
            long prog_data_end = prog_ui.Prog_data_end();
            if (prog_data_end == -1) prog_data_end = src_conn.getContentLength(); // NOTE: may be -1 if server does not report the length            
            byte data[] = new byte[4096];
            int read = 0;
            while ((read = src_stream.read(data)) != -1) {
                prog_data_cur += read;
                trg_stream.write(data, 0, read);
                this.Checkpoint__save(prog_data_cur);
                if (prog_ui.Prog_notify_and_chk_if_suspended(prog_data_cur, prog_data_end)) return Gfo_prog_ui_.Status__suspended;
            }
        } catch (Exception e) {
            throw Err_.new_("download_file", "downloading failed", "src", src_url, "trg_url", trg_url, "err", e.toString());
        }
        finally {
            try {
                if (trg_stream != null) trg_stream.close();
                if (src_stream != null) src_stream.close();
            } 	catch (IOException e) {}
            if (src_conn != null) src_conn.disconnect();
        }
        return Gfo_prog_ui_.Status__done;
	}
	//#}
}
