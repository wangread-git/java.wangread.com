package com.read.test.pattern.command;

/**
 * Created with IntelliJ IDEA.
 * User: yfwangrui
 * Date: 14-6-16
 * Time: обнГ3:40
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] argv) {
        Reciever reciever = new Reciever();
        Command command = new ConcreteCommand(reciever);
        Involker involker = new Involker(command);
        involker.execute();
    }
}