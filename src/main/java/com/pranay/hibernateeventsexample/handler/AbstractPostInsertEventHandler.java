package com.pranay.hibernateeventsexample.handler;

import org.hibernate.event.spi.PostInsertEvent;

public abstract class AbstractPostInsertEventHandler implements PostInsertEventHandler {
    protected PostInsertEvent event;

    @Override
    public final void register(PostInsertEvent ev) {
        this.event = ev;
    }
}
