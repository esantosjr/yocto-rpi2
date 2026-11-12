# yocto-rpi2
Yocto project in Raspberry Pi 2 for learning.

## Build with kas

The kas configuration pins Poky and meta-raspberrypi to the exact Scarthgap
commits used by the working Raspberry Pi 2 image. External repositories,
build output, and caches are excluded from Git.

Install kas in an isolated environment (Ubuntu/Debian):

```bash
sudo apt install python3-venv
python3 -m venv .venv
.venv/bin/pip install kas
```

From the project root, in a fresh terminal without a previously sourced Yocto
environment:

```bash
KAS_BUILD_DIR=build-kas .venv/bin/kas build kas/raspberrypi2.yml
```

Use `build-kas` because the original `build` tree was moved from another path
and retains absolute paths. The existing build and caches are left untouched.
The first kas build creates its own caches and may require substantial disk
space. Do not run the original and kas builds concurrently against the same
layer checkouts.

The resulting image and block map are in:

```text
build-kas/tmp/deploy/images/raspberrypi2/
```

The image includes Dropbear SSH. `debug-tweaks` permits root login without a
password and is intended for development only.

Commit only the project configuration, documentation, custom layers, and
application sources. Publish generated images as release assets instead.

```bash
git add .gitignore kas/raspberrypi2.yml README.md
git commit -m "Add pinned kas configuration for Raspberry Pi 2"
```
