package util;

import org.kie.api.event.rule.*;

/**
 * Created by cx on 2018/11/22.
 */
public class MyAgendaEventListner implements AgendaEventListener {
    public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
        System.out.println("[Agenda Event] AfterMatchFired: \n\tThe rule "
                + afterMatchFiredEvent.getMatch().getRule().getName()
                + " has been fired");
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

    }

    public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {

    }

    public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {

    }

    public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
        System.out.println("[Agenda Event] BeforeMatchFired: \n\tThe rule "
                + beforeMatchFiredEvent.getMatch().getRule().getName()
                + " will be fired");
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

    }

    public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
        System.out.println("[Agenda Event] MatchCancelled: \n\tThe rule "
                + matchCancelledEvent.getMatch().getRule().getName()
                + " cannot be in agenda");
    }

    public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
        System.out.println("[Agenda Event] MatchCreated: \n\tThe rule "
                + matchCreatedEvent.getMatch().getRule().getName()
                + " can be fired in agenda");
    }
}
