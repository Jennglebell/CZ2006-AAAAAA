package Model;

public class oi {

    public String name;
    public Boolean applied;
    public String imgurl;

    public oi(String name, String imgurl){
        this.name = name;
        this.applied = true;
        this.imgurl = imgurl;
    }
    public void setApplied(){
        this.applied = true;
    }
}
