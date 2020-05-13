package com.pranay.hibernateeventsexample.listener;

import com.pranay.hibernateeventsexample.handler.PostInsertEventHandler;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertListener implements PostInsertEventListener {

    private final List<PostInsertEventHandler> handlers;
    private final ExecutorServiceFactory factory;

    @Autowired
    public InsertListener(List<PostInsertEventHandler> handlers, ExecutorServiceFactory factory) {
        this.handlers = handlers;
        this.factory = factory;
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        for (PostInsertEventHandler han : handlers) {
            han.register(postInsertEvent);
            this.factory.getExecutorService().execute(han);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
