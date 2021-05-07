package com.example.akka.single;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainSingle {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("test-system");
        ActorRef sender = actorSystem.actorOf(Props.create(ReceiveActor.class), "sender");
        sender.tell(new Msg("nice day"),sender);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actorSystem.terminate();
    }
}
