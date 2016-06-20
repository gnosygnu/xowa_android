package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
public class Mustache_wtr_ {
	public static byte[] Write_to_bry(byte[] src, Mustache_doc_itm itm) {return Write_to_bry(Bry_bfr_.New(), src, itm);}
	public static byte[] Write_to_bry(Bry_bfr bfr, byte[] src, Mustache_doc_itm itm) {
		Mustache_tkn_parser parser = new Mustache_tkn_parser();
		Mustache_tkn_itm root = parser.Parse(src, 0, src.length);
		Mustache_render_ctx ctx = new Mustache_render_ctx().Init(itm);
		Mustache_bfr mbfr = new Mustache_bfr(bfr);
		root.Render(mbfr, ctx);
		return mbfr.To_bry_and_clear();
	}
}
