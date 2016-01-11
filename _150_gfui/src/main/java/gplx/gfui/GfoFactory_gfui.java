package gplx.gfui; import gplx.*;
public class GfoFactory_gfui {
	public static void Btn_MinWin(GfuiElem owner, GfoMsg appWinMsg) {
		GfuiBtn_.msg_("minWin", owner, GfoMsg_.chain_(appWinMsg, GfuiWin.Invk_Minimize)).Text_("_").TipText_("minmize window").Width_(20);
	}
	public static void Btn_MinWin2(GfuiElem owner) {
		GfuiBtn_.msg_("minWin", owner, GfoMsg_.root_(".", GfuiElemBase.Invk_OwnerWin_cmd, GfuiWin.Invk_Minimize)).Text_("_").TipText_("minmize window").Width_(20);
	}
	public static void Btn_MoveBox(GfuiElem owner, GfuiElem target) {
		GfuiElem rv = GfuiBtn_.new_("moveBox").Owner_(owner).Text_("*").TipText_("move box").Width_(20);
		GfuiMoveElemBnd bnd = GfuiMoveElemBnd.new_();
		bnd.TargetElem_set(target);
		rv.Inject_(bnd);			
	}
	public static GfuiBtn Btn_QuitWin3(GfuiElem owner) {
		return (GfuiBtn)GfuiBtn_.msg_("quitWin", owner, GfoMsg_.root_(".", GfuiElemBase.Invk_OwnerWin_cmd, GfuiWin.Invk_Quit)).Text_("X").TipText_("quit win").Width_(20);
	}
	public static void Btn_QuitWin2(GfuiElem owner, GfoMsg quitMsg) {
		GfuiBtn_.msg_("quitWin", owner, quitMsg).Text_("X").TipText_("quit win").Width_(20);
	}
	public static final GfoFactory_gfui Instance = new GfoFactory_gfui(); GfoFactory_gfui() {}
}
