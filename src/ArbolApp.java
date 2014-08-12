import java.io.*;
import java.util.Scanner;

public class ArbolApp {
	
	Arbol miArbol = new Arbol();
	
	ProductoAMBL miPropiedadesAMBL = new ProductoAMBL();
	// NovedadesABML miNovedadesABML = new NovedadesABML();
	
	Scanner entrada = new Scanner(System.in);
	//BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));


	// Metodos para actualizar el precio de alquiler
	public void actualizarPrecioAlquiler() {
		recorrerArbolParaActualizarPrecio(miArbol.getRaiz());
	}

	public void recorrerArbolParaActualizarPrecio(NodoArbol p) {
		if (p != null) {
			recorrerArbolParaActualizarPrecio(p.getPi());
			// miPropiedadesAMBL.actualizarPrecioAlquiler(p.getCodigoPropiedad(), p.getMonto());
			recorrerArbolParaActualizarPrecio(p.getPd());
		}
	}

	// Metodo para generar el arbol
	public void crearArbol() throws IOException {
		while (continuar()) {
			
			System.out.print("Ingrese un codigo: ");
			int codigo = Integer.parseInt(entrada.nextLine());
			
			System.out.print("Ingrese la cantidad: ");
			int cantidad = Integer.parseInt(entrada.nextLine());
			
			System.out.print("Ingrese el precio: ");
			float precio = Float.parseFloat(entrada.nextLine());

			miArbol.insertar(codigo, cantidad, precio);
		}
	}

	// Metodos para Imprimir elementos del Arbol
	public void imprimirArbol() {
		System.out.println("=====================");
		int niv = 0;
		System.out.println("Elementos del arbol");
		System.out.println("niv   Codigo   Cantidad Precio");
		print(miArbol.getRaiz(), niv);
		System.out.println("");
	}
	

	public void print(NodoArbol p, int nivel) {
		if (p != null) {
			print(p.getPi(), nivel + 1);
			System.out.print("   " + nivel + "  ");
			System.out.print("    " + p.getCodigo() + "   ");
			System.out.print("    " + p.getCantidad() + "   ");
			System.out.print("    " + p.getPrecio() + "   ");
			System.out.print("\n");
			print(p.getPd(), nivel + 1);
		}
	}



	// Otros metodos para imprimir el arbol por nivel
	public void imprimirN() throws IOException {
		System.out.println("=====================");
		int niv = 0;
		System.out.print("Ingrese Nivel:");
		int x = Integer.parseInt(entrada.nextLine());
		System.out.println("Elementos del arbol");
		System.out.println("niv   Codigo    Cantidad    Precio");
		printN(miArbol.getRaiz(), x, niv);
		System.out.println("");
		System.out.println("=====================");
	}
	
	public void printN(NodoArbol p, int x1, int nivel) {
		if (p != null) {
			printN(p.getPi(), x1, nivel + 1);
			if (nivel == x1) {
				System.out.print("   " + nivel + "  ");
				System.out.print("    " + p.getCodigo() + "   ");
				System.out.print("    " + p.getCantidad() + "   ");
				System.out.print("    " + p.getPrecio() + "   ");
				System.out.print("\n");
			}
			printN(p.getPd(), x1, nivel + 1);
		}
	}



	// Metodo para generar el archivo Novedades a partir de los datos del arbol
	public void generarArchivoNovedades() throws IOException {
		recorrerArbolParaGenerarArchivoNovedades(miArbol.getRaiz());
	}

	public void recorrerArbolParaGenerarArchivoNovedades(NodoArbol p) throws IOException {
		if (p != null) {
			recorrerArbolParaGenerarArchivoNovedades(p.getPi());
			// miNovedadesABML.altas(p.getCodigoPropiedad(), p.getMonto());
			recorrerArbolParaGenerarArchivoNovedades(p.getPd());
		}
	}
	
	// Metodo Continuar
	public boolean continuar() throws IOException {
		System.out.println("Continuar S/N");
		String opc = entrada.nextLine();
		opc = opc.equalsIgnoreCase("") ? "S" : opc;
		return opc.toUpperCase().charAt(0) != 'N';
	}

	// Menu de Opciones
	public void menu() throws IOException {
		int op = -1;
		do {
	
			System.out.println("  Actualizacion de Alquiler");
			System.out.println("  =================");
			System.out.println("1- Generar Arbol");
			System.out.println("2- Actualizar monto  de alquiler");
			System.out.println("3- Imprimir Arbol");
			System.out.println("4- Generar archivo Novedades");
			System.out.println("5- Listado archivo Novedades");
			System.out.println("0 - Salir");
			System.out.println();
			System.out.print("Ingrese su opcion: ");
			op = Integer.parseInt(entrada.nextLine());
	
			switch (op) {
			case 1:
				crearArbol();
				break;
			case 2:
				actualizarPrecioAlquiler();				
				break;
			case 3:
				imprimirArbol();
				break;
			case 4:
				// generarArchivoNovedades();
				break;
			case 5:
				//Listado de novedades
				//miNovedadesABML.listado();
				break;
			case 0:
				break;
			}
		} while (op != 0);
	}
	
}
