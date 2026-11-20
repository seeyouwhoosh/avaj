# avaJ
## Conceptos de la subject
* **Unified Modeling Language**: Básicamente, una forma de visualizar el diseño de un sistema. El que nos dan en la subject:
<img width="1926" height="637" alt="image" src="https://github.com/user-attachments/assets/f3b4a191-79b9-436a-ad1e-bf890a383369" />
* Java package naming conventions:
  * Reverse domain naming convention 
  * Packages in the Java language itself begin with java. or javax.

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
* Los operadores son como en C: la división da naturalmente enteros, el módulo es el remainder, hay terminarios, short form evaluation