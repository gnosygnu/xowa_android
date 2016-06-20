package gplx.core.ios.drives; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
public interface Io_drive {
	long Get_space_total(Io_url drive);
	long Get_space_free (Io_url drive);
}
