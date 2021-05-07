package com.example.akka.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.example.akka.entity.Food;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return new ReceiveBuilder()
                 .match(Food.class,this::receiveFood)
                 .matchAny(obj->log.warn("unknow msg: {}",obj))
                 .build();
    }


    public void receiveFood(Food food) {
        food.eat();
        getSender().tell("success",getSelf());

    }
}
