
public class NodoArbol {
	
	// Atributos
	private NodoArbol pi;
	private NodoArbol pd;
	private int codigo;
	private int cantidad;
	private float precio;
	
	// Constructor
	public NodoArbol(int codigo, int  cantidad, float precio){
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.precio = precio;
		pi = null;
		pd = null;
	}

	public NodoArbol getPi() {
		return pi;
	}

	public void setPi(NodoArbol pi) {
		this.pi = pi;
	}

	public NodoArbol getPd() {
		return pd;
	}

	public void setPd(NodoArbol pd) {
		this.pd = pd;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
