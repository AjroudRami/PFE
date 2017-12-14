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

### Storage primitives
[`dbi:DBIStorage-init`](#dbiDBIStorageinit)
[`dbi:DBIStorage-add`](#dbiDBIStorageadd)
[`dbi:DBIStorage-remove`](#dbiDBIStorageremove)
[`dbi:DBIStorage-update`](#dbiDBIStorageupdate)

### DBIAgent primitives
[`dbi:DBIAgent`](#dbiDBIAgent)
[`dbi:DBIAgent-import`](#dbiDBIAgentimport)
[`dbi:DBIAgent-updateBelief`](#dbiDBIAgentupdateBelief)
[`dbi:DBIAgent-export`](#dbiDBIAgentexport)
[`dbi:DBIAgent-goalsAsLogoList`](#dbiDBIAgentgoalsAsLogoList)
[`dbi:DBIAgent-desires`](#dbiDBIAgentdesires)
[`dbi:DBIAgent-believes`](#dbiDBIAgentbelieves)
[`dbi:DBIAgent-goalsAsFactSet`](#dbiDBIAgentgoalsAsFactSet)

### Atom primitives
[`dbi:Atom`](#dbiAtom)

### Formula primitives
[`dbi:Formula`](#dbiFormula)
[`dbi:Formula-fromAtom`](#dbiFormulafromAtom)
[`dbi:Formula-fromFact`](#dbiFormulafromFact)

### Operator primitives
[`dbi:Operator`](#dbiOperator)
[`dbi:Operator-toExtensionType`](#dbiOperatortoExtensionType)

### PropositionalFormula primitives
[`dbi:PropositionalFormula`](#dbiPropositionalFormula)
[`dbi:PropositionalFormula-fromAtom`](#dbiPropositionalFormulafromAtom)

### PropositionalAtom primitives
[`dbi:PropositionalAtom`](#dbiPropositionalAtom)

### Proposition primitives
[`dbi:Proposition`](#dbiProposition)

### Fact primitives
[`dbi:Fact`](#dbiFact)
[`dbi:Fact-fromFormula`](#dbiFactfromFormula)
[`dbi:Fact-negate`](#dbiFactnegate)
[`dbi:Fact-getFormula`](#dbiFactgetFormula)

### FactSet primitives
[`dbi:Factset-membership`](#dbiFactsetmembership)

### TruthDegree primitives
[`dbi:TruthDegree-doubleValue`](#dbiTruthDegreedoubleValue)

### Utils primitives
[`dbi:Utils-removeDuplicates`](#dbiUtilsremoveDuplicates)

### `dbi:DBIStorage-init`

```NetLogo
dbi:DBIStorage-init
```

### `dbi:DBIStorage-add`
### `dbi:DBIStorage-remove`
### `dbi:DBIStorage-update`
### `dbi:DBIAgent`

```NetLogo
dbi:DBIAgent
```

### `dbi:DBIAgent-import`
### `dbi:DBIAgent-updateBelief`
### `dbi:DBIAgent-export`
### `dbi:DBIAgent-goalsAsLogoList`
### `dbi:DBIAgent-desires`
### `dbi:DBIAgent-believes`
### `dbi:DBIAgent-goalsAsFactSet`

### `dbi:Atom`

[``](#)

## Terms of Use


