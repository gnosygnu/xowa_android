package gplx.xowa.wikis.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.wikis.data.tbls.*;
public interface Xow_page_fetcher {
	Xow_page_fetcher Wiki_(Xowe_wiki v);
	byte[] Get_by(int ns_id, byte[] ttl);
	void Clear();
}
