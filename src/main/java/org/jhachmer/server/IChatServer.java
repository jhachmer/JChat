package org.jhachmer.server;

import org.jhachmer.client.IChatClient;
import org.jhachmer.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IChatServer extends Remote {
    void login(String username, IChatClient newClient) throws RemoteException;

    void logout(String username) throws RemoteException;

    void send(Message message) throws RemoteException;

    boolean checkNameForAvailability(String newUsername) throws RemoteException;
}
