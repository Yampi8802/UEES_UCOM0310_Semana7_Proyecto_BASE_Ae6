package edu.uees.testing.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaServiceTest {

    private final ReservaService servicio = new ReservaService(null, null, null);

    @Test
    void cincoHorasPermitenCancelar() {
        // Arrange
        int horas = 5;

        // Act
        boolean resultado = servicio.puedeCancelar(horas);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void dosHorasEsElLimitePermitido() {
        // Arrange
        int horas = 2;

        // Act
        boolean resultado = servicio.puedeCancelar(horas);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void unaHoraNoPermiteCancelar() {
        // Arrange
        int horas = 1;

        // Act
        boolean resultado = servicio.puedeCancelar(horas);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void ceroHorasNoPermiteCancelar() {
        // Arrange
        int horas = 0;

        // Act
        boolean resultado = servicio.puedeCancelar(horas);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void normalNoRecibeDescuento() {
        // Arrange
        double totalBase = 100;

        // Act
        double resultado = servicio.calcularTotal("NORMAL", totalBase);

        // Assert
        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    void vipRecibeQuincePorCientoDeDescuento() {
        // Arrange
        double totalBase = 100;

        // Act
        double resultado = servicio.calcularTotal("VIP", totalBase);

        // Assert
        assertEquals(85.0, resultado, 0.001);
    }

    @Test
    void estudianteRecibeDiezPorCientoDeDescuento() {
        // Arrange
        double totalBase = 100;

        // Act
        double resultado = servicio.calcularTotal("ESTUDIANTE", totalBase);

        // Assert
        assertEquals(90.0, resultado, 0.001);
    }

    @Test
    void vipConCeroMantieneCero() {
        // Arrange
        double totalBase = 0;

        // Act
        double resultado = servicio.calcularTotal("VIP", totalBase);

        // Assert
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    void totalNegativoEsInvalido() {
        // Arrange
        double totalBase = -1;

        // Act
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicio.calcularTotal("NORMAL", totalBase)
        );

        // Assert
        assertEquals("Total base inválido", ex.getMessage());
    }
}