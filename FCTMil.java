import java.util.Arrays;
class FCTMil{
    private int[] winnerArray = new int[13];
    private Double prizePool;
    /**
     * @param prizePool game prize pool
     */
    public void newGame(Double prizePool){
        this.prizePool = prizePool;
        for(int i=1; i<winnerArray.length; i++){
            winnerArray[i] = 0;
        }
        System.out.println(Arrays.toString(winnerArray));
    }
}