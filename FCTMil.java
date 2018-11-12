//import java.util.Arrays;
class FCTMil{
    private int[] winnerArray;
    private int[] bets;
    private int[] key;
    private Double prizePool;
    private int hitNums;
    private int hitStars;
    public boolean inGame;
    public boolean inMenu;
    private IteratorInt starIte;
    private IteratorInt numIte;
    Key keyC = new Key();
    public FCTMil(){
    winnerArray = new int[13];
    bets = new int[7];
    key = new int[7];
    inGame = false;
    inMenu = true;
    }
    /**
     * @param prizePool game prize pool
     */
    public void newGame(Double prizePool){
        this.prizePool = prizePool;
        for(int i=1; i<winnerArray.length; i++){
            winnerArray[i] = 0;
        }
        starIte = keyC.criaIteratorStars();
        numIte = keyC.criaIteratorNumbers();
        inGame = true;
    }
    // Betting is directed here
    public int bet(int[] bets){
        this.bets = bets;
        //System.out.println(Arrays.toString(bets));
        int prizeNum = prize(getKey(),bets);
        winnerArray[prizeNum-1]++;
        return prizeNum;
    }
    // Return current key array
    private int[] getKey(){
        for(int i = 0;numIte.hasNextInt();i++){
            key[i]=numIte.nextInt();
        }
        for(int i = 0;starIte.hasNextInt();i++){
            key[i+5]=starIte.nextInt();
        }
        numIte.reinitialize();
        starIte.reinitialize();
        return key;
    }
    
    private int prize(int[] key,int[] bet){
        hitNums = 0;
        hitStars = 0;
        for(int i = 0;i<5;i++){
            for(int a = 0;a<5;a++){
                if(key[i] == bet[a]){
                    hitNums++;
                }
            }
        }
        for(int i = 5;i<7;i++){
            for(int a = 5;a<7;a++){
                if(key[i] == bet[a]){
                    hitStars++;
                }
            }
        }
        // Since hitNums and hitStars are both lower than 10 I can use them as a sort of index for the switch statement
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
            default:
                prizeSelector = 0;
                break;
        }
        return prizeSelector;
    }
public String exit(){
    String exitString = "";
    String formattedPercent = "";
    Double[] percent = {43.2,4.15,1.92,1.45,1.48,1.67,1.38,1.75,2.85,4.1,4.95,13.85,17.25};
    // Replace percent array with each prizes value
    for (int i = 0;percent.length>i;i++){
        percent[i]=prizePool*percent[i]*0.01/winnerArray[i];
    }
    //System.out.println(Arrays.toString(percent));
    for (int i = 0;winnerArray.length>i;i++){
        if(percent[i]!=1/0f){
            formattedPercent = String.format("%.2f",percent[i]).replaceAll(",",".");
            // Nivel: 1 Jogadores: 999 Valor premio: 999999,99 Euros
            exitString += "Nivel: " + (i+1) + " Jogadores: " + winnerArray[i] + " Valor premio: " + formattedPercent + " Euros \n";
        }else{
            exitString += "Nivel: " + (i+1) + " Jogadores: " + winnerArray[i] + "\n";
        }
    }
    //System.out.println("exitString: " + exitString);
    return exitString;
    }

}