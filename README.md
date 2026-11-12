# yocto-rpi2

Yocto Scarthgap project for learning on Raspberry Pi 2. Build directly with
BitBake; no kas setup is required.

## Upstream layers

These repositories are excluded from Git. On a new checkout, fetch the same
revisions used by the working image:

```bash
git clone --branch scarthgap https://git.yoctoproject.org/poky
git -C poky checkout 6b7c1066f778db5a6b3e35400617b10342988fa8
git clone --branch scarthgap https://github.com/agherzan/meta-raspberrypi.git
git -C meta-raspberrypi checkout 018d44467c4f09eb63f8ea5e86e85d755250cf89
```

The existing checkouts can be reused without cloning again.

## Build

Run in Bash, as your normal user, from the project root:

```bash
bash
source poky/oe-init-build-env build
bitbake core-image-base
```

`build/conf/local.conf` selects Raspberry Pi 2, enables Dropbear SSH, and
preserves the IPv4/source-mirror download workaround. `bblayers.conf` uses
paths relative to the build directory.

`TMPDIR` is set to `${TOPDIR}/tmp-bitbake` because the original build was
moved from another directory. Downloads and shared-state caches are retained.
The old temporary build output and `build-kas/` are not deleted automatically.

New images are generated in:

```text
build/tmp-bitbake/deploy/images/raspberrypi2/
```

The development image permits root SSH login without a password. Set a
password on the target with `passwd`.

## Version control

The `.gitignore` keeps upstream checkouts, downloads, caches, generated images,
and other build output out of Git. Only `local.conf` and `bblayers.conf` are
included from the build directory. Keep custom layers, recipes, patches, and
application source code tracked normally. Publish image binaries separately.

## Ubuntu 24.04 user namespaces

The previously observed AppArmor user-namespace error is a host restriction
that also applies when invoking BitBake directly. Removing kas does not fix
that restriction. No AppArmor profile has been installed by this project.
