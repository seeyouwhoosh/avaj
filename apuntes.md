# Java
## Funcionamiento de Java
Proceso: `file.java -> file.class en bytecode -> jvm traduce a machine code`

### Arquitectura de Java
#### JVM
Java Virtual Machine
* Carga el código, verifica que está bien y es seguro y tal, y lo ejecuta
* Platform dependent

#### JRE
Java Runtime Environment
* Para correr la aplicación, incluye JVM y librerías estándar
* A partir de Java 11, no hay que instalarlo de forma independiente

#### JDK
Java Development Kit
* Todo junto con algunas otras cosas like `javac`, `javadoc`, `jdb`

### Just-In-Time Compiler
Antes era "interpretado", que al compilarlo a Bytecode, el JVM lo interpreta.

Ahora es como híbrido raro (?): Empieza interpretando y guarda los cachos de código que más se ejecutan para compilarlo a lenguaje de máquina y lo guarda en memoria; y así lo usa después. YAy :D

## Conceptos de código
* clase HelloWorld pide archivo HelloWorld.java
> helloworld.java:1: error: class HelloWorld is public, should be declared in a file named HelloWorld.java

* For-each
```java
String[] nombres = {"Ana", "Luis", "Pedro"};

for (String nombre : nombres) {
    System.out.println(nombre);
}
```

* Está el tipo `byte`
* **Inferencia de tipado** (Java 10+) poniendo `var`y la inicialización. *Nota*: no permite nulos directos, y no permite en atributos de clase ni como parametros
* **Los operadores son como en C**: la división da naturalmente enteros, el módulo es el remainder, hay terminarios, short form evaluation
* Java 14+ añade asignaciones switch:
    ```java
    String nombreDia = switch (diaSemana) {
        case 1 -> "Lunes";
        case 2 -> "Martes";
        case 3 -> "Miércoles";
        case 4, 5 -> "Jueves o Viernes"; // agrupar casos
        case 6, 7 -> {
            System.out.println("Día no laboral detectado");
            yield "Sábado o Domingo";
        } // minibloques con más comandos, para asignar es yield
        default -> "Día inválido";
    };
    ```

## Input/Output:
#### Output
clase System.out

```java
System.out.print();
System.out.println();
System.out.printf(); // Como en C
```

#### Input
clase Scanner de `java.util.Scanner`, leer de System.in

```java
import java.util.Scanner;
// ...
Scanner scanner = new Scanner(System.in);
scanner.nextLine();
scanner.close();
```

EL scanner puede leer datos específicos:

```java
int edad = scanner.nextInt();
double peso = scanner.nextDouble();
String nombre = scanner.next(); // word
```

> **Nota**: `scanner.nextInt()` no se come el newline del final, por lo que si hacemos luego un `scanner.nextLine()`, solo hay una línea vacía.

Para lo de no mostrar contraseñas mientras se escribe: clase Console de `System.console()`

```java
Console consola = System.console();

if (consola != null) {
    String usuario = consola.readLine("Usuario: ");
    char[] pass = consola.readPassword("Contraseña: ");
}
```

> `console` solo es útil en terminal

## clase Arrays
```java
import java.util.Arrays;

int numeros[] = {1, 2, 3, 4};

System.out.println(Arrays.toString(numeros)); // Imprimir

Arrays.sort(numeros); 

int posicion = Arrays.binarySearch(numeros, 3); // si no lo encuentra, devuelve - (punto de inserción + 1)

int[] a = {1, 2, 3};
int[] b = {1, 2, 3};

System.out.println(a == b); // false
System.out.println(Arrays.equals(a, b)); // true
Arrays.compare(a, b) // 0

int[4] c;
Arrays.fill(c, 0);

int[] origen = {1, 2, 3};
int[] masGrande = Arrays.copyOf(origen, 5); // segundo parámetro es el nuevo tamaño. masGrande es [1, 2, 3, 0, 0]
```

#### arrays
Parecido a C, excepto que tiene actually una estructura (?).
Por ejemplo, tiene el atributo *length*

## Estructuras varias
### Enums
```java
public enum Talla {
    PEQUENA, MEDIANA, GRANDE, EXTRAGRANDE
}
Talla miTalla = Talla.MEDIANA;

switch (miTalla) {
    case PEQUENA -> System.out.println("Talla S");
    case MEDIANA -> System.out.println("Talla M");
    case GRANDE, EXTRAGRANDE -> System.out.println("Talla L/XL");
} // lo he puesto porque al parecer no hay que poner Talla.COSO en caso de switches
```
#### Clase enum

> **Nota**: enum es actually una clase especial heredada de `java.lang.Enum`. Así que podemos definir atributos, constructores y métodos:
> ```java
>  public enum Talla {
>    // 1. Definimos las constantes, pasando datos al constructor
>    PEQUENA("S", 48),
>    MEDIANA("M", 50),
>    GRANDE("L", 52),
>    EXTRAGRANDE("XL", 54); // El punto y coma separa las constantes del resto del código
>
>    // 2. Atributos (finales, porque los enums son constantes)
>    private final String abreviatura;
>    private final int anchoCm;
>
>    // 3. Constructor (Privado obligatoriamente)
>    // Solo se llama internamente al crear las constantes de arriba
>    private Talla(String abreviatura, int ancho) {
>        this.abreviatura = abreviatura;
>        this.anchoCm = ancho;
>    }
>
>    // 4. Getters (para acceder a los datos)
>    public String getAbreviatura() { return abreviatura; }
>    public int getAnchoCm() { return anchoCm; }
>  }
> ```

Otro ejemplo interesante es:
```java
public enum Operacion {
    SUMA, RESTA, MULTIPLICACION, DIVISION;

    public double calcular(double a, double b) {
        return switch (this) { // 'this' es la constante actual (SUMA, RESTA...)
            case SUMA -> a + b;
            case RESTA -> a - b;
            case MULTIPLICACION -> a * b;
            case DIVISION -> a / b;
        };
    }
}

double res = Operacion.SUMA.calcular(10, 20);
```

Además, tienen unos métodos  estáticos:
```java
for (Talla t : Talla.values()) {
    System.out.println(t); // Imprime PEQUENA, MEDIANA...
}

String texto = "GRANDE";
Talla t = Talla.valueOf(texto); // Convierte a Talla.GRANDE
```

### Clases
La idea es igual que en todas partes, como C++.

Lo único aquí es que para instanciarlo siempre se usa `new` y no hay copy operator direcmente. Y también hay garbage collector, basta con asignarlo a `null` el objeto