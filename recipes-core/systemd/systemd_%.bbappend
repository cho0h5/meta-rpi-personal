FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://25-wireless.network"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    
    install -m 0644 ${UNPACKDIR}/25-wireless.network ${D}${sysconfdir}/systemd/network/
}
