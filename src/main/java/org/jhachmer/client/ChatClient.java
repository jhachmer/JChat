package org.jhachmer.client;

import org.jhachmer.*;
import org.jhachmer.gui.ChatFrame;
import org.jhachmer.gui.EnterListener;
import org.jhachmer.gui.ExitListener;
import org.jhachmer.server.IChatServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements IChatClient {
    ChatFrame gui;
    String name;
    IChatServer server;
    String serverUrl;

    public ChatClient(String name, String host) throws RemoteException, RuntimeException, MalformedURLException, NotBoundException {
        this.name = name;
        this.serverUrl = "rmi://" + host + "/ChatServer";
        server = (IChatServer) Naming.lookup(serverUrl);
        if (!server.checkNameForAvailability(name)) {
            throw new RuntimeException("user already exists");
        }
        gui = new ChatFrame("Chat");
        gui.getInput().addKeyListener(new EnterListener(this, this.gui));
        gui.addWindowListener(new ExitListener(this));
        connect();
    }

    private void connect() {
        try {
            server.login(name, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            server.logout(this.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTextToChat(String text) {
        try {
            server.send(new Message(this.name, text));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEnter(String name) {
        gui.getOutput().append(name + " entered\n");
    }

    @Override
    public void receiveExit(String name) throws RemoteException {
        gui.getOutput().append(name + " exited\n");
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        gui.getOutput().append(message.name() + ": " + message.text() + "\n");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java ChatClient <name> <server url>");
        }
        try {
            new ChatClient(args[0], args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
