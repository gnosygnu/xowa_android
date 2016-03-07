package gplx.gfui; import gplx.*;
public class GfuiWin_ {//_20101212
	public static final String 
		  InitKey_winType			= "winType"
		, InitKey_winType_toaster	= "toaster"
		, InitKey_winType_app		= "app"
		, InitKey_winType_tool		= "tool"
		;
	public static GfuiWin as_(Object obj) {return obj instanceof GfuiWin ? (GfuiWin)obj : null;}
	public static GfuiWin cast(Object obj) {try {return (GfuiWin)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfuiWin.class, obj);}}
	public static GfuiWin app_(String key)							{return bld_(key, InitKey_winType_app, new Keyval_hash());}
	public static GfuiWin tool_(String key)							{return bld_(key, InitKey_winType_tool, new Keyval_hash()).TaskbarVisible_(false);}
	public static GfuiWin sub_(String key, GfuiWin ownerWin)		{
		Keyval_hash ctorArgs = new Keyval_hash();
		if (ownerWin != null) ctorArgs.Add(GfuiElem_.InitKey_ownerWin, ownerWin);	// WORKAROUND/JAVA: null ownerWin will fail when adding to hash
		return bld_(key, InitKey_winType_tool, ctorArgs);
	}
	public static GfuiWin toaster_(String key, GfuiWin ownerWin)	{return bld_(key, InitKey_winType_toaster, new Keyval_hash().Add(GfuiElem_.InitKey_ownerWin, ownerWin));}
	static GfuiWin bld_(String key, String winType, Keyval_hash ctorArgs) {
		GfuiWin rv = new GfuiWin();
		rv.Key_of_GfuiElem_(key);
		ctorArgs.Add(InitKey_winType, winType)
			.Add(GfuiElem_.InitKey_focusAble, false);
		rv.ctor_GfuiBox_base(ctorArgs);
		return rv;
	}
	public static GfuiWin kit_(Gfui_kit kit, String key, GxwWin under, Keyval_hash ctorArgs) {
		GfuiWin rv = new GfuiWin();
		rv.ctor_kit_GfuiElemBase(kit, key, under, ctorArgs);
		return rv;
	}
}
class GfuiWinUtl {//_20101212
	@gplx.Internal protected static void Open_exec(GfuiWin win, List_adp loadList, GfuiElemBase owner) {
		for (int i = 0; i < owner.SubElems().Count(); i++) {
			GfuiElemBase sub = (GfuiElemBase)owner.SubElems().Get_at(i);
			sub.OwnerWin_(win);
			for (Object itmObj : loadList) {
				GfuiWinOpenAble itm = (GfuiWinOpenAble)itmObj;
				itm.Open_exec(win, owner, sub);
			}
			Open_exec(win, loadList, sub);
		}
	}
	@gplx.Internal protected static void SubElems_dispose(GfuiElem owner) {
		for (int i = 0; i < owner.SubElems().Count(); i++) {
			GfuiElem sub = (GfuiElem)owner.SubElems().Get_at(i);
			sub.Dispose();
			SubElems_dispose(sub);
		}
	}
}
interface GfuiWinOpenAble {//_20101212
	void Open_exec(GfuiWin win, GfuiElemBase owner, GfuiElemBase sub);
}
