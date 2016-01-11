package gplx.core.net; import gplx.*; import gplx.core.*;
public interface Server_socket_adp {
	Server_socket_adp Ctor(int port);
	Socket_adp Accept();
	void Rls();
}
