package com.android_project.multimedia.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class ColorfulCube {
    private static final int FACES_NUMBER = 6;

    private FloatBuffer floatBuffer;

    private float[][] colors = {
            {1.0f, 0.5f, 0.0f, 1.0f},
            {0.0f, 0.0f, 1.0f, 1.0f},
            {0.0f, 1.0f, 0.0f, 1.0f},
            {1.0f, 0.0f, 0.0f, 1.0f},
            {1.0f, 0.0f, 1.0f, 1.0f},
            {1.0f, 1.0f, 0.0f, 1.0f}
    };

    private float[] vertices = {
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f,  1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f
    };

    public ColorfulCube() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(vertices);
        floatBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);

        for (int i = 0; i < FACES_NUMBER; i++) {
            gl.glColor4f(colors[i][0], colors[i][1], colors[i][2], colors[i][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*4, 4);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
