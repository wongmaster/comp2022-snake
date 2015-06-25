import javax.swing.JPanel;

public class Lista extends JPanel {

	private Lista inicio1; // Node criado para representar o inicio

	private int x;
	private Lista proximo;
	private int y;
	
	public Lista() {

	}

	public Lista inserirFinal(Lista _node) {
		if (isEmpty()) {
			inicio1 = _node;
		} else {
			Lista aux = inicio1;
			while (aux.getProximo() != null) {
				aux = aux.getProximo();
			}
			aux.setProximo(_node);

		}

		return _node;
	}

	private boolean isEmpty() {
		if (inicio1 == null) {
			return true;
		} else {
			return false;
		}
	}

	public Lista getInicio1() {
		return inicio1;
	}

	public void setInicio1(Lista inicio1) {
		this.inicio1 = inicio1;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public Lista getProximo() {
		return proximo;
	}

	public void setProximo(Lista proximo) {
		this.proximo = proximo;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	

}
