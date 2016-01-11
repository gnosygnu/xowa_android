package gplx.gfui; import gplx.*;
//#{import
import java.awt.Graphics2D;
//#}
public class GfxAdp_ {//_20101212
	@gplx.Internal protected static GfxAdp new_(Graphics2D graphics) {return GfxAdpBase.new_(graphics);}	//#<>Graphics~Graphics2D
	public static GfxAdp image_(ImageAdp image) {
		//#{image_
		Graphics2D graphics = (Graphics2D)((java.awt.Image)image.Under()).getGraphics();
		//#}
		return GfxAdpBase.new_(graphics);
	}
}
