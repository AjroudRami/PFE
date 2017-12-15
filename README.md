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

Here is an example of usage. Replace the filename variable by the filename.

```NetLogo
to setup
  import
  formulas
end

to import
  set agent dbi:DBIAgent-import "filename"
end

to formulas
  let viable dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "viable"
  let fertile dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "fertile"
  let accessible dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "accessible"

  let notOp dbi:Operator "not"

  let notViable dbi:PropositionalFormula notOp viable
  let notFertile dbi:PropositionalFormula notOp fertile
  let notAccessible dbi:PropositionalFormula notOp accessible

  dbi:DBIAgent-updateBelief agent dbi:Fact-fromFormula notViable 1
  dbi:DBIAgent-updateBelief agent dbi:Fact-fromFormula fertile 0.25
  dbi:DBIAgent-updateBelief agent dbi:Fact-fromFormula accessible 0.2
end

to showAgent
  show agent
end

to showAgentGoals
  foreach (dbi:DBIAgent-goalsAsLogoList agent) [
    fact -> foreach ( dbi:Formula-getAtoms dbi:Fact-getFormula fact) [
     atom -> show dbi:Atom-getName atom
    ]
  ]
end
```

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

### Fact primitives
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
  **Description:**

  Initialize the DBIStorage. Must be called before any other DBIStorage primitive.

  **Example code:**
  
```NetLogo
dbi:DBIStorage-init
```

### `dbi:DBIStorage-add`
  **Description:**
  Adds a new DBIAgent to the DBIStorage. The agent is identified by the given name.

  :warning: If the agent's name is already bound in the storage, the agent won't be added.
  
   **arguments:**
   * DBIAgent: the agent to store
   * String: a save name

   **Example code:**
   
```NetLogo
dbi:DBIStorage-add agent "name"
```
    
### `dbi:DBIStorage-remove`
  **Description:**
  Remove an Agent stored in the DBIStorage.

  **argument:** 
  * String: a save name

  **Example code:**
  
```NetLogo
dbi:DBIStorage-remove "name"
```

### `dbi:DBIStorage-update`
   **Description:**
   Replace a DBIAgent in the storage.
   
   **argument:**
   * String: A save name
   
   **Example code:**
   
```NetLogo
dbi:DBIStorage-update agent "name"
```
    
### `dbi:DBIStorage-get`
   **Description:**
    Returns the DBIAgent corresponding to the given name. If the agent doesn't exist, it throws an ExtensionException.

   **argument:**
   * String: A save name
   
   **returns:** a DBIAgent
   
   **Example code:**
   
```NetLogo
dbi:DBIStorage-get "name"
```

### `dbi:DBIAgent`
   **Description:**
   Create an empty DBIAgent.
   
   **returns:**
   DBIAgent
   
   **Example code:**

```NetLogo
dbi:DBIAgent
```
   
### `dbi:DBIAgent-import`
   **Description:**
   Import a DBIAgent from an apl file. An Exception can occur if the filename is incorrect or if an error occurs during the file reading.
   
   **argument:**
   * String: the path to the filename to import
   
   **returns:** DBIAgent
   
   **Example Code:**
   
```NetLogo
dbi:DBIAgent-import "filename"
```

### `dbi:DBIAgent-updateBelief`
   **Description:**
   Performs belief revision according to a new piece of information (a fact) told by a source with given degree of trust.
   
   **arguments:**
   * DBIAgent: the agent to update
   * Fact: the new Fact to tell
   * TruthDegree: the fact's truth degree
   
   **Example code:**
   
```NetLogo
dbi:DBIAgent-updateBelief agent fact truthDegree
```

### `dbi:DBIAgent-export`
   **Description:**
   Export the DBIAgent to a file. An Exception can occur if the file can't be created.
   
   **arguments:**
   * DBIAgent: the agent to export
   * String: the path to the filename
   
   **Example code:**
    
```NetLogo
dbi:DBIAgent-export agent "filename"
```

### `dbi:DBIAgent-goalsAsLogoList`
   **Description:**
   This reporter returns the agent's goals as a LogoList. This LogoList is a list of Fact that can be iterated over in NetLogo using a foreach loop.
   
   **arguments:**
   * DBIAgent: the agent to retreive the goals
   
   **returns:**
   LogoList of Facts
   
   **Example code:**
   
```NetLogo
dbi:DBIAgent-goalsAsLogoList agent
```

### `dbi:DBIAgent-goalsAsFactSet`
   **Description:**
   This reporter returns the agent's goals as a Factset. This Factset that can't be iterated over in NetLogo. It is mostly used to get a TruthDegree using the dbi:Factset-membership reporter.
   
   **arguments:**
   * DBIAgent: the agent to retreive the goals
   
   **returns:**
   Factset: the agent's goals as a Factset

   **Example code:**

```NetLogo
dbi:DBIAgent-goalsAsFactSet agent
```

### `dbi:DBIAgent-desires`
  **Description:**
  This primitive returns the agent's desire truth degree for a fact as a number ( [0,1] ).
  
  **arguments:**
  * DBIAgent: the agent
  * Fact: the fact to retreive the truth degree
  
  **returns:**
  A number between 0 and 1.
  
  **Example code:**
  
```NetLogo
dbi:DBIAgent-desires agent
```

### `dbi:DBIAgent-believes`
   **Description:**
   This reporter takes an agent and a fact and returns the belief factor of the agent for this fact.
   
   **arguments:**
   * DBIAgent: the agent
   * Fact: the fact to retreive the truth degree
   
   **returns:**
   A number between 0 and 1.
   
   **Example code:**
   
```NetLogo
dbi:DBIAgent-believes agent
```

