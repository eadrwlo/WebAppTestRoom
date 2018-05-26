
import java.io.IOException;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eadrwlo
 */
@ServerEndpoint("/rooms/{roomID}")
public class EchoServer {

    @OnOpen
    public void onOpen(Session session, @PathParam("roomID") final String roomID) {
        System.out.println(session.getId() + "has opened connection!");
        try {
            session.getBasicRemote().sendText("Connection established! Room ID = " + roomID);
            SessionHandler.addSession(session);
            session.getUserProperties().put("roomID", roomID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnMessage
    public void onMessage(String message, Session session) {
       System.out.println("Message from " + session.getId() + ": " + message);
       //SessionHandler.sendToAllConnectedSessions(message);
       SessionHandler.sendToAllConnectedSessionsInRoom(session.getUserProperties().get("roomID").toString(), message);
       } 

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " +session.getId()+" has ended");
        SessionHandler.removeSession(session);
    }
    
}
