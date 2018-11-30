package demo;

import org.junit.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import util.*;

/**
 * Created by cx on 2018/11/19.
 */

@SuppressWarnings("restriction")
public class ForthTest {
    KieSession sessionStateful = null;
    static KieContainer kieContainer = null;

    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledegeSessionHelper.CreateRuleBase();
    }

    @Before
    public void setUp() {
        System.out.println("----------Before----------");
    }


    @After
    public void tearDown() {
        System.out.println("----------After----------");
    }

    @Test
    public void testProcess() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules4");

//        sessionStateful.addEventListener(new MyRuntimeEventListner());
//        sessionStateful.addEventListener(new MyAgendaEventListner());
        sessionStateful.addEventListener(new MyProcessEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Account acc = new Account();
        sessionStateful.insert(acc);
        sessionStateful.startProcess("RF1");
        sessionStateful.fireAllRules();
    }

    @Test
    public void testRuleFlow3() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules4");

        sessionStateful.addEventListener(new MyProcessEventListner());

        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        Account a = new Account();
        a.setBalance(2500);
        sessionStateful.insert(a);
        AccountPeriod period = new AccountPeriod();
        sessionStateful.insert(period);
        sessionStateful.fireAllRules();
    }
}