SUMMARY = "A small C application for learning Yocto"
DESCRIPTION = "Prints a greeting on the Raspberry Pi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c56209e3c3b88a0c45ac9f0158ea4a9"

SRC_URI = "file://hello-pi.c \
           file://LICENSE \
"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${CPPFLAGS} ${CFLAGS} ${S}/hello-pi.c -o ${B}/hello-pi ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/hello-pi ${D}${bindir}/hello-pi
}
