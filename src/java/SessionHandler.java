
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eadrwlo
 */
public class SessionHandler {
    private static final Set<Session> sessions = new HashSet<>();
    public static void addSession(Session session)
    {
        sessions.add(session);
    }
    public static void removeSession(Session session)
    {
        sessions.remove(session);
    }
    public static void sendToSession(Session session, String msg)
    {
        
    }
    public static void sendToAllConnectedSessions(String msg)
    {
        for (Session session : sessions)
        {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException ex) {
                Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public static void sendToAllConnectedSessionsInRoom(String roomID, String message)
    {
        for (Session session : sessions)
        {
            if(session.getUserProperties().get("roomID").equals(roomID))
            {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException ex) {
                    Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
