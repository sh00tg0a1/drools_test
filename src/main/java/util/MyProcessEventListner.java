package util;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.kie.api.event.process.*;

/**
 * Created by cx on 2018/11/29.
 */
public class MyProcessEventListner implements ProcessEventListener {
    public void beforeProcessStarted(ProcessStartedEvent processStartedEvent) {
        System.out.println("[Process Event] before started: \n\t"
                + processStartedEvent.getProcessInstance().getProcessName()
        );
    }

    public void afterProcessStarted(ProcessStartedEvent processStartedEvent) {
        System.out.println("[Process Event] after started: \n\t" + processStartedEvent.getProcessInstance().getProcessName());
    }

    public void beforeProcessCompleted(ProcessCompletedEvent processCompletedEvent) {

    }

    public void afterProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
        System.out.println("[Process Event] after process completed: \n\t"
                + processCompletedEvent.getProcessInstance().getProcessName()
        );
    }

    public void beforeNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {

    }

    public void afterNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {

    }

    public void beforeNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
        if (processNodeLeftEvent.getNodeInstance() instanceof RuleSetNodeInstance) {
            System.out.println("[Process Event] before node left: \n\t"
                    + processNodeLeftEvent.getNodeInstance().getNodeName()
            );
        }
    }

    public void afterNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
        if (processNodeLeftEvent.getNodeInstance() instanceof RuleSetNodeInstance) {
            System.out.println("[Process Event] after node left: \n\t"
                    + processNodeLeftEvent.getNodeInstance().getNodeName()
            );
        }
    }

    public void beforeVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {

    }

    public void afterVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {

    }
}
