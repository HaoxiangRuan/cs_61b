public class NBody {

    public static double readRadius(String filename){
        In in = new In(filename);
         int Number = in.readInt();
         return in.readDouble();
    }
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int Number = in.readInt();
        double radius = in.readDouble();
        Body[] allbodys = new Body[Number];
        for(int i=0; i<allbodys.length;i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            allbodys[i]=new Body(xp,yp,xv,yv,mass,image);
        }
        return allbodys;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double time = 0;
        double radius = readRadius(filename) ;
        Body[] allbodys = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        double [] xForce = new double [allbodys.length];
        double [] yForce = new double [allbodys.length];
        while(time<=T){

            for (int i=0;i<allbodys.length;i++){
                xForce[i] = allbodys[i].calcNetForceExertedByX(allbodys);
                yForce[i] = allbodys[i].calcNetForceExertedByY(allbodys);
            }
            for (int i=0;i<allbodys.length;i++){
                allbodys[i].update(dt,xForce[i],yForce[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Body b:allbodys){
                b.draw();
            }
            //don't  forget show method!'
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;


        }
        StdOut.printf("%d\n", allbodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allbodys.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allbodys[i].xxPos, allbodys[i].yyPos, allbodys[i].xxVel,
                    allbodys[i].yyVel, allbodys[i].mass, allbodys[i].imgFileName);
        }



    }
}
