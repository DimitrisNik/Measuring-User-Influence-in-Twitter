package paper2;

public class Influence {
    User user;
    private double rf;
    private double rrt;
    private double ri;
    double klout;
    double SNP;

    public void setUser(User user) {
        this.user = user;
    }

    public void setRf(double rf) {
        this.rf = rf;
    }

    public void setRrt(double rrt) {
        this.rrt = rrt;
    }

    public void setRi(double ri) {
        this.ri = ri;
    }

    public void setKlout(double klout) {
        this.klout = klout;
    }

    public void setSNP(double SNP) {
        this.SNP = SNP;
    }

    public User getUser() {
        return user;
    }

    public double getRf() {
        return rf;
    }

    public double getRrt() {
        return rrt;
    }

    public double getRi() {
        return ri;
    }

    public double getKlout() {
        return klout;
    }

    public double getSNP() {
        return SNP;
    }

    
    
    

    @Override
    public String toString() {
        return "Influence{" + "rf=" + rf + ", rrt=" + rrt + ", ri=" + ri + ", klout=" + klout + ", SNP=" + SNP + '}';
    }
    
    
    
     
    
}
