package unidad8.tp1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) throws Exception {

        // Obtener la clase Persona mediante Reflection
        Class<?> clasePersona = Persona.class;

        // 1. Obtener e imprimir los constructores
        System.out.println("=== CONSTRUCTORES ===");

        Constructor<?>[] constructores = clasePersona.getDeclaredConstructors();

        for (Constructor<?> constructor : constructores) {
            System.out.println(constructor);
        }

        // 2. Instanciar un objeto utilizando Reflection
        Constructor<?> constructor = clasePersona.getConstructor(
                String.class,
                int.class
        );

        Persona persona = (Persona) constructor.newInstance(
                "Juan",
                25
        );

        System.out.println("\n=== OBJETO CREADO ===");
        persona.mostrarInformacion();

        // 3. Modificar el valor de nombre en tiempo de ejecución
        Field campoNombre = clasePersona.getDeclaredField("nombre");

        campoNombre.setAccessible(true);
        campoNombre.set(persona, "Carlos");

        System.out.println("\n=== NOMBRE MODIFICADO ===");
        persona.mostrarInformacion();

        // 4. Invocar un método que muestre la información
        Method metodoInformacion = clasePersona.getDeclaredMethod(
                "mostrarInformacion"
        );

        metodoInformacion.invoke(persona);

        // 5. Modificar el encapsulamiento de Saludar()
        //    hacia private usando Reflection
        Method metodoSaludar = clasePersona.getDeclaredMethod("Saludar");

        metodoSaludar.setAccessible(true);

        System.out.println("\n=== MÉTODO SALUDAR ===");
        System.out.println("Modificador original: "
                + Modifier.toString(metodoSaludar.getModifiers()));

        System.out.println("Acceso mediante Reflection: "
                + metodoSaludar.invoke(persona));
    }
}