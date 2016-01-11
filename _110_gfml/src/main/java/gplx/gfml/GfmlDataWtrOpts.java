package gplx.gfml; import gplx.*;
public class GfmlDataWtrOpts {//_20101121
	public static final String Key_const = "GfmlDataWtrOpts";
	public String KeyedSpr() {return keyedSeparator;} public GfmlDataWtrOpts KeyedSeparator_(String val) {keyedSeparator = val; return this;} private String keyedSeparator = " ";
	public boolean IndentNodes() {return indentNodes;} public GfmlDataWtrOpts IndentNodesOn_() {indentNodes = true; return this;} private boolean indentNodes;
	public boolean IgnoreNullNames() {return ignoreNullNames;} public GfmlDataWtrOpts IgnoreNullNamesOn_() {ignoreNullNames = true; return this;} private boolean ignoreNullNames;
        public static final GfmlDataWtrOpts Instance = new GfmlDataWtrOpts();
	public static GfmlDataWtrOpts new_() {return new GfmlDataWtrOpts();} GfmlDataWtrOpts() {}
	public static GfmlDataWtrOpts cast(Object obj) {try {return (GfmlDataWtrOpts)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfmlDataWtrOpts.class, obj);}}
}
