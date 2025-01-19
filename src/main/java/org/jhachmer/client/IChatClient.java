package org.jhachmer.client;

import org.jhachmer.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatClient extends Remote {
    void receiveEnter(String name) throws RemoteException;

    void receiveExit(String name) throws RemoteException;

    void receiveMessage(Message message) throws RemoteException;
}
