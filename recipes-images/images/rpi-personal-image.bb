require recipes-extended/images/core-image-full-cmdline.bb

SUMMARY = "Personal RPi image"

inherit extrausers

ROOT_IMAGE_PASSWORD ??= ""

python () {
    if not d.getVar('ROOT_IMAGE_PASSWORD'):
        bb.fatal("ROOT_IMAGE_PASSWORD is not set in local.conf!")
}

EXTRA_USERS_PARAMS = "usermod -p '${ROOT_IMAGE_PASSWORD}' root;"

IMAGE_INSTALL:append = " \
    kernel-modules \
    linux-firmware-rtl8851 \
    usb-modeswitch \
    wireless-regdb-static \
    wpa-supplicant \
"
