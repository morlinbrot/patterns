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

### [Simple & Common Patterns](#simple--common-patterns-1)

- [Abstract Factory](#abstract-factory) [(creational)](#creational-1)
- [Factory Method](#factory-method) [(creational)](#creational-1)
- [Adapter](#adapter) [(structural)](#structural-1)
- [Composite](#composite) [(structural)](#structural-1)
- [Decorator](#decorator) [(structural)](#structural-1)
- [Observer](#observer) [(behavioral)](#behavioral-1)
- Strategy [(behavioral)](#behavioral-1)

### [All Patterns](#all-patterns-1)

#### [Creational](#creational-2)

- Builder
- [Prototype](#prototype)
- [Singleton](#singleton)

#### [Structural](#structural-2)

- [Bridge](#bridge)
- Facade
- [Flyweight](#flyweight)
- Proxy

#### [Behavioral](#behavioral-2)

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

[Kotlin](kotlin/src/main/kotlin/AbstractFactory.kt)

#### Why

A system should be independent from how its parts are being created, composed,
and represented. The parts form some sort of theme (family of objects) where
client code should only be able to use parts that belong together.

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
`WebWidgetFactory` are concretions that return of an abstract `ScrollBarWidget`
(like `MobileScrollBar` or `WebScrollBar`). The `App` relies only on
`WidgetFactory`'s interface for creation of widgets and abstract
`ScrollBarWidget`'s interface for usage of widgets.

#### Discussion

- Con: _Supporting new kinds of products is difficult._ The abstract factory's
  interface needs to be changed and with it all its implementors. Can be
  alleviated by using the **Protoype** pattern for concrete factories.
- Commonly, a factory will just be a collection of **Factory Methods** and is
  also not abstract itself, e.g. providing default implementations where only
  specific methods can be overriden by further concrete factories.

### Factory Method

Define an interface for creating an object, but let subclasses decide which
class to instantiate. Factory Method lets a class defer instantiation to
subclasses.

[Kotlin](kotlin/src/main/kotlin/FactoryMethod.kt)

#### Why

A) A creator class define how to create objects but shouldn't know how to create
them itself.

B) Provide users of a library or framework with a way of extending its
functionality.

C) Need to manage large, resource-intensive objects like db connections. Use a
factory method to centrally manage connections.

#### What

Create a `Creator` class defining a `createProduct` method (it could optionally
provide a default implementation). Subclass the creator with `ConcreteCreators`,
implementing the interface and creating a `ConcreteProduct`. All
`ConcreteProducts` need to implement the same interface defined by
`createProduct`.

#### Examples

Logistics app: A `LogisticsApp` uses a `createTransportation` with a return type
of `Transportation` to creat objects that can make a delivery. `RoadLogistics`
and `SeaLogistics` subclass it and return `Truck` and `Ship` instances,
respectively. Both implement the `Transportation` interface with a `deliver`
method.

#### Discussion

- Use only if the creator is subclassed anyway, otherwise the pattern creates
  unnecessary overhead by forcing clients to subclass the creator just to
  instantiate concrete products.
- The pattern can easily evolve into the **Abstract Factory** pattern,
  difference being: Abstract Factory provides access to many different products
  which share a theme. _Abstract Factories are often implemented using Factory
  Methods._

## Structural

### Adapter

Convert the interface of a class into another interface clients expect. Adapter
lets classes work together that couldn’t otherwise because of incompatible
interfaces.

[Kotlin](kotlin/src/main/kotlin/Adapter.kt)

#### Why

You want to use an existing class but its interface doesn't match what your
existing system's interfaces or you want to create a reusable class that
cooperates with unrelated or unforeseen classes (e.g. generic TreeView)

#### What

There are class and object versions of the pattern. Either

1. define a class that inherits the internal interface and proxies the external
   class' implementation, or
2. compose an instance of the external object with an internal one that
   implements the internal interface

#### Examples

A `TreeView` target class defines a narrow interface consisting of `getChildren`
and `createNode` methods. A `DirectoryTreeView` adapter implements the
interface, by specialising it to be compatible with the interface of
`FileSystemEntity` objects. Other `AdaptedTreeView` may adapt the interface to
different `AdapteeEntity` objects. A `Client` class can freely construct and
work with trees of any kind without knowing their type.

#### Discussion

- Con class: must commit to a concrete `Adaptee` class, e.g. won't work for
  adapting a class _and all its subclasses_.
- Pro class: no additional pointer indirection.
- Pro object: lets `Adapter` work with many `Adaptee`s and can add functionality
  to all `Adaptee`s at once.
- Con object: Harder to override `Adaptee` behaviour, needs to create `Adaptee`
  subclasses.
- Similar to **Bridge** but different in intent and usage: Use **Bridge** when
  designing a system, **Adapter** when adapting already existing systems.

### Composite

Compose objects into tree structures to represent part-whole hierarchies.
Composite lets clients treat individual objects and compositions of objects
uniformly.

_"Lets you compose objects into tree structures and then work with these
structures as if they were individual objects."_

- https://refactoring.guru/design-patterns/composite

#### Why

You need to be able to treat all objects uniformely, no matter if they are
composed of multiple objects or not.

[Kotlin](kotlin/src/main/kotlin/Adapter.kt)

#### What

Define an interface `IComponent` with operations like a `getPrice` method that
should be shared by both composite and primitive objects. Create `Composite` and
`Primitive` base classes which implement the interface. `Composite` will
additionally define methods to operate on the tree structure (whether to include
these in the `IComponent` interface or not is up for discussion).

Create implementors of the base classes like `Product`, implementing
`Primitive`, and `Box`, implementing `Composite`. Compute the price of boxes
and/or products without knowing exactly what they are.

#### Examples

See above and example code.

#### Discussion

- Makes sense only if the core model can be represented as a tree.

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
state, all its dependents are notified and updated automatically.

[Kotlin](kotlin/src/main/kotlin/Observer.kt)

#### Why

- Changes in one object require changes in others, without knowing how many
  dependent objects there are.
- When an object should notifiy others about updates without making any
  assumptions about them, e.g. _to prevent tight coupling_.

#### What

Define a base `Subject` class with `subscribe()`/`unsubscribe()` and `notify()`
methods. A `ConcreteSubject` implements the interface and provides additional
`getState()`/`setState()` methods.

A base `Observer` class defines an `update()` method. A `ConcreteObserver` holds
a reference to a `ConcreteSubject` on which it calls `subscribe()`. After the
subject calls `update()` on the observer, it in turn calls `getState()` on the
subject to receive the updated state.

Optional: Introduce specific events (aspects) that observers can subscribe to,
making the interface more fine-grained and efficient:
`subscribe(observer: Observer, event: Event)`.

Optional: Introduce a `ChangeManager` object that sits between subjects and
observers and handles the updating logic. This way, different and more complex
updating logic can be implemented, e.g. a `DAGChangeManager` which manages a
directed-acyclic graph of dependencies might prevent redundant updates by
grouping updates from several subjects into a single call.

#### Examples

See code.

#### Discussion

- There is only _abstract coupling_ between subject and observer. This means
  that they can _belong to different layers of abstraction of the system without
  violating the layering principle_.
- The `notify` operation can be called either by the subject itself or the
  client code with different tradeoffs. The former will make sure that updates
  will be broadcast but may incur unnecessary costs for many updates in quick
  succession, the latter avoids that by making it possible to "batch" update
  calls but may become more brittle as the responsibility is moved to the
  client.
- Find a balance between the extremes push and pull model. In the former, the
  subject will send detailled information about the change which implies that
  the subject knows something about its observers. The latter may be inefficient
  as observers need to figure out _what_ changed without the help of the
  subject.

### Strategy

Define a family of algorithms, encapsulate each one, and make them
interchangeable. Strategy lets the algorithm vary independently from clients
that use it.

## All Patterns

## Creational

### Prototype

Specify the kinds of objects to create using a prototypical instance, and create
new objects by copying this prototype.

[Kotlin](kotlin/src/main/kotlin/Prototype.kt)

#### Why

- You want to clone objects without knowing/being dependent on their classes.
- A program needs to be able to receive objects "from the outside" and/or at
  runtime without knowing their classes.
- Move the (arbitrarily complex) logic of (deep) cloning objects away from the
  client into the objects themselves.

#### What

Define a `Prototype` interface which most oftenly consist of only one method,
`clone`. Implementations of `clone` have access to private fields and methods
and take care of producing an identical copy, including any state the object may
hold. Optionally, define an `initialize` method on the interface that can be
used to reset objects to an "empty" state.

#### Examples

#### Discussion

- Reduced subclassing compared to the **Factory Method** which often produces a
  hierarchy of `Creator` classes that parallel the product class hierarchy.

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
shrapnel) store a color and a sprite (lots of memory). A large number of
`MovingParticle` objects only store information on coords, vector, speed, etc.

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
