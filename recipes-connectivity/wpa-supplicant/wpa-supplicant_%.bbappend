FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wpa_supplicant-wlan0.conf"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " wpa_supplicant@wlan0.service"

python () {
    if not d.getVar('REAL_WIFI_CONFIG'):
        bb.fatal("REAL_WIFI_CONFIG is not set in local.conf!")
}

do_install:append() {
    install -d ${D}${sysconfdir}/wpa_supplicant

    if [ -f "${REAL_WIFI_CONFIG}" ]; then
        bbnote "Applying private Wi-Fi configuration from local.conf"
        cp "${REAL_WIFI_CONFIG}" ${UNPACKDIR}/wpa_supplicant-wlan0.conf
    fi

    install -m 0600 ${UNPACKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
}
