# DBI Agent Extension for NetLogo

This package contains the DBI Agent extensions.

## Building

Prerequisites:
- Git 2.15 or higher
- SBT 0.13.16 or higher
- JDK 1.8 or higher
- Internet connection

Steps:
- Open the command prompt (terminal on ubuntu)
- Clone this repository using: ``git clone https://github.com/AjroudRami/PFE.git``
- Go to the ``PFE/extension`` directory
- Run `sbt package`

If the build success, you will find a zip file in the extension directory. Unzip this file in your NetLogo install directory in ``NetLogo-version/app/extensions``.

## Using

This extension adds DBI Agents to NetLogo.

### How to use

### Known Issues

### Credits

## Primitives

### Storage Primitives
[`dbi:DBIStorage-init`](#dbiDBIStorageinit)
### DBIAgent primitives
[`dbi:DBIAgent`](#dbiDBIAgent)

### `dbi:DBIStorage-init`

```NetLogo
dbi:DBIStorage-init
```

### `dbi:DBIAgent`

```NetLogo
dbi:DBIAgent "filename-as-a-string"
```


## Terms of Use


