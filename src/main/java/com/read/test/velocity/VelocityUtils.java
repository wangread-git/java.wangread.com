package com.read.test.velocity;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by bjyfwangrui on 2017/12/20.
 */
public final class VelocityUtils {
    private static final ConcurrentMap<Integer, Template> templateCache = Maps.newConcurrentMap();

    static {
        Velocity.setProperty(org.apache.velocity.app.VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        Velocity.init();
    }

    private static RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();

    public static String merge(String str, VelocityContext velocityContext) throws ParseException {
        int hashCode = str.hashCode();
        Template template = templateCache.get(hashCode);
        if (template == null) {
            SimpleNode node = runtimeServices.parse(new StringReader(str), "Template name");
            template = new Template();
            template.setRuntimeServices(runtimeServices);
            template.setData(node);
            template.initDocument();
            templateCache.putIfAbsent(hashCode, template);
        }
        StringWriter sw = new StringWriter();
        template.merge(velocityContext, sw);
        return sw.toString();
    }

    @Test
    public void test() {
        VelocityContext context = new VelocityContext();
        context.put("StringUtils", StringUtils.class);
        context.put("str", "nmb");
        String code = "$StringUtils.equals(\"nmb\", $str)";
        try {
            System.out.println(VelocityUtils.merge(code, context));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
