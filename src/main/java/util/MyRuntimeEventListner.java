package util;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

/**
 * Created by cx on 2018/11/22.
 */
public class MyRuntimeEventListner implements RuleRuntimeEventListener {
    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
        System.out.println("[RT Event] Object inserted: \n\t" + objectInsertedEvent.getObject().toString());
    }

    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        System.out.println("[RT Event] Object updated: \n\t" + objectUpdatedEvent.getObject().toString());
    }

    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
        System.out.println("[RT Event] Object deleted: \n\t" + objectDeletedEvent.getOldObject().toString());
    }
}
