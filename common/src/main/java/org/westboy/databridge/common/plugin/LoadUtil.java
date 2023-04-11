package org.westboy.databridge.common.plugin;

import java.util.HashMap;
import java.util.Map;

import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.common.errorcode.FrameworkErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 插件加载工具类
 * <p>
 * 大体上分为Reader、Transformer和Writer三种插件类型
 *
 * @author mumu
 * @since 2023/4/2 12:39
 */
public final class LoadUtil {

    private static final String PLUGIN_TYPE_NAME_FORMAT = "plugin.{}.{}";
    private static Map<String, JarLoader> jarLoaderCenter = new HashMap<>();

    @Getter
    @AllArgsConstructor
    private enum ContainerType {
        JOB("Job"),
        TASK("Task");
        private final String value;
    }

    public static synchronized JarLoader getJarLoader(PluginType pluginType, String pluginName) {
        String pluginKey = genenratePluginKey(pluginType, pluginName);
        JarLoader jarLoader = jarLoaderCenter.get(pluginKey);
        if(ObjUtil.isNull(jarLoader)) {
            Config pluginConfig = getPluginConfig(pluginType, pluginName);
            String pluginPath = pluginConfig.get("path", String.class);
            if(StrUtil.isBlank(pluginPath)) {
                String description = StrUtil.format("[{}]插件路径[{}]非法", pluginType, pluginName);
                throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.PLUGIN_LOAD_ERROR, description);
            }
            jarLoader = new JarLoader(new String[]{pluginPath});
            jarLoaderCenter.put(pluginKey, jarLoader);
        }
        return jarLoader;
    }

    public static AbstractJobPlugin loadJobPlugin(PluginType pluginType, String pluginName) {

    }

    public static AbstractTaskPlugin loadTaskPlugin(PluginType pluginType, String pluginName) {

    }

    private static Config getPluginConfig(PluginType pluginType, String pluginName) {
        return null;
    }

    private static String genenratePluginKey(PluginType pluginType, String pluginName) {
        return StrUtil.format(PLUGIN_TYPE_NAME_FORMAT, pluginType.getName(), pluginName);
    }

    @SuppressWarnings("unchecked")
    private static synchronized Class<? extends AbstractPlugin> loadPluginClass(PluginType pluginType, String pluginName, ContainerType containerType) {
        Config pluginConfig = getPluginConfig(pluginType, pluginName);
        JarLoader jarLoader = getJarLoader(pluginType, pluginName);
        try {
            String className = pluginConfig.get("class", String.class) + "$" + containerType.getValue();
            return (Class<? extends AbstractPlugin>)jarLoader.loadClass(className);
        } catch(Exception e) {
            throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.RUNTIME_ERROR, e);
        }
    }
}