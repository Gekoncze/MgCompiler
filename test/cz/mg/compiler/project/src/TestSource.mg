# Every file should start with a specification of destination location.
# All subsequent entity definitions are stored in that location.
# The location is created if it doesn't exist yet.
LOCATION cz.mg.test

# If you want to use an entity from another file,
# you have to explicitly state how you want to use it.

# You are using an entity directly with its name.
USING cz.mg.collections.Array

# You are using an entity with an alias.
USING cz.mg.example.Foo ALIAS Bar

# You are using all child entities of an entity directly with their names.
USING ALL cz.mg.language.types  # we will be using standard data types
USING ALL cz.mg.language.stamps  # we will be using standard stamps


# An example of a class definition.
# Most language entities can be marked with stamps, which has various meanings.
@public
CLASS Color
    @private @get @set
    UInt8$ r, g, b, a

    @public @create
    FUNCTION create INPUT Color& self, UInt8$ r, g, b, a
        self.r = r
        self.g = g
        self.b = b
        self.a = a

    @public @destroy
    FUNCTION destroy INPUT Color& self

    @public
    FUNCTION mix INPUT Color& a, Color& b, Float32$ t OUTPUT Color$ c
        c.r = a.r * ("1" - t) + b.r * t
        c.g = a.g * ("1" - t) + b.g * t
        c.b = a.b * ("1" - t) + b.b * t
        c.a = (-a.a) * ("1" - t) + b.a * t

    @public
    FUNCTION plus INPUT Color& a, Color& b OUTPUT Color$ c OPERATOR +
        c.r = a.r + b.r
        c.g = a.g + b.g
        c.b = a.b + b.b
        c.a = a.a + b.a

    @private
    FUNCTION voidTest INPUT Color& a, Color& b
        VOID = plus: a, b  # ignoring return value has to be explicit using VOID

    @public
    FUNCTION clear INPUT Color& self
        self.r = "0"
        self.g = "0"
        self.b = "0"
        self.a = "0"

    @private
    FUNCTION switchTest INPUT Int32$ a OUTPUT Text& t
        Float32$ f = a AS Float32$
        SWITCH a
            CASE "1"
                RETURN t = "one" AS Text&
            CASE "2"
                RETURN t = "two"
            CASE "3"
                RETURN t = "three"
            ELSE
                RETURN t = "other"

@public
CLASS Fruit
    @private
    Int32$ sweetness

    @private
    Float32$ weight  # grams


## inheritance can be used to create one-way (baseclass <- subclass) commutable types
## inheritance can add new properties
## inheritance can add new functions
## multiple inheritance is allowed (cuz why the hell not, idiots)
#@public
#CLASS Strawberry IS Fruit, Food
#    @private
#    Color$ color = Color: "0", "0", "0", "0"
#
#    @private
#    Strawberry& nextStrawberry &= NULL
#
#    @public
#    FUNCTION pick INPUT Strawberry* self
#        IF sweetness > "0.75"
#            self.color = Color: "128", "64", "0", "255"
#        ELSE IF sweetness > "0.7"
#            self.color = Color: "255", "0", "0", "255"
#        ELSE IF sweetness > "0.5"
#            self.color = Color: "255", "255", "128", "255"
#        ELSE
#            self.color = Color: "128", "255", "128", "255"
#
## alias can be used to create two-way (baseclass <-> subclass) commutable types
## alias cannot add new properties, only use existing ones
## alias can add new functions
## alias can be of only one class
#@public
#CLASS Strawberries ALIAS Array OF Strawberry
#    @public
#    FUNCTION pick INPUT Strawberries* self
#        FOR EACH Strawberry strawberry IN self
#            strawberry.pick
#
## views can be used to create one-way (baseclass -> subclass) commutable types
## views can be used to expose only some properties or functions of a given class
## views can be of only one class
#@public
#CLASS ReadonlyColor VIEW Color
#    @private @get
#    UInt8 r, g, b, a
#
#    @public
#    FUNCTION mix INPUT Color a, Color b, Float32 t OUTPUT Color c
#
#    @public
#    FUNCTION plus INPUT Color a, Color b OUTPUT Color c OPERATOR +