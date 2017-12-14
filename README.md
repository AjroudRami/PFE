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
[`dbi:DBIStorage-init`](#dbidbistorage-init)
[`dbi:DBIStorage-add`](#dbidbistorage-add)
[`dbi:DBIStorage-remove`](#dbidbistorage-remove)
[`dbi:DBIStorage-update`](#dbidbistorage-update)
[`dbi:DBIStorage-get`](#dbidbistorage-get)

### DBIAgent primitives
[`dbi:DBIAgent`](#dbidbiagent)
[`dbi:DBIAgent-import`](#dbidbiagent-import)
[`dbi:DBIAgent-updateBelief`](#dbidbiagent-updatebelief)
[`dbi:DBIAgent-export`](#dbidbiagent-export)
[`dbi:DBIAgent-goalsAsLogoList`](#dbidbiagent-goalsaslogolist)
[`dbi:DBIAgent-desires`](#dbidbiagent-desires)
[`dbi:DBIAgent-believes`](#dbidbiagent-believes)
[`dbi:DBIAgent-goalsAsFactSet`](#dbidbiagent-goalsasfactset)

### Atom primitives
[`dbi:Atom`](#dbiatom)
[`dbi:Atom-getName`](#dbiatom-getname)

### Formula primitives
[`dbi:Formula`](#dbiformula)
[`dbi:Formula-fromAtom`](#dbiformula-fromatom)
[`dbi:Formula-fromFact`](#dbiformula-fromfact)

### Operator primitives
[`dbi:Operator`](#dbioperator)
[`dbi:Operator-toExtensionType`](#dbioperator-toextensiontype)

### PropositionalFormula primitives
[`dbi:PropositionalFormula`](#dbipropositionalformula)
[`dbi:PropositionalFormula-fromAtom`](#dbipropositionalformula-fromatom)

### PropositionalAtom primitives
[`dbi:PropositionalAtom`](#dbipropositionalatom)

### Proposition primitives
[`dbi:Proposition`](#dbiproposition)

### Fact primitives
[`dbi:Fact`](#dbifact)
[`dbi:Fact-fromFormula`](#dbifact-fromformula)
[`dbi:Fact-negate`](#dbifact-negate)
[`dbi:Fact-getFormula`](#dbifact-getformula)

### FactSet primitives
[`dbi:Factset-membership`](#dbifactset-membership)

### TruthDegree primitives
[`dbi:TruthDegree-doubleValue`](#dbitruthdegree-doublevalue)

### Utils primitives
[`dbi:Utils-removeDuplicates`](#dbiutils-removeduplicates)

### `dbi:DBIStorage-init`
Initialize the DBIStorage. Must be called before any other DBIStorage primitive.
#### Example code
```NetLogo
dbi:DBIStorage-init
```

### `dbi:DBIStorage-add`
Adds a new DBIAgent to the DBIStorage. The agent is identified by the given name.

:warning: If the agent's name is already bound in the storage, the agent won't be added.
#### Example code
```NetLogo
dbi:DBIStorage-add DBIAgent "name"
```
**argument**: String: a save name

### `dbi:DBIStorage-remove`
Remove an Agent stored in the DBIStorage.
```NetLogo
dbi:DBIStorage-remove "name"
```
**argument**: String: a save name

### `dbi:DBIStorage-update`
```NetLogo
dbi:DBIStorage-update DBIAgent "name"
```
**argument**: String: a save name

### `dbi:DBIStorage-get`
```NetLogo
dbi:DBIStorage-get "name"
```
**argument**: String: a save name

**returns:** a DBIAgent

### `dbi:DBIAgent`
Create an empty DBIAgent.
```NetLogo
dbi:DBIAgent
```
**returns:** a DBIAgent

### `dbi:DBIAgent-import`
```NetLogo
dbi:DBIAgent-import "filename"
```
**argument**: the filename to import as a String

**returns:** a DBIAgent

### `dbi:DBIAgent-updateBelief`
```NetLogo
dbi:DBIAgent-updateBelief DBIAgent Fact TruthDegree
```
**argument**:

### `dbi:DBIAgent-export`
```NetLogo
dbi:DBIAgent-export DBIAgent "filename"
```
**argument**:

### `dbi:DBIAgent-goalsAsLogoList`
```NetLogo
dbi:DBIAgent-goalsAsLogoList DBIAgent
```
**argument**: a DBIAgent

**returns:** a LogoList of Fact

### `dbi:DBIAgent-desires`
```NetLogo
dbi:DBIAgent-desires DBIAgent
```
**argument**: a DBIAgent

**returns:**

### `dbi:DBIAgent-believes`
```NetLogo
dbi:DBIAgent-believes DBIAgent
```
**argument**: a DBIAgent

**returns:**

### `dbi:DBIAgent-goalsAsFactSet`
```NetLogo
dbi:DBIAgent-goalsAsFactSet DBIAgent
```
**returns:** a Factset


### `dbi:Atom`
```NetLogo
dbi:Atom "name"
```
**argument**: the Atom name as a String

**returns:** an Atom

### `dbi:Atom-getName`
**Description:** This primitive returns the given Atom's name. It can be usefull for handling the decision taken by the DBIAgent in the Netlogo code as the name can stand for a purpose (ex: "buy", "sell", ...)

**argument**: the Atom

**returns:** a String, the Atom's name

**Code example**
```NetLogo
dbi:Atom-getName Atom
```

### `dbi:Formula`
```NetLogo
dbi:Formula
```
**returns:** a Formula

### `dbi:Formula-fromAtom`
```NetLogo
dbi:Formula-fromAtom Atom
```
**argument**: an Atom

**returns:** a Formula

### `dbi:Formula-fromFact`
```NetLogo
dbi:
```
**returns:** a Formula

### `dbi:Operator`
```NetLogo
dbi:
```
**returns:** an Operator

### `dbi:Operator-toExtensionType`
```NetLogo
dbi:
```
**returns:** an ExtensionObject containing an Operator

### `dbi:PropositionalFormula`
```NetLogo
dbi:
```
**returns:** a PropositionalFormula

### `dbi:PropositionalFormula-fromAtom`
```NetLogo
dbi:
```
**returns:** a PropositionalFormula

### `dbi:PropositionalAtom`
```NetLogo
dbi:
```
**returns:** a PropositionalAtom


### `dbi:Proposition`
```NetLogo
dbi:
```
**returns:** a Propositional

### `dbi:Fact`
```NetLogo
dbi:
```
**returns:** a Fact

### `dbi:Fact-fromFormula`
```NetLogo
dbi:
```
**returns:** a Fact

### `dbi:Fact-negate`
```NetLogo
dbi:
```
**returns:** a Fact

### `dbi:Fact-getFormula`
```NetLogo
dbi:
```
**returns:** a Formula

### `dbi:Factset-membership`
```NetLogo
dbi:
```
**returns:** a TruthDegree

### `dbi:TruthDegree-doubleValue`
```NetLogo
dbi:
```
**returns:** a Number

### `dbi:Utils-removeDuplicates`
```NetLogo
dbi:Utils-removeDuplicates LogoList
```
**returns:** a LogoList


## Terms of Use


