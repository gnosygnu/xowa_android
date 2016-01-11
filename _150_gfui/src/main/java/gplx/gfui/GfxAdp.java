package gplx.gfui; import gplx.*;
//#{import
//#}
public interface GfxAdp extends Rls_able {//_20101212
	void DrawLine(PenAdp pen, PointAdp src, PointAdp trg);
	void DrawRect(PenAdp pen, int x, int y, int width, int height);
	void DrawRect(PenAdp pen, PointAdp location, SizeAdp size);
	void DrawRect(PenAdp pen, RectAdp rect);
	void FillRect(SolidBrushAdp brush, int x, int y, int width, int height);
	void DrawImage(ImageAdp image, PointAdp location);
	void DrawImage(ImageAdp img, int trg_x, int trg_y, int trg_w, int trg_h, int src_x, int src_y, int src_w, int src_h);
	void DrawStringXtn(String s, FontAdp font, SolidBrushAdp brush, float x, float y, float width, float height, GfxStringData sd);
	float[] MeasureStringXtn(String s, FontAdp font, GfxStringData sd);
}
