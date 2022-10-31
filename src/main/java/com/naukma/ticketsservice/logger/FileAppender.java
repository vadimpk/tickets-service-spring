package com.naukma.ticketsservice.logger;

import java.io.BufferedWriter;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "FileAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class FileAppender extends AbstractAppender {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();

    protected FileAppender(String name, Filter filter, Layout<? extends Serializable> layout,
                               final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions, null);
        LOGGER.info("MyCustomAppender is instantiated..");
    }

    @PluginFactory
    public static FileAppender createAppender(@PluginAttribute("name") String name,
                                                  @PluginElement("Layout") Layout<? extends Serializable> layout,
                                                  @PluginElement("Filter") final Filter filter, @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("There is no name provided for FileAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new FileAppender(name, filter, layout, true);

    }

    @Override
    public void append(LogEvent logEvent) {
        readLock.lock();
        try {
            final byte[] bytes = getLayout().toByteArray(logEvent);
            //Printing log into console
//            BufferedWriter bw = new BufferedWriter()
            System.out.print("File Log: " + new String(bytes));
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }
}