import java.io.IOException;
import java.util.Scanner;


public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		
		Scanner entrada = new Scanner(System.in);
		ProductoAMBL miProductoAMBL = new ProductoAMBL();
		ArbolApp miArbolApp = new ArbolApp();
		
		int op=9;
		do{
			System.out.println("*********************************************************");
			System.out.println("*********************OPCIONES*********************");
			System.out.println("1- Generar Arbol");
			System.out.println("2- Actualizacion de Productos");
			System.out.println("0- Salir");
			System.out.print("Ingrese una Opcion: ");
			op=Integer.parseInt(entrada.next());
			switch (op) {
			case 1:
				miArbolApp.menu();
				break;
			case 2:
				miProductoAMBL.menu();
				break;
			}
		}while(op!=0);
		entrada.close();
	}

}
