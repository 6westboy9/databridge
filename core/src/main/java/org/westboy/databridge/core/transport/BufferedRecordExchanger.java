package org.westboy.databridge.core.transport;

import java.util.ArrayList;
import java.util.List;

import org.westboy.databridge.common.element.Record;
import org.westboy.databridge.common.plugin.RecordReceiver;
import org.westboy.databridge.common.plugin.RecordSender;
import org.westboy.databridge.common.plugin.TaskPluginCollector;

public class BufferedRecordExchanger implements RecordSender, RecordReceiver {

    private final Channel channel;
    private final TaskPluginCollector taskPluginCollector;
    private final List<Record> buffer;

    public BufferedRecordExchanger(Channel channel, TaskPluginCollector taskPluginCollector) {
        this.channel = channel;
        this.taskPluginCollector = taskPluginCollector;
        
        
        this.buffer = new ArrayList<>();
    }




    @Override
    public Record get() {
       

        return null;
    }

    @Override
    public Record createRecord() {
      
        return null;
    }

    @Override
    public void send(Record record) {
       
    }

    @Override
    public void flush() {
       
    }

    @Override
    public void terminate() {
       
    }

    @Override
    public void shutdown() {
      
    }
    
}
