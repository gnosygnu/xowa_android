package gplx.gfui; import gplx.*;
public class GfuiQuitMode {
	public int Val() {return val;} int val;
	GfuiQuitMode(int val) {this.val = val;}
	public static final GfuiQuitMode ExitApp		= new GfuiQuitMode(1);
	public static final GfuiQuitMode Destroy		= new GfuiQuitMode(2);
	public static final GfuiQuitMode Suspend		= new GfuiQuitMode(3);
	public static final String
		    Destroy_cmd			= "destroy"
		,	Suspend_cmd			= "suspend"
		,	SuspendApp_cmd		= "suspendApp"	// TODO: merge with suspend; needs Msg Addressing (*.suspend vs app.suspend)
		;
	public static void Exec(GfoInvkAble invk, GfuiQuitMode stopMode) {
		int val = stopMode.Val();
		if		(val == GfuiQuitMode.Destroy.Val())		GfoInvkAble_.InvkCmd(invk, GfuiQuitMode.Destroy_cmd);
		else if	(val == GfuiQuitMode.Suspend.Val())		GfoInvkAble_.InvkCmd(invk, GfuiQuitMode.Suspend_cmd);
		else if	(val == GfuiQuitMode.ExitApp.Val())		GfuiEnv_.Exit();
	}
}
