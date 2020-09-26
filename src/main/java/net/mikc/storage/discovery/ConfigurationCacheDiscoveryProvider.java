package net.mikc.storage.discovery;

import net.mikc.storage.SmartCacheConfiguration;

import java.util.List;

public class ConfigurationCacheDiscoveryProvider implements IDiscoveryProvider {
    private final SmartCacheConfiguration configuration;

    public ConfigurationCacheDiscoveryProvider(SmartCacheConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<String> getPeers() {
        return configuration.getPeers();
    }
}
