LINUX_VERSION ?= "7.1.0-rc5"
LINUX_RPI_BRANCH ?= "rpi-7.1.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.12"

SRCREV_machine = "d460d03b215c069772998fcdc9927ac1112b33b0"
SRCREV_meta = "7c9e1447ddc7e4780508e8f91974ceb0863b7ef0"

KMETA = "kernel-meta"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
    "
SRC_URI:remove = "file://vc4graphics.cfg"

require recipes-kernel/linux/linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"

FILESEXTRAPATHS:prepend := "${THISDIR}/../../../meta-raspberrypi/recipes-kernel/linux/files:"
