package Objects;

public class Clown extends ImageObject {

    public Clown(int posX, int posY, String path) {
        super(posX, posY, path);
    }

    @Override
    public void setY(int mY) {
        return;
    }
    public int getLeftCenter()
    {
    return this.getX()+50;
    }
     public int getRightCenter()
    {
    return this.getX()+265;
    }
}
