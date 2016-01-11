package gplx.core.ios; import gplx.*; import gplx.core.*;
public interface Io_url_gen {//_20120616
	Io_url Cur_url();
	Io_url Nxt_url();
	Io_url[] Prv_urls();
	void Del_all();
}
