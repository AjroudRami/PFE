;Ajout d'extension java

;Ajout de fichiers "foo.nls" contenant du code NetLogo
__includes["include-output.nls" "include-setup.nls" "include-utils.nls" "include-gisFiles.nls" "include-BDI.nls" ]

;Définition des variables globales du modèle
globals[

]

;Définition des espèces d'agents
breed [owners owner]
breed [exploitations exploitation]
breed [landscapeunits landscapeunit]
breed [towns town]

;Définition des attributs des différents agents

;; Les 3 derniers attributs sont des % de perceptions de ces differents domaines
owners-own [rank ecopower money oldmoney radius maxNumberExploit numberOfExploitation minNumberVilla listExploitation landproductivity landmanagement macrocontext farmbought villabought cerveau-bdi ]
;;LandscapeUnits
landscapeunits-own [ name location agrologicalpower2 ]
;;piece of lands
patches-own [landscapeType haveTools agrologicalPower exploited yearsExploited production accessibility climat cluster pedoValue ]
;; agrological power : % d'influence des conditions climatiques
exploitations-own[landowner typeOfExploitation listofpatches symbolicvalue totalproduction maxsizeofland minsizeofland totalaccesibility notRentable age]
;;Ville et capitale
towns-own [ location taille isCapital listofpatch ]

;;ODD COMMENTAIRE
;;Output 29 & 30 ????
;;t1 : 22->24 ??!!! Aucun modele de cout difficile de faire ça

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;FONCTIONS PRINCIPALES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;Remise à zéro de la simulation
to clear
  ca
  reset-ticks
end

;Fonction qui appelle les setups de l'environnement et des différentes entités
to setup
  setupTown
  setup-profit
  setupLandscapeType
  ;setupcolorlandscape
  setupClimatique

  setupLandOwners
  setupmoney
  setupExploitationPerOwners

  setupSymbolic
  setupAgricultureStructure
  setupYearsOfExploitation
  randomEcoPowerOwners
  init-stats ;include-outpus.nls
end

;Fonction qui fait avancer le monde
to go
  if (ticks >= 300) [
    stats-maison ;include-output.nls
    stop]

  stuffBeforeOwnersCalcul
  calculHigherEqualLowerProfit

  increment-years-exploited ;include-uils
  increment-age-exploitations ;include-uils
  changeClimat ; include-uils

  stats-surface
  tick
