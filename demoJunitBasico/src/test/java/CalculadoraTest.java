import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    @DisplayName("Metodo que suma dos cifras")
    void suma() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.suma(32, 94);
        assertEquals(resultado, 126); //Valor que debería dar, Variable a comparar
    }

    @Test
    @Disabled
    void resta() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.resta(28, 13);
        assertEquals(resultado, 15); //Valor que debería dar, Variable a comparar
    }

    @Test
    @Tag("avanzada")
    void division() {
        Calculadora calculadora = new Calculadora();
        double resultado = calculadora.division(20, 5);
        assertEquals(resultado, 4); //Valor que debería dar, Variable a comparar
    }

    @Test
    @Tag("avanzada")
    void multiplicacion() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.multiplicacion(100, 6);
        assertEquals(resultado, 600); //Valor que debería dar, Variable a comparar

    }
}