package edu.upc.essi.gps.ecommerce;

public class Screen {

    private String lastMessage=null;

    public void println(String msg){
        System.out.println(msg);
        this.lastMessage = msg;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
