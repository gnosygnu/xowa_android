package gplx.gfml; import gplx.*;
public interface GfmlItm extends GfmlObj, To_str_able {//_20101127
	GfmlTkn		KeyTkn(); String Key(); // Key() is alternative to Key().Val()
	GfmlType	Type();
	boolean		KeyedSubObj();
	int			SubObjs_Count();
	GfmlObj		SubObjs_GetAt(int i);
	void		SubObjs_Add(GfmlObj obj);
}
class GfmlItm_ {//_20101127
	public static GfmlItm as_(Object obj) {return obj instanceof GfmlItm ? (GfmlItm)obj : null;}
}
