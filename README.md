# tpeProg3
Trabajo practico especial Programación 3 - 2026

Servicios (Parte 1):
- Servicio 1: Utilizamos un HashMap para indexar los paquetes por su código. Esto permite que la búsqueda sea directa, logrando una complejidad de O(1).
- Servicio 2: Precalculamos dos listas separadas (conAlimentos y sinAlimentos) al leer los archivos. De esta forma, el método no necesita iterar y evaluar 
              los paquetes cada vez que es llamado, retornando la respuesta en O(1).
- Servicio 3: Armamos una lista extra ordenada por nivel de urgencia. En lugar de recorrer todos los paquetes uno por uno (lo que costaría O(N)),
              implementamos una búsqueda binaria. Esto permite saltar al primer paquete válido en O(log N) y desde ahí solo iterar los que entran en el rango, 
              cortando la ejecución apenas se supera la urgencia máxima.    

Algoritmos (Parte 2):
- Greedy: Implementamos una estrategia donde ordenamos por peso y buscamos el camión que quede más justo. Primero ordenamos los paquetes por peso (de mayor a menor).
          Luego, cada paquete se ubica en el camión disponible que quede con la menor capacidad restante. 
          Esta nos asegura que los paquetes más pesados entren primero, y minimiza el espacio desperdiciado en cada camión.     

- Backtracking: Con este algoritmo nos aseguramos de encontrar la mejor asignación posible, 
                usando la poda para no evaluar ramas inutiles y ahorrar tiempo. 
                Mantenemos un registro global de la mejor solución encontrada (mejorPesoNoAsignadoGlobal).
                Si durante la recursión el peso de los paquetes no asignados en esa rama ya iguala o 
                supera a nuestra mejor solucion, la rama se corta, evitando explorar estados inútiles.


CAMBIOS REALIZADOS PARA LA REENTREGA:

- Implementacion de un Main para la prueba del trabajo con todos los servicios y algoritmos llevados a cabo.

- Atributos privados + getters respectivos en las clases Paquete y Camion.

- Corrección del error de compilación en las clases Paquete y Camion eliminando el modificador "static".

- Corrección en la instanciación(se reemplazó new List<>() por new ArrayList<>() para solucionar el error de instanciación).

- Implementacion de los archivos .csv en la raiz del proyecto para que no tire errores.

- Implementacion y actualizacion de la variable mejorPesoNoAsignadoGlobal en la clase Backtracking (mejoria en la complejidad temporal) + correccion de errores de sintaxis.

- Implementacion de una poda de optimidad gracias a la variable mejorPesoNoAsignadoGlobal (Backtracking).

- Corrección en nombres de variables utilizadas por parametros en el servicio 3 (urgenciaMinima y urgenciaMaxima)

- Faltaba el return dentro del metodo busquedaBinariaInferior en la clase Servicios.

