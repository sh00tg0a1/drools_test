package demo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import util.KnowledegeSessionHelper;
import util.MyAgendaEventListner;
import util.MyRuntimeEventListner;
import util.OutputDisplay;

/**
 * Created by cx on 2018/11/19.
 */

@SuppressWarnings("restriction")
public class FirstTest {
    StatelessKieSession sessionStateless = null;
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
    public void testFirstOne() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        sessionStateful.fireAllRules();
    }

    @Test
    public void TestLesson1() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        // OutputDisplay outputDisplay = new OutputDisplay();
        // sessionStateful.setGlobal("showResults", outputDisplay);
        Account acc = new Account();
        sessionStateful.insert(acc);
        sessionStateful.fireAllRules();
    }

    @Test
    public void testFirstRuleWithGlobalAndCallback() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        sessionStateful.addEventListener(new MyRuntimeEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Account acc = new Account();
        acc.setAccountno(10);
        FactHandle handlea = sessionStateful.insert(acc);
        acc.setBalance(12.0);

        sessionStateful.update(handlea, acc);
        sessionStateful.delete(handlea);

        // 删除之后就不能 fire 了
        sessionStateful.fireAllRules();
    }

    @Test
    public void testFireTwice() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        sessionStateful.addEventListener(new MyRuntimeEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Account acc = new Account();
        acc.setAccountno(10);
        FactHandle handlea = sessionStateful.insert(acc);
        // 事件只能 fire 一次
        System.out.println("Fire the first time");
        sessionStateful.fireAllRules();

        acc.setBalance(12.0);
        // 删除之后就不能 fire 了
        System.out.println("Fire the second time");

        sessionStateful.fireAllRules();
    }

    @Test
    public void testFireTwiceWithUpdate() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");

        sessionStateful.addEventListener(new MyRuntimeEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Account acc = new Account();
        acc.setAccountno(10);
        FactHandle handlea = sessionStateful.insert(acc);

        System.out.println("Fire the first time");
        sessionStateful.fireAllRules();

        // Update 之后可以再次 Fire
        sessionStateful.update(handlea, acc);

        System.out.println("Fire the second time");

        sessionStateful.fireAllRules();
    }

    @Test
    public void testWithInsertActions() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        sessionStateful.addEventListener(new MyRuntimeEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        CashFlow cf = new CashFlow();
        sessionStateful.insert(cf);
        sessionStateful.fireAllRules();
    }

    @Test
    public void testWithListeners() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules");
        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        CashFlow cf = new CashFlow();
        sessionStateful.insert(cf);
        sessionStateful.fireAllRules();
    }
}