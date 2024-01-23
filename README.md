# Design Patterns

Short descriptions and example implementations of Design Patterns closely
following the
[Gang of Four's Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
book.

[Refactoring Guru](https://refactoring.guru/design-patterns/) is another great
resource for some hands-on material on design patterns.

For front-end devs, there's [patterns.dev](https://www.patterns.dev/vanilla/).

For Pythonistas, there's [python-patterns.dev](https://python-patterns.guide/)
by Brandon Rhodes.

[springframework.guru](https://springframework.guru/gang-of-four-design-patterns/)
has another great, comprehensive series on the topic.

The README is structured according to the book and its "Guide to Readers"
section which considers six patterns to be the simplest and most common ones.

## Table of Contents

### Simple & Common Patterns

- [Abstract Factory (creational)](#abstract-factory)
- Factory Method (creational)
- Adapter (structural)
- Composite (structural)
- [Decorator (structural)](#decorator)
- Observer (behavioral)
- Strategy (behavioral)

### All Patterns

#### Creational

- Builder
- Prototype
- [Singleton](#singleton)

#### Structural

- [Bridge](#bridge)
- Facade
- [Flyweight](#flyweight)
- Proxy

#### Behavioral

- Chain of Responsibility
- Command
- Interpreter
- Iterator
- Mediator
- Memento
- State
- Template Method
- Visitor

## Simple & Common Patterns

## Creational

### Abstract Factory

Provide an interface for creating families of related or dependent objects
without specifying their concrete classes.

#### Why

Some sort of system should be independent from how its parts are being created,
composed, and represented. The parts form some sort of theme (family of objects)
where client code should only be able to use parts that belong together.

#### What

Create an `AbstractFactory` class which defines an interface for creating
objects ("product objects"). A `ConcreteFactory1` class implements the
interface, returning concretions of an abstract `AbstractProduct`, specific to
that concrete's theme/kind (e.g. `ConcreteProduct1`). A `Client` class depends
only on the abstract interfaces to create and interact with its objects, e.g. it
is unaware of which theme/kind is in use.

#### Examples

UI toolkit: An `App` class uses methods like `createScrollBar` or `createWindow`
that are defined by an abstract `WidgetFactory`. `MobileWidetFactory` and
`WebWidgetFactory` are concretions that return concretions of an abstract
`ScrollBarWidget` (like `MobileScrollBar` or `WebScrollBar`). The `App` relies
only on `WidgetFactory`'s interface for creation of widgets and abstract
`ScrollBarWidget`'s interface for usage of widgets.

#### Discussion

- Con: _Supporting new kinds of products is difficult._ The abstract factory's
  interface needs to be changed and with it all its implementors. Can be
  alleviated by using the **Protoype** pattern for concrete factories.
- Commonly, the concrete factories will use a **FactoryMethod** for each product
  object.

### Factory Method

Define an interface for creating an object, but let subclasses decide which
class to instantiate. Factory Method lets a class defer instantiation to
subclasses.

## Structural

### Adapter

Convert the interface of a class into another interface clients expect. Adapter
lets classes work together that couldn’t otherwise because of incompatible
interfaces.

### Composite

Compose objects into tree structures to represent part-whole hierarchies.
Composite lets clients treat individual objects and compositions of objects
uniformly.

### Decorator

Attach additional responsibilities to an object dynamically. Decorators provide
a flexible alternative to subclassing for extending functionality.

#### Why

Add functionality to an object without wanting to change (or having access to)
its internals. Use when functionality can be added and/or withdrawn
_dynamically_.

#### What

Create an object that mirrors the the interface of another object, adding
specific functionality where needed. The decorator forwards calls to methods of
the interface to the wrapped object.

Create a BaseDecorator class which holds a reference to a wrapped object. Base
decorator must forward all work to the wrapped object, thereby _implementing its
interface_.

Create ConcreteDecorator classes inheriting from BaseDecorator (implementing the
original interface) can add functionality by doing work _before or after
delegating_ calls to the parent methods.

#### Examples

An `Application` handles a stream of data provided by a `DataSource`.
`DataSource` is an interface which a base `DataSourceDecorator` implements.
There may be a simple `FileDataSource` which implements the interface and any
number of decorators like `EncryptionDecorator` or `CompressionDecorator`.

A client may now compose data sources and decorators freely and dynamically,
while the `Application` doesn’t need to know which decorators are in use.

#### Discussion

- Pro: Wrapped object - and its consumers - don’t need to know about the
  extension, which is the difference to the **Strategy** pattern (Decorator
  changes the skin, Strategy changes the guts)
- Pro: Can be used to extend methods which are declared `final`.
- Con: Only suited for wrapping objects with relatively small interfaces as
  mirroring the large interfaces in many decorators gets unwieldy.

## Behavioral

### Observer

Define a on-to-many dependency between objects so that when one object changes
state, all its dependents are notified an updated automatically.

### Strategy

Define a family of algorithms, encapsulate each one, and make them
interchangeable. Strategy lets the algorithm vary independently from clients
that use it.

## All Patterns

## Creational

### Singleton

Ensure a class only has one instance, and provide a global point of access to
it.

[Kotlin](kotlin/src/main/kotlin/Singleton.kt)

#### Why

There must be exactly one instance of a class.

#### What

Create a class with a private default constructor which initialises a _private
instance property_ to null. Implement a static creation method _getInstance_
which uses the private constructor and saves the created instance to a cache. On
successive call, return the cached instance.

#### Examples

- Global app config
- Single database connection pool

#### Discussion

_Singleton is nowadays mostly considered an anti-pattern_ because it is

- hard to test
- needs to be refactored eventually more often than not which becomes expensive
- requires extra care for thread safety
- leads to inflexible code, implicit dependencies

Use _dependency injection_ to pass single instances of objects around.

_“the fact that it is singleton is merely a property of the application /
caller, not of the object, itself”_ -
https://www.michaelsafyan.com/tech/design/patterns/singleton

## Structural

### Bridge

Decouple an abstraction from its implementation so that the two can vary
independently.

[Kotlin](kotlin/src/main/kotlin/Bridge.kt)

#### Why

A) Avoid permanently binding implementations to abstractions. Both abstractions
as well as implementations can be subclassed and extended independently.
_Changes in implementations should have no effect on the clients using them._

B) Mix and match any number of concrete implementors of multiple abstractions
without creating an excessive amount of objects representing each combination,
e.g. _reduce the number of objects from the cartesian product down to the sum_
of different abstractions.

#### What

Create one hierarchy consisting of abstract classes and a separate hierarchy
consisting of implementors. A client only knows about the base abstraction which
bridges to the implementor.

#### Examples

- Window drawing: An abstract hierarchy of `Window`, `IconWindow`,
  `TransientWindow` bridges to an interface `WindowImpl` which is implemented by
  concrete `XWindowImpl` and `PMWindowImpl` implementors.

- Media App: An abstract `View` has concrete `LongView` and `ShortView`
  implementors which render a `resource` that represents an `Artist`, `Album`,
  `Author` or `Book`. `Resource` is an (abstract) interface that bridges
  `ArtistResource` (etc.) implementors to a `View`.

#### Discussion

### Flyweight

Use sharing to support large numbers of fine-grained objects efficiently.

#### Why

A massive number of similar objects is causing the program to run out of memory.

#### What

For each object, determine its _intrinsic_ and _extrinsic_ state. Keep only the
intrinsic state in a _concrete flyweight_ object and make it _immutable_.

Create any number of _contextual objects_ (referencing their respective
flyweight) which hold the extrinsic, _unique_ and _mutable_) state.

Let _concrete flyweights_ provide methods like draw(position) or move(speed,
vector) which receive the extrinsic state as arguments.

#### Examples

Word processor: Limited number of `Letter` objects store only a character code.
Any number of `Glyph` objects store state like position, font style, etc.

Multiplayer shooter: Limited number of `Particle` objects (bullet, missile,
shrapnel) store a color and a sprite (lots of memory). Any number of
`MovingParticle` objects store information on coords, vector, speed, etc.

#### Discussion

Optional: Create a `FlyweightFactory` that takes care of creating and managing
references to all existing flyweights. A client passes the _intrinsic_ state to
receive a flyweight. If a flyweight doesn’t exist, it is being created, if it
exists, a reference is handed out.

Optional: For convenience, a client may keep all extrinsic state in a separate
`Context` class.

[Entity Component Systems](https://en.wikipedia.org/wiki/Entity_component_system)
are an interesting application of this pattern.

## Behavioral

### Empty

#### Why

#### What

#### Examples

#### Discussion
