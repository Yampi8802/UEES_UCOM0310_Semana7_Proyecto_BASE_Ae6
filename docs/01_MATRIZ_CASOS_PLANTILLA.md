# Matriz de casos

| ID | Regla | Escenario | Entrada | Esperado | Tipo | Riesgo |
|---|---|---|---|---|---|---|
| CP-01 | puedeCancelar permite cancelar con 2 o más horas | Anticipación normal | 5 horas | true | Normal | Bajo |
| CP-02 | puedeCancelar permite cancelar con 2 o más horas | Límite permitido | 2 horas | true | Límite | Alto |
| CP-03 | puedeCancelar no permite cancelar con menos de 2 horas | Límite no permitido | 1 hora | false | Límite | Alto |
| CP-04 | puedeCancelar no permite cancelar con menos de 2 horas | Anticipación mínima | 0 horas | false | Extremo | Medio |
| CP-05 | calcularTotal no aplica descuento a NORMAL | Cliente normal | NORMAL, 100 | 100.0 | Normal | Bajo |
| CP-06 | calcularTotal aplica 15% de descuento a VIP | Cliente VIP | VIP, 100 | 85.0 | Alternativo | Medio |
| CP-07 | calcularTotal aplica 10% de descuento a ESTUDIANTE | Cliente estudiante | ESTUDIANTE, 100 | 90.0 | Alternativo | Medio |
| CP-08 | calcularTotal mantiene cero aunque sea VIP | Total base cero | VIP, 0 | 0.0 | Límite | Medio |
| CP-09 | calcularTotal rechaza valores negativos | Total base inválido | NORMAL, -1 | IllegalArgumentException | Inválido | Alto |