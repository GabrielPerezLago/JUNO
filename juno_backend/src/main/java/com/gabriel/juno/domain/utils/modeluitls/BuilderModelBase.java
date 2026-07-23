package com.gabriel.juno.domain.utils.modeluitls;

/**
 *
 * @param <T>
 *
 *     Interfaz planteada para crear un builder manual para los modelos del dominio sin depender directamente de lombok, solucionando la dependencia de una libreria externa.
 *     Implementa el metodo buld con la clase padre insertada como clase aniunada.
 *
 * @author Gabriel
 */
public interface BuilderModelBase<T> {
    public T build();
}
