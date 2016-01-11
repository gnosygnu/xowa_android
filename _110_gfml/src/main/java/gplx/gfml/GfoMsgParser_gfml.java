package gplx.gfml; import gplx.*;
import gplx.core.gfo_regys.*;
public class GfoMsgParser_gfml implements GfoMsgParser {
	public GfoMsg ParseToMsg(String s) {return GfmlDataNde.XtoMsg(s);}
        public static final GfoMsgParser_gfml Instance = new GfoMsgParser_gfml(); GfoMsgParser_gfml() {}
}
