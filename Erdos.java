import java.io.*;
import java.util.*;
/**
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 *
 * El codigo {@code Erdos} class es la representacion
 * de la solucion hacia el problema numero de Erdos. 
 * en codigo java.
 *
 * Compilacion: make
 */

public class Erdos {
	/* String Estatico Erdos, P. que sera la raiz de nuestro Grafo
	   HashMap que me da la facilidad de obtener un String vinculado a un Entero, 
	   aqui es donde se guardara (autor, numero de Erdo)
	   */
   private static final String ERDOS = "Erdos, P.";
   HashMap<String, Integer> numeroErdos = new HashMap<String, Integer>();

	
		/**
		 *Carga en un grafo la informacion almacenada en el archivo de texto cuya direccion viene dada 
		 * por @param dirArchivo 
		 * @throws IllegalArgumentException if {@code P < 1 or P>3200}
		 * @throws IllegalArgumentException if {@code E < 0 or E>3000}
		*/
		public void Problem(String dirArchivo) {
				try{
						//Para leer el archivo con un Buffer
						//String dirArchivo=args[0];
						File file = new File(dirArchivo);
						FileReader fileReader = new FileReader(file);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						StringBuffer stringBuffer = new StringBuffer();
						String line;
						//Leemos la primera linea (Num de vertices)
						line = bufferedReader.readLine();
						String[] datos = line.split(" ");
						int P = Integer.parseInt(datos[0]);
						if (P <= 0 || P>32000) throw new IllegalArgumentException("El numero de publicaciones debe ser no negativo");
						int N = Integer.parseInt(datos[1]);
						if (N <= 0 || N>3000) throw new IllegalArgumentException("El numero de autores debe ser no negativo");
						//Inicializo Dirafo 
						GrafoNoDirigido G= new GrafoNoDirigido();
						/*		var:
								@numPersonas= El numero de personas a calcular numero de Erdos
								@numArticulos= El numero de publicaciones. 
								
						*/
						int numArticulos=0;
						int numPersonas=0;
						//Leemos las siguientes lineas para crear el Grafo
						while (numArticulos<P) {
									 LinkedList<String> lista = new LinkedList<String>();
									 line = bufferedReader.readLine();
									 String[] information = line.split(":");
									 String[] info = information[0].split(",");
									for(int i=0; i<info.length-1; i+=2){
										String autor = info[i].trim() + ", " + info[i+1].trim();
										lista.add(autor);
									}
									for (String i: lista){
										for (String j: lista){
											if(i.equals(j)==false){
												Vertice a = new Vertice(i,0);
												Vertice b = new Vertice(j,0);
												G.agregarVertice(a);
												G.agregarVertice(b);
												Arista arc=new Arista(a.getId()+b.getId(),0,a,b);
												G.agregarArista(arc);
											}
										}
									}
									 numArticulos++;

						}
						/*
						Aplicamos BFS para calcular el numero de Erdos e imprimimos
						segun la cantidad de personas leidas en el archivo de texto.
						Recordemos que cuando el autor no tiene vinculacion con Erdos, P. 
						su numero de Erdos sera igual a "infinity"
						*/

						BFS erdos = new BFS(G,ERDOS);
						System.out.println("Escenario " + (numPersonas+1));
						while (numPersonas<N) {
									 String people = bufferedReader.readLine();
										System.out.print(people + " ");
										if(erdos.caminoHasta(people)==true){
										Integer numero =  erdos.numeroErdos(people);
										System.out.println(numero);
									 }
									 else {
										System.out.println("infinity");
									 }
									 numPersonas++;
						}

				}catch (Exception e) {
						throw new InputMismatchException("Invalid input format constructor");
				}
		}
		public static void main(String[] args) throws IOException {
					/* 
					Ejecuta el problema pasando por la consola el archivo a leer
					con el formato adecuado
					*/
					new Erdos().Problem(args[0]);
		}
		
	}