end


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Starting T1 functions;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;Phase de calcul nécessaire à chaque étape
to stuffBeforeOwnersCalcul
  let _neareast 0
  ask patches[
    set production agrologicalPower ;chaque piece of land set sa production
    impactAgriculturalStructure ; include-utils (augmente la production si il existe des structures agricoles sur une piece of land
    impactYearsExploitation ; include-utils (Diminue la production si la piece of land est exploité depuis longtemps
    ;; T1 : 15

    ;set accessibilité d'une piece of land la distance qui la sépare de la ville la plus proche
    if exploited [ set _neareast min-one-of towns [distance myself] set accessibility distance _neareast]
  ]

  ;Pour chaque exploitations
  ask exploitations[
    ;;T1 : 14
    set totalproduction (sum [production] of  (patch-set listofpatches)) ;Sa production = somme des production de ses pice of lan
    if typeOfExploitation = "villa" [bonus-villa] ;si l'exploitation est une villa on applique son bonus à son total de production
    ;; T1 : 16 != 17 -> On calcule la distance par rapport à la CAPITALE et non plus aux villes
    set totalaccesibility (mean [accessibility] of (patch-set listofpatches));
  ]

  ;Pour chaque owner
 ask owners [
    set oldmoney money;On set son argent à t-1
    set ecopower (oldmoney - money)
    set money (sum [(2 * totalproduction) - totalaccesibility] of turtle-set listExploitation); set profit à t now.
  ]

  let _freepol patches with [exploited = false]

end


;;Fonctions nécessaire au gros tableaux du ODD

;Appelle les fonctions de maintien d'une exploitation
to maintain

end

;Appelle les fonctions d'améliorations de l'exploitation
to improve
  addStructure ; include-utils
end

;crée une exploitation si on peut encore en créer
to creation
  let tmp [numberOfExploitation] of myself
  let maxx [maxNumberExploit] of myself
  if tmp < maxx [
    chooseRentableExploitationAndExpand
  ]
end

;;Si on abandonne une exploitation Alors on en crée une autre
;;dans le rayon d'action par rapport à son ancienne exploitation
;;puis on tue l'exploitation courante (non rentable)
to abandon
  let _nameOwner [landowner] of self
  let _r [rank] of owner landowner
  let _t typeOfExploitation
  let _cap one-of towns with [isCapital]

  ;Si un aristocrat abandonne sa villa qui est sous l'influence de la capitale il DOIT en recréer une proche de la capitale
  ;sinon on créer simplement une exploitation supplémentaire
  ifelse _r = "aristocrat" and _t ="villa" and distance _cap < 27  [ create-near-capital ][ creation]

  ask owner _nameOwner [
    set numberOfexploitation numberofExploitation - 1
    set listExploitation (remove myself listExploitation)
   ]

;raz des pieces of land que l'exploitation contenait
 ask (patch-set listofpatches) [
   recolor-pedo-paysage;; redonne la couleur de base aux patchs
    set exploited false
    set cluster nobody
  ]


  set abandonned abandonned + 1
  set avgSizeA avgSizeA + length listofpatches
  die ;; tue l'exploitation non rentable
end

;Agrandit un terrain d'une parcelle contigu au terrain de l'exploitation appellante
to enlarge2 [listpatches]
  ;tous les patches collés au terrain de l'exploitation
  let _watchterrain (patch-set [neighbors4] of (patch-set listpatches)) with [exploited = false]
  let _better _watchterrain with [haveTools]
  let _parcelle patch 0 0
  if ( count _watchterrain) > 0 [
    ifelse count _better > 0 [set _parcelle max-n-of 1 _better [production]] ;prefere prendre un terrain avec des structures agricoles
    [set _parcelle max-n-of 1 _watchterrain [production]] ; s'il n'en trouve pas alors en prend un sans

  ;ajoute la piece of land au terrain de l'exploitation
  set listofpatches sentence listpatches (sort _parcelle)
  ask _parcelle [
      set exploited true
      set pcolor [color] of myself
    ]
  ]
end

;;Si le profit de la nouvelle exploitation est Haut alors en crée jusqu'au max
;called by exploitations
to chooseRentableExploitationAndExpand

  ;Necessary to loop
  let _nameOwner [landowner] of self
  let _goodprofit 0 ;
  let _profit 0 ; valeur servant à definir si le profit que nous rapporte une nouvelle exploitation est suffisant pour en creer de nouveau une autre.

  ;;Necessary to enlarge terrain

  let _probablygood patches in-radius ([radius] of myself) with [exploited = false]
  let _better _probablygood with [haveTools]
  let _startcluster patch 0 0 ;initialisation
  if(count _probablygood > 0) [
    ifelse count _better > 0 [set _startcluster max-n-of 1 _better [production]] ;prefere prendre un terrain avec des structures agricoles
    [set _startcluster max-n-of 1 _probablygood [production]] ; s'il n'en trouve pas alors en prend un sans


    ;foreach terrain [set exploited true]
    ask _startcluster [
      set pcolor [color] of owner _nameOwner
      set exploited true
      sprout-exploitations 1 [
        set createdEx createdEx + 1
        set landowner _nameOwner
        set shape "house"
        set listofpatches (list patch-here)
        set symbolicvalue random(4)
        set totalproduction 0
        set notRentable 0
        set color [color] of owner landowner
        set totalaccesibility 0

        ;Si l'owner n'a pas son minimum de villa alors cette nouvelle exploitation en sera une
        ;sinon ce sera une ferme
        ifelse not-enough-villa? [do-villa][do-farm]
        ;On regarde si l'exploitation nouvellement crée est High/Low rentable
        ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
        repeat minsizeofland [ enlarge2 listofpatches ]
        set totalproduction (sum [production] of  (patch-set listofpatches))
        set totalaccesibility (mean [accessibility] of (patch-set listofpatches))
        set _goodprofit (totalproduction - totalaccesibility)
        ask patch-here [set pcolor black]
        ask owner _nameOwner [
          set listExploitation lput myself listExploitation
          set numberOfExploitation numberOfExploitation + 1
        ]; fin ask owner
      ] ;fin sprout
    ] ; fin startcluster
    let _numb [numberOfExploitation] of owner _nameOwner
    let _maxn [maxNumberExploit] of owner _nameOwner ;+1 pour les fermiers leur permettant d'installer une autre exploitation avant d'abandonner la leur
    ;goodprofit est le profit que rapporte cette nouvelle exploitation
    ;_profit est le seuil de rentabilité d'une farm ou d'une villa
    if (_goodprofit > _profit) and (_numb < _maxn)  [chooseRentableExploitationAndExpand]
  ]; fin if probablygood
end


;Appelle les fonctions responsables des choix en fonctions des profits réalisés
;call by go func
to calculHigherEqualLowerProfit
  ask owners [
    ifelse rank = "aristocrat" [ cmd1 ]
    [ifelse rank = "rich" [cmd2]
    [cmd3-bdi]] ;;farmers
  ]
end

;Appelle la fonction correspondant à l'état du profit des aristocrats
;called by owners
to cmd1 ;aristocrats money = profit (ODD)
  ifelse money < oldmoney [checkProfitDecreased ]
  [ ifelse money > oldmoney [checkProfitIncreased ]
    [checkProfitEquivalent ]
  ]
end

;Appelle la fonction correspondant à l'état du profit des Big Landowner
to cmd2 ;rich
ifelse money < oldmoney [checkProfitDecreased ]
  [ ifelse money > oldmoney [checkProfitIncreased ]
    [checkProfitEquivalent ]
  ]
end

;Appelle la fonction correspondant à l'état du profit des fermiers
to cmd3 ; farmers
ifelse money < oldmoney [checkProfitDecreasedFarmer ]
  [ ifelse money > oldmoney [checkProfitIncreased ]
    [checkProfitEquivalentFarmer ]
  ]
end

to cmd3-bdi
  let viable dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "viable"
  let fertile dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "fertile"
  let accessible dbi:PropositionalFormula-fromAtom dbi:PropositionalAtom "accessible"

  let notOp dbi:Operator "not"

  let notViable dbi:PropositionalFormula notOp viable
  let _viable 0
  foreach listExploitation [
    [exploit] -> ask exploit [
      set _viable totalproduction < profit-farm
      dbi:DBIAgent-updateBelief ([cerveau-bdi] of owner landowner) dbi:Fact-fromFormula viable 0.1;(valueViable _viable)
      dbi:DBIAgent-updateBelief ([cerveau-bdi] of myself) dbi:Fact-fromFormula fertile 1;valueFertile totalproduction
      dbi:DBIAgent-updateBelief [cerveau-bdi] of myself dbi:Fact-fromFormula accessible 1;valueAccessible totalaccesibility
      dbi:DBIAgent-updateBelief [cerveau-bdi] of myself dbi:Fact-fromFormula notViable 1;valueAccessible totalaccesibility
      let gol agent-goals [cerveau-bdi] of myself
      ifelse gol = "enlarge" [ifelse length listofpatches < maxsizeofland [enlarge2 listofpatches][maintain]]
          [ifelse gol = "leave" [abandon][maintain]]
    ]
  ]

end


;Choix concernant les exploitations des fermiers
;called by owners
to checkProfitDecreasedFarmer ;Baisse de profit
  let _profit 0
  foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      ifelse (totalproduction - totalaccesibility) > _profit [ maintain ]
      [ ifelse notRentable > 1 [ abandon] [set notRentable (notRentable + 1)]
    ]
  ]
  ]
