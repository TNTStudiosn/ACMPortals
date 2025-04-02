#version 150

// Atributos que nos pasa Minecraft (por VertexFormats.POSITION_TEXTURE)
in vec3 Position;
in vec2 UV0;

// Uniforms que MC inyecta automáticamente
uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

// Pasamos las coordenadas a fragment
out vec2 texCoord;

void main() {
    // Simplemente asignamos uv
    texCoord = UV0;

    // Transformación final en clip-space
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
}
