package gplx.core.ios; import gplx.*; import gplx.core.*;
public interface Io_zip_mgr {
	void Zip_fil(Io_url src_fil, Io_url trg_fil);
	byte[] Zip_bry(byte[] src, int bgn, int len);
	byte[] Unzip_bry(byte[] src, int bgn, int len);
	void Unzip_to_dir(Io_url src_fil, Io_url trg_dir);
	void Zip_dir(Io_url src_dir, Io_url trg_fil);
}
