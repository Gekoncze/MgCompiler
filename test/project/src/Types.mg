LOCATION cz.mg.types

USING ALL cz.mg.stamps
NATIVE USING "stdint.h"


@private @publicentities
NATIVE TYPE uint8_t
    NATIVE FUNCTION assign INPUT uint8_t$ a, b OUTPUT uint8_t$ r OPERATOR =
    NATIVE FUNCTION plus INPUT uint8_t$ a, b OUTPUT uint8_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT uint8_t$ a, b OUTPUT uint8_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT uint8_t$ a, b OUTPUT uint8_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT uint8_t$ a, b OUTPUT uint8_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE int8_t
    NATIVE FUNCTION plus INPUT int8_t$ a, b OUTPUT int8_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT int8_t$ a, b OUTPUT int8_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT int8_t$ a, b OUTPUT int8_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT int8_t$ a, b OUTPUT int8_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE uint16_t
    NATIVE FUNCTION plus INPUT uint16_t$ a, b OUTPUT uint16_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT uint16_t$ a, b OUTPUT uint16_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT uint16_t$ a, b OUTPUT uint16_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT uint16_t$ a, b OUTPUT uint16_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE int16_t
    NATIVE FUNCTION plus INPUT int16_t$ a, b OUTPUT int16_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT int16_t$ a, b OUTPUT int16_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT int16_t$ a, b OUTPUT int16_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT int16_t$ a, b OUTPUT int16_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE uint32_t
    NATIVE FUNCTION plus INPUT uint32_t$ a, b OUTPUT uint32_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT uint32_t$ a, b OUTPUT uint32_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT uint32_t$ a, b OUTPUT uint32_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT uint32_t$ a, b OUTPUT uint32_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE int32_t
    NATIVE FUNCTION plus INPUT int32_t$ a, b OUTPUT int32_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT int32_t$ a, b OUTPUT int32_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT int32_t$ a, b OUTPUT int32_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT int32_t$ a, b OUTPUT int32_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE uint64_t
    NATIVE FUNCTION plus INPUT uint64_t$ a, b OUTPUT uint64_t$ r OPERATOR +
    NATIVE FUNCTION minus INPUT uint64_t$ a, b OUTPUT uint64_t$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT uint64_t$ a, b OUTPUT uint64_t$ r OPERATOR *
    NATIVE FUNCTION divide INPUT uint64_t$ a, b OUTPUT uint64_t$ r OPERATOR /


@private @publicentities
NATIVE TYPE float
    NATIVE FUNCTION plus INPUT float$ a, b OUTPUT float$ r OPERATOR +
    NATIVE FUNCTION minus INPUT float$ a, b OUTPUT float$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT float$ a, b OUTPUT float$ r OPERATOR *
    NATIVE FUNCTION divide INPUT float$ a, b OUTPUT float$ r OPERATOR /


@private @publicentities
NATIVE TYPE double
    NATIVE FUNCTION plus INPUT double$ a, b OUTPUT double$ r OPERATOR +
    NATIVE FUNCTION minus INPUT double$ a, b OUTPUT double$ r OPERATOR -
    NATIVE FUNCTION multiply INPUT double$ a, b OUTPUT double$ r OPERATOR *
    NATIVE FUNCTION divide INPUT double$ a, b OUTPUT double$ r OPERATOR /


@public
TYPE UInt8 ALIAS uint8_t

@public
TYPE Int8 ALIAS int8_t

@public
TYPE UInt16 ALIAS uint16_t

@public
TYPE Int16 ALIAS int16_t

@public
TYPE UInt32 ALIAS uint32_t

@public
TYPE Int32 ALIAS int32_t

@public
TYPE UInt64 ALIAS uint64_t

@public
TYPE Int64 ALIAS int64_t

@public
TYPE Float32 ALIAS float

@public
TYPE Float64 ALIAS double

@public
TYPE Bool8 LIKE UInt8