end

;called by owners
to checkProfitEquivalentFarmer ; Profit Equivalent
  let _profit 0
   foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      ifelse (totalproduction - totalaccesibility) > _profit [ maintain]
      [ ifelse notRentable > 1 [ abandon] [set notRentable (notRentable + 1)]
    ]
  ]
  ]
end

;called by owners
to checkProfitIncreasedFarmer ;Augmentation Profit
  let _profit 0
foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      if (totalproduction - totalaccesibility) > _profit [ifelse length listofpatches < maxsizeofland [enlarge2 listofpatches][maintain]]
    ]
  ]
end

;Choix concernant les exploitations des aristocrates et des Big landowners
;;Profit ici est la valeur limite entre un "High profit" et un "low profit"
;called by owners
to checkProfitDecreased ; Baisse de profit
  let _profit 0
  foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      ifelse (totalproduction - totalaccesibility) > _profit [maintain]
      [ ifelse notRentable > 1 [ abandon] [set notRentable (notRentable + 1)]
    ]
  ]
  ]
end

;called by owners
to checkProfitEquivalent ; Profit Equivalent
  let _profit 0
   foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      ifelse (totalproduction - totalaccesibility) > _profit [ maintain creation]
      [ ifelse symbolicvalue > 0 [ maintain] [abandon]
    ]
  ]
  ]
end

