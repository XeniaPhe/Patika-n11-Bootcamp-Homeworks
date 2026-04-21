package main.java.com.xenia.n11bootcamp.paymentprocessing.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceRegistry {
	private final Map<Type, Type> bindings = new HashMap<>();

	public <T> void addTransient(Class<T> parentType, Class<? extends T> childType) {
		bindings.put(parentType, childType);
	}

	public <T> void addTransient(TypeLiteral<T> parentType, Class<? extends T> childType) {
		bindings.put(parentType.getType(), childType);
	}

	public <T> void addTransient(TypeLiteral<T> parentType, TypeLiteral<? extends T> childType) {
		bindings.put(parentType.getType(), childType.getType());
	}

	public ServiceContainer build() {
		for (Type type : bindings.keySet()) {
			dfs(type, new ArrayList<>());
		}

		return new ServiceContainer(bindings);
	}
	
	private void dfs(Type type, List<Type> path) {
		var firstIdx = path.indexOf(type);

		if (firstIdx != -1) {
			throw new RuntimeException(
				"Circular dependency detected:\n%s -> %s"
				.formatted(
					path.subList(firstIdx, path.size())
					.stream()
					.map(Type::getTypeName)
					.collect(Collectors.joining(" -> ")),
					type.getTypeName()
				)
			);
		}

		Class<?> impl = InjectionUtils.resolveConcreteLeaf(bindings, type);
		Constructor<?> injectCtor = InjectionUtils.getInjectConstructor(impl);
		Type[] paramsTypes = injectCtor.getGenericParameterTypes();
		path.add(type);
		Arrays.stream(paramsTypes).forEach(p -> dfs(p, path));
		path.remove(path.size() - 1);
	}
}
