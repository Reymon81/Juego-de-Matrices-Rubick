import java.util.InputMismatchException;
import java.util.Scanner;

public class Rubick {

	public static String FILACOLUMNA = "Inserta el numero del tamaño de la matriz NxN: ";
	public static String JUGADOR1 = "Jugador1 inserta el numero de columna que vas a mover: ";
	public static String JUGADOR2 = "Jugador2 inserta el numero de fila que vas a mover: ";
	public static String CELDAS = "Inserta el nomero de celdas que quieres mover: ";
	public static String PUNTOS1 = "Los puntos del jugador 1 son: ";
	public static String PUNTOS2 = "Los puntos del jugador 2 son: ";
	public static String FILAS = "Iserta el numero de filas de la matriz: ";
	public static String COLUMNAS = "Iserta el numero de columnas de la matriz: ";
	public static int N = 3; // LA MATRIZ DEBE DE SER COMO MINIMO DE 3X3

	// FUNCION PARA CREAR LA MATRIZ CON NUMEROS ALEATORIOS
	public static int[][] crearMatriz(int filas, int columnas) {
		int[][] matriz = new int[filas][columnas];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = (int) (Math.random() * 9) + 1;
			}
		}
		return matriz;
	}

	// METODO PARA MOSTRAR LA MATRIZ POR FILAS
	public static void mostrarMatriz(int[][] matriz) {
		System.out.println();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// METODO PARA DESPLAZAR LOS ELEMENTOS DE UNA FILA
	public static void desplazarFila(int[][] matriz, int columnas, int fila, int desplazamientos) {
		for (int x = 0; x < desplazamientos; x++) {

			int aux = matriz[fila][columnas - 1];
			for (int i = matriz[0].length - 1; i > 0; i--) {
				matriz[fila][i] = matriz[fila][i - 1];
			}
			matriz[fila][0] = aux;
		}
	}

	// METODO PARA DESPLAZAR LOS ELEMENTOS DE UNA COLUMNA
	public static void desplazarColumna(int[][] matriz, int filas, int columna, int desplazamientos) {
		for (int x = 0; x < desplazamientos; x++) {

			int aux = matriz[filas - 1][columna];
			for (int i = matriz.length - 1; i > 0; i--) {
				matriz[i][columna] = matriz[i - 1][columna];
			}
			matriz[0][columna] = aux;
		}
	}

	// FUNCION QUE COMPRUEBA FILA A FILA SI HAY 3 ELEMENTOS JUNTOS IGUALES, LOS SUMA
	// EN TOTAL Y LOS PONE A 0
	public static int puntosFilas(int[][] matriz) {
		int suma = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = matriz[0].length - 1; j > 1; j--) {
				if (matriz[i][j] == matriz[i][j - 1] && matriz[i][j] == matriz[i][j - 2]) {
					suma += matriz[i][j] * 3;
					matriz[i][j] = 0;
					matriz[i][j - 1] = 0;
					matriz[i][j - 2] = 0;
				}
			}
		}
		return suma;
	}

	// FUNCION QUE COMPRUEBA COLUMNA A COLUMNA SI HAY 3 ELEMENTOS JUNTOS IGUALES,
	// LOS SUMA EN TOTAL Y LOS PONE A 0
	public static int puntosColumnas(int[][] matriz) {
		int suma = 0;
		for (int j = 0; j < matriz[0].length; j++) {
			for (int i = matriz.length - 1; i > 1; i--) {
				if (matriz[i][j] == matriz[i - 1][j] && matriz[i][j] == matriz[i - 2][j]) {
					suma += matriz[i][j] * 3;
					matriz[i][j] = 0;
					matriz[i - 1][j] = 0;
					matriz[i - 2][j] = 0;
				}
			}
		}
		return suma;
	}

	// METODO PARA CAMBIAR EL VALOR DE LAS CELDAS A UN NUMERO ALEATORIO CUANDO SON
	// DIFERENTES A 0
	public static void cambiarNumeros(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] != 0)
					matriz[i][j] = (int) (Math.random() * 9) + 1;
			}
		}
	}

	// METODO PARA IMPRIMIR EL MENU
	public static void printMenu() {
		System.out.println("\n****** MENU *******\n");
		System.out.println("1. Rubik Numbers NxN\n" + "2. Rubik Numbers NxM\n" + "3. Salir\n");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int puntosJugador1 = 0, puntosJugador2 = 0, columna = 0, fila = 0, desplazamientos, n, filas, columnas;
		String respuesta="";
		int[][] matriz;
		boolean salir = false;
		int opcion; // GUARDAMOS LA OPCION DEL USUARIO

		while (!salir) {

			printMenu();
			try {

				System.out.print("Elije una opcion: ");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					while (true) {// PARA QUE FUNCIONE EL JUEGO LA MATRIZ DEBE DE SER COMO MINIMO DE 3X3
						System.out.println(FILACOLUMNA);// PIDO EL NUMERO DE FILAS Y COLUMNAS
						n = sc.nextInt();
						if (n < N) {
							System.out.println("la matriz debe ser como minimo de: " + N + "x" + N);
						} else {
							break;
						}
					}

					matriz = crearMatriz(n, n);// CREO LA MATRIZ

					mostrarMatriz(matriz);// MUESTRO LA MATRIZ

					while (puntosJugador1 < 100 && puntosJugador2 < 100) { // MIENTRAS QUE NINGUNO DE LOS 2 LLEGUE A 100
																			// SE JUEGA

						while (true) {// COMPRUEBO QUE LA COLUMNA QUE INSERTA EL JUGADOR ESTA DENTRO DEL RANGO DE LA
										// MATRIZ
							System.out.println(JUGADOR1);
							columna = sc.nextInt() - 1;
							if (columna >= n || columna < 0) {
								System.out.println("la columna no existe");
							} else {
								break;
							}
						}
						System.out.println(CELDAS);// PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
						desplazamientos = sc.nextInt();
						desplazarColumna(matriz, n, columna, desplazamientos);// HAGO LOS CAMBIOS EN LA MATRIZ
						puntosJugador1 += puntosFilas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS EN LA
																// MATRIZ
						mostrarMatriz(matriz);

						System.out.println(PUNTOS1 + puntosJugador1); // MUESTRO LOS PUNTOS DE CADA JUGADOR
						System.out.println(PUNTOS2 + puntosJugador2);
						System.out.println();

						/* JUGADOR2 */

						while (true) {// COMPRUEBO QUE LA FILA QUE ME DA EL JUGADOR ESTA DENTRO DEL RANGO DE LA MATRIZ
							System.out.println(JUGADOR2);
							fila = sc.nextInt() - 1;
							if (fila >= n || fila < 0) {
								System.out.println("la fila no existe");
							} else {
								break;
							}
						}
						System.out.println(CELDAS); // PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
						desplazamientos = sc.nextInt();
						desplazarFila(matriz, n, fila, desplazamientos); // HAGO LOS CAMBIOS EN LA MATRIZ
						puntosJugador2 += puntosColumnas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS EN
																	// LA MATRIZ
						mostrarMatriz(matriz);
						System.out.println(PUNTOS1 + puntosJugador1);// MUESTRO LOS PUNTOS DE LOS JUGADORES
						System.out.println(PUNTOS2 + puntosJugador2);
						System.out.println();
					}
					if (puntosJugador1 > puntosJugador2) {
						System.out.println("el ganador es el jugador 1 con " + puntosJugador1 + " puntos");
					} else {
						System.out.println("el ganador es el jugador 2 con " + puntosJugador2 + " puntos");
					}
					puntosJugador1 = 0;// RESETEAMOS LOS VALORES DESPUES DE JUGAR
					puntosJugador2 = 0;
					columna = 0;
					fila = 0;
					break;
				case 2:

					while (true) {
						System.out.println(FILAS);
						filas = sc.nextInt();
						if (filas < 3)
							System.out.println("La matriz debe tener al menos 3 filas");
						else
							break;
					}
					while (true) {
						System.out.println(COLUMNAS);
						columnas = sc.nextInt();
						if (filas < 3)
							System.out.println("La matriz debe tener al menos 3 columnas");
						else
							break;
					}
					matriz = crearMatriz(filas, columnas);
					mostrarMatriz(matriz);// MUESTRO LA MATRIZ
					while (puntosJugador1 < 100 && puntosJugador2 < 100) { // MIENTRAS QUE NINGUNO DE LOS 2 LLEGUE A 100
																			// SE JUEGA
						while (true) {
							Scanner sl = new Scanner(System.in);
							System.out.println("Jugador1 elige mover fila o columna: (escribe f o c) ");
							respuesta = sl.nextLine();
							if (!respuesta.equals("f") && !respuesta.equals("c"))
								System.out.println("la respuesta es incorrecta, prueba de nuevo");
							else
								break;
						}

						if (respuesta.equals("c")) {

							while (true) {// COMPRUEBO QUE LA COLUMNA QUE INSERTA EL JUGADOR ESTA DENTRO DEL RANGO DE LA
								// MATRIZ
								System.out.println(JUGADOR1);
								columna = sc.nextInt() - 1;
								if (columna >= columnas || columna < 0) {
									System.out.println("la columna no existe");
								} else {
									break;
								}
							}
							System.out.println(CELDAS);// PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
							desplazamientos = sc.nextInt();
							desplazarColumna(matriz, filas, columna, desplazamientos);// HAGO LOS CAMBIOS EN LA
																							// MATRIZ
							puntosJugador1 += puntosFilas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS EN
																	// LA MATRIZ
							cambiarNumeros(matriz);// HACEMOS ALEATORIOS LOS NUMEROS QUE NO SON 0
							mostrarMatriz(matriz);

							System.out.println(PUNTOS1 + puntosJugador1); // MUESTRO LOS PUNTOS DE CADA JUGADOR
							System.out.println(PUNTOS2 + puntosJugador2);
							System.out.println();
						} else {
							while (true) {// COMPRUEBO QUE LA COLUMNA QUE INSERTA EL JUGADOR ESTA DENTRO DEL RANGO DE LA
								// MATRIZ
								System.out.println("Jugador1 inserta el numero de fila que vas a mover: ");
								fila = sc.nextInt() - 1;
								if (fila >= filas || columna < 0) {
									System.out.println("la fila no existe");
								} else {
									break;
								}
							}
							System.out.println(CELDAS); // PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
							desplazamientos = sc.nextInt();
							desplazarFila(matriz, columnas, fila, desplazamientos); // HAGO LOS CAMBIOS EN LA MATRIZ
							puntosJugador1 += puntosColumnas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS
																		// EN LA MATRIZ
							cambiarNumeros(matriz);// HACEMOS ALEATORIOS LOS NUMEROS QUE NO SON 0
							mostrarMatriz(matriz);
							System.out.println(PUNTOS1 + puntosJugador1);// MUESTRO LOS PUNTOS DE LOS JUGADORES
							System.out.println(PUNTOS2 + puntosJugador2);
							System.out.println();
						}

						/* JUGADOR2 */

						while (true) {
							Scanner sl = new Scanner(System.in);
							System.out.println("Jugador2 elige mover fila o columna: (escribe f o c) ");
							respuesta = sl.nextLine();
							if (!respuesta.equals("f") && !respuesta.equals("c"))
								System.out.println("la respuesta es incorrecta, prueba de nuevo");
							else
								break;
						}

						if (respuesta.equals("c")) {

							while (true) {// COMPRUEBO QUE LA COLUMNA QUE INSERTA EL JUGADOR ESTA DENTRO DEL RANGO DE LA
								// MATRIZ
								System.out.println("Jugador2 inserta el numero de columna que vas a mover: ");
								columna = sc.nextInt() - 1;
								if (columna >= columnas || columna < 0) {
									System.out.println("la columna no existe");
								} else {
									break;
								}
							}
							System.out.println(CELDAS);// PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
							desplazamientos = sc.nextInt();
							desplazarColumna(matriz, filas, columna, desplazamientos);// HAGO LOS CAMBIOS EN LA
																							// MATRIZ
							puntosJugador2 += puntosFilas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS EN
																	// LA MATRIZ
							cambiarNumeros(matriz);// HACEMOS ALEATORIOS LOS NUMEROS QUE NO SON 0
							mostrarMatriz(matriz);

							System.out.println(PUNTOS1 + puntosJugador1); // MUESTRO LOS PUNTOS DE CADA JUGADOR
							System.out.println(PUNTOS2 + puntosJugador2);
							System.out.println();
						} else {
							while (true) {// COMPRUEBO QUE LA COLUMNA QUE INSERTA EL JUGADOR ESTA DENTRO DEL RANGO DE LA
								// MATRIZ
								System.out.println(JUGADOR2);
								fila = sc.nextInt() - 1;
								if (fila >= filas || columna < 0) {
									System.out.println("la fila no existe");
								} else {
									break;
								}
							}
							System.out.println(CELDAS); // PIDO EL NUMERO DE CELDAS QUE VOY A DESPLAZAR
							desplazamientos = sc.nextInt();
							desplazarFila(matriz, columnas, fila, desplazamientos); // HAGO LOS CAMBIOS EN LA MATRIZ
							puntosJugador2 += puntosColumnas(matriz);// SUMO LOS PUNTOS OBTENIDOS DESPUES DE LOS CAMBIOS
																		// EN LA MATRIZ
							cambiarNumeros(matriz);// HACEMOS ALEATORIOS LOS NUMEROS QUE NO SON 0
							mostrarMatriz(matriz);
							System.out.println(PUNTOS1 + puntosJugador1);// MUESTRO LOS PUNTOS DE LOS JUGADORES
							System.out.println(PUNTOS2 + puntosJugador2);
							System.out.println();
						}
					}
					if (puntosJugador1 > puntosJugador2) {
						System.out.println("el ganador es el jugador 1 con " + puntosJugador1 + " puntos");
					} else {
						System.out.println("el ganador es el jugador 2 con " + puntosJugador2 + " puntos");
					}
					puntosJugador1 = 0;// RESETEAMOS LOS VALORES DESPUES DE JUGAR
					puntosJugador2 = 0;
					columna = 0;
					fila = 0;

					break;
				case 3:
					salir = true;
					System.out.println("Acabas de salir del juego");
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Error, elije una opcion del 1 al 3");
				sc.next();
			}
		}
	}
}
