package com.example.akka.single;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ReceiveActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return new ReceiveBuilder().match(Msg.class,t->{
            System.out.println(t);
        }).build();
    }
}
