import java.io.*;
import java.util.*;
/**
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 *
 * El codigo {@code BFS} class es la representacion
 * de la solucion hacia el problema numero de Erdos, ejecutando
 * la tecnica de busqueda por amplitud sobre grafos 
 *
 * Compilacion: make
 */

public class BFS {
	private LinkedList<String> visitado;            // Almacena todos los vertices visitados durante el BFS
	private HashMap<String, Integer> numeroErdos;   // Almacena el Autor con su respectivo Numero de Erdos

	/**
	 * Busca los caminos mas cortos desde {@code s} vertice fuente hasta otro en el Grafo {@code G}.
	 * @param G el Grafo para la busqueda por amplitud
	 * @param s El vertice fuente por el cual comenzara la busqueda. 
	 */

	public BFS(GrafoNoDirigido G, String s) {
		visitado = new LinkedList<String>();
		numeroErdos = new HashMap<String, Integer>();
		bfs(G, s);
	}

	/**
	 * Recorre el grafo {@code G} desde un vertice fuente {@code s} hasta otro vertice alcanzable.
	 * @param G  Grafo
	 * @param s Vertice fuente
	 * Pre-condicion: Grafo G bien formado y un String S contenido en el Grafo como vertice.
	 * Post-condicion: Almacena en el HashMap el numero de Erdo correspondiente al nivel de busqueda por amplitud
	 * 
	*/

	private void bfs(GrafoNoDirigido G, String s) {
		LinkedList<String> lista = new LinkedList<String>();
		visitado.add(s);
		lista.addLast(s);
		numeroErdos.put(s,0);
		while (lista.isEmpty()==false) {
			String v = lista.getFirst();
			lista.removeFirst();
			for (Vertice w : G.adyacentes(v)) {
				if (visitado.contains(w.getId())==false) {
					numeroErdos.put(w.getId(),numeroErdos.get(v)+1);
					visitado.add(w.getId());
					lista.addLast(w.getId());
				}
			}
		}
	}

	
	/**
	 * Indica si existe un camino desde el vertice fuente {@code s} al vertice {@code v}
	 * @param v vertice destino
	 * @return {@code true} Si existe camino, {@code false} si no existe camino.
	 * Pre-condicion: String v que exista en el Grafo {@code G}
	 * Post-condicion: true si existe camino desde el vertice fuente {@code s} al vertice {@code v} || 
	 * false si no exite camino de {@code s} al vertice {@code v}
	 */
	public boolean caminoHasta(String v) {
		return visitado.contains(v);
	}

	/**
	 * Retorna el numero de erdos, dependiendo del vertice fuente {@code s} al vertice
	 * final  {@code v}
	 * @param v Vertice final
	 * @return numero de erdos del vertice {@code v}
	 * Pre-condicion: String v que exista en el Grafo {@code G}
	 * Post-condicion: Numero entero que sera el numero de erdos. 
	 */
	public Integer numeroErdos(String v) {
		return numeroErdos.get(v);
	}

}
