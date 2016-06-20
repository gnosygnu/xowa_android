package gplx.xowa.drds.ios.media_scanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.drds.*; import gplx.xowa.drds.ios.*;
public interface Xod_media_scanner extends Gfo_evt_itm {
	Xod_media_scanner Add(Io_url url);
	void Scan();
}
