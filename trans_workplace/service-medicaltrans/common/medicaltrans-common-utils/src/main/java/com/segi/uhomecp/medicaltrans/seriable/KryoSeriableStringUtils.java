package com.segi.uhomecp.medicaltrans.seriable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;

/**
 * Kryo序列化，反序列化帮助类
 * @author Jimmy
 * 2018-3-21
 */
public class KryoSeriableStringUtils {
	/**
	 * 序列化JavaBean
	 * @param obj
	 * @return
	 */
	public static <T extends Serializable> String seriaObject(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }
	
	/**
	 * 反序列化JavaBean
	 * @param obj
	 * @param clazz
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deSerialObject(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
 
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }
 
    /**
     * 序列化 List
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serialList(List<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    /**
     * 反序列化List
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> deSerialList(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }
 
    /**
     * 序列化Map
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serialMap(Map<String, T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
    
    /**
     * 反序列化Map
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Map<String, T> deSerialMap(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (Map<String, T>) kryo.readObject(input, HashMap.class, serializer);
    }
    
    /**
     * 序列化Set
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serialSet(Set<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    /**
     * 反序列化Set
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Set<T> deSerialSet(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(obj));
        Input input = new Input(bais);
        return (Set<T>) kryo.readObject(input, HashSet.class, serializer);
    }
}