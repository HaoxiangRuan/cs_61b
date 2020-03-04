

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        double Distance = Math.pow((b.yyPos-this.yyPos),2)+Math.pow((b.xxPos-this.xxPos),2);
        return Math.sqrt(Distance);
    }
    public double calcForceExertedBy(Body b){
        double Distance = calcDistance(b);
        return (6.67*1e-11* this.mass*b.mass)/Math.pow(Distance,2);

    }
    public double calcForceExertedByX(Body b){
        return calcForceExertedBy(b)*(b.xxPos-this.xxPos)/calcDistance(b);
    }
    public double calcForceExertedByY(Body b){
        return calcForceExertedBy(b)*(b.yyPos-this.yyPos)/calcDistance(b);

    }
    public double calcNetForceExertedByX(Body[] allBodys){
        double NetForceX = 0;
        for (Body b:allBodys){
            if(!b.equals(this)) {
                NetForceX += calcForceExertedByX(b);
            }
      }
        return NetForceX;
    }
    public double calcNetForceExertedByY(Body[] allBodys){
        double NetForceY = 0;
        for (Body b:allBodys){
            if(!b.equals(this)){
                NetForceY+=calcForceExertedByY(b);
            }
        }
        return NetForceY;

    }
    public void update(double t, double forceX, double forceY){
        double accelerateX = forceX/this.mass;
        double accelerateY = forceY/this.mass;
        this.yyVel += t*accelerateY;
        this.xxVel += t*accelerateX;
        this.yyPos += t*this.yyVel;
        this.xxPos += t*this.xxVel;

    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}
