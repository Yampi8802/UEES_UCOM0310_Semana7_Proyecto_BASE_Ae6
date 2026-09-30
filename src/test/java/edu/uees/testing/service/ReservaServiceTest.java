package edu.uees.testing.service;

import edu.uees.testing.availability.DisponibilidadClient;
import edu.uees.testing.domain.Reserva;
import edu.uees.testing.notification.Notificador;
import edu.uees.testing.repository.ReservaRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @Test
    void confirmarRechazaReservaNula() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);
        ReservaService servicio = new ReservaService(
                disponibilidad,
                repository,
                notificador
        );

        // Act
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicio.confirmar(null)
        );

        // Assert
        assertEquals("Reserva obligatoria", ex.getMessage());
        verify(disponibilidad, never()).estaDisponible(any(Reserva.class));
        verify(repository, never()).guardar(any(Reserva.class));
        verify(notificador, never()).enviarConfirmacion(any(Reserva.class));
    }

    @Test
    void confirmarRechazaReservaNoDisponible() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);
        ReservaService servicio = new ReservaService(
                disponibilidad,
                repository,
                notificador
        );
        Reserva reserva = new Reserva("R-001", "NORMAL");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(false);

        // Act
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> servicio.confirmar(reserva)
        );

        // Assert
        assertEquals("Horario no disponible", ex.getMessage());
        verify(disponibilidad).estaDisponible(reserva);
        verify(repository, never()).guardar(any(Reserva.class));
        verify(notificador, never()).enviarConfirmacion(any(Reserva.class));
    }

    @Test
    void confirmarGuardaYNotificaReservaDisponible() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);
        ReservaService servicio = new ReservaService(
                disponibilidad,
                repository,
                notificador
        );
        Reserva reserva = new Reserva("R-002", "VIP");
        when(disponibilidad.estaDisponible(reserva)).thenReturn(true);

        // Act
        servicio.confirmar(reserva);

        // Assert
        assertEquals("CONFIRMADA", reserva.getEstado().name());
        verify(disponibilidad).estaDisponible(reserva);
        verify(repository).guardar(reserva);
        verify(notificador).enviarConfirmacion(reserva);
    }
        @Test
    void cancelarCambiaEstadoACancelada() {
        // Arrange
        Reserva reserva = new Reserva("R-003", "NORMAL");

        // Act
        reserva.cancelar();

        // Assert
        assertEquals("CANCELADA", reserva.getEstado().name());
    }
}