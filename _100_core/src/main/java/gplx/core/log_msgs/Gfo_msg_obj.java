package gplx.core.log_msgs; import gplx.*; import gplx.core.*;
public interface Gfo_msg_obj {
	String Key_str();
	Gfo_msg_obj Subs_get_by_key(String sub_key);
}
