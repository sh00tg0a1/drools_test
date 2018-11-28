package demo;

import java.util.Date;

/**
 * Created by cx on 2018/11/19.
 */
public class AccountPeriod {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public AccountPeriod() {
        super();
    }


    @Override
    public String toString() {
        return "AccountPeriod [startDate=" + startDate +", endDate=" + endDate +"]";
    }

}
