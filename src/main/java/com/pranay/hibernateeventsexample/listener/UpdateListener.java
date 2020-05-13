package com.pranay.hibernateeventsexample.listener;

import com.pranay.hibernateeventsexample.handler.PostUpdateEventHandler;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateListener implements PostUpdateEventListener {

    private final List<PostUpdateEventHandler> handlers;
    private final ExecutorServiceFactory factory;

    @Autowired
    public UpdateListener(List<PostUpdateEventHandler> handlers, ExecutorServiceFactory factory) {
        this.handlers = handlers;
        this.factory = factory;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        for (PostUpdateEventHandler han : handlers) {
            han.register(postUpdateEvent);
            this.factory.getExecutorService().execute(han);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}