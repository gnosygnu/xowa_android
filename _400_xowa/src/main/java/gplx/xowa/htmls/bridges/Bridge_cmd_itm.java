package gplx.xowa.htmls.bridges; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.langs.jsons.*;
public interface Bridge_cmd_itm {
	byte[] Key();
	void Init_by_app(Xoa_app app);
	String Exec(Json_nde data);
}
