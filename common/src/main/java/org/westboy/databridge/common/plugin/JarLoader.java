package org.westboy.databridge.common.plugin;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.westboy.databridge.common.errorcode.FrameworkErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 提供Jar隔离的加载机制
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
public class JarLoader extends URLClassLoader {

    public JarLoader(String[] paths) {
        this(paths, JarLoader.class.getClassLoader());
    }

    public JarLoader(String[] paths, ClassLoader parent) {
        super(getURLs(paths).toArray(new URL[0]), parent);
    }

    private static List<URL> getURLs(String[] paths) {
        Validate.isTrue(ArrayUtil.isNotEmpty(paths), "jar包路径不能为空");
        List<String> dirs = new ArrayList<>();
        for (String path : paths) {
            dirs.add(path);
            collectToDirs(path, dirs);
        }
        List<URL> urls = new ArrayList<>();
        for (String dir : dirs) {
            urls.addAll(doGetURLs(dir));
        }
        return urls;
    }

    private static List<URL> doGetURLs(String path) {
        Validate.isTrue(StrUtil.isNotEmpty(path), "jar包路径不能为空");
        File jarPath = FileUtil.file(path);
        Validate.isTrue(jarPath.exists() && jarPath.isDirectory(), "jar包路径必须存在且为目录");
        
        FileFilter fileFilter = pathname -> pathname.getName().endsWith(".jar");
        File[] allJars = FileUtil.file(path).listFiles(fileFilter);
        List<URL> jarURLs = new ArrayList<>(allJars.length);
        for (File jar : allJars) {
            try {
                jarURLs.add(jar.toURI().toURL());
            } catch (Exception e) {
                throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.PLUGIN_LOAD_ERROR, e);
            }
        }
        return jarURLs;
    }

    private static void collectToDirs(String path, List<String> dirs) {
        if (StrUtil.isEmpty(path)) {
            return;
        }
        File cur = FileUtil.newFile(path);
        if (!cur.exists() || !cur.isDirectory()) {
            return;
        }

        for(File child : cur.listFiles()) {
            if (!cur.isDirectory()) {
                continue;
            }
            dirs.add(child.getAbsolutePath());
            collectToDirs(child.getAbsolutePath(), dirs);
        }
    }
}
