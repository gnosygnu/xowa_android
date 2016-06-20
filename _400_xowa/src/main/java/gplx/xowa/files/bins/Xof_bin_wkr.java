package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
import gplx.xowa.files.fsdb.*;
public interface Xof_bin_wkr {
	byte			Tid();
	String			Key();
	boolean			Resize_allowed(); void Resize_allowed_(boolean v);
	Io_stream_rdr	Get_as_rdr	(Xof_fsdb_itm itm, boolean is_thumb, int w);
	boolean			Get_to_fsys	(Xof_fsdb_itm itm, boolean is_thumb, int w, Io_url bin_url);
}
