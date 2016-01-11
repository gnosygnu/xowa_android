package gplx.gfml; import gplx.*;
public class GfmlType implements GfmlScopeItm {//_20101107
	public String Key() {return key;} private String key;				// key for typeRegy;		EX: itm/rect
	public String NdeName() {return ndeName;} private String ndeName;	// name for typeResolver;	EX: rect	
	public GfmlDocPos DocPos() {return docPos;} public GfmlType DocPos_(GfmlDocPos val) {docPos = val; return this;} GfmlDocPos docPos = GfmlDocPos_.Null;
	public GfmlFldList SubFlds() {return subFlds;} GfmlFldList subFlds = GfmlFldList.new_();
	public GfmlType Clone() {
		GfmlType rv = new GfmlType().ctor_GfmlType_(key, ndeName).DocPos_(docPos.NewClone());
		for (int i = 0; i < subFlds.Count(); i++) {
			GfmlFld subFld = (GfmlFld)subFlds.Get_at(i);
			rv.subFlds.Add(subFld.Clone());
		}
		return rv;
	}
	public boolean IsTypeAny() {return String_.Eq(key, GfmlType_.AnyKey);}
	public boolean IsTypeNull() {return String_.Eq(key, GfmlType_.NullKey);}
	@gplx.Internal protected GfmlType ctor_GfmlType_(String key, String ndeName) {
		this.key = key;
		this.ndeName = ndeName;
		return this;
	}
}
class GfmlType_ {//_20101107
	public static final String 
		  AnyKey	= "gfml.any"
		, StringKey	= "gfml.String"
		, NullKey	= "gfml.null"
		;
	public static final GfmlType Root		= null;
	public static final GfmlType String		= new_(StringKey, StringKey);
	public static final GfmlType Null		= new_(NullKey, NullKey);

	@gplx.Internal protected static GfmlType new_(String key, String ndeName) {return new GfmlType().ctor_GfmlType_(key, ndeName);}
	@gplx.Internal protected static GfmlType new_any_() {return new_(AnyKey, AnyKey);}
	@gplx.Internal protected static String MakeKey(String ownerKey, String ndeName) {return String_.Concat(ownerKey, "/", ndeName);}
}
