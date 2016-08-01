package gplx;
import gplx.core.logs.*;
public class Gfo_log_ {
	public static Gfo_log Instance = new Gfo_log__mem();
	public static Gfo_log Instance__set(Gfo_log v) {
		v.Itms_(Instance.Itms());
		Instance = v;			
		return v;
	}
	public static final String File__fmt = "yyyyMMdd_HHmmss", File__ext = ".log";
	public static Io_url New_url(Io_url dir) {
		return dir.GenSubFil(Datetime_now.Get().XtoUtc().XtoStr_fmt(Gfo_log_.File__fmt) + Gfo_log_.File__ext);
	}
	public static Gfo_log New_file(Io_url dir) {
		Io_url url = dir.GenSubFil(Datetime_now.Get().XtoStr_fmt(File__fmt) + File__ext);
		Gfo_log__file.Delete_old_files(dir, Gfo_log_.Instance);
		return new Gfo_log__file(url, new Gfo_log_itm_wtr__csv());
	}
}
