package com.example.spring_web_log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.marker.ObjectAppendingMarker;
import org.slf4j.event.KeyValuePair;

public class CustomLogstashEncoder extends LogstashEncoder {

    private final String gradeFieldName = "grade";
    private final String gradeFieldValue = "GRADE-A";

    @Override
    public byte[] encode(ILoggingEvent event) {
        if (event instanceof LoggingEvent ev) {
            boolean hasClassification = isHasClassification(event);

            if (!hasClassification) {
                ev.addKeyValuePair(new KeyValuePair(gradeFieldName, gradeFieldValue));
            }

        }
        // Call the superclass's encode method to perform the standard encoding
        return super.encode(event);
    }

    private boolean isHasClassification(ILoggingEvent event) {
        Object[] argumentArray = event.getArgumentArray();

        if (argumentArray == null) {
            return false;
        }

        for (Object o : argumentArray) {
            if(o instanceof ObjectAppendingMarker objectAppendingMarker) {
                if (gradeFieldName.equals(objectAppendingMarker.getFieldName())) {
                    return true;
                }
            }
        }
        return false;
    }
}