### `dbi:Atom`
   **Description:**
   Create an Atom by it's name.
   
   **argument:**
   * String: the Atom's name
   
   **returns:**
   Atom
   
   **Example code:**
   
```NetLogo
dbi:Atom "name"
```

### `dbi:Atom-getName`
   **Description:** This primitive returns the given Atom's name. It can be usefull for handling the decision taken by the DBIAgent in the Netlogo code as the name can stand for a purpose (ex: "buy", "sell", ...)

   **argument:**
   * Atom: the atom

   **returns:**
   String: the Atom's name

   **Code example**
   
```NetLogo
dbi:Atom-getName atom
```

### `dbi:Formula`
   **Description:**
   Create a Formula object.
   
   **arguments:**
   * Operator: an operator of arity n
   * LogoList: a list of n Formula
   
   **returns:**
   Formula:  a logical formula
   
   **Example code:**
   
```NetLogo
dbi:Formula operator formulaList
```

### `dbi:Formula-fromAtom`
   **Description:**
   Create a Formula object from an Atom. The resulting formula is called atomic and only composed of the atom. This formula does not contain any Operator.
   
   **argument:**
   * Atom: the atom to create a formula from
   
   **returns:**
   Formula: a formula
   
   **Example code:**
   
```NetLogo
dbi:Formula-fromAtom atom
```

### `dbi:Formula-fromFact`
   **Description:**
   Create a Formula object from a Fact.
   
   **argument:**
   * Fact: a fact
   
   **returns:**
   Formula: the created formula
   
   **Example code:**
   
```NetLogo
dbi:Fact-fromFact fact
```
    
### `dbi:Operator`
   **Description:**
   Create an Operator. This method takes different types of arguments. The two ways to create an Operator are:
   * Create a default Operator (AND, OR, NOT, XOR) by using only one argument, the operator name as a String. **Example:**
 ```NetLogo
 dbi:Operator "not"
 ```
   * Create a custom Operator by specifying two arguments: a name (String) and its arity (integer). **Example:**
```NetLogo
dbi:Operator "myOp" 2
```
   
   **returns:**
    Operator: The created Operator

### `dbi:Operator-toExtensionType`
   **Description:**
   the ExtensionType reporter are primitives used to display informations about the object. It's the `` toString() ``
   equivalent. It should be used only to display objects using the primitive `` show ``.
   
  **argument:**
  * Operator: an operator object
  
  **returns:** an ExtensionObject containing an Operator
  
  **Example code:**
  
  ```NetLogo
  dbi:Operator:toExtensionType
  ```

### `dbi:PropositionalFormula`
   **Description:**
   Create a PropositionalFormula object. The number of formulas given in the arguments must match the operator arity
   
   **arguments:**
   * Operator: a root operator
   * Formula: one or more formula
   
   **returns:**
   PropositionalFormula: a propositional formula
   
   **Example code:**
   
```NetLogo
dbi:PropositionalFormula andOp formula1 formula2
```

```NetLogo
dbi:PropositionalFormula not formula
```

### `dbi:PropositionalFormula-fromAtom`
   **Description:**
    Create a PropositionalFormula from an Atom. The returned formula is atomic.
    
   **argument:**
   * Atom: an atom
    
   **returns:**
   PropositionalFormula: a propositional formula
    
   **Example code:**
    
```NetLogo
dbi:PropositionalFormula-fromAtom atom
```

### `dbi:PropositionalAtom`
   **Description:**
   Create a PropositionalAtom by it's name.
   
   **argument:**
   * String: the atom's name
   
   **returns:**
   PropositionalAtom: the created propositional atom
   
   **Example code:**
   
```NetLogo
dbi:PropositionalAtom "name"
```

### `dbi:Fact-fromFormula`
   **Description:**
   Create a Fact objet from a Formula. Facts can be used to update agent beliefs.
   
   **argument:**
   * Formula: a formula
   
   **returns:**
   Fact: a fact
   
   **Example code:**
   
```NetLogo
dbi:Fact-fromFormula formula
```

### `dbi:Fact-negate`
   **Description:**
   Returns the given fact's negatation.
   
   **argument:**
   * Fact: the fact to negate
   
   **returns:**
   Fact: the fact's negation
   
   **Example code:**
   
```NetLogo
dbi:Fact-negate fact
```

### `dbi:Fact-getFormula`
   **Description:**
   Returns the Fact's formula. Usefull to extract DBIAgent's goals.
   
   **argument:**
   * Fact: a fact
   
   **returns:**
   Formula: the fact's formula
   
   **Example code:**
   
```NetLogo
dbi:Fact-getFormula fact
```

### `dbi:Factset-membership`
   **Description:**
   Returns the Fact's TruthDegree.
   
   **argument:**
   * Factset: a fact set
   * Fact: the fact to retreive the truth degree
   
   **returns:**
   TruthDegree: the fact's truth degree
   
   **Example code:**
   
```NetLogo
dbi:Factset-membership
```

### `dbi:TruthDegree-doubleValue`
   **Description:**
   Reporter used to retreive a truth degree as a number between 0 and 1.
   
   **argument:**
   * TruthDegree: the truth degree to convert
   
   **returns:**
   Number between 0 and 1
   
   **Example code:**
   
```NetLogo
dbi:TruthDegree-doubleValue
```

### `dbi:Utils-removeDuplicates`
   **Description:**
   Util function used in the NetLogo simulation to remove duplicate patches (same id) in a LogoList of Patches
   
   **argument:**
   * LogoList of Patches
   
   **returns:**
   LogoList of Patches
   
   **Example code:**
   
```NetLogo
dbi:Utils-removeDuplicates logoList
```
