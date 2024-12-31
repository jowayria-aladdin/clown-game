package Objects;

public class Plates extends ImageObject {
   private String color,shape;
    public Plates(String color, String shape, int posX, int posY, String path) {
        super(posX, posY, path);
        this.color = color;
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
    

    public int getCenter()
    {
        if (this.getShape().equals("Plate"))
              return (int)(this.getX()+(this.getWidth()/2)+10);
        else 
            return (int)(this.getX()+(this.getWidth()/2));
    }

//    @Override
//    public void setX(int mX) {
//        return ;
//    }

}
