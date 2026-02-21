package org.neev.utils;

import java.io.*;

/**
 * Utility class for serializing and deserializing objects to and from byte arrays. This is useful for sending
 * complex objects through Kafka or storing them in a binary format.
 */
public class SerializationUtil {

    private SerializationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Serializes an object to a byte array.
     *
     * @param obj the object to serialize
     * @return a byte array representing the serialized object
     * @throws IOException if an I/O error occurs during serialization
     */
    public static byte[] serialize(Object obj) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        objectStream.writeObject(obj);
        objectStream.flush();

        return byteStream.toByteArray();
    }

    /**
     * Deserializes an object from a byte array.
     *
     * @param data the byte array containing the serialized object
     * @return the deserialized object
     * @throws IOException            if an I/O error occurs during deserialization
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

        return objectStream.readObject();
    }

}

