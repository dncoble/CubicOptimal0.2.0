package q;
import c.*;
/* I have no memory of making this code.
 * the comment says that it doesn't work but i don't know how wrong it is
 * i never made a fromInt method, which isn't necessary since you can't use Kociemba with symmetry anyways
 */
public class Kociemba implements Coordinate, RawCoord {
    public static String NAME;
    private static int MAX_SIZE;
    static {
        NAME = "Kociemba";
        //i don't know the size
        MAX_SIZE = -1;
    }

    public Kociemba() {}
    /* this creates an int representation based on the Kociemba subset, just like the above subsets
     * the kociemba subset is generated by <U, R2, D>, or can be considered to be all CO & EO oriented,
     * with all midlayer edges on the midlayer.
     * code for creating the midlayer slice coord is beyond me. i heavily relied on Kociemba's
     * definition of the midlayer slice, and copied the code for writing the binomial coefficient
     * from https://stackoverflow.com/questions/36925730/java-calculating-binomial-coefficient &
     * https://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java
     * *this method does not currently work, as it's outputs are out of int max range.
     * it could be fixed by using + Integer.MIN_VALUE, but that is not currently implemented, because of potential
     * problems in the Table Builder class */

     public int value(Cube cube) {
        int rtrn = ((new CO()).value(cube) * 2048 + (new EO()).value(cube)) * 495;
        int[] ep = cube.getEP();
        int intSlice = 0;
        int k = 3;
        for(int i = 11; i >= 0; i --) {
            if(ep[i] > 7 || ep[i] < 4) {
                int binoCoef = 0;
                //the below code is what was copied & altered from other sites
                if (k > i - k) {k = i - k;}
                for (int j = 1, m = i; j <= k; j++, m--) {binoCoef = binoCoef * m / j;}
                intSlice += binoCoef;
            }
            else {
                k --;
            }
        }
        rtrn += intSlice;
        return rtrn;
    }
    public int rotate(Cube cube, int rotation) {
         cube.rotateCO(rotation);
         cube.rotateEO(rotation);
         cube.rotateCP(rotation);
         cube.rotateEP(rotation);
         return value(cube);
    }
    public Cube setCoord(Cube cube, int value) {/*this method has not been made*/; return cube;}
    public String name() {return NAME;}
    public int size() {return MAX_SIZE;}
}
