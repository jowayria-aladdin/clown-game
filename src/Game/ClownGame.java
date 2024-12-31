package Game;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.*;
import Objects.*;
import Strategy.DefaultPlateEntryStrategy;
import Strategy.PlateEntryStrategy;
import Strategy.ScoringStrategy;
import Strategy.SimpleScoringStrategy;

public class ClownGame implements World {

    private static int MAX_TIME = 1 * 80 * 1000;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> movable = new LinkedList<GameObject>();
    public final List<GameObject> controlable = new LinkedList<GameObject>();
    private final int SPEED = 10;
    private final int CONTROLSPEED = 20;
    private final int CERTAINLIMIT = 10;
    public static int width, height, score;
    private long startTime = System.currentTimeMillis();
    public Stack rightCollectedObjects;
    public Stack leftCollectedObjects;
    public List<GameObject> left = new LinkedList<GameObject>();
    public List<GameObject> right = new LinkedList<GameObject>();
    //bomb and other shapes
    private ScoringStrategy scoringStrategy;
    //left or right stack
    private PlateEntryStrategy leftPlateEntryStrategy;
    private PlateEntryStrategy rightPlateEntryStrategy;
    //state design diagram
    private final String[] SHAPES = {"Circle", "Triangle", "Plate", "Square"};
    private final String[] COLORS = {"Blue", "Green", "Red", "Yellow"};
    Random random;
    String name;
    Clown clown;

    public ClownGame(int width, int height) {
        this.random = new Random();
        this.width = width;
        this.height = height;
        this.score = 0;
        this.rightCollectedObjects = new Stack();
        this.leftCollectedObjects = new Stack();
        this.scoringStrategy = new SimpleScoringStrategy();
        this.leftPlateEntryStrategy = new DefaultPlateEntryStrategy();
        this.rightPlateEntryStrategy = new DefaultPlateEntryStrategy();

        int x, y;

        this.constant.add(new ImageObject(0, 0, "/Theme1.jpg"));
        // control objects 
        clown = new Clown(this.width / 3 - 50, (int) (this.height * 0.8) - 270, "/Clown.png");
        controlable.add(clown);

        // moving objects 
        for (int i = 0; i < 5; i++) {
            x = random.nextInt(4);
            y = random.nextInt(4);
            name = "/" + this.COLORS[x] + this.SHAPES[y] + ".png";

            movable.add(new Plates(this.COLORS[x], this.SHAPES[y], (int) (Math.random() * this.width), -1 * (int) (Math.random() * this.height), name));   //plates
        }
    }

    public boolean isPlateEnteredTheRightStack(Plates plate) {
        return (this.clown.getRightCenter() - plate.getX() <= 7 && this.clown.getRightCenter() - plate.getX() >= -7);
    }

