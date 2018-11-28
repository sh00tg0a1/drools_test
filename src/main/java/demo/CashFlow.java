package demo;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by cx on 2018/11/19.
 */
public class CashFlow {
    public static int CREDIT = 1;
    public static int DEBIT = 2;

    private Date mvtDate;
    private double amount;
    private int type;
    private long accountno;

    public Date getMvtDate() {
        return this.mvtDate;
    }

    public void setMvtDate(Date date) {
        this.mvtDate = date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getAccountno() {
        return this.accountno;
    }

    public void setAccountno(long accountno) {
        this.accountno = accountno;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuffer buff = new StringBuffer();
        buff.append("-----CashFlow-----)\n");
        buff.append("Account no=" + this.accountno + "\n");
        if (this.mvtDate != null) {
            buff.append("Mouvement Date= "
                    + DateFormat.getDateInstance().format(this.mvtDate)
                    + "\n");
        } else {
            buff.append("No Mouvement date was set\n");
        }
        buff.append("Mouvement Amount=" + this.amount + "\n");
        buff.append("-----CashFlow end--)");
        return buff.toString();
    }
}
