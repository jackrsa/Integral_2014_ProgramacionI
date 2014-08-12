import java.io.IOException;
import java.util.Scanner;


public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		
		Scanner entrada = new Scanner(System.in);
		ProductoAMBL miPropiedadesAMBL = new ProductoAMBL();
		ArbolApp miArbolApp = new ArbolApp();
		
		int op=9;
		do{
			System.out.println("*********************************************************");
			System.out.println("*********************OPCIONES*********************");
			System.out.println("1- Actualizacion de Alquiler");
			System.out.println("2- Actualizacion de Propiedades");
			System.out.println("0- Salir");
			System.out.print("Ingrese una Opcion: ");
			op=Integer.parseInt(entrada.next());
			switch (op) {
			case 1:
				miArbolApp.menu();
				break;
			case 2:
				miPropiedadesAMBL.menu();
				break;
			}
		}while(op!=0);
		entrada.close();
	}

}
