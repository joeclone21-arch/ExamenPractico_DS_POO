# Buscaminas POO - Examen Práctico

Este proyecto consiste en el desarrollo del juego clásico **Buscaminas ** en consola implementado en **Java**. El diseño de la aplicación aplica estrictamente los conceptos del paradigma de la **Programación Orientada a Objetos (POO)**, sigue el patrón arquitectónico **MVC (Modelo-Vista-Controlador)**, e integra principios de código limpio (**KISS**), manejo de excepciones personalizadas, persistencia de datos mediante serialización binaria y pruebas unitarias (**JUnit**).

---

##  Tecnologías y Herramientas Utilizadas

* **Lenguaje:** Java (JDK 26 o superior)
* **IDE Recomendado:** Eclipse IDE / IntelliJ IDEA
* **Gestión de Versiones:** Git y GitHub
* **Pruebas Unitarias:** JUnit 5 (TDD approach)
* **Modelado:** PlantUML (Arquitectura como código)

---

##  Arquitectura del Proyecto (MVC)

El código fuente está modularizado en paquetes independientes para garantizar un bajo acoplamiento y una alta cohesión:

* `principal`: Contiene la clase `Main.java`, punto de entrada que inicializa las dependencias e inicia el flujo.
* `modelo`: Gestiona la lógica del negocio (`Tablero.java` y `Casilla.java`).
* `vista`: Controla la interfaz de usuario por consola (`VistaConsola.java`).
* `controlador`: Coordina la interacción entre el modelo y la vista (`JuegoControlador.java`).
* `servicio`: Administra la persistencia binaria en disco (`ArchivoServicio.java`).
* `excepcion`: Define errores controlados del dominio (`CasillaYaDescubiertaException.java`).

---

##  Instalación y Configuración

Siga estos pasos para clonar y ejecutar el proyecto localmente en su entorno:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/joeclone21-arch/ExamenPractico_DS_POO.git](https://github.com/joeclone21-arch/ExamenPractico_DS_POO.git)
   
2. **Importar en Eclipse IDE:**

Abra Eclipse.

Seleccione File -> Import... -> Existing Projects into Workspace.

Elija el directorio de la carpeta clonada y presione Finish.

Asegurar dependencias de pruebas:

Si las pruebas unitarias muestran error, haga clic derecho sobre el proyecto -> Build Path -> Add Libraries... -> Seleccione JUnit (versión 5) y aplique los cambios

## Instrucciones de Uso y Comandos
Al iniciar el juego, se desplegará una matriz de 10x10 indexada con filas de la A a la J y columnas del 1 al 10. En cada turno, el controlador solicitará una acción mediante consola:

Revelar una casilla: Ingrese directamente la coordenada en mayúsculas o minúsculas.

Ejemplo: A5 (Revela la casilla en la fila A, columna 5).

Colocar/Quitar una bandera: Use el prefijo F antes de la coordenada para marcar un peligro potencial.

Ejemplo: FA5 (Coloca o remueve una bandera de aviso en A5).

Guardar Partida (Persistencia): Escriba el comando SAVE en cualquier momento para almacenar el estado del tablero actual.

Cargar Partida (Restauración): Escriba el comando LOAD para recuperar la última partida guardada y continuar jugando desde ese punto.

## Ejemplos de Ejecución
Ciclo de Juego Normal (Revelar y Bandera)

Juego Buscaminas Examen Practico POO

Comandos: 'A5' (revelar casilla), 'FA5' (bandera), 'SAVE' (guardar), 'LOAD' (cargar)

    1  2  3  4  5  6  7  8  9  10
A [ .  .  .  .  .  .  .  .  .  . ]

B [ .  .  .  .  .  .  .  .  .  . ]
...

Introduzca el movimiento: A5

Flujo de Persistencia (SAVE / LOAD)

Introduzca el movimiento: A5

Aviso: La casilla ya ha sido descubierta. Intente con otra posicion.

Introduzca el movimiento: Z20

Error de entrada: Formato invalido. Use una letra entre A-J y un numero entre 1-10 por favor.

## Ejecución de Pruebas Unitarias
El proyecto cuenta con una suite de pruebas automatizadas en src/test/java (o dentro de la estructura del modelo) bajo la clase TableroTest.java.

Para ejecutarlas en Eclipse:

Haga clic derecho sobre TableroTest.java.

Seleccione Run As -> JUnit Test.

Verifique que la barra de progreso se muestre completamente en verde, validando la lógica del tamaño del mapa, el conteo perimetral de minas y el disparo correcto de excepciones.
