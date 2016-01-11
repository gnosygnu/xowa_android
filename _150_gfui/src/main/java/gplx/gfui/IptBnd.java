package gplx.gfui; import gplx.*;
import gplx.core.interfaces.*;
public interface IptBnd extends SrlAble {
	String			Key();
	List_adp			Ipts();
	IptEventType	EventTypes();
	void			Exec(IptEventData iptData);
}
