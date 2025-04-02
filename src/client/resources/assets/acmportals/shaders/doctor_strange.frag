#version 150

in vec2 texCoord;
out vec4 fragColor;

uniform float time; // Tiempo en ticks para animación

void main() {
    // Convertir coordenadas a rango [-1,1]
    vec2 uv = texCoord * 2.0 - 1.0;
    float radius = length(uv);

    // Vórtice de círculos dorados giratorios
    float angle = atan(uv.y, uv.x);
    float ring = sin(20.0 * radius - time * 5.0 + angle * 3.0);

    float fade = smoothstep(0.7, 0.2, radius);
    float alpha = smoothstep(0.5, 0.45, abs(ring)) * fade;

    vec3 color = mix(vec3(1.0, 0.6, 0.1), vec3(1.0, 0.8, 0.3), ring * 0.5 + 0.5);

    fragColor = vec4(color, alpha);
}
