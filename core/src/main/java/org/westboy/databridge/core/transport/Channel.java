package org.westboy.databridge.core.transport;

import org.westboy.databridge.common.config.Config;

public abstract class Channel {
   
    private Config allConfig;

    public Channel(Config allConfig) {
        this.allConfig = allConfig;
    }
    
    
    
}
