package gplx.core.ios; import gplx.*; import gplx.core.*;
public interface Io_sort_filCmd {
	void Bfr_add(Io_line_rdr stream);
	void Fil_bgn(Io_line_rdr stream);
	void Fil_end();
}
class Io_sort_filCmd_null implements Io_sort_filCmd {
	public void Bfr_add(Io_line_rdr stream) {}
	public void Fil_bgn(Io_line_rdr stream) {}
	public void Fil_end() {}
	public static final    Io_sort_filCmd_null Instance = new Io_sort_filCmd_null(); Io_sort_filCmd_null() {}	// TS.static
}
