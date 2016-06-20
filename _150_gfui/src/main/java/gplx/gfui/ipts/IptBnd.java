package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
import gplx.core.interfaces.*;
public interface IptBnd extends SrlAble {
	String			Key();
	List_adp			Ipts();
	IptEventType	EventTypes();
	void			Exec(IptEventData iptData);
}
