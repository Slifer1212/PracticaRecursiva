package validator;

public interface InputValidator<T> {

    /**
     * Valida el valor proporcionado.
     *
     * @param value valor a validar
     * @throws IllegalArgumentException si el valor no pasa la validación
     */
    void validate(T value);
}
