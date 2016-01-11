package gplx.gfui; import gplx.*;
public class GfuiBnd_win_host {
	public GfuiWin HosterWin() {return hosterWin;} GfuiWin hosterWin;
	public GfuiElem HosteeBox() {return hosteeBox;} GfuiElem hosteeBox;
	public GfuiWin OwnerWin()  {return ownerWin;} GfuiWin ownerWin;
	public static GfuiBnd_win_host new_(GfuiElem elemToHost, GfuiWin ownerForm) {
		GfuiBnd_win_host rv = new GfuiBnd_win_host();
		rv.ctor_(elemToHost, ownerForm);
		return rv;
	}
	void ctor_(GfuiElem elemToHost, GfuiWin ownerForm) {
		this.hosteeBox = elemToHost;
		this.ownerWin = ownerForm;
		this.hosterWin = GfuiWin_.toaster_("hosterWin_" + elemToHost.Key_of_GfuiElem(), ownerForm);

		hosterWin.IptBnds().EventsToFwd_set(IptEventType_.None);
		hosterWin.Size_(hosteeBox.Size());
		hosteeBox.Owner_(hosterWin);
		hosterWin.TaskbarVisible_(false);
		hosterWin.TaskbarParkingWindowFix(ownerForm);	// else ContextMenu shows up as WindowsFormsParkingWindow
	}
}	
