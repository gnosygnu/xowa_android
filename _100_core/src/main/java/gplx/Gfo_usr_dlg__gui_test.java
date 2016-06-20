package gplx;
import gplx.core.lists.rings.*;
public class Gfo_usr_dlg__gui_test implements Gfo_usr_dlg__gui {
	public List_adp Warns() {return warns;} private final    List_adp warns = List_adp_.New();
	public List_adp Msgs() {return msgs;} private final    List_adp msgs = List_adp_.New();
	public Ring__string Prog_msgs() {return ring;} private final    Ring__string ring = new Ring__string().Max_(0);
	public void Clear() {msgs.Clear(); warns.Clear();}
	public void Write_prog(String text) {msgs.Add(text);}
	public void Write_note(String text) {msgs.Add(text);}
	public void Write_warn(String text) {warns.Add(text);} 
	public void Write_stop(String text) {msgs.Add(text);}
}
