/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrix.binarywebsocketserver.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Василий
 */
@Named(value = "endpoint")
@SessionScoped
@ServerEndpoint("/endpoint")
public class BinaryWebSocketServer implements Serializable{

    private
static final Set<Session> sessions =
Collections.synchronizedSet(new HashSet<Session>());
   
    
    
    
    
    @OnMessage
    public void onMessage(String byteBuffer) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText("Hello from server");//sendBinary(byteBuffer);
             System.out.println(byteBuffer); 
           //FacesContext.getCurrentInstance().getViewRoot().
            // FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/hello");
             
            } catch (IOException ex) {
                Logger.getLogger(BinaryWebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
   
@OnOpen
public void onOpen(Session session) {
   
    sessions.add(session);
}

@OnClose
public void onClose(Session session) {
  sessions.remove(session);
}

public void sendString(){
    for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(session.getId());
            } catch (IOException ex) {
                Logger.getLogger(BinaryWebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   
}

    @OnError
    public void onError(Throwable t) {
    }


    
    
}
