package io.fouad.examples.qrcl;

import io.quarkus.redis.client.RedisClientName;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.vertx.redis.client.Command;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

@QuarkusMain
public class Launcher implements QuarkusApplication {
    
    @Inject @RedisClientName("query") RedisDataSource queryRedisDataSource;
    @Inject @RedisClientName("subscribe") RedisDataSource subscribeRedisDataSource;
    
    private static final int ITERATIONS_COUNT = 50;
    private static final int EACH_ITERATION_SUBSCRIPTIONS_COUNT = 200;
    
    @Override
    public int run(String... args) {
        var response = queryRedisDataSource.execute(Command.INFO, "clients");
        int openConnectionsCount = extractConnectedClientsCount(response.toString());
        int maxConnectionsCount = extractMaxClientsCount(response.toString());
        System.out.printf("At startup: connected_clients: %d | maxclients: %d%n",
                          openConnectionsCount, maxConnectionsCount); // connected_clients is 1, for the query connection
        
        var pubSubCommands = subscribeRedisDataSource.pubsub(String.class);
        
        for (int i = 0; i < ITERATIONS_COUNT; i++) {
            var clientIds = new ArrayList<UUID>();
            var unSubscribers = new ArrayList<Runnable>();
            for (int j = 0; j < EACH_ITERATION_SUBSCRIPTIONS_COUNT; j++) {
                if (i == ITERATIONS_COUNT - 1 && j == EACH_ITERATION_SUBSCRIPTIONS_COUNT - 1) {
                    // for the connection #10_000, we will defer it so that we can query "info clients"
                    break;
                }
                
                var clientId = UUID.randomUUID();
                clientIds.add(clientId);
                Runnable unSubscriber = pubSubCommands.subscribe(clientId.toString(), s -> {})::unsubscribe;
                unSubscribers.add(unSubscriber);
            }
            
            response = queryRedisDataSource.execute(Command.INFO, "clients");
            openConnectionsCount = extractConnectedClientsCount(response.toString());
            System.out.printf("%d clients #(%d-%d) have subscribed -> connected_clients: %d%n",
                              clientIds.size(), i * EACH_ITERATION_SUBSCRIPTIONS_COUNT + 1,
                              i * EACH_ITERATION_SUBSCRIPTIONS_COUNT + EACH_ITERATION_SUBSCRIPTIONS_COUNT,
                              openConnectionsCount);
            
            for (int j = 0; j < EACH_ITERATION_SUBSCRIPTIONS_COUNT; j++) {
                if (i == ITERATIONS_COUNT - 1 && j == EACH_ITERATION_SUBSCRIPTIONS_COUNT - 1) {
                    // for the connection #10_000, we will defer it so that we can query "info clients"
                    break;
                }
                unSubscribers.get(j).run();
            }
            
            response = queryRedisDataSource.execute(Command.INFO, "clients");
            openConnectionsCount = extractConnectedClientsCount(response.toString());
            System.out.printf("%d clients #(%d-%d) have unSubscribed -> connected_clients: %d%n",
                              clientIds.size(), i * EACH_ITERATION_SUBSCRIPTIONS_COUNT + 1,
                              i * EACH_ITERATION_SUBSCRIPTIONS_COUNT + EACH_ITERATION_SUBSCRIPTIONS_COUNT,
                              openConnectionsCount);
        }
        
        System.out.printf("Now that we have %d open connections and max clients Redis can handle is %d, we try one more subscription...%n",
                          openConnectionsCount, maxConnectionsCount);
        
        try {
            pubSubCommands.subscribe(UUID.randomUUID().toString(), s -> {});
            System.out.printf("Client #%d has subscribed successfully%n",
                              ITERATIONS_COUNT * EACH_ITERATION_SUBSCRIPTIONS_COUNT);
        } catch (Exception e) {
            System.out.printf("Client #%d failed to subscribe%n",
                              ITERATIONS_COUNT * EACH_ITERATION_SUBSCRIPTIONS_COUNT);
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public static void main(String... args) {
        Quarkus.run(Launcher.class, args);
    }
    
    private static final Pattern PATTERN_CONNECTED_CLIENTS = Pattern.compile("^.+connected_clients:(\\d+).+$", Pattern.DOTALL);
    private static final Pattern PATTERN_MAX_CLIENTS = Pattern.compile("^.+maxclients:(\\d+).+$", Pattern.DOTALL);
    
    private static int extractConnectedClientsCount(String infoText) {
        var matcher = PATTERN_CONNECTED_CLIENTS.matcher(infoText);
        if (matcher.matches()) return Integer.parseInt(matcher.group(1));
        throw new IllegalArgumentException();
    }
    
    private static int extractMaxClientsCount(String infoText) {
        var matcher = PATTERN_MAX_CLIENTS.matcher(infoText);
        if (matcher.matches()) return Integer.parseInt(matcher.group(1));
        throw new IllegalArgumentException();
    }
}