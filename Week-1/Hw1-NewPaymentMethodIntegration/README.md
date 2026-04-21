# Payment Processing System — SOLID Design Example

## Overview

This project demonstrates a simple payment processing system designed according to SOLID principles, with emphasis on extensibility and minimal modification of existing code when adding new payment methods.

The application supports multiple payment methods and dynamically resolves the appropriate payment strategy using a lightweight dependency injection container.

---

## Architecture

The project is organized into the following layers:

### Models

The `PaymentMethod` abstract class represents a generic payment method.
Concrete implementations include:

* `BankCardPaymentMethod` (existing payment method)
* `CryptoPaymentMethod`
* `PayPalPaymentMethod` (newly added payment method)

Each payment method uses the Builder pattern to simplify object construction and maintain immutability-like behavior.

---

### Strategies

The payment behavior is implemented using the Strategy pattern. These are usually called services in backend applications. The payment logic could've been implemented directly inside the models/entities, however since the actual payment logic would have many dependencies such as the payment APIs, logging, and more;

* `IPaymentStrategy<T>` defines a generic payment strategy interface
* `GenericPaymentStrategy<T>` provides a default implementation for all payment methods
* `PayPalPaymentStrategy` overrides the generic strategy for PayPal-specific behavior

This allows new payment types to be added without modifying existing logic, satisfying the Open/Closed Principle.

---

### Dependency Injection (IoC Container)

A lightweight dependency injection container is implemented using:

* `ServiceRegistry` (service registration)
* `ServiceContainer` (service resolution)
* `Inject` annotation (constructor injection)

Dependencies are automatically resolved using reflection and explicit constructor injection.
This removes tight coupling between components and improves extensibility.

---

## Automatic Injection Behavior

Automatic dependency injection works by resolving dependencies using their registered types.
For example:

* `IPaymentRepository` resolves to `MockDB`
* `IPaymentStrategy<PaymentMethod>` resolves to `GenericPaymentStrategy`

However, this mechanism becomes limited when working with generics.

### The Generic Limitation

In this project:

* `GenericPaymentStrategy<PaymentMethod>` acts as the default strategy
* `PayPalPaymentStrategy` overrides the behavior for `PayPalPaymentMethod`

While I intended to override the default behaviour implemented at `GenericPaymentStrategy<PaymentMethod>` for the PayPal payment method, currently all payment methods get wired to the default implementation. It should be possible to make it so that every payment method wires to the default strategy (GenericPaymentStrategy) while also wiring PayPal payment method to the more specific implementation (PaypalPaymentStrategy) through a single injected service. However I could not figure out how to do it using my primitive IoC container.

---

## Workaround

In real-world backend applications, this limitation is usually avoided by separating controllers or services per model instead of relying on generic strategy resolution.

For example:

* `BankCardPaymentController`
* `PayPalPaymentController`
* `CryptoPaymentController`

Each controller directly injects its specific strategy:

```
PayPalPaymentController → PayPalPaymentStrategy
```

This avoids generic ambiguity and keeps dependency resolution simple and predictable.

---

## SOLID Principles Applied

### Single Responsibility Principle

* Models represent data only
* Strategies contain payment logic
* Repository handles (fake) persistence
* UI handles presentation
* IoC container handles dependency resolution

### Open/Closed Principle

New payment methods can be added by:

1. Creating a new `PaymentMethod`
2. Creating a new `PaymentStrategy`
3. Registering it in the container

No existing code needs to be modified except for registering the new services at the startup code.

### Dependency Inversion Principle

High-level modules depend on abstractions:

* `IPaymentRepository`
* `IPaymentStrategy`

Concrete implementations are injected at runtime.

---

## Design Goals

* Extensible payment system
* Minimal coupling
* SOLID-compliant architecture
* Lightweight dependency injection
* Generic fallback strategy support

---

## Conclusion

This project demonstrates how new payment methods can be integrated into an existing system without modifying existing logic, while maintaining clean architecture and SOLID principles.

### Mustafa Tunahan Baş