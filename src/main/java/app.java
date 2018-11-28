import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by cx on 2018/11/19.
 */
public class app {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();

        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        kieSession.fireAllRules();
        kieSession.destroy();
    }
}
