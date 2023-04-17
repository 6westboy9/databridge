package org.westboy.databridge.common.spi;

import java.util.List;

import org.westboy.databridge.common.BaseObject;
import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.common.plugin.AbstractJobPlugin;
import org.westboy.databridge.common.plugin.AbstractTaskPlugin;
import org.westboy.databridge.common.plugin.RecordSender;

/**
 * @author mumu
 * @since 2023/4/7 20:43
 */
public abstract class Reader extends BaseObject {

    public static abstract class Job extends AbstractJobPlugin {
        public abstract List<Config> split(int adviceNumber);
    }

    public static abstract class Task extends AbstractTaskPlugin {
        public abstract void startRead(RecordSender recordSender);
    }
}
