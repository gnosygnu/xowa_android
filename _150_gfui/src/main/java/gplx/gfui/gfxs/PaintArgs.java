package gplx.gfui.gfxs; import gplx.*; import gplx.gfui.*;
public class PaintArgs {//_20101212
	public GfxAdp Graphics() {return graphics;} GfxAdp graphics;
	public RectAdp ClipRect() {return clipRect;} RectAdp clipRect;

	public static PaintArgs cast(Object obj) {try {return (PaintArgs)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, PaintArgs.class, obj);}}
	public static PaintArgs new_(GfxAdp graphics, RectAdp clipRect) {
		PaintArgs rv = new PaintArgs();
		rv.graphics = graphics; rv.clipRect = clipRect;
		return rv;
	}
}
