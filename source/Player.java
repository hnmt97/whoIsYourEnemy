// class for all poker player
class Player{
	private int id;
	private int role;
	private int AITendency;
	private double skill;
	private Hand hand;
	private int bet;
	private int chips;
	private int[] clues;
	
	//constructor
	public Player(int id, int role, int AITendency, double skill, Hand hand, int bet, int chips, int[] clues){
		this.id = id;
		this.role = role;
		this.AITendency = AITendency;
		this.skill = skill;
		this.hand = hand;
		this.bet = bet;
		this.chips = chips;
		this.clues = clues;		
	}
	
	//Accessor for reading parameter
	public int getid() {return this.id;}
	public int getrole() {return this.role;}
	public int getAITendency() {return this.AITendency;}
	public double getskill() {return this.skill;}
	public Hand gethand() {return this.hand;}
	public int getbet() {return this.bet;}
	public int getchips() {return this.chips;}
	public int[] getclues() {return this.clues;}
	
	//Mutator for update parameter by turn
//	public void sethand(int[] newhand) {this.hand = newhand;}
	public void setbet(int newbet) {this.bet = newbet;}
	public void setchips(int newchips) {this.chips = newchips;}
	public void addchips (int newchips) {this.chips += newchips;}
	public void removechips (int newchips) {this.chips -= newchips;}
	
}// player class end

