
public class Arbol {
	// Atributo
	private NodoArbol raiz;

	// Constructor
	public Arbol() {
		raiz = null;
	}

	// Metodo Insertar
	public void insertar(int codigo, int cantidad, float precio) {
		NodoArbol p = raiz;
		NodoArbol ant = null;
		while (p != null) {
			ant = p;
			if (p.getCodigo() < codigo) {
				p = p.getPd();
			} else {
				p = p.getPi();
			}
		}

		NodoArbol x = new NodoArbol(codigo, cantidad, precio);
		if (raiz == null) {
			raiz = x;
		} else {
			if (codigo > ant.getCodigo()) {
				ant.setPd(x);
			} else {
				ant.setPi(x);
			}
		}
	}

	// Metodo Eliminar
	public NodoArbol eliminar(int elem) {
		boolean encontrado = false;
		NodoArbol p = raiz;
		NodoArbol ant = null;
		while ((p != null) && (!encontrado)) {
			if (elem == p.getCodigo()) {
				encontrado = true;
			} else {
				ant = p;
				if (elem < p.getCodigo()) {
					p = p.getPi();
				} else {
					p = p.getPd();
				}
			}

		}
		if (encontrado) {
			delNodo(ant, p);
		}
		return p;
	}
	
	// Metodo Buscar
	public boolean buscar(int cod) {
		boolean b = false;
		NodoArbol p = raiz;
		while (p != null && !b) {
			if (cod == p.getCodigo()) {
				b = true;
				System.out.println("Elemento encontrado");
			} else {
				if (cod < p.getCodigo()) {
					p = p.getPi();
				} else {
					p = p.getPd();
				}
			}
		}
		return b;
	}

	// Metodo utilizado por Eliminar()
	public void delNodo(NodoArbol anterior, NodoArbol p) {
		if ((p.getPd() != null) && (p.getPi() != null)) {
			del2SubArbol(anterior, p);
		} else {
			if ((p.getPd() == null) && (p.getPi() == null)) {
				delHoja(anterior, p);
			} else {
				del1SubArbol(anterior, p);
			}
		}
	}

	// Metodo utilizado por delNodo()
	public void delHoja(NodoArbol anterior, NodoArbol p) {
		if (p == raiz) {
			raiz = null;
		} else {
			if (anterior.getCodigo() > p.getCodigo()) {
				anterior.setPi(null);
			} else {
				anterior.setPd(null);
			}
		}
	}

	// Metodo utilizado por delNodo()
	public void del1SubArbol(NodoArbol anterior, NodoArbol p) {
		if (anterior != null) {
			if (anterior.getCodigo() > p.getCodigo()) {
				if (p.getPi() != null) {
					anterior.setPi(p.getPi());
				} else {
					anterior.setPi(p.getPd());
				}
			} else if (p.getPd() != null) {
				anterior.setPd(p.getPd());
			} else {
				anterior.setPd(p.getPi());
			}
		} else {
			if (p.getPd() != null) {
				raiz = p.getPd();
			} else {
				raiz = p.getPi();
			}
		}
	}

	// Metodo utilizado por delNodo()
	public void del2SubArbol(NodoArbol anterior, NodoArbol p) {
		NodoArbol q = p.getPd();
		NodoArbol antQ = p;
		while (q.getPi() != null) {
			antQ = q;
			q = q.getPi();
		}
		p.setCodigo(q.getCodigo());
		delNodo(antQ, q);
	}

	// Get y Set de raiz
	public NodoArbol getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoArbol raiz) {
		this.raiz = raiz;
	}

}
