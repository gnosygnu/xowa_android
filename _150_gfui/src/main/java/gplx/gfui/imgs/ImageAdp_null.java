package gplx.gfui.imgs; import gplx.*; import gplx.gfui.*;
import gplx.gfui.kits.core.*;
public class ImageAdp_null implements ImageAdp {
	public Gfui_kit Kit() {return Gfui_kit_.Mem();}
	public SizeAdp Size() {return SizeAdp_.Zero;}
	public int Width() {return 0;}
	public int Height() {return 0;}
	public Io_url Url() {return Io_url_.Empty;} public ImageAdp Url_(Io_url v) {return this;}
	public Object Under() {return null;}
	public boolean Disposed() {return disposed;} private boolean disposed = false;
	public void Rls() {disposed = true;}
	public void SaveAsBmp(Io_url url) {}
	public void SaveAsPng(Io_url url) {}
	public ImageAdp Extract_image(RectAdp src_rect, SizeAdp trg_size) {return Extract_image(src_rect.X(), src_rect.Y(), src_rect.Width(), src_rect.Height(), trg_size.Width(), trg_size.Height());}
	public ImageAdp Extract_image(int src_x, int src_y, int src_w, int src_h, int trg_w, int trg_h) {return this;}
	public ImageAdp Resize(int width, int height) {return this;}
	public static final    ImageAdp_null Instance = new ImageAdp_null(); ImageAdp_null() {}
}
