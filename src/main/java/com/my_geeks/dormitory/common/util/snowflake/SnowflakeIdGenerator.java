package com.my_geeks.dormitory.common.util.snowflake;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

public class SnowflakeIdGenerator implements BeforeExecutionGenerator {

    private static final Snowflake SNOWFLAKE = new Snowflake();

    @Override
    public Object generate(
            SharedSessionContractImplementor session,
            Object owner,
            Object currentValue,
            EventType eventType
    ) {
        if (currentValue != null) {
            return currentValue;
        }
        return SNOWFLAKE.nextId();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }
}
