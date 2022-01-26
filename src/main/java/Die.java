import java.util.Random;
//Die object
public class Die {
    private int value;

    public Die() {
        super();
        Random rand = new Random();
        int roll = (rand.nextInt(5)+1);//randomly gets a value 1 through 6
        this.value = roll;
    }//end Die

    public int Roll() {
        Random rand = new Random();
        int result = (rand.nextInt(6)+1);
        this.value = result;
        return result;
    }//end Roll

    public void Draw() {
        switch(this.value){//decides what die to draw
            case 1:
                Art.one();
                break;
            case 2:
                Art.two();
                break;
            case 3:
                Art.three();
                break;

            case 4:
                Art.four();
                break;

            case 5:
                Art.five();
                break;

            case 6:
                Art.six();
                break;

            default://don't think this can be reached
                System.out.println("Something went wrong");
                break;
        }
    }
}
