import java.io.IOException;
import java.io.RandomAccessFile;


public class Producto {
	
	// Atributos
	private int codigo; //4
	private String denominacion;//30 + 2
	private int cantidadEx;//4
	private float precioVenta;//4
	private int tipo;//4
	private boolean estado;//1
	
	private final int TAMREG = 49;
	private final int TOTALREG = 100;
	
	// Lee datos del archivo a atraves del flujo recibido.
	public void leer(RandomAccessFile flujo) throws IOException{
		codigo = flujo.readInt();
		denominacion = flujo.readUTF();
		cantidadEx = flujo.readInt();
		precioVenta = flujo.readFloat();
		tipo = flujo.readInt();
		estado = flujo.readBoolean();
	}
	
	// Graba datos en el archivo a atraves del flujo recibido.
	public void grabar(RandomAccessFile flujo) throws IOException{
		flujo.seek(posicionar(codigo));
		
		flujo.writeInt(codigo);
		flujo.writeUTF(ajustarCadena(denominacion, 30));
		flujo.writeInt(cantidadEx);
		flujo.writeFloat(precioVenta);
		flujo.writeInt(tipo);
		flujo.writeBoolean(estado);
	}
	
	// permite posicionarse en un registro determinado.
	public int posicionar(int n) {
		return TAMREG*(n-1);
	}

	// Permite ajustar una cadena al tama�o indicado en el parametro "n".
	public String ajustarCadena(String cadena, int n) {
		if (cadena.length() > n ) cadena = cadena.substring(0, n);
		else 
			for(int i = cadena.length(); i < n; i++) {
			   cadena=cadena + " "; }
		return cadena;
	}
	
	// Genera archivo vacio con la cantidad de registros indicada en TOTALREG
	public void generarVacio(RandomAccessFile flujo) throws IOException{
		tipo = cantidadEx = 0;
		precioVenta = 0;
		denominacion = ajustarCadena("", 30);
		estado = false;
		int i = 1;
		while (i <= TOTALREG) {
			codigo = i;
			grabar(flujo);
			i++;
		}
		
	}
	
	// Metodos gets y sets

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public int getCantidadEx() {
		return cantidadEx;
	}

	public void setCantidadEx(int cantidadEx) {
		this.cantidadEx = cantidadEx;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getTAMREG() {
		return TAMREG;
	}

	public int getTOTALREG() {
		return TOTALREG;
	}

	
	
}
