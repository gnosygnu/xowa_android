package gplx.core.ios.zips; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
import gplx.core.progs.*;
public interface Io_zip_decompress_cmd {
	String Fail_msg();
	Io_zip_decompress_cmd Make_new();
	long Checkpoint__load_by_src_fil(Io_url src_fil);
	byte Exec(gplx.core.progs.Gfo_prog_ui prog_ui, Io_url src_fil, Io_url trg_dir, List_adp trg_fils);
	void Exec_cleanup();
}
