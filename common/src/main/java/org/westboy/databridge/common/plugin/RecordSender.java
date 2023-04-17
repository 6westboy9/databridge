package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.element.Record;

public interface RecordSender {
    
    Record createRecord();
    
    void send(Record record);

    void flush();

    void terminate();

    void shutdown();

}
