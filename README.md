# UEES UCOM0310 — Semana 7 — Proyecto base Ae6

Proyecto base para las actividades individuales de Semana 7.

## Requisitos
- Java 21
- Maven 3.9+
- Git

## Verificación inicial
```bash
mvn clean test
```

## Cobertura
```bash
mvn clean test
```# UEES UCOM0310 — Semana 7 — Proyecto Ae6

Proyecto individual de la Actividad Evaluada 3 – Ae6, orientado al diseño de casos de prueba, implementación de pruebas unitarias, uso de Stub/Mock, análisis de cobertura con JaCoCo y trazabilidad mediante Git y Pull Request.

## Requisitos

* Java 21
* Maven 3.9+
* Git

## Ejecución de pruebas

Para ejecutar la suite completa desde cero:

```bash
mvn clean test
```

Resultado verificado:

* 13 pruebas ejecutadas
* 0 fallos
* 0 errores
* 0 pruebas omitidas
* BUILD SUCCESS

## Cobertura con JaCoCo

El reporte de cobertura se genera después de ejecutar:

```bash
mvn clean test
```

Luego abrir:

```text
target/site/jacoco/index.html
```

Resultados observados:

* Cobertura global de instrucciones: **90 %**
* Cobertura global de ramas: **83 %**
* Método `Reserva.cancelar()`: **100 %**

A partir del análisis de JaCoCo se identificó que `cancelar()` no tenía cobertura. Se agregó la prueba `cancelarCambiaEstadoACancelada()` para verificar el cambio de estado de la reserva.

## Suite de pruebas

La suite incluye **13 escenarios** que cubren:

* Casos normales.
* Casos límite.
* Entradas inválidas.
* Excepciones.
* Confirmación de reservas disponibles y no disponibles.
* Uso de Mockito para controlar la disponibilidad y verificar interacciones con el repositorio y el notificador.
* Cancelación de una reserva.

## Estructura de documentación

* `docs/01_MATRIZ_CASOS_PLANTILLA.md` — Matriz de casos de prueba.
* `docs/02_ANALISIS_COBERTURA_PLANTILLA.md` — Análisis de cobertura con JaCoCo.
* `docs/03_PULL_REQUEST_PLANTILLA.md` — Información y evidencia del Pull Request.

## Git

La actividad se desarrolló en la rama:

```text
ae6/suite-pruebas
```

El historial contiene commits incrementales y descriptivos relacionados con:

* Matriz de casos.
* Suite de pruebas con Stub/Mock.
* Cobertura de `cancelar()`.
* Documentación de cobertura y Pull Request.

## Pull Request

Pull Request de la actividad:

```text
#1 — test: completar suite, cobertura y evidencia de Ae6
```

Rama base:

```text
main
```

Rama de trabajo:

```text
ae6/suite-pruebas
```

## Regla de trabajo

No se modifica el código productivo únicamente para hacer pasar una prueba sin justificar el cambio.

Primero se diseña el caso, luego se implementa la prueba, se ejecuta la suite y finalmente se interpreta la cobertura obtenida.

## Limitaciones conocidas

La cobertura no es completa en todos los elementos de la clase `Reserva`. Permanecen sin cobertura los métodos `getId()` y `getTipo()` y una rama del constructor relacionada con la validación del identificador.

Estos elementos no se cubrieron únicamente para aumentar el porcentaje, sino que se priorizó la protección de comportamientos relevantes para la actividad.


Luego abrir:
`target/site/jacoco/index.html`

## Regla de trabajo
No modifiques el código productivo solo para hacer pasar una prueba sin justificar el cambio.
Primero diseña el caso, luego implementa la prueba y finalmente interpreta el resultado.
