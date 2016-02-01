package gplx.xowa.parsers.lnkis.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
public interface Xop_file_logger {
	void Log_file(Xop_ctx ctx, Xop_lnki_tkn lnki, byte caller_tid);
}
class Xop_file_logger__noop implements Xop_file_logger {
	public void Log_file(Xop_ctx ctx, Xop_lnki_tkn lnki, byte caller_tid) {}
}
