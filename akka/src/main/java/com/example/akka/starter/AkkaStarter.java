package com.example.akka.starter;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.akka.actor.FriendActor;
import com.example.akka.common.RemoteConstant;
import com.example.common.util.NetUtils;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class AkkaStarter {

    public static ActorSystem actorSystem;
    @Getter
    private static String actorSystemAddress;

    private static final String AKKA_PATH = "akka://%s@%s/user/%s";

    public static void init() {
        // 启动 ActorSystem
        int port = 10086;

        Stopwatch stopwatch = Stopwatch.createStarted();

        String SERVER_AKKA_CONFIG_NAME = "oms-server.akka.conf";
        Map<String, Object> overrideConfig = Maps.newHashMap();
        String localIP = NetUtils.getLocalHost();
        overrideConfig.put("akka.remote.artery.canonical.hostname", localIP);
        overrideConfig.put("akka.remote.artery.canonical.port", port);
        String actorSystemAddress = localIP + ":" + port;
        log.info("[PowerJob] akka-remote server address: {}", actorSystemAddress);

        Config akkaBasicConfig = ConfigFactory.load(SERVER_AKKA_CONFIG_NAME);
        Config akkaFinalConfig = ConfigFactory.parseMap(overrideConfig).withFallback(akkaBasicConfig);
        actorSystem = ActorSystem.create(RemoteConstant.SERVER_ACTOR_SYSTEM_NAME, akkaFinalConfig);

        actorSystem.actorOf(Props.create(FriendActor.class), RemoteConstant.SERVER_FRIEND_ACTOR_NAME);

        log.info("[PowerJob] PowerJob's akka system started successfully, using time {}.", stopwatch);
    }

}
