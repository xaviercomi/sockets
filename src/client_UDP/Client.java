/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author xcomi
 */
public class Client {
    
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;
    
    public Client() throws SocketException, UnknownHostException {
        // global DatagramSocket and address of the server
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
        
    }
    // send messages to the server and return the response
    // first convert the string message into a byte array
    // create the datagram packet for sending messages
    // we send the message and convert the DatagramPacket into a receiving one
    // convert the bytes to a string and return the string
    public String send(String msg) throws IOException {
        
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }
    
    public void close() {
        socket.close();
    }
    
}
