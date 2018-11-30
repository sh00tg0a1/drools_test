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
public class ThirdTest {
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
    public void testListCondition() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules4");

//        sessionStateful.addEventListener(new MyRuntimeEventListner());
//        sessionStateful.addEventListener(new MyAgendaEventListner());
        sessionStateful.addEventListener(new MyRuntimeEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        CashFlow cf = new CashFlow();
        cf.setAccountno(1);
        cf.setType(CashFlow.CREDIT);
        cf.setAmount(1000);
        FactHandle handlea = sessionStateful.insert(cf);

        CashFlow cf2 = new CashFlow();
        cf2.setAccountno(1);
        cf2.setAmount(500);

        cf2.setType(CashFlow.DEBIT);

        sessionStateful.insert(cf2);
        sessionStateful.fireAllRules();

    }

    @Test
    public void testNestedObject() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Customer customer = new Customer("AAA", "B", "C");
        PrivateAccount account = new PrivateAccount();
        account.setOwner(customer);

        sessionStateful.insert(account);
        sessionStateful.fireAllRules();
    }

    @Test
    public void testAndOrRule() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        // 规则测试
        Customer customer = new Customer("A", "A", "US");
        PrivateAccount account = new PrivateAccount();
        account.setOwner(customer);
        sessionStateful.insert(customer);
        sessionStateful.insert(account);

        Customer customer2 = new Customer("B", "B", "GB");
        PrivateAccount account2 = new PrivateAccount();
        account2.setOwner(customer2);
        sessionStateful.insert(customer2);
        sessionStateful.insert(account2);

        Customer customer3 = new Customer("C", "C", "CN");
        PrivateAccount account3 = new PrivateAccount();
        account3.setOwner(customer3);
        sessionStateful.insert(customer3);
        sessionStateful.insert(account3);

        sessionStateful.fireAllRules();
    }

    @Test
    public void testWithNot() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        sessionStateful.fireAllRules();
    }

    @Test
    public void testWithExits() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        PrivateAccount pAcc = new PrivateAccount();
        sessionStateful.insert(pAcc);

        sessionStateful.fireAllRules();
    }


    @Test
    public void testWithForAll() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());

        // 这两行代码不设置也可以，规则文件中貌似会自动设置
        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);

        Account acc = new Account();
        acc.setAccountno(1);
        CashFlow cf = new CashFlow();
        cf.setAccountno(1);

        sessionStateful.insert(acc);
        sessionStateful.insert(cf);

        CashFlow cf2 = new CashFlow();
        cf2.setAccountno(1);

        sessionStateful.insert(cf2);
        sessionStateful.fireAllRules();
    }

    @Test
    public void testWithList() {
        sessionStateful = KnowledegeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

        sessionStateful.addEventListener(new MyRuntimeEventListner());
        sessionStateful.addEventListener(new MyAgendaEventListner());


        OutputDisplay outputDisplay = new OutputDisplay();
        sessionStateful.setGlobal("showResults", outputDisplay);
        sessionStateful.setGlobal("serviceCustomer", new CustomerService());
        Customer c = new Customer("A", "A", "CN");
        sessionStateful.insert(c);
        sessionStateful.fireAllRules();
    }

    @Test
    public void test2CashLines() {
        try {

            sessionStateful = KnowledegeSessionHelper
                    .getStatefulKnowledgeSession(kieContainer, "ksession-rules3");

            //        sessionStateful.addEventListener(new MyRuntimeEventListner());
            //        sessionStateful.addEventListener(new MyAgendaEventListner());

            // 这两行代码不设置也可以，规则文件中貌似会自动设置
            OutputDisplay outputDisplay = new OutputDisplay();
            sessionStateful.setGlobal("showResults", outputDisplay);

            // 规则测试
            Account a = new Account(1, 0);
            FactHandle fh = sessionStateful.insert(a);
            CashFlow cash1 = new CashFlow(1, CashFlow.CREDIT, DateHelper.getDate("2018-01-15"), 1000);
            sessionStateful.insert(cash1);

            CashFlow cash2 = new CashFlow(1, CashFlow.CREDIT, DateHelper.getDate("2018-02-10"), 1000);
            sessionStateful.insert(cash2);

            CashFlow cash3 = new CashFlow(1, CashFlow.DEBIT, DateHelper.getDate("2018-03-15"), 500);
            sessionStateful.insert(cash3);

            AccountPeriod period = new AccountPeriod(DateHelper.getDate("2018-01-01"), DateHelper.getDate("2018-03-31"));

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