package client_UPD;

import client_UDP.Client;
import client_UDP.Server;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author xcomi
 */
public class UDPTest {
    
    Client client;
    
    /**
     *
     * @throws SocketException
     * @throws UnknownHostException
     */
    @Before
    public void setup() throws SocketException, UnknownHostException {
        
        new Server().start();
        
        client = new Client();
        
    }
    
    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() throws IOException {
        
        String echo = client.send("Hello Server");
        
        assertEquals("Hello Server", echo);
        
        echo = client.send("Server is working");
        
        assertFalse(echo.equals("Hello Server"));
        
    }
    
    @After
    public void tearDown() throws IOException {
        
        client.send("end");
        
        client.close();
    }
    
}
