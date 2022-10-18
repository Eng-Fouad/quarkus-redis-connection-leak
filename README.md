## quarkus-redis-connection-leak

This project demonstrates a connection leak in Redis client used in Quarkus:
[Redis client connection leak when using Pub/Sub](https://github.com/quarkusio/quarkus/issues/28656)

---

Run the sample with:

    ./mvnw quarkus:dev

Output:

    At startup: connected_clients: 1 | maxclients: 10000
    200 clients #(1-200) have subscribed -> connected_clients: 201
    200 clients #(1-200) have unSubscribed -> connected_clients: 201
    200 clients #(201-400) have subscribed -> connected_clients: 401
    200 clients #(201-400) have unSubscribed -> connected_clients: 401
    200 clients #(401-600) have subscribed -> connected_clients: 601
    200 clients #(401-600) have unSubscribed -> connected_clients: 601
    200 clients #(601-800) have subscribed -> connected_clients: 801
    200 clients #(601-800) have unSubscribed -> connected_clients: 801
    200 clients #(801-1000) have subscribed -> connected_clients: 1001
    200 clients #(801-1000) have unSubscribed -> connected_clients: 1001
    200 clients #(1001-1200) have subscribed -> connected_clients: 1201
    200 clients #(1001-1200) have unSubscribed -> connected_clients: 1201
    200 clients #(1201-1400) have subscribed -> connected_clients: 1401
    200 clients #(1201-1400) have unSubscribed -> connected_clients: 1401
    200 clients #(1401-1600) have subscribed -> connected_clients: 1601
    200 clients #(1401-1600) have unSubscribed -> connected_clients: 1601
    200 clients #(1601-1800) have subscribed -> connected_clients: 1801
    200 clients #(1601-1800) have unSubscribed -> connected_clients: 1801
    200 clients #(1801-2000) have subscribed -> connected_clients: 2001
    200 clients #(1801-2000) have unSubscribed -> connected_clients: 2001
    200 clients #(2001-2200) have subscribed -> connected_clients: 2201
    200 clients #(2001-2200) have unSubscribed -> connected_clients: 2201
    200 clients #(2201-2400) have subscribed -> connected_clients: 2401
    200 clients #(2201-2400) have unSubscribed -> connected_clients: 2401
    200 clients #(2401-2600) have subscribed -> connected_clients: 2601
    200 clients #(2401-2600) have unSubscribed -> connected_clients: 2601
    200 clients #(2601-2800) have subscribed -> connected_clients: 2801
    200 clients #(2601-2800) have unSubscribed -> connected_clients: 2801
    200 clients #(2801-3000) have subscribed -> connected_clients: 3001
    200 clients #(2801-3000) have unSubscribed -> connected_clients: 3001
    200 clients #(3001-3200) have subscribed -> connected_clients: 3201
    200 clients #(3001-3200) have unSubscribed -> connected_clients: 3201
    200 clients #(3201-3400) have subscribed -> connected_clients: 3401
    200 clients #(3201-3400) have unSubscribed -> connected_clients: 3401
    200 clients #(3401-3600) have subscribed -> connected_clients: 3601
    200 clients #(3401-3600) have unSubscribed -> connected_clients: 3601
    200 clients #(3601-3800) have subscribed -> connected_clients: 3801
    200 clients #(3601-3800) have unSubscribed -> connected_clients: 3801
    200 clients #(3801-4000) have subscribed -> connected_clients: 4001
    200 clients #(3801-4000) have unSubscribed -> connected_clients: 4001
    200 clients #(4001-4200) have subscribed -> connected_clients: 4201
    200 clients #(4001-4200) have unSubscribed -> connected_clients: 4201
    200 clients #(4201-4400) have subscribed -> connected_clients: 4401
    200 clients #(4201-4400) have unSubscribed -> connected_clients: 4401
    200 clients #(4401-4600) have subscribed -> connected_clients: 4601
    200 clients #(4401-4600) have unSubscribed -> connected_clients: 4601
    200 clients #(4601-4800) have subscribed -> connected_clients: 4801
    200 clients #(4601-4800) have unSubscribed -> connected_clients: 4801
    200 clients #(4801-5000) have subscribed -> connected_clients: 5001
    200 clients #(4801-5000) have unSubscribed -> connected_clients: 5001
    200 clients #(5001-5200) have subscribed -> connected_clients: 5201
    200 clients #(5001-5200) have unSubscribed -> connected_clients: 5201
    200 clients #(5201-5400) have subscribed -> connected_clients: 5401
    200 clients #(5201-5400) have unSubscribed -> connected_clients: 5401
    200 clients #(5401-5600) have subscribed -> connected_clients: 5601
    200 clients #(5401-5600) have unSubscribed -> connected_clients: 5601
    200 clients #(5601-5800) have subscribed -> connected_clients: 5801
    200 clients #(5601-5800) have unSubscribed -> connected_clients: 5801
    200 clients #(5801-6000) have subscribed -> connected_clients: 6001
    200 clients #(5801-6000) have unSubscribed -> connected_clients: 6001
    200 clients #(6001-6200) have subscribed -> connected_clients: 6201
    200 clients #(6001-6200) have unSubscribed -> connected_clients: 6201
    200 clients #(6201-6400) have subscribed -> connected_clients: 6401
    200 clients #(6201-6400) have unSubscribed -> connected_clients: 6401
    200 clients #(6401-6600) have subscribed -> connected_clients: 6601
    200 clients #(6401-6600) have unSubscribed -> connected_clients: 6601
    200 clients #(6601-6800) have subscribed -> connected_clients: 6801
    200 clients #(6601-6800) have unSubscribed -> connected_clients: 6801
    200 clients #(6801-7000) have subscribed -> connected_clients: 7001
    200 clients #(6801-7000) have unSubscribed -> connected_clients: 7001
    200 clients #(7001-7200) have subscribed -> connected_clients: 7201
    200 clients #(7001-7200) have unSubscribed -> connected_clients: 7201
    200 clients #(7201-7400) have subscribed -> connected_clients: 7401
    200 clients #(7201-7400) have unSubscribed -> connected_clients: 7401
    200 clients #(7401-7600) have subscribed -> connected_clients: 7601
    200 clients #(7401-7600) have unSubscribed -> connected_clients: 7601
    200 clients #(7601-7800) have subscribed -> connected_clients: 7801
    200 clients #(7601-7800) have unSubscribed -> connected_clients: 7801
    200 clients #(7801-8000) have subscribed -> connected_clients: 8001
    200 clients #(7801-8000) have unSubscribed -> connected_clients: 8001
    200 clients #(8001-8200) have subscribed -> connected_clients: 8201
    200 clients #(8001-8200) have unSubscribed -> connected_clients: 8201
    200 clients #(8201-8400) have subscribed -> connected_clients: 8401
    200 clients #(8201-8400) have unSubscribed -> connected_clients: 8401
    200 clients #(8401-8600) have subscribed -> connected_clients: 8601
    200 clients #(8401-8600) have unSubscribed -> connected_clients: 8601
    200 clients #(8601-8800) have subscribed -> connected_clients: 8801
    200 clients #(8601-8800) have unSubscribed -> connected_clients: 8801
    200 clients #(8801-9000) have subscribed -> connected_clients: 9001
    200 clients #(8801-9000) have unSubscribed -> connected_clients: 9001
    200 clients #(9001-9200) have subscribed -> connected_clients: 9201
    200 clients #(9001-9200) have unSubscribed -> connected_clients: 9201
    200 clients #(9201-9400) have subscribed -> connected_clients: 9401
    200 clients #(9201-9400) have unSubscribed -> connected_clients: 9401
    200 clients #(9401-9600) have subscribed -> connected_clients: 9601
    200 clients #(9401-9600) have unSubscribed -> connected_clients: 9601
    200 clients #(9601-9800) have subscribed -> connected_clients: 9801
    200 clients #(9601-9800) have unSubscribed -> connected_clients: 9801
    199 clients #(9801-10000) have subscribed -> connected_clients: 10000
    199 clients #(9801-10000) have unSubscribed -> connected_clients: 10000
    Now that we have 10000 open connections and max clients Redis can handle is 10000, we try one more subscription...
    Client #10000 failed to subscribe
    io.smallrye.mutiny.TimeoutException
    at io.smallrye.mutiny.operators.uni.UniBlockingAwait.await(UniBlockingAwait.java:64)
    at io.smallrye.mutiny.groups.UniAwait.atMost(UniAwait.java:65)
    at io.quarkus.redis.runtime.datasource.BlockingPubSubCommandsImpl.subscribe(BlockingPubSubCommandsImpl.java:51)
    at io.quarkus.redis.runtime.datasource.BlockingPubSubCommandsImpl.subscribe(BlockingPubSubCommandsImpl.java:31)
    at io.fouad.examples.qrcl.Launcher.run(Launcher.java:77)
    at io.fouad.examples.qrcl.Launcher_Subclass.run$$superforward1(Unknown Source)
    at io.fouad.examples.qrcl.Launcher_Subclass$$function$$1.apply(Unknown Source)
    at io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:54)
    at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.proceed(InvocationInterceptor.java:62)
    at io.quarkus.arc.runtime.devconsole.InvocationInterceptor.monitor(InvocationInterceptor.java:51)
    at io.quarkus.arc.runtime.devconsole.InvocationInterceptor_Bean.intercept(Unknown Source)
    at io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:42)
    at io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:41)
    at io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:33)
    at io.fouad.examples.qrcl.Launcher_Subclass.run(Unknown Source)
    at io.fouad.examples.qrcl.Launcher_ClientProxy.run(Unknown Source)
    at io.quarkus.runtime.ApplicationLifecycleManager.run(ApplicationLifecycleManager.java:131)
    at io.quarkus.runtime.Quarkus.run(Quarkus.java:69)
    at io.quarkus.runtime.Quarkus.run(Quarkus.java:42)
    at io.fouad.examples.qrcl.Launcher.main(Launcher.java:90)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
    at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.base/java.lang.reflect.Method.invoke(Method.java:568)
    at io.quarkus.runner.bootstrap.StartupActionImpl$1.run(StartupActionImpl.java:103)
    at java.base/java.lang.Thread.run(Thread.java:833)