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
	
	//dzesanas funkcija
	public void remove(Ttype element) throws Exception{
		if(isEmpty()) {
			throw new Exception("Koks ir tukšs, nevar dzest elementu!");
		}
		removeHelper(rootNode, element);
	}
	
	private void removeHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp != null) {
			//ja sakrit, atgriez ka atrasts
			if(nodeTemp.getElement().equals(element)) {
				//visi 4 dzesanas gadijumi:
				
				//1. gadijums, ja elements ir lapa, nav neviena berna
				if(nodeTemp.getLeftChildNode() == null && nodeTemp.getRightChildNode() == null) {
					MyNode<Ttype> parentNode = nodeTemp.getParrentNode();
					//janoskaidro, kura puse ir berns
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0 ) {//ja lielaks => labais berns
						parentNode.setRightChildNode(null);
					}
					else {//ja mazaks => kreisais berns
						parentNode.setLeftChildNode(null);
					}
				}
				//2. gadijums, ja berns tikai kreisa puse
				else if (nodeTemp.getLeftChildNode() != null && nodeTemp.getRightChildNode() == null) {
					MyNode<Ttype> parentNode = nodeTemp.getParrentNode();
					MyNode<Ttype> leftChildNode = nodeTemp.getLeftChildNode();
					//vai dzesamais elements lielaks par savu vecaku
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0 ) {//ja lielaks => labais berns
						parentNode.setLeftChildNode(leftChildNode);
						leftChildNode.setParrentNode(parentNode);
					}
					else {
						parentNode.setRightChildNode(leftChildNode);
						leftChildNode.setParrentNode(parentNode);
					}
					
				}
				//3. gadijums, ja berns tikai laba puse
				else if (nodeTemp.getLeftChildNode() == null && nodeTemp.getRightChildNode() != null) {
					MyNode<Ttype> parentNode = nodeTemp.getParrentNode();
					MyNode<Ttype> rightChildNode = nodeTemp.getRightChildNode();
					//vai dzesamais elements lielaks par savu vecaku
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0 ) {//ja lielaks => labais berns
						parentNode.setRightChildNode(rightChildNode);
						rightChildNode.setParrentNode(parentNode);
					}
					else {
						parentNode.setLeftChildNode(rightChildNode);
						rightChildNode.setParrentNode(parentNode);
					}
				}
				//4. gadijums, ja abi berni eksiste
				else {
					//TODO
				}
				
				
				
				
			}
			else {//ja nesakrit tad turpina meklet
				if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {//mekle pa labo pusi
					if(nodeTemp.getRightChildNode() != null) {
						//ja elements eksiste
						removeHelper(nodeTemp.getRightChildNode(), element);;
					}
				}
				else {//mekle pa kreiso pusi
					if(nodeTemp.getLeftChildNode() != null) {
						//ja elements eksiste
						removeHelper(nodeTemp.getLeftChildNode(), element);;
					}
				}
			}
		}

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