;called by owners
to checkProfitIncreased ;Augmentation du profit
  let _profit 0
   foreach listExploitation [
    [exploit] -> ask exploit [
      ifelse typeOfExploitation = "farm" [set _profit profit-farm] [set _profit profit-villa]
      ifelse (totalproduction - totalaccesibility) < _profit [ maintain improve]
      [ ifelse length listofpatches < maxsizeofland [enlarge2 listofpatches show "YOUHOU"][creation show "NULL"]]
    ]
  ]
end
@#$#@#$#@
GRAPHICS-WINDOW
210
10
923
724
-1
-1
5.0
1
10
1
1
1
0
0
0
1
-70
70
-70
70
0
0
1
ticks
30.0

BUTTON
73
97
136
130
NIL
setup\n
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
33
57
176
90
NIL
load-climatique
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
74
11
137
44
NIL
clear
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

PLOT
1022
17
1222
167
Owners Money in time
NIL
NIL
0.0
10.0
0.0
10.0
true
true
"" ""
PENS
"" 1.0 0 -11085214 true "" "ask owners [\n create-temporary-plot-pen (word who)\n set-plot-pen-color color\n plotxy ticks money\n ]"

BUTTON
70
206
141
239
NIL
go
T
1
T
OBSERVER
NIL
NIL
NIL
NIL
0

MONITOR
993
511
1059
556
Fermes
count exploitations with [typeOfExploitation = 0]
17
1
11

MONITOR
1222
263
1292
308
Villa
count exploitations with [typeOfExploitation = 1]
17
1
11

TEXTBOX
1188
467
1338
485
Model Output
13
0.0
1

MONITOR
993
559
1079
604
Created Farm
createdEx
17
1
11

MONITOR
1086
558
1172
603
Maintained Farm
maintained
17
1
11

MONITOR
1178
558
1265
603
Enlarged Farm
enlarged
17
1
11

MONITOR
1274
558
1362
603
Abandonned farm
abandonned
17
1
11

MONITOR
1268
612
1382
657
Abandonned Farm
avgSizeA / abandonned
2
1
11

MONITOR
1061
614
1150
659
Created Farm
avgSizeC / createdEx
2
1
11

MONITOR
1155
614
1259
659
Maintained Farm
avgSizeM / maintained
2
1
11

MONITOR
994
684
1060
729
Alluvian
surfaceExploitAllu * 100
2
1
11

MONITOR
1071
683
1143
728
Foot
surfaceExploitFoot * 100
2
1
11

MONITOR
1299
683
1362
728
Hills
surfaceExploitHills * 100
2
1
11

MONITOR
1151
683
1217
728
Plat
surfaceExploitPlat * 100
2
1
11

MONITOR
1222
683
1292
728
Sediment
surfaceExploitSediment * 100
2
1
11

TEXTBOX
952
623
1049
641
Average Size ->
13
0.0
1

TEXTBOX
926
662
1076
694
% of surface exploited by landscape unit
13
0.0
1

