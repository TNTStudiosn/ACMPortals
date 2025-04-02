#version 150

// Recibimos las coordenadas de textura (uv) y exportamos el color final
in vec2 texCoord;
out vec4 fragColor;

// Uniform global para animación
uniform float time;

void main() {
    // Transformamos [0..1] a [-1..1]
    vec2 uv = texCoord * 2.0 - 1.0;

    // Calculamos radio y ángulo polar
    float radius = length(uv);
    float angle = atan(uv.y, uv.x);

    //------------------------------------------------
    // 1) Generamos varios anillos y patrones en fase
    //    para dar la sensación de runas giratorias.

    // swirl1 -> patrón principal
    float swirl1 = sin(20.0 * radius - time * 5.0 + angle * 3.0);
    // swirl2 -> segundo patrón, distinto "frecuencial"
    float swirl2 = sin(10.0 * radius + time * 3.5 - angle * 2.0);

    // Combinamos ambos patrones en un "ring" de 0..1
    // Cuanto mayor sea swirl2, más se intensifica swirl1
    float ring = swirl1 * 0.6 + swirl2 * 0.4;

    //------------------------------------------------
    // 2) Controlamos "fade" externo e interno:
    //    a) fade radial para que se desvanezca en el borde
    //    b) un threshold para descartar píxeles con alpha muy bajo

    // fade radial (0.7..0.2) define las distancias donde "apaga" el portal
    float fade = smoothstep(0.7, 0.2, radius);

    // alpha se basa tanto en swirl como en fade
    // subimos la amplitud con 0.5 para forzar un rango mayor
    float alpha = smoothstep(0.5, 0.2, abs(ring)) * fade;

    //------------------------------------------------
    // 3) Calculamos color. Mezclamos dos gamas (naranja -> dorado):
    //    con una pequeña variación en el swirl para que parpadee

    // color base1 => anaranjado
    vec3 base1 = vec3(1.0, 0.6, 0.1);
    // color base2 => dorado
    vec3 base2 = vec3(1.0, 0.8, 0.3);
    // interpolación
    float t = ring * 0.5 + 0.5; // mapea ring -1..1 a 0..1
    vec3 color = mix(base1, base2, t);

    //------------------------------------------------
    // 4) Opcional: descartar píxeles con alpha muy pequeño,
    //    para evitar contornos negros o translúcidos raros.
    //    (Si no quieres descartar, puedes eliminar este if.)

    if (alpha < 0.05) {
        discard;
    }

    fragColor = vec4(color, alpha);
}
