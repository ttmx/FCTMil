import java.util.Arrays;
class FCTMil{
    private int[] winnerArray;
    private int[] bets;
    private int[] key;
    private Double prizePool;
    private int hitNums;
    private int hitStars;
    public boolean inGame;
    public boolean inMenu;
    public FCTMil(){
    winnerArray = new int[13];
    bets = new int[7];
    key = new int[7];
    inGame = false;
    inMenu = true;
    prizes = new int[6][3];
    }
    /**
     * @param prizePool game prize pool
     */
    public void newGame(Double prizePool){
        this.prizePool = prizePool;
        for(int i=1; i<winnerArray.length; i++){
            winnerArray[i] = 0;
        }
        System.out.println(Arrays.toString(winnerArray));
        System.out.println(prizePool);
        inGame = true;
    }
    public int bet(int[] bets){
        this.bets = bets;
        System.out.println(Arrays.toString(bets));
        return prize(randomKey(),bets);
    }
    private int[] randomKey(){
        for(int i = 0; i<5;i++){
            key[i] = (int)Math.random()*51;
        }
        for(int i = 0; i<2;i++){
            key[i] = (int)Math.random()*13; 
        }
        return key;
    }
    private int prize(int[] key,int[] bet){
        hitNums = 0;
        hitStars = 0;
        for(int i = 0;i<5;i++){
            for(int a = 0;a<5;a++){
                if(key[i] == bet[a]){
                    hitNums++;
                    break;
                }
            }
        }
        for(int i = 5;i<7;i++){
            for(int a = 5;a<7;a++){
                if(key[i] == bet[a]){
                    hitStars++;
                    break;
                }
            }
        }
        int prizeSelector = 10*hitNums + hitStars;
        switch(prizeSelector){
            case 52:
                prizeSelector = 1;
                break;
            case 51:
                prizeSelector = 2;
                break;
            case 50:
                prizeSelector = 3;
                break;
            case 42:
                prizeSelector = 4;
                break;
            case 41:
                prizeSelector = 5;
                break;
            case 32:
                prizeSelector = 6;
                break;
            case 40:
                prizeSelector = 7;
                break;
            case 22:
                prizeSelector = 8;
                break;
            case 31:
                prizeSelector = 9;
                break;
            case 30:
                prizeSelector = 10;
                break;
            case 12:
                prizeSelector = 11;
                break;
            case 21:
                prizeSelector = 12;
                break;
            case 20:
                prizeSelector = 13;
                break;
        }
        return prizeSelector;
    }
}