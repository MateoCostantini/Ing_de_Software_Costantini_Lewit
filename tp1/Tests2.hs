import Palet 
import Stack
import Route
import Truck

import Control.Exception
import System.IO.Unsafe
import qualified Data.Type.Bool as False

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


paletBsas    = newP "bsas" 4
paletTigre   = newP "tigre" 8
paletBelgrano= newP "belgrano" 2
paletLujan   = newP "lujan" 1
paletLujan2  = newP "lujan" 3
paletTucuman = newP "tucuman" 3
paletFake    = newP "ciudadInexistente" 5
paletCABA1   = newP "CABA" 4 
paletCABA2   = newP "CABA" 7



ruta1 = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]
rutaVacia = newR []
rutaRepetida = newR ["bsas", "lujan", "tigre", "lujan", "CABA"]


-- stack
stackVacio = newS 10
stackCargado = stackS stackVacio paletTucuman
stackCargado2 = stackS stackCargado paletLujan -- lujan tucuman
stackCargado3 = stackS stackCargado2 paletTigre -- tigre lujan tucuman
stackLleno = stackS (stackS (newS 2) paletCABA1) paletTigre
stackIntentoSobrellenar = stackS stackLleno paletLujan
stackSinCapacidad = newS 0
stackSinCapacidadLleno = stackS stackSinCapacidad paletTigre


-- pop
stackCiudadesRepetidas = stackS  
                        (stackS 
                        (stackS 
                        (stackS (newS 6) paletLujan) 
                                        paletCABA1) 
                                        paletCABA2) 
                                        paletLujan2
stackPopPaletDelMedio = popS stackCiudadesRepetidas "CABA"                                        
stackPopCiudadInexistente = popS stackCiudadesRepetidas "ciudadInexistente"
stackPopPaletCima = popS stackCiudadesRepetidas "lujan"
stackPopMultiplesPalets = popS stackPopPaletCima "CABA"
stackPopUltimoPalet = popS stackPopMultiplesPalets "lujan"
stackPopVacio = popS stackVacio "CABA"


-- emprolijar/usar lo anterior

truckUnaBahia = newT 1 3 ruta1
truckMultiplesBahias = newT 4 3 ruta1
truckCargado1 = loadT truckMultiplesBahias paletTigre

truckCargado2 = loadT (loadT truckUnaBahia paletCABA1) paletLujan

truckRutaRepetida = loadT (loadT (newT 1 10 rutaRepetida) paletLujan) paletTigre
truckPalRutaRepetidaPermitida = loadT truckRutaRepetida paletLujan2 -- me deberia dejar
truckPalNoPermitido = loadT truckRutaRepetida paletCABA1 -- me deberia dejar


-- t3 = loadT t2 p2
-- t4 = loadT t3 p4

-- t5 = unloadT t4 "bsas"
-- hasta aca

-- ruta1 = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]



-- Tests Palet.hs

tests_netP = 
    [ netP paletBsas == 4
    , netP paletTigre == 8
    ]

tests_destinationP = 
    [ destinationP paletBsas == "bsas"
    , destinationP paletLujan == "lujan"
    ]

testsPalet = concat [tests_netP, tests_destinationP]

-- Tests Route.hs

tests_inOrderR = 
    [ inOrderR ruta1 "tigre" "tucuman" == True
    , inOrderR ruta1 "tigre" "lujan" == False
    , inOrderR ruta1 "ciudadInexistente" "tigre" == False
    , inOrderR ruta1 "tigre" "ciudadInexistente" == True
    , inOrderR ruta1 "CABA" "CABA" == True
    , testF (inOrderR rutaVacia "tigre" "bsas")
    -- agregar caso donde ninguna de las dos ciudades esten en la ruta --> error
    ]

tests_inRouteR = 
    [ inRouteR ruta1 "tigre" == True
    , inRouteR ruta1 "ciudadInexistente" == False
    , inRouteR rutaVacia "tucuman" == False
    ]

testsRoute = concat [tests_inOrderR, tests_inRouteR]

-- Tests Stack.hs

tests_freeCellsS = 
    [ freeCellsS stackVacio == 10
    , freeCellsS stackCargado == 9
    , freeCellsS stackLleno == 0
    , freeCellsS stackSinCapacidad == 0
    ]

tests_stackS =
    [ freeCellsS stackCargado == 9
    , testF (freeCellsS stackIntentoSobrellenar) 
    , testF (freeCellsS stackSinCapacidadLleno)
    , freeCellsS stackCiudadesRepetidas == 2
    ]

tests_netS = 
    [ netS stackVacio == 0
    , netS stackCargado == netP paletTucuman
    , netS stackLleno == (netP paletCABA1) + (netP paletTigre)
    , testF (netS stackIntentoSobrellenar)
    ]

tests_holdsS = 
    [ holdsS stackCargado paletBsas ruta1 == True
    , holdsS stackCargado2 paletCABA2 ruta1 == False
    , holdsS stackCargado paletBsas rutaVacia == False
    , holdsS stackCargado3 paletLujan2 rutaRepetida == True 
    , holdsS stackCargado3 paletFake ruta1 == False
    ]

