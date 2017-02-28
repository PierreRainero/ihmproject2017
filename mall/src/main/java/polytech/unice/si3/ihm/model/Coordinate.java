package polytech.unice.si3.ihm.model;

import java.util.List;


public class Coordinate {
    /**
     * Coordinates x and y
     */
    private int x;
    private int y;

    /**
     * Constructs an object Coordinate with the given numbers.
     *
     * @param a First coordinate
     * @param b Second coordinate
     */
    public Coordinate(int a, int b) {
        x = a;
        y = b;
    }

    /**
     * Returns the coordinates of the object.
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Compares two positions.
     *
     * @return true if the coordinates are the same, otherwise false.
     */
    @Override
    public boolean equals(Object coor) {
        if (this == coor) {
            return true;
        }
        if (coor == null || this.getClass() != coor.getClass()) {
            return false;
        }
        Coordinate anotherCoord = (Coordinate) coor;

        return this.x == anotherCoord.getX() && this.y == anotherCoord.getY();
    }
    /**
     * Figures out the distance between two points.
     */
    public static double distance(Coordinate coor1, Coordinate coor2) {
        return Math.sqrt(Math.pow((double)coor1.getY() - (double)coor2.getY(), 2) + Math.pow((double)coor1.getX() - (double)coor2.getX(), 2));  //sqrt((y2-y1)²+(x2-x1)²)
    }

    /**
     * Finds the closest point to this object (between many points).
     *
     * @param coords the points to be compared.
     * @return the closest point between all the points.
     */
    Coordinate closestPoint(List<Coordinate> coords) {
        Coordinate clostest = coords.get(0);
        double minDist = distance(this, clostest);
        double currentDist;

        for (Coordinate coor: coords) {
            currentDist = distance(this, coor);
            if (currentDist < minDist) {
                minDist = currentDist;
                clostest = coor;
            }
        }
        return clostest;
    }

    /**
     * Creates a string thanks to the coordinates of this object.
     */
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}