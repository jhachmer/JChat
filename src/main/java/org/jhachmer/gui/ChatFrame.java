package org.jhachmer.gui;

import java.awt.*;

public class ChatFrame extends Frame {
    protected TextArea output;

    protected TextField input;

    public ChatFrame(String title) {
        super(title);
        this.setLayout(new BorderLayout());
        this.add("Center", output = new TextArea());
        this.output.setEditable(false);
        this.add("South", input = new TextField());
        this.pack();
        this.setVisible(true);
        this.input.requestFocus();
    }

    public TextArea getOutput() {
        return output;
    }

    public TextField getInput() {
        return input;
    }
}
