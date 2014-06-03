package ru.darklogic.jericho; /**
 * Created by abalashov on 6/3/14.
 */

import java.net.*;
import java.io.*;

/** TCP Socker server for 1 receive*/
public class TcpSock {
    protected ServerSocket sock;

    /** just bind server socket on particular port
     *
     * @param port tcp port to bind
     * @throws IOException
     */
    public void bind(int port) throws IOException {
            sock = new ServerSocket(port) ;
    }

    /** Get a new client connection and receive <b>one</b> message from it
     *
     * @return Received from a client String
     * @throws IOException
     */
    public String recv() throws IOException {
        Socket client = sock.accept();
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // TODO: We read only one line here! Probably we should read more
        String res = buf.readLine();
        client.close();
        return res;
    }

    public void close() throws IOException {
        if (sock.isBound()){
            sock.close();
        }
    }
}
