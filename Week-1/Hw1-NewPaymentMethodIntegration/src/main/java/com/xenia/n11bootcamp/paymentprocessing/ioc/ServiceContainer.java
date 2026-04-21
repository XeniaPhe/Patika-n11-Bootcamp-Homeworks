package main.java.com.xenia.n11bootcamp.paymentprocessing.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class ServiceContainer {
    private final Map<Type, Type> bindings;

    public ServiceContainer(Map<Type, Type> bindings) {
        this.bindings = bindings;
    }

	public <T> T get(Class<T> type) {
		return get((Type) type);
	}

	public <T> T get(TypeLiteral<T> type) {
		return get(type.getType());
	}

    private <T> T get(Type type) {
		try {
			Class<?> impl = InjectionUtils.resolveConcreteLeaf(bindings, type);
			Constructor<?> injectCtor = InjectionUtils.getInjectConstructor(impl);
			Type[] paramsTypes = injectCtor.getGenericParameterTypes();
			Object[] params = Arrays.stream(paramsTypes).map(this::get).toArray();
			Object instance = injectCtor.newInstance(params);
			@SuppressWarnings("unchecked")
			T result = (T)instance;
			return result;
		} catch (Exception cause) {
			throw new RuntimeException(
				"Dependency injection failed for type: %s".formatted(type.getTypeName()),
				cause
			);
		}
	}
}