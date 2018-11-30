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
public class SecondTest {
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
    public void testBinding() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules2");

//        sessionStateful.addEventListener(new MyRuntimeEventListner());
//        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        CashFlow cf = new CashFlow();
        cf.setAccountno(10);
        cf.setType(CashFlow.CREDIT);
        FactHandle handlea = sessionStateful.insert(cf);

        Account acc = new Account();
        sessionStateful.insert(acc);
        sessionStateful.fireAllRules();

    }

    @Test
    public void testUpdateObject() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules2");

//        sessionStateful.addEventListener(new MyRuntimeEventListner());
//        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        sessionStateful.insert(a);
        CashFlow cash1 = new CashFlow();
        cash1.setAccountno(1);
        cash1.setAmount(1000);
        cash1.setType(CashFlow.CREDIT);
        sessionStateful.insert(cash1);

        AccountPeriod period = new AccountPeriod();
        sessionStateful.insert(period);
        sessionStateful.fireAllRules();

        // Session 中会去设置对象的值
        Assert.assertEquals(a.getBalance(), 1000,0);
    }

    @Test
    public void testCashFlowMovement() {
        try {

            sessionStateful = KnowledegeSessionHelper
                    .getStatefulKnowledgeSession(kieContainer, "ksession-rules2");

            //        sessionStateful.addEventListener(new MyRuntimeEventListner());
            //        sessionStateful.addEventListener(new MyAgendaEventListner());

            // 这两行代码不设置也可以，规则文件中貌似会自动设置
            OutputDisplay outputDisplay = new OutputDisplay();
            sessionStateful.setGlobal("showResults", outputDisplay);

            // 规则测试
            Account a = new Account();
            a.setAccountno(1);
            a.setBalance(0);
            sessionStateful.insert(a);
            CashFlow cash1 = new CashFlow();
            cash1.setAccountno(1);
            cash1.setAmount(1000);
            cash1.setMvtDate(DateHelper.getDate("2018-01-15"));
            cash1.setType(CashFlow.CREDIT);
            sessionStateful.insert(cash1);

            CashFlow cash2 = new CashFlow();
            cash2.setAccountno(1);
            cash2.setAmount(1000);
            cash2.setMvtDate(DateHelper.getDate("2018-01-15"));
            cash2.setType(CashFlow.CREDIT);
            sessionStateful.insert(cash2);

            CashFlow cash3 = new CashFlow();
            cash3.setAccountno(1);
            cash3.setAmount(500);
            cash3.setMvtDate(DateHelper.getDate("2018-02-15"));
            cash3.setType(CashFlow.DEBIT);
            sessionStateful.insert(cash3);

            AccountPeriod period = new AccountPeriod();
            period.setStartDate(DateHelper.getDate("2018-01-01"));
            period.setEndDate(DateHelper.getDate("2018-03-01"));

            sessionStateful.insert(period);
            sessionStateful.fireAllRules();

            // Session 中会去设置对象的值
            Assert.assertEquals(1500, a.getBalance(),0);
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}