    public boolean isPlateEnteredTheLeftStack(Plates plate) {
        return (this.clown.getLeftCenter() - plate.getX() <= 7 && this.clown.getLeftCenter() - plate.getX() >= -7);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return this.constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return this.movable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return this.controlable;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public int getLeftHeight() {
        int height = 0;
        for (int i = 0; i < this.left.size(); i++) {
            height -= this.left.get(i).getHeight();
        }
        return 295 + height;
    }

    public int getRightHeight() {
        int height = 0;
        for (int i = 0; i < this.right.size(); i++) {
            height -= this.right.get(i).getHeight();
        }
        return 295 + height;
    }

    public boolean checkHight(Plates plate) {
        if (this.right.isEmpty() || this.left.isEmpty()) {
            if (plate.getY() + plate.getHeight() - (295) <= 2 && plate.getY() + plate.getHeight() - (295) >= -2) {

                return true;
            }
        } else {
            if (this.isPlateEnteredTheLeftStack(plate)) {
                if ((plate.getY() + plate.getHeight() - (this.getLeftHeight()) <= 2) && (plate.getY() + plate.getHeight() - (this.getLeftHeight()) >= -2)) {
                    return true;
                }

            } else if (this.isPlateEnteredTheRightStack(plate)) {
                if ((plate.getY() + plate.getHeight() - (this.getRightHeight()) <= 2) && (plate.getY() + plate.getHeight() - (this.getRightHeight()) >= -2)) {
                    return true;
                }

            }
        }
        return false;
    }

    public void updateLeftScore() {

        try {
            Plates temp1 = (Plates) this.left.get(this.left.size() - 1);
            Plates temp2 = (Plates) this.left.get(this.left.size() - 2);
            if (left.size() == 2) {
                System.out.println(temp1.getColor() + temp2.getColor());
            }
            if (temp1.getColor().equals(temp2.getColor())) {
                System.out.println("hi");
                this.score += 5;
                this.left.remove(temp1);
                this.left.remove(temp2);
                this.leftCollectedObjects.pop();
                this.leftCollectedObjects.pop();
                this.controlable.remove(temp1);
                this.controlable.remove(temp2);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void updateRightScore() {

        try {
            Plates temp1 = (Plates) this.right.get(this.right.size() - 1);
            Plates temp2 = (Plates) this.right.get(this.right.size() - 2);
            if (right.size() == 2) {
                System.out.println(temp1.getColor() + temp2.getColor());
            }
            if (temp1.getColor().equals(temp2.getColor())) {
                System.out.println("hi");
                this.score += 5;
                this.right.remove(temp1);
                this.right.remove(temp2);
                this.rightCollectedObjects.pop();
                this.rightCollectedObjects.pop();
                this.controlable.remove(temp1);
                this.controlable.remove(temp2);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void updateScore() {
        try {

            Plates temp1 = (Plates) this.left.get(this.left.size() - 1);
            Plates temp2 = (Plates) this.left.get(this.left.size() - 2);
            if (left.size() == 2) {
                System.out.println(temp1.getColor() + temp2.getColor());
            }
            Plates temp4 = (Plates) this.right.get(this.right.size() - 1);
            Plates temp5 = (Plates) this.right.get(this.right.size() - 2);
            
            if (temp1.getColor().equals(temp2.getColor())) {
                System.out.println("hi");
                this.score += 5;
                this.left.remove(temp1);
                this.left.remove(temp2);
                this.leftCollectedObjects.pop();
                this.leftCollectedObjects.pop();
                this.controlable.remove(temp1);
                this.controlable.remove(temp2);
            }
            if (temp4.getColor().equals(temp5.getColor())) {
                System.out.println("iu");
                this.score += 5;
                this.right.remove(temp4);
                this.right.remove(temp5);
                this.rightCollectedObjects.pop();
                this.rightCollectedObjects.pop();
                this.controlable.remove(temp4);
                this.controlable.remove(temp5);
            }
        } catch (IndexOutOfBoundsException e) {
            //fill?

        }
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;

        for (int i = 0; i < this.movable.size(); i++) {
            this.updateLeftScore();
            this.updateRightScore();
            Plates x = (Plates) this.movable.get(i);
           
            this.movable.get(i).setY(this.movable.get(i).getY() + 1);
          
            if (this.isPlateEnteredTheLeftStack((Plates) this.movable.get(i)) && this.checkHight((Plates) this.movable.get(i))) {
                System.out.println("entered left stack");
                System.out.println(this.movable.get(i).getHeight());
                if (this.left.isEmpty()) {
                    if (x.getShape().equals("Plate")) {
                        this.movable.get(i).setY(335);
                    } else {
                        this.movable.get(i).setY(295);
                    }
                } else {
                   
                    this.movable.get(i).setY(this.getLeftHeight());
                }

                this.movable.get(i).setX(this.clown.getLeftCenter());
                this.left.add(this.movable.get(i));
                this.leftCollectedObjects.push(this.movable.get(i));
                //this.controlable.add(this.movable.get(i));
                this.controlable.add(this.movable.get(i));
                this.movable.remove(this.movable.get(i));
            } else if (this.isPlateEnteredTheRightStack((Plates) this.movable.get(i)) && this.checkHight((Plates) this.movable.get(i))) {
                System.out.println("entered right stack");
                System.out.println(this.movable.get(i).getY() + "," + this.movable.get(i).getHeight());
                if (this.right.isEmpty()) {
                    if (x.getShape().equals("Plate")) {
                        this.movable.get(i).setY(335);
                    } else {
                        this.movable.get(i).setY(295);
                    }
                } else {
                    //this.movable.get(i).setY(295 - this.right.get(this.right.size() - 1).getHeight());
                    this.movable.get(i).setY(this.getRightHeight());

                }
                this.movable.get(i).setX(this.clown.getRightCenter());
                this.right.add(this.movable.get(i));
                this.rightCollectedObjects.push(this.movable.get(i));
                this.controlable.add(this.movable.get(i));
                this.movable.remove(this.movable.get(i));
            } else if (this.movable.get(i).getY() >= this.getHeight()) {
                this.movable.get(i).setY(-1 * (int) (Math.random() * getHeight()));
                this.movable.get(i).setX((int) (Math.random() * getWidth()));

            }
        }

        // if the object enters a stack -> then remove it from moving and add it to control
        // if missed five plates -> return false
        // if it collected three objects from same color -> pop them , score+=1
        // move the objects ->check the intersection
        // if size of stack >certain limit ->returns false
//        if (this.movable.isEmpty()) {
//            return false;
//        }
        return !timeout;
    }
//@Override
//public boolean refresh() {
//    boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
//
//    for (int i = 0; i < this.movable.size(); i++) {
//        this.updateLeftScore();
//        this.updateRightScore();
//        Plates x = (Plates) this.movable.get(i);
//
//        if (this.isPlateEnteredTheLeftStack(x) && this.checkHight(x)) {
//            if (this.left.isEmpty()) {
//                if (x.getShape().equals("Plate")) {
//                    this.movable.get(i).setY(335);
//                } else {
//                    this.movable.get(i).setY(295);
//                }
//            } else {
//                this.movable.get(i).setY(this.getLeftHeight());
//            }
//
//            this.movable.get(i).setX(this.clown.getLeftCenter());
//            this.left.add(this.movable.get(i));
//            this.leftCollectedObjects.push(this.movable.get(i));
//            this.controlable.add(this.movable.get(i));
//            this.movable.remove(this.movable.get(i));
//        } else if (this.isPlateEnteredTheRightStack(x) && this.checkHight(x)) {
//            if (this.right.isEmpty()) {
//                if (x.getShape().equals("Plate")) {
//                    this.movable.get(i).setY(335);
//                } else {
//                    this.movable.get(i).setY(295);
//                }
//            } else {
//                this.movable.get(i).setY(this.getRightHeight());
//            }
//            this.movable.get(i).setX(this.clown.getRightCenter());
//            this.right.add(this.movable.get(i));
//            this.rightCollectedObjects.push(this.movable.get(i));
//            this.controlable.add(this.movable.get(i));
//            this.movable.remove(this.movable.get(i));
//        } else if (this.movable.get(i).getY() >= this.getHeight() && !this.controlable.contains(x)) {
//            // Only add to controlable if it's not already in there
//            this.controlable.add(this.movable.get(i));
//            this.movable.remove(this.movable.get(i));
//        } else {
//            // Only update the y-coordinate if the plate is not in the controlable list
//            this.movable.get(i).setY(this.movable.get(i).getY() + 1);
//        }
//    }
//
//    return !timeout;
//}

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getControlSpeed() {
        return CONTROLSPEED;
    }

}
