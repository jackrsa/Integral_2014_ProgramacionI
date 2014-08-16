import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class ProductoAMBL {
	
	Producto miProducto = new Producto();
	File archProducto = new File("producto.dat");
	RandomAccessFile flujoProducto = null;
	
	
	Scanner entrada = new Scanner(System.in);
	
	
	// Metodo abrir archivos, si el archivo no existe lo genera vacio.
	public void abrirArchivo() {
		try {
			if (!archProducto.exists()) {
				flujoProducto = new RandomAccessFile(archProducto, "rw");
				miProducto.generarVacio(flujoProducto);
			} else {
				flujoProducto = new RandomAccessFile(archProducto, "rw");
			}
		} catch (Exception e) {
			System.out.println("No se pudo abrir el archivo propiedades.");
		}
		
	}
	
	// Cierra el archivo
	public void cerrarArchivo() {
		try {
			flujoProducto.close();
		} catch (Exception e) {
			System.out.println("no se pudo cerrar el archivo propiedades");
		}
	}
	
	// Metodo altas
	public void altas() {
		int codigo, cantidadEx, tipo;
		float precioVenta;
		String denominacion;
		try {
			
			while (continuar()) {
				System.out.println();
				System.out.print("Ingrese un codigo: ");
				codigo = Integer.parseInt(entrada.nextLine());
				while (codigo <= 0 || codigo > 100) {
					System.out.println("El codigo debe ser mayor que cero y menor que cien.");
					System.out.print("Ingrese un codigo: ");
					codigo = Integer.parseInt(entrada.nextLine());
				} 
				// Controla alta existente.
				if (!validarCodPropiedad(codigo)) {
					
					System.out.print("Ingrese la denominacion: ");
					denominacion = entrada.nextLine();
					
					System.out.print("Ingrese la cantidad existente: ");
					cantidadEx = Integer.parseInt(entrada.nextLine());
					
					System.out.print("Ingrese el precio de venta: ");
					precioVenta = Float.parseFloat(entrada.nextLine());
					
					System.out.print("Ingrese el tipo 1(Hardware) / 2(Software): ");
					tipo = Integer.parseInt(entrada.nextLine());
					
									
					// seteo de datos
					miProducto.setCodigo(codigo);
					miProducto.setDenominacion(denominacion);
					miProducto.setCantidadEx(cantidadEx);
					miProducto.setPrecioVenta(precioVenta);					
					miProducto.setTipo(tipo);
					miProducto.setEstado(true);
					
					// grabar registro
					abrirArchivo();
					miProducto.grabar(flujoProducto);
					cerrarArchivo();
				} else {
					System.out.println("Alta existente");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Listado del archivo
	public void listado() {
		System.out.println("\t ***************LISTADO DE PRODUCTOS***************");
		System.out.println("\t ----------------------------------------------------");
		System.out.println("\t codigo \t deno                  \t cantEx \t precio \t tipo");
		System.out.println();
		try {
			abrirArchivo();
			int i = 1;
			while (i <= miProducto.getTOTALREG()) {
				flujoProducto.seek(miProducto.posicionar(i));
				miProducto.leer(flujoProducto);
				if (miProducto.isEstado()) {
					System.out.println("\t " + miProducto.getCodigo() + "\t\t " + miProducto.getDenominacion() + "\t " + miProducto.getCantidadEx() +
							"\t " + miProducto.getPrecioVenta() + "\t\t " + miProducto.getTipo());
				}
				i++;
			}
			cerrarArchivo();
		} catch (Exception e) {
			System.out.println("Error en listado");
		}
	}
	
	// Metodo para actualizar el precio del alquiler
	public void actualizarProductos(int codigo, int cantidad, float precio) {
		try {
			abrirArchivo();
			flujoProducto.seek(miProducto.posicionar(codigo));
			miProducto.leer(flujoProducto);
			if (miProducto.isEstado()) {
				// Actualizar cantidad existente
				miProducto.setCantidadEx(miProducto.getCantidadEx() + cantidad);				
				// Actualizar precio de alquiler
				miProducto.setPrecioVenta(precio);
				
				// grabar registro modificado
				miProducto.grabar(flujoProducto);
				cerrarArchivo();
				
				System.out.println();
				System.out.println("Producto con codigo " + codigo + " actualizado!");
			} else {
				// Si el producto no existe, dar el alta
				System.out.println();
				System.out.println("Producto Inexistente!");
				System.out.println();
				System.out.print("Ingrese la denominacion: ");
				String denominacion = entrada.nextLine();
				System.out.print("Ingrese el tipo 1(Hardware) / 2(Software): ");
				int tipo = Integer.parseInt(entrada.nextLine());
				
				// seteo de datos
				miProducto.setCodigo(codigo);
				miProducto.setDenominacion(denominacion);
				miProducto.setCantidadEx(cantidad);
				miProducto.setPrecioVenta(precio);					
				miProducto.setTipo(tipo);
				miProducto.setEstado(true);
				
				// grabar registro
				abrirArchivo();
				miProducto.grabar(flujoProducto);
				cerrarArchivo();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Valida un codigo de producto, retorna true si existe y false en caso contrario.
	public boolean validarCodPropiedad(int codigo) {
		boolean estado = false;
		try {
			abrirArchivo();
			flujoProducto.seek(miProducto.posicionar(codigo));
			miProducto.leer(flujoProducto);
			estado = miProducto.isEstado();
			cerrarArchivo();
		} catch (Exception e) {
			System.out.println("Error en validarCodigo");
		}
		return estado;
	}

	// Metodo Confirmar
	public boolean confirmar() throws IOException {
		System.out.println("Confirma S/N: ");
		String opc = entrada.nextLine();
		opc = opc.equalsIgnoreCase("") ? "S" : opc;
		return opc.toUpperCase().charAt(0) != 'N';
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
			System.out.println("\n\n ** Menu de Opciones - Productos ** \n");
			System.out.println("1- Altas");
			System.out.println("2- Listado de Productos");
			System.out.println("0- Salir");
			System.out.print("Ingrese una Opcion: ");
			
			op = Integer.parseInt(entrada.nextLine());
	
			switch (op) {
			case 1:
				altas();
				break;
			case 2:
				listado();
				break;
			case 0:
				break;
			}
		} while (op != 0);
	}

}
