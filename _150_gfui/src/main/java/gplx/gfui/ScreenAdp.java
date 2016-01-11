package gplx.gfui; import gplx.*;
public class ScreenAdp {//_20100610
	public int Index() {return index;} int index;
	public RectAdp Rect() {return bounds;} RectAdp bounds = RectAdp_.Zero;
	public SizeAdp Size() {return bounds.Size();}
	public int Width() {return bounds.Width();}
	public int Height() {return bounds.Height();}		
	public PointAdp Pos() {return bounds.Pos();}
	public int X() {return bounds.X();}
	public int Y() {return bounds.Y();}
			
	@gplx.Internal protected ScreenAdp(int index, RectAdp bounds) {
		this.index = index; this.bounds = bounds;
	}
}
