package main.java.com.xenia.n11bootcamp.paymentprocessing.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

final class InjectionUtils {
    static Class<?> resolveConcreteLeaf(Map<Type, Type> bindings, Type type) {
        Type leafType = type;
		while(bindings.containsKey(leafType)) {
			leafType = bindings.get(leafType);
		}

		Class<?> leafClass = toClass(leafType);

		if (Modifier.isAbstract(leafClass.getModifiers()) || leafClass.isInterface()) {
			throw new RuntimeException(
				"No concrete implementation registered for type: %s".formatted(type.getTypeName())
			);
		}

		return leafClass;
    }

    static Constructor<?> getInjectConstructor(Class<?> type) {
        Constructor<?>[] ctors = type.getConstructors();
		List<Constructor<?>> injectCtors = 
			Arrays.stream(ctors)
			.filter(ctor -> ctor.isAnnotationPresent(Inject.class))
			.toList();
				
		if (injectCtors.size() == 0) {
			throw new RuntimeException(
				"An explicit @%s constructor is required for dependency injection"
				.formatted(Inject.class.getSimpleName())
			);
		} else if (injectCtors.size() > 1) {
			throw new RuntimeException(
				"Ambiguity in dependency injection, there are multiple @%s constructors"
				.formatted(Inject.class.getSimpleName())
			);
		}

		return injectCtors.get(0);
    }

	static Class<?> toClass(Type type) {
	    if (type instanceof ParameterizedType pt) {
			type = pt.getRawType();
	    }

		if (type instanceof Class<?> cls) {
	        return cls;
	    }

	    throw new AssertionError(
	        "Resolved type is not instantiable: " + type
	    );
	}
}
