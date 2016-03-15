package com.read.test.pattern.command;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-16
 * Time: 下午3:38
 * 调用者
 */
public class Involker {

    private Command command;

    public Involker(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
