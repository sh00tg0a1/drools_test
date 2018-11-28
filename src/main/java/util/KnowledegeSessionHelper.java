package util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * Created by cx on 2018/11/19.
 */
public class KnowledegeSessionHelper {
    public static KieContainer CreateRuleBase() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        return kieContainer;
    }

    public static StatelessKieSession getStatelessKnowledgeSession(KieContainer kieContainer, String sessionName) {
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession(sessionName);
        return kieSession;
    }

    public static KieSession getStatefulKnowledgeSesion(KieContainer kieContainer, String sessionName) {
        KieSession kieSession = kieContainer.newKieSession(sessionName);
        return kieSession;
    }
}
