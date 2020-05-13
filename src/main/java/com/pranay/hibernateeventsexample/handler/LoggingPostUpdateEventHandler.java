package com.pranay.hibernateeventsexample.handler;

import org.springframework.stereotype.Component;

@Component
public class LoggingPostUpdateEventHandler extends AbstractPostUpdateEventHandler {
    @Override
    public void run() {
        System.out.println("------HIBERNATE EVENT------");
        System.out.println("Thread ID of event listener: " + Thread.currentThread().getId());

        StringBuilder sb = new StringBuilder();
        sb.append("Update to entity ")
                .append(this.event.getPersister().getEntityMetamodel().getName())
                .append(" with ID ")
                .append(this.event.getId())
                .append(" was committed by Hibernate. ")
                .append("The following fields were updated: \n");

        for (int p : this.event.getDirtyProperties()) {
            sb.append("\t");
            sb.append(this.event.getPersister().getEntityMetamodel().getProperties()[p].getName());
            sb.append(" (Old value: ")
                    .append(this.event.getOldState()[p])
                    .append(", New value: ")
                    .append(this.event.getState()[p])
                    .append(")\n");
        }

        System.out.println(sb.toString());
    }
}
