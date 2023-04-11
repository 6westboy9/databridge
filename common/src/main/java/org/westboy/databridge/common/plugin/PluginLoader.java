package org.westboy.databridge.common.plugin;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.common.errorcode.FrameworkErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 插件加载组件
 * <p>
 * 大体上分为Reader、Transformer和Writer三种插件类型
 *
 * @author mumu
 * @since 2023/4/2 12:39
 */
public final class PluginLoader {

    private static final String PLUGIN_TYPE_NAME_FORMAT = "plugin.{}.{}";
    private static PluginLoader INSTANCE;
    private final Map<String, JarLoader> jarLoaderHashMap = new HashMap<>();
    private final Config allConfig;
    private final Map<String, Config> pluginConfigs = new HashMap<>();

    private PluginLoader(Config allConfig) {
        this.allConfig = allConfig;
    }

    /**
     * 单例
     *
     * @param allConfig 全局配置
     * @return 单例插件组件
     */
    public static void init(Config allConfig) {
        if (ObjectUtil.isNull(INSTANCE)) {
            synchronized (PluginLoader.class) {
                if (ObjectUtil.isNull(INSTANCE)) {
                    INSTANCE = new PluginLoader(allConfig);
                }
            }
        }
    }

    /**
     * 获取单例插件加载组件
     *
     * @return 单例插件加载组件
     */
    public static synchronized PluginLoader getInstance() {
        return INSTANCE;
    }

    /**
     * 容器类型
     */
    @Getter
    @AllArgsConstructor
    private enum ContainerType {
        JOB("Job"),
        TASK("Task");
        private final String value;
    }

    /**
     * 根据插件类型和插件名称获取JarLoader对象
     *
     * @param pluginType 插件类型
     * @param pluginName 插件名称
     * @return JarLoader对象
     */
    public synchronized JarLoader getJarLoader(PluginType pluginType, String pluginName) {
        String pluginKey = generatePluginKey(pluginType, pluginName);
        JarLoader jarLoader = jarLoaderHashMap.get(pluginKey);
        if (ObjectUtil.isNull(jarLoader)) {
            Config pluginConfig = getPluginConfig(pluginType, pluginName);
            String pluginPath = pluginConfig.getValue("path", String.class);
            if (StrUtil.isBlank(pluginPath)) {
                String description = StrUtil.format("[{}]插件路径[{}]非法", pluginType, pluginName);
                throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.PLUGIN_LOAD_ERROR, description);
            }
            jarLoader = new JarLoader(new String[]{pluginPath});
            jarLoaderHashMap.put(pluginKey, jarLoader);
        }
        return jarLoader;
    }

    /**
     * 加载Job容器的插件
     *
     * @param pluginType 插件类型
     * @param pluginName 插件名称
     * @return Job容器插件
     */
    public synchronized AbstractJobPlugin loadJobPlugin(PluginType pluginType, String pluginName) {
        Class<? extends AbstractPlugin> pluginClass = loadPluginClass(pluginType, pluginName, ContainerType.JOB);
        try {
            AbstractJobPlugin plugin = (AbstractJobPlugin) pluginClass.newInstance();
            plugin.setConfig(getPluginConfig(pluginType, pluginName));
            return plugin;
        } catch (Exception e) {
            String description = StrUtil.format("DataBridge未能找到[{}]的插件[{}]", pluginType, pluginName);
            throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.RUNTIME_ERROR, description);
        }
    }

    /**
     * 加载Task容器的插件
     *
     * @param pluginType 插件类型
     * @param pluginName 插件名称
     * @return Task容器插件
     */
    public synchronized AbstractTaskPlugin loadTaskPlugin(PluginType pluginType, String pluginName) {
        Class<? extends AbstractPlugin> pluginClass = loadPluginClass(pluginType, pluginName, ContainerType.TASK);
        try {
            AbstractTaskPlugin plugin = (AbstractTaskPlugin) pluginClass.newInstance();
            plugin.setConfig(getPluginConfig(pluginType, pluginName));
            return plugin;
        } catch (Exception e) {
            String description = StrUtil.format("DataBridge未能找到[{}]的插件[{}]", pluginType, pluginName);
            throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.RUNTIME_ERROR, description);
        }
    }

    /**
     * 获取插件配置
     *
     * @param pluginType 插件类型
     * @param pluginName 插件名称
     * @return 插件配置
     */
    private synchronized Config getPluginConfig(PluginType pluginType, String pluginName) {
        String pluginKey = generatePluginKey(pluginType, pluginName);
        return pluginConfigs.computeIfAbsent(pluginKey, key -> allConfig.getConfig(key));
    }

    /**
     * 生成插件关键字
     *
     * @param pluginType 插件类型
     * @param pluginName 插件名称
     * @return 插件关键字
     */
    private synchronized String generatePluginKey(PluginType pluginType, String pluginName) {
        return StrUtil.format(PLUGIN_TYPE_NAME_FORMAT, pluginType.getValue(), pluginName);
    }

    /**
     * 加载插件类
     *
     * @param pluginType    插件类型
     * @param pluginName    插件名称
     * @param containerType 容器类型，见：{@link ContainerType}
     * @return 插件类对象
     */
    @SuppressWarnings("unchecked")
    private synchronized Class<? extends AbstractPlugin> loadPluginClass(PluginType pluginType, String pluginName, ContainerType containerType) {
        Config pluginConfig = getPluginConfig(pluginType, pluginName);
        JarLoader jarLoader = getJarLoader(pluginType, pluginName);
        try {
            String className = pluginConfig.getValue("class", String.class) + "$" + containerType.getValue();
            return (Class<? extends AbstractPlugin>) jarLoader.loadClass(className);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.RUNTIME_ERROR, e);
        }
    }
}