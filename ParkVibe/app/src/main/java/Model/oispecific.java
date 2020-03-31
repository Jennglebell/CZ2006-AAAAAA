package Model;

public class oispecific {

    //if start from other type, change type to string.
    public String name;
    public String oitype;
    public String location;
    public String distanceFromUser; //(30m away)
    public String imgurl1;
    public String imgurl2; //idk yall figure this out. dynamic array?
    public String imgurl3;
    public String imgurl4;
    public String imgurl5;

    public oispecific(String name, String oitype, String location, String distanceFromUser, String imgurl1, String imgurl2, String imgurl3, String imgurl4, String imgurl5) {
        this.name = name;
        this.oitype = oitype;
        this.location = location;
        this.distanceFromUser = distanceFromUser;
        this.imgurl1 = imgurl1;
        this.imgurl2 = imgurl2;
        this.imgurl3 = imgurl3;
        this.imgurl4 = imgurl4;
        this.imgurl5 = imgurl5;
    }
}
