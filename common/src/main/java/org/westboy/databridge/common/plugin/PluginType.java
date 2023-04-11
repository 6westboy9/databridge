import org.westboy.databridge.common.config.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 插件类型
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
@Getter
@AllArgsConstructor
public enum PluginType {

    READER("reader"),
    WRITER("writer"),
    HANDLER("handler");

    private final String name;

}