tests_popS =
    [ freeCellsS stackCiudadesRepetidas == 2
    , freeCellsS stackPopPaletDelMedio == freeCellsS stackCiudadesRepetidas
    , freeCellsS stackPopCiudadInexistente == freeCellsS stackCiudadesRepetidas
    , freeCellsS stackPopPaletCima == (freeCellsS stackCiudadesRepetidas)+1
    , freeCellsS stackPopMultiplesPalets == (freeCellsS stackPopPaletCima)+2
    , freeCellsS stackPopUltimoPalet == (freeCellsS stackPopMultiplesPalets)+1
    , freeCellsS stackPopVacio == freeCellsS stackVacio && freeCellsS stackPopVacio == 10
    ]

testsStack = concat [tests_freeCellsS, tests_stackS, tests_netS, tests_holdsS, tests_popS]

-- Tests Truck.hs

tests_freeCellsT =
    [ freeCellsT truckUnaBahia == 3
    , freeCellsT truckMultiplesBahias == 12
    , freeCellsT truckCargado1 == 11
    ]

tests_loadT = 
    [ freeCellsT truckCargado2 == (freeCellsT truckUnaBahia) -2
    --, freeCellsT truckPalRutaRepetidaPermitida == 7
    , testF (freeCellsT truckPalNoPermitido)
    , testF (loadT truckUnaBahia paletFake)
    -- problema en el caso en que tenga la ruta lujan-tigre-lujan: si ya tengo un palet de lujan
    -- entonces cuanod quiera agregar uno de tigre no me va a dejar xq esta lujan antes en la lista
    -- de rutas. Y deber'ia dejarme.
    ]

-- tests_unloadT =
--     [ netT truckDescargado < netT truckCargado4  -- sacó al menos un pallet
--     , netT truckDescargado2 < netT truckDescargado
--     , netT truckDescargado3 == netT truckDescargado2 -- no cambia si ciudad no está
--     ]


-- tests_netT = 
--     [

--     ]

testsTruck = concat [tests_freeCellsT, tests_loadT]--, tests_unloadT, tests_netT]

--------------------------------------------------
-- RUNNING TESTS
--------------------------------------------------

tests = concat [testsPalet, testsRoute, testsStack, testsTruck]









-- tests_viejo = [
--     -- Palet.hs
--     netP palet1 == 4,
--     destinationP palet1 == "bsas",
--     -- Route.hs: inOrderR
--     inOrderR ruta1 "tigre" "tucuman" == True, 
--     inOrderR ruta1 "tigre" "lujan" == False,
--     inOrderR ruta1 "ciudadInexistente" "tigre" == False,
--     inOrderR ruta1 "tigre" "ciudadInexistente" == True,
--     inOrderR ruta1 "CABA" "CABA" == True,
--     inOrderR rutaVacia "tigre" "bsas" == False,
--     -- Route.hs: inRouteR
--     inRouteR ruta1 "tigre" == True,
--     inRouteR ruta1 "ciudadInexistente" == False,
--     inRouteR rutaVacia "tucuman" == False,
--     -- Stack.hs
--     freeCellsS stack1 == 2,
--     freeCellsS stack2 == 1,
--     freeCellsS stack3 == 0,
--     freeCellsS stack4 == 0,
--     freeCellsS stackVacio == 0,
--     stack3 == stack4, -- preguntamos emilio
--     freeCellsS stack3 == freeCellsS stack4,
--     netS stack3 == netS stack4,
--     netS stack2 == 4,
--     netS stack3 == 4+8,
--     holdsS stack3 palet1 ruta1 == True,
--     holdsS stack3 palet7 ruta1 == False,
--     freeCellsS stack6 == 0, -- verificamos que stack6 este lleno por mas que la ruta no lo permita (stackS no tiene en cuenta ruta, holdsS si)
--     netS stack6 == 4+3, -- verificamos lo mismo con el peso
--     holdsS stack6 palet2 rutaVacia == False, -- holdsS usa inRouteR, como la ciudad de palet2 no esta en rutaVacia da False.
--     freeCellsS stack6 == freeCellsS stack7, -- al popear una ciudad que no es la actual, no hace nada
--     freeCellsS stackCiuRepetido3 == 5-2,
--     freeCellsS stackCiuRepetido4 == 5, -- probamos que se poppeen los dos pallets de la misma ciudad
--     -- si queremos agregar un palet mas a un stack lleno ==> queremos que devuelva el mismo stack
--     freeCellsS stackCiuIntermedia4 == 5, -- ahora probamos el caso en que en el stack haya palets de ciudad A, B, A respectivamente
--     freeCellsS stackCiuIntermedia5 == 6, -- probamos que solamente saque del stack el palet de lujan que esta arriba y no los palets de lujan que estan abajo que otras ciudades.
--     testF(loadT t2 p2) -- CAMBIAR Y HACER PROLIJO
    

--     ]