MONITOR
949
216
1007
261
Farmers
count owners with [rank = \"farmers\"]
2
1
11

MONITOR
1015
215
1102
260
Big landowners
count owners with [rank = \"rich\"]
17
1
11

MONITOR
1110
215
1183
260
Aristocrats
count owners with [rank = \"aristocrat\"]
17
1
11

TEXTBOX
957
172
1171
204
Number of each type of Landowners\n
13
0.0
1

MONITOR
1056
270
1173
315
Aristo Moyenne Farms
mean [farmbought] of owners with [rank = \"aristocrat\"]
2
1
11

MONITOR
1058
319
1171
364
Aristo Min Farms
min [farmbought] of owners with [rank = \"aristocrat\"]
2
1
11

MONITOR
1061
373
1170
418
Aristo Max Farms
max [farmbought] of owners with [rank = \"aristocrat\"]
2
1
11

MONITOR
932
372
1056
417
BigOwner Max Farm
max [farmbought] of owners with [rank = \"rich\"]
2
1
11

MONITOR
935
321
1054
366
BigOwner Min Farm
min [farmbought] of owners with [rank = \"rich\"]
2
1
11

MONITOR
932
268
1052
313
BigOwner Mean Farms
mean [farmbought] of owners with [rank = \"rich\"]
2
1
11

SWITCH
54
260
175
293
show-color?
show-color?
1
1
-1000

BUTTON
61
307
153
340
NIL
show-color\n
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
59
161
142
194
BeforeGo
clear\nload-climatique\nsetup\ntest
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
70
128
135
161
NIL
doBDI
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

@#$#@#$#@
## WHAT IS IT?

(a general understanding of what the model is trying to show or explain)

## ENTITY DETAILS

* **Landowners** _(Turtle breed : owners)_
    * **rank :** Rang social (Farmier/Riche/Aristocrate)
    * **money :** Pouvoir économique de départ
    * **oldmoney :** Revenu à t-1
    * **radius :** Rayon de recherche de nouvelle exploitation en fonction du rang
    * **numberOfExploitation :** Nombre d'exploitation actuelle
    * **listExploitation :** Liste contenant les exploitations
    * **landproductivity :** Total de production de toutes les exploitations
    * **landmanagement :** % d'impact sur l'owner concernant les aménagements établie d'un terrain 
    * **macrocontext :** % d'influence du macro-eco-context sur l'owner
  
* **Landscape Unit** _(Turtle breed : landscapeunits)_
    * **location :** Contient tout les patchs étant de ce type d'unité
    * **agrologicalpower2 :** Contient le pouvoir agricole de cette unité
    * **name :** Contient le nom de l'unité  	

* **Piece of land** _(Patches)_
    * **landscapeType :** Type de terrain
    * **haveTools :** Boolean -> true si il y a des aménagement agricole, false sinon
    * **agrologicalPower :** Pouvoir agricole du terrain dépend du landscapeType
    * **exploited :** Boolean -> true si appartient à une exploitation non abandonnée, false sinon
    * **yearsExploited :** Nombre d'années consécutive exploité
    * **production :** Total de production fourni par ce terrain
    * **accessibility:** Valeur représentant l'accesibilité de ce terrain
    * **climat:** Climat touchant le terrain representé par une valeur (voir tab climatique)

  * **Exploitation** _(Turtle breed : exploitations)_
    * **landowner :** Nom de l'owner de l'exploitation
    * **typeOfExploitation :** Ferme/Villa
    * **listofpatches :** Liste des pieces of lands representant le terrain de l'exploitation
    * **symbolicvalue :** Valeurs symbolique comprise dans l'intervalle [0,4].
    * **totalproduction :** Somme des production de chaque piece of land appartenant à l'exploitation
    * **sizeofland :** Nombre de piece of lands contenus dans l'exploitation
    * **totalaccesibility :** Somme de l'accessibilité de chaque piece of land contenus dans l'exploitation

* **Town**

## HOW IT WORKS

(what rules the agents use to create the overall behavior of the model)

## HOW TO USE IT

(how to use the model, including a description of each of the items in the Interface tab)

## THINGS TO NOTICE

(suggested things for the user to notice while running the model)

## THINGS TO TRY

(suggested things for the user to try to do (move sliders, switches, etc.) with the model)

## EXTENDING THE MODEL

(suggested things to add or change in the Code tab to make the model more complicated, detailed, accurate, etc.)

## NETLOGO FEATURES

(interesting or unusual features of NetLogo that the model uses, particularly in the Code tab; or where workarounds were needed for missing features)

## RELATED MODELS

(models in the NetLogo Models Library and elsewhere which are of related interest)

## CREDITS AND REFERENCES

(a reference to the model's URL on the web if it has one, as well as any other necessary credits, citations, and links)
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
15
Circle -1 true true 203 65 88
Circle -1 true true 70 65 162
Circle -1 true true 150 105 120
Polygon -7500403 true false 218 120 240 165 255 165 278 120
Circle -7500403 true false 214 72 67
Rectangle -1 true true 164 223 179 298
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Circle -1 true true 3 83 150
Rectangle -1 true true 65 221 80 296
Polygon -1 true true 195 285 210 285 210 240 240 210 195 210
Polygon -7500403 true false 276 85 285 105 302 99 294 83
Polygon -7500403 true false 219 85 210 105 193 99 201 83

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

wolf
false
0
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true 2 194 13 197 30 191 38 193 38 205 20 226 20 257 27 265 38 266 40 260 31 253 31 230 60 206 68 198 75 209 66 228 65 243 82 261 84 268 100 267 103 261 77 239 79 231 100 207 98 196 119 201 143 202 160 195 166 210 172 213 173 238 167 251 160 248 154 265 169 264 178 247 186 240 198 260 200 271 217 271 219 262 207 258 195 230 192 198 210 184 227 164 242 144 259 145 284 151 277 141 293 140 299 134 297 127 273 119 270 105
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
NetLogo 6.0.2
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180
@#$#@#$#@
0
@#$#@#$#@
