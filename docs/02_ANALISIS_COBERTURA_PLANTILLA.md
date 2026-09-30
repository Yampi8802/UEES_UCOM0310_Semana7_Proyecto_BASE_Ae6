# Análisis de cobertura

## Resultado observado

* Cobertura global de instrucciones: **90 %**
* Cobertura global de ramas: **83 %**
* Clase analizada: **Reserva**
* Método analizado: **cancelar()**
* Cobertura de `cancelar()`: **100 %**
* La cobertura global aumentó de **87 % a 90 %** después de agregar la nueva prueba.

## Huecos relevantes

1. Los métodos `getId()` y `getTipo()` de la clase `Reserva` todavía no tienen cobertura.
2. El constructor de `Reserva` mantiene una rama sin cubrir relacionada con la validación del identificador.

## Decisiones

* Se agregó la prueba `cancelarCambiaEstadoACancelada()`, donde se crea una reserva pendiente, se ejecuta `cancelar()` y se verifica que el estado cambie a `CANCELADA`.
* La prueba sirve para comprobar que la cancelación de una reserva funcione correctamente y detectar si en el futuro se modifica esta transición de estado.
* El porcentaje de cobertura por sí solo no es suficiente, porque una prueba puede ejecutar una línea sin comprobar que el resultado sea correcto. Por eso, además de revisar JaCoCo, se comprobó el resultado esperado mediante una aserción sobre el estado de la reserva.
