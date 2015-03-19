package com.read.test.pattern.command;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-16
 * Time: ÏÂÎç3:35
 * ¾ßÌåÃüÁî
 */
public class ConcreteCommand implements Command {

    private Reciever reciever;

    public ConcreteCommand(Reciever reciever) {
        this.reciever = reciever;
    }

    @Override
    public void execute() {
        reciever.action();
    }
}
