package net.mikc.storage;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.mikc.storage.core.CacheManager;
import net.mikc.storage.core.ICacheManager;
import net.mikc.storage.core.InternalCacheManager;
import net.mikc.storage.resources.CacheResource;

public class SmartCacheApplication extends Application<SmartCacheConfiguration> {


    public static void main(final String[] args) throws Exception {
        new SmartCacheApplication().run(args);
    }

    @Override
    public String getName() {
        return "SmartCache";
    }

    @Override
    public void initialize(final Bootstrap<SmartCacheConfiguration> bootstrap) {
        // TODO: application initialization


    }

    @Override
    public void run(final SmartCacheConfiguration configuration,
                    final Environment environment) {
        final ICacheManager internalCacheManager = new InternalCacheManager();
        final ICacheManager defaultCacheManager = new CacheManager(configuration.getPeers(), internalCacheManager);
        environment.jersey().register(new CacheResource(defaultCacheManager, internalCacheManager));
    }

}
