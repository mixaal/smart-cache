package net.mikc.storage.discovery;

import java.util.List;

public interface IDiscoveryProvider {
    /**
     * Provide list of active peers.
     *
     * @return list of active peers
     */
    List<String> getPeers();
}
