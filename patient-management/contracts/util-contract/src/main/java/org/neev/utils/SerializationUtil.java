package org.neev.utils;

import java.io.*;

public class SerializationUtil {

    public static byte[] serialize(Object obj) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        objectStream.writeObject(obj);
        objectStream.flush();

        return byteStream.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

        return objectStream.readObject();
    }

}

