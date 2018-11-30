package demo;

/**
 * Created by cx on 2018/11/19.
 */
public class Account {
    private long accountno;
    private double balance;

    public Account() {
        super();
    }

    public Account(long accountno, double balance) {
        this.accountno = accountno;
        this.balance = balance;
    }

    public long getAccountno() {
        return this.accountno;
    }

    public void setAccountno(long no) {
        this.accountno = no;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountno=" + accountno +", balance=" + balance +"]\n";
    }

}
