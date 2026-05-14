package datastr;

public class MyBST<Ttype>{
	private MyNode<Ttype> rootNode = null;
	private int howManyElements = 0;
	
	//geteri, prieks blokiem nevajag
	public int getHowManyElements() {
		return howManyElements;
	}
	
	//seteri - nav nepieciesami
	
	//konstruktori - nevajag nevienu, jo default jau nak no Object klases
	
	private boolean isEmpty() {
		return (howManyElements == 0); 
	}
	
	private boolean isFull() {
		try {
			new MyNode<Character>('A');//megina RAM rezervet vietu
			return false;
		}
		catch(OutOfMemoryError e){
			return true;
		}
	}
	
	public void add(Ttype element) throws Exception{
		if(isFull()) {
			throw new Exception("Koks ir pilns, nevar pievienot elementu!");
		}
		//Ja koks tukss, ieliek pirmo ka root
		if(isEmpty()) {
			MyNode<Ttype> newNode = new MyNode<Ttype>(element);
			rootNode = newNode;
		}
		else {
		addHelper(rootNode, element);
		}
		howManyElements++;
	}
	
	private void addHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp != null) {
			if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {//ja lielaks, iet pa labi
				if(nodeTemp.getRightChildNode() == null) {//laba puse neka nav
					MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					newNode.setParrentNode(nodeTemp);
					nodeTemp.setRightChildNode(newNode);
				}
				else {//laba puse jau ir bloks, tapec rekursivi izsauc so pasu funkciju
				addHelper(nodeTemp.getRightChildNode(), element);
				}
			}
			else {//ja mazaks, iet pa kreisi
				if(nodeTemp.getLeftChildNode() == null) {//kreisa puse neka nav
					MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					newNode.setParrentNode(nodeTemp);
					nodeTemp.setLeftChildNode(newNode);
				}
				else {//kreisa puse jau ir bloks, tapec rekursivi izsauc so pasu funkciju
					addHelper(nodeTemp.getLeftChildNode(), element);
				}
			}
		}
	}
	
	//meklesanas funkcija
	public boolean search(Ttype element) throws Exception{
		if(isEmpty()) {
			throw new Exception("Koks ir tukss, nevar mekelet elementus!");
		}
		return searchHelper(rootNode, element);
	}
	
	private boolean searchHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp != null) {
			//ja sakrit, atgriez ka atrasts
			if(nodeTemp.getElement().equals(element)) {
				return true;
			}
			else {//ja nesakrit tad turpina meklet
				if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {//mekle pa labo pusi
					if(nodeTemp.getRightChildNode() == null) {
						//ja elements neeksiste
						return false;
					}
					else {
						return searchHelper(nodeTemp.getRightChildNode(),element);
					}
				}
				else {//mekle pa kreiso pusi
					if(nodeTemp.getLeftChildNode() == null) {
						//ja elements neeksiste
						return false;
					}
					else {
						return searchHelper(nodeTemp.getLeftChildNode(),element);
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	//print funkcija
	public void print() throws Exception{
		if(isEmpty()) {
			throw new Exception("Koks ir tukss, nevar izdrukat!");
		}
		printHelper(rootNode);
	}
	
	private void printHelper(MyNode<Ttype> nodeTemp) {
		if(nodeTemp != null) {
			System.out.println("P: " + nodeTemp.getElement());
			//vai eksiste kreisais berns
			if(nodeTemp.getLeftChildNode() != null) {
				System.out.println("P: " + nodeTemp.getElement() + " L child: " + nodeTemp.getLeftChildNode().getElement());
				//izpilda rekursivi funkciju uz kreisa berna
				printHelper(nodeTemp.getLeftChildNode());
			}
			//vai eksiste labais berns
			if(nodeTemp.getRightChildNode() != null) {
				System.out.println("P: " + nodeTemp.getElement() + " R child: " + nodeTemp.getRightChildNode().getElement());
				//izpilda rekursivi funkciju uz laba berna
				printHelper(nodeTemp.getRightChildNode());
			}
		}
	}

}
