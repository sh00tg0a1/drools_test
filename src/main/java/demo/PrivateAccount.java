package demo;

/**
 * Created by cx on 2018/11/27.
 */
public class PrivateAccount extends Account {
    private Customer owner;

    public Customer getOwner() {
        return this.owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("-----Private Account-\n");
        buff.append(super.toString());
        if (this.owner != null) {
            buff.append(this.owner.toString());
        }
        buff.append("-----Private Account end-\n");
        return buff.toString();
    }

}
