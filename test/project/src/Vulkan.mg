LOCATION cz.mg.vulkan

USING ALL cz.mg.stamps
USING ALL cz.mg.types
NATIVE USING "vulkan.h"

@public @publicentities
NATIVE STRUCTURE VkExtent3D
    UInt32$ width
    UInt32$ height
    UInt32$ depth