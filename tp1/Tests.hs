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
paletTigre2   = newP "tigre" 2
paletTigre3   = newP "tigre" 1
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
stackCargado2 = stackS stackCargado paletLujan
stackCargado3 = stackS stackCargado2 paletTigre
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


truckVacio = newT 4 10 ruta1

truckUnaBahia = newT 1 3 ruta1
truckMultiplesBahias = newT 4 3 ruta1
truckCargado1 = loadT truckMultiplesBahias paletTigre

truckCargado2 = loadT (loadT truckUnaBahia paletCABA1) paletTigre2
truckLleno = loadT truckCargado2 paletLujan
truckIntentoSobrecargar = loadT truckLleno paletBsas

truckSobrePeso = loadT truckCargado2 paletTigre

truckRutaRepetida = loadT (loadT (newT 1 10 rutaRepetida) paletLujan) paletTigre2
truckPalRutaRepetidaPermitida = loadT truckRutaRepetida paletLujan2
truckCiudadNoPermitida = loadT truckRutaRepetida paletCABA1

truckPalMismasCiuDistintasBahias = loadT (loadT (loadT (newT 2 2 ruta1) paletTucuman) paletTigre2) paletTigre3


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
    , testF (freeCellsT truckSobrePeso)
    , freeCellsT truckPalRutaRepetidaPermitida == 7
    , testF (freeCellsT truckCiudadNoPermitida)
    , testF (loadT truckUnaBahia paletFake)
    , freeCellsT truckLleno == 0
    , testF (truckIntentoSobrecargar)
    ]

tests_unloadT =
    [ freeCellsT (unloadT truckCargado1 "tigre") == (freeCellsT truckCargado1) +1
    , freeCellsT (unloadT truckCargado1 "CABA") == freeCellsT truckCargado1
    , freeCellsT (unloadT truckPalMismasCiuDistintasBahias "tigre") == (freeCellsT truckPalMismasCiuDistintasBahias) +2
    , freeCellsT (unloadT truckPalMismasCiuDistintasBahias "ciudadInexistente") == freeCellsT truckPalMismasCiuDistintasBahias
    ]


tests_netT = 
    [ netT truckVacio == 0
    , netT truckCargado1 == netP paletTigre
    , netT truckCargado2 == netP paletCABA1 + netP paletTigre2
    ]

testsTruck = concat [tests_freeCellsT, tests_loadT, tests_unloadT, tests_netT]


tests = foldr (&&) True (concat [testsPalet, testsRoute, testsStack, testsTruck])