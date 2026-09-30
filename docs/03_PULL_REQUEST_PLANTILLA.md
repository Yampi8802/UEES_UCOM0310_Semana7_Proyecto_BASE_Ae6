# Pull Request

## Objetivo

Completar las pruebas unitarias de la Semana 7, agregando casos normales, límites, entradas inválidas y pruebas usando dobles de prueba. También revisar la cobertura con JaCoCo y agregar una prueba para cubrir un comportamiento que faltaba.

## Cambios realizados

* Se completó la matriz de casos con **13 escenarios de prueba**.
* Se agregaron pruebas en `ReservaServiceTest` usando **Mockito** para la disponibilidad, el repositorio y el notificador.
* Se agregó una prueba para el método `cancelar()` de `Reserva`, ya que se identificó que no tenía cobertura en JaCoCo.

## Casos de prueba

* Casos límite de `puedeCancelar()`, incluyendo el límite de **2 horas**.
* Casos normales, alternativos e inválidos de `calcularTotal()`.
* Casos de `confirmar()` con reserva nula, horario no disponible y horario disponible.
* Caso de cancelación donde se verifica que el estado cambie a `CANCELADA`.

## Cómo verificar

```bash
mvn clean test
```

Resultado obtenido:

* **13 pruebas ejecutadas**
* **0 fallos**
* **0 errores**
* **0 pruebas omitidas**
* **BUILD SUCCESS**

## Cobertura

La cobertura global de instrucciones aumentó de **87 % a 90 %**.

La cobertura global de ramas se mantuvo en **83 %**.

En la clase `Reserva`, el método `cancelar()` pasó de **0 % a 100 %** de cobertura después de agregar la prueba.

## Limitaciones

Todavía quedan algunos elementos sin cobertura completa, principalmente los métodos `getId()` y `getTipo()` de `Reserva` y una rama del constructor relacionada con la validación del identificador. No se agregaron pruebas solo para aumentar el porcentaje, sino que se priorizaron los comportamientos más importantes.

## Autorrevisión

* [x] Compila
* [x] Pruebas en verde
* [x] Sin archivos accidentales
* [x] Commits descriptivos
* [x] Documentación actualizada

## Uso de IA

Se utilizó ChatGPT como apoyo para revisar la cobertura de JaCoCo, identificar un comportamiento que no tenía cobertura y revisar la estructura de las pruebas y la documentación. La implementación y ejecución del proyecto se realizaron sobre el repositorio de la actividad.
