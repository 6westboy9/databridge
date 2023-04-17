package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.element.Record;

public interface RecordReceiver {
    
    Record get();

    void shutdown();
    
}
