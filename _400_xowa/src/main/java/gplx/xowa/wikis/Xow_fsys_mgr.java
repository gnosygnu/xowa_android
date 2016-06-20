package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
public class Xow_fsys_mgr {		
	public Xow_fsys_mgr(Io_url root_dir, Io_url file_dir) {
		this.root_dir = root_dir; this.file_dir = file_dir; this.tmp_dir = root_dir.GenSubDir("tmp");			
	}
	public Io_url Root_dir()				{return root_dir;}		private final    Io_url root_dir;
	public Io_url File_dir()				{return file_dir;}		private final    Io_url file_dir;
	public Io_url Tmp_dir()					{return tmp_dir;}		private final    Io_url tmp_dir;
}
