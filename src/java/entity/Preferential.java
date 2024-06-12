package entity;

public class Preferential {

    protected String preferential;
    protected String preferentialName;
    protected String startDay;
    protected String endDay;
    protected int quantity;
    protected String preferentiaDescription;
    protected String preferentiaImg;
    protected String CTVID;

    public Preferential(String preferential, String preferentialName, String startDay, String endDay, int quantity, String preferentiaDescription, String preferentiaImg, String CTVID) {
        this.preferential = preferential;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.preferentiaDescription = preferentiaDescription;
        this.preferentiaImg = preferentiaImg;
        this.CTVID = CTVID;
    }

    public Preferential(String name, String description, int discount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Preferential(String preferential, String preferentialName, String startDay, String endDay, int quantity, String preferentiaDescription, String preferentiaImg) {
              this.preferential = preferential;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.preferentiaDescription = preferentiaDescription;
        this.preferentiaImg = preferentiaImg;
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public String getPreferentialName() {
        return preferentialName;
    }

    public void setPreferentialName(String preferentialName) {
        this.preferentialName = preferentialName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPreferentiaDescription() {
        return preferentiaDescription;
    }

    public void setPreferentiaDescription(String preferentiaDescription) {
        this.preferentiaDescription = preferentiaDescription;
    }

    public String getPreferentiaImg() {
        return preferentiaImg;
    }

    public void setPreferentiaImg(String preferentiaImg) {
        this.preferentiaImg = preferentiaImg;
    }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }

    @Override
    public String toString() {
        return "Preferential{" + "preferential=" + preferential + ", preferentialName=" + preferentialName + ", startDay=" + startDay + ", endDay=" + endDay + ", Quantity=" + quantity + ", preferentiaDescription=" + preferentiaDescription + ", preferentiaImg=" + preferentiaImg + ", CTVID=" + CTVID + '}';
    }


    public Preferential() {
    }
    
}
