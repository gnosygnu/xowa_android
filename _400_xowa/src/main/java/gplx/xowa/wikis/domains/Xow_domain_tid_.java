package gplx.xowa.wikis.domains; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xow_domain_tid_ {
	public static final int
	  Int__null					=  0
	, Int__wikipedia			=  1
	, Int__wiktionary			=  2
	, Int__wikisource			=  3
	, Int__wikivoyage			=  4
	, Int__wikiquote			=  5
	, Int__wikibooks			=  6
	, Int__wikiversity			=  7
	, Int__wikinews				=  8
	, Int__wikimedia			=  9
	, Int__species				= 10
	, Int__commons				= 11
	, Int__wikidata				= 12
	, Int__mediawiki			= 13
	, Int__meta					= 14
	, Int__incubator			= 15
	, Int__wmfblog				= 16
	, Int__home					= 17
	, Int__other				= 18
	, Int___len					= 19
	;
	public static final String	// SERIALIZED:xowa.gfs
	  Str__wikipedia			= "wikipedia"
	, Str__wiktionary			= "wiktionary"
	, Str__wikisource			= "wikisource"
	, Str__wikivoyage			= "wikivoyage"
	, Str__wikiquote			= "wikiquote"
	, Str__wikibooks			= "wikibooks"
	, Str__wikiversity			= "wikiversity"
	, Str__wikinews				= "wikinews"
	, Str__wikimedia			= "wikimedia"
	, Str__species				= "species"
	, Str__commons				= "commons"
	, Str__wikidata				= "wikidata"
	, Str__mediawiki			= "mediawiki"
	, Str__meta					= "meta"
	, Str__incubator			= "incubator"
	, Str__wmforg				= "wikimediafoundation"
	, Str__home					= "home"
	, Str__other				= "other"
	;
	public static final byte[] 
	  Bry__wikipedia			= Bry_.new_a7(Str__wikipedia)
	, Bry__wiktionary			= Bry_.new_a7(Str__wiktionary)
	, Bry__wikisource			= Bry_.new_a7(Str__wikisource)
	, Bry__wikivoyage			= Bry_.new_a7(Str__wikivoyage)
	, Bry__wikiquote			= Bry_.new_a7(Str__wikiquote)
	, Bry__wikibooks			= Bry_.new_a7(Str__wikibooks)
	, Bry__wikiversity			= Bry_.new_a7(Str__wikiversity)
	, Bry__wikinews				= Bry_.new_a7(Str__wikinews)
	, Bry__wikimedia			= Bry_.new_a7(Str__wikimedia)
	, Bry__species				= Bry_.new_a7(Str__species)
	, Bry__commons				= Bry_.new_a7(Str__commons)
	, Bry__wikidata				= Bry_.new_a7(Str__wikidata)
	, Bry__mediawiki			= Bry_.new_a7(Str__mediawiki)
	, Bry__meta					= Bry_.new_a7(Str__meta)
	, Bry__incubator			= Bry_.new_a7(Str__incubator)
	, Bry__wmforg				= Bry_.new_a7(Str__wmforg)
	, Bry__home					= Bry_.new_a7(Str__home)
	, Bry__other				= Bry_.new_a7(Str__other)
	;
	private static final Xow_domain_tid[] ary = new Xow_domain_tid[Int___len];
	private static final Hash_adp_bry type_regy = Hash_adp_bry.ci_a7();	// LOC:must go before new_()
	private static final Hash_adp_bry abrv_regy = Hash_adp_bry.cs();		// LOC:must go before new_()
	public static final Xow_domain_tid 
	  Itm__wikipedia			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikipedia		, Bry__wikipedia		, "w"			, ".wikipedia.org")
	, Itm__wiktionary			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wiktionary		, Bry__wiktionary		, "d"			, ".wiktionary.org")
	, Itm__wikisource			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikisource		, Bry__wikisource		, "s"			, ".wikisource.org")
	, Itm__wikivoyage			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikivoyage		, Bry__wikivoyage		, "v"			, ".wikivoyage.org")
	, Itm__wikiquote			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikiquote		, Bry__wikiquote		, "q"			, ".wikiquote.org")
	, Itm__wikibooks			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikibooks		, Bry__wikibooks		, "b"			, ".wikibooks.org")
	, Itm__wikiversity			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikiversity		, Bry__wikiversity		, "u"			, ".wikiversity.org")
	, Itm__wikinews				= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikinews			, Bry__wikinews			, "n"			, ".wikinews.org")
	, Itm__wikimedia			= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__wikimedia		, Bry__wikimedia		, "m"			, ".wikimedia.org")
	, Itm__species				= new_(Bool_.Y	, Xow_domain_type_src_.Int__wmf	, Int__species			, Bry__species			, "species"		, Xow_domain_itm_.Str__species)
	, Itm__commons				= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__commons			, Bry__commons			, "c"			, Xow_domain_itm_.Str__commons)
	, Itm__wikidata				= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__wikidata			, Bry__wikidata			, "wd"			, Xow_domain_itm_.Str__wikidata)
	, Itm__mediawiki			= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__mediawiki		, Bry__mediawiki		, "mw"			, Xow_domain_itm_.Str__mediawiki)
	, Itm__meta					= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__meta				, Bry__meta				, "meta"		, Xow_domain_itm_.Str__meta)
	, Itm__incubator			= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__incubator		, Bry__incubator		, "qb"			, Xow_domain_itm_.Str__incubator)
	, Itm__wmforg				= new_(Bool_.N	, Xow_domain_type_src_.Int__wmf	, Int__wmfblog			, Bry__wmforg			, "wmf"			, Xow_domain_itm_.Str__wmforg)
	, Itm__home					= new_(Bool_.N	, Xow_domain_type_src_.Int__xowa, Int__home				, Bry__home				, "home"		, Xow_domain_itm_.Str__home)
	, Itm__other				= new_(Bool_.N	, Xow_domain_type_src_.Int__mw	, Int__other			, Bry__other			, ""			, "")
	;
	private static Xow_domain_tid new_(boolean multi_lang, int src, int tid, byte[] key_bry, String abrv_xo_str, String domain_bry) {
		byte[] abrv_xo_bry = Bry_.new_u8(abrv_xo_str);
		Xow_domain_tid rv = new Xow_domain_tid(multi_lang, src, tid, key_bry, abrv_xo_bry, Bry_.new_u8(domain_bry));
		ary[tid] = rv;
		type_regy.Add(key_bry, rv);
		abrv_regy.Add(abrv_xo_bry, rv);
		return rv;
	}
	public static Xow_domain_tid Get_abrv_as_itm(byte[] src, int bgn, int end) {return (Xow_domain_tid)abrv_regy.Get_by_mid(src, bgn, end);}
	public static Xow_domain_tid Get_type_as_itm(int tid) {return ary[tid];}
	public static byte[] Get_type_as_bry(int tid) {return ary[tid].Key_bry();}
	public static int Get_type_as_tid(byte[] src) {return Get_type_as_tid(src, 0, src.length);}
	public static int Get_type_as_tid(byte[] src, int bgn, int end) {
		Object o = type_regy.Get_by_mid(src, bgn, end);
		return o == null ? Xow_domain_tid_.Int__null : ((Xow_domain_tid)o).Tid();
	}
}
