package gplx.xowa.addons.searchs.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
public interface Srch_text_parser_wkr {
	void Parse_done(Srch2_word_itm word);
}
class Srch_text_parser_wkr__noop implements Srch_text_parser_wkr {
	public void Parse_done(Srch2_word_itm word) {}
        public static final Srch_text_parser_wkr__noop Instance = new Srch_text_parser_wkr__noop(); Srch_text_parser_wkr__noop() {}
}
