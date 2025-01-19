package org.jhachmer.gui;

import org.jhachmer.client.ChatClient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnterListener extends KeyAdapter {
    ChatClient client;
    ChatFrame gui;

    public EnterListener(ChatClient client, ChatFrame gui) {
        this.client = client;
        this.gui = gui;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            client.sendTextToChat(gui.getInput().getText());
            gui.getInput().setText("");
        }
    }
}
