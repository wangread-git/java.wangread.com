package com.read.test.groovy;

import groovy.lang.*;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.security.AccessController;
import java.security.PrivilegedAction;

import static groovy.lang.GroovyShell.DEFAULT_CODE_BASE;

/**
 * Created by bjyfwangrui on 2018/1/17.
 */
public class GroovyScriptHelper {

    private static final String SCRIPT_PREFIX = "Script";
    private static final String SCRIPT_SUFFIX = ".groovy";

    private static final GroovyClassLoader loader = AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>) () -> new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), CompilerConfiguration.DEFAULT));

    private static String generateScriptName(int hashCode) {
        return SCRIPT_PREFIX + hashCode + SCRIPT_SUFFIX;
    }

    public static Script getInstance(String scriptText, Binding binding, boolean shouldCacheSource) throws CompilationFailedException {
        int hashCode = scriptText.hashCode();
        String name = generateScriptName(hashCode);
        GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>) () -> new GroovyCodeSource(scriptText, name, DEFAULT_CODE_BASE));

        return InvokerHelper.createScript(parseClass(gcs, shouldCacheSource), binding);
    }

    private static Class parseClass(final GroovyCodeSource codeSource, boolean shouldCacheSource) throws CompilationFailedException {
        return loader.parseClass(codeSource, shouldCacheSource);
    }
}
