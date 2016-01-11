package gplx.xowa.guis.views.boots; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*; import gplx.xowa.guis.views.*;
//#{imports
import java.awt.*;
import java.awt.event.*;
//#}
public class Xog_splash_win implements Rls_able {
	//#{members
	private SplashScreen splash;
	private Graphics2D graphics; private boolean graphics_init = true;
	//#}
	public Xog_splash_win(boolean app_mode_is_gui) {
		//#{ctor
        if (app_mode_is_gui) {
	        this.splash = SplashScreen.getSplashScreen();
	        if (splash == null) System.out.println("SplashScreen.getSplashScreen() returned null");
        }
        //#}
	}
	public void Write(String msg) {
		//#{Write
		if (splash == null) return;
    	if (graphics_init) {
    		graphics_init = false;
	        if (graphics == null) {
	        	graphics = splash.createGraphics();
	        	if (graphics == null) System.out.println("graphics is null");
	        }        
    	}
    	if (graphics == null) return;
        graphics.setComposite(AlphaComposite.Clear);
        graphics.fillRect(120,140,200,40);
        graphics.setPaintMode();
        graphics.setColor(Color.BLACK);
        graphics.drawString(msg, 0, 0);
        splash.update();
    	//#}
	}
	public void Rls() {
		//#{Rls
		if (splash == null) return;
    	splash.close();
    	//#}
	